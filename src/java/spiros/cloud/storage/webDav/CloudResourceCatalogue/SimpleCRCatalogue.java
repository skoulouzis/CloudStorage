/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

/**
 *
 * @author alogo
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;

public class SimpleCRCatalogue implements ICRCatalogue {

    private File db;
    private static boolean debug = true;

    //
    public SimpleCRCatalogue() throws MalformedURLException, URISyntaxException {
        this.db = new File(new URI(Config.DB_LOC_URI));
    }

    public void deleteAllEntries() {
        File[] data = db.listFiles();
        for (int i = 0; i < data.length; i++) {
            data[i].delete();
        }
    }

    public String getDBLocation() {
        return this.db.getAbsolutePath();
    }

    private void debug(String msg) {
        if (debug) {
            System.err.println(this.getClass().getSimpleName() + ": " + msg);
        }
    }

    @Override
    public void registerResourceEntry(IResourceEntry entry)
            throws URISyntaxException, IOException {
        ResourceFolderEntry parentEntry = null;
        String parentLRN;
        try {
            if (!entry.isRoot() && entry.isTopLevel()) {
                saveEntry(entry);
            } else if (!entry.isRoot() && !entry.isTopLevel()) {
                parentLRN = getParentLRN(entry);
                parentEntry = (ResourceFolderEntry) getResourceEntryByLRN(parentLRN);
                if (resourceEntryExists(entry)) {
                    parentEntry.addChild((ResourceEntry) entry);
                } else {
                    parentEntry = new ResourceFolderEntry(parentLRN);
                    parentEntry.addChild((ResourceEntry) entry);
                }
                registerResourceEntry(parentEntry);
            }

        } catch (Exception ex) {
            Logger.getLogger(SimpleCRCatalogue.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void saveEntry(IResourceEntry entry) throws URISyntaxException,
            IOException {
        FileOutputStream fos = null;
        ObjectOutputStream out = null;

        String fileName = logicalResourceName2FileName(entry.getLRN());

        fos = new FileOutputStream(new File(new URI(Config.DB_LOC_URI
                + File.separator + fileName)));
        out = new ObjectOutputStream(fos);
        out.writeObject(entry);
        out.close();
    }

    private String logicalResourceName2FileName(String logicalPath) {
        return logicalPath.replaceAll("/", "");
    }

    @Override
    public IResourceEntry getResourceEntryByLRN(String logicalResourceName)
            throws IOException, ClassNotFoundException, Exception {

        if (logicalResourceName.equals("") || logicalResourceName.equals("/")) {
            return getRoot();
        }

        File[] data = db.listFiles();
        for (int i = 0; i < data.length; i++) {
            ResourceEntry e = loadNodeEntry(data[i]);
            if (e != null && e.getLRN().equals(logicalResourceName)) {
                return e;
            }
        }

        List<ResourceEntry> all = loadAllEntries();
        for (ResourceEntry e : all) {
            debug("Looking " + e.getLRN());
            if (e.getLRN().equals(logicalResourceName)) {
                return e;
            }
        }
        return null;
    }

    @Override
    public void unregisterResourceEntry(IResourceEntry entry) {
        String fName = logicalResourceName2FileName(entry.getLRN());

        File[] data = db.listFiles();

        for (int i = 0; i < data.length; i++) {
            if (data[i].getName().equals(fName)) {
                data[i].delete();
            }
        }
    }

    @Override
    public Boolean resourceEntryExists(IResourceEntry entry)
            throws IOException, ClassNotFoundException {
        File[] data = db.listFiles();
        for (int i = 0; i < data.length; i++) {
            IResourceEntry loadedEntry = loadNodeEntry(data[i]);
            if (compareEntries(entry, loadedEntry)) {
                return true;
            }
        }

        List<ResourceEntry> allEntries = loadAllEntries();
        for (ResourceEntry e : allEntries) {
            if (compareEntries(e, entry)) {
                return true;
            }
        }

        return false;
    }

    public List<ResourceEntry> loadAllEntries() throws IOException,
            ClassNotFoundException {
        List<ResourceEntry> entries = null;
        List<ResourceEntry> allEntries = new ArrayList<ResourceEntry>();
        File[] data = db.listFiles();
        for (int i = 0; i < data.length; i++) {
            ResourceEntry loadedEntry = loadNodeEntry(data[i]);
            allEntries.addAll(addEntries(loadedEntry, entries));
        }

        // for(ResourceEntry e : allEntries){
        // debug("All ent: "+e.getLRN()+" "+e.getUID());
        // }

        return allEntries;
    }

    private List<ResourceEntry> addEntries(ResourceEntry loadedEntry,
            List<ResourceEntry> entries) {
        if (entries == null) {
            entries = new ArrayList<ResourceEntry>();
        }
        if (!entries.contains(loadedEntry) && loadedEntry != null) {
            entries.add(loadedEntry);
        }

        if (loadedEntry instanceof ResourceFolderEntry) {
            if (((ResourceFolderEntry) loadedEntry).hasChildren()) {
                List<ResourceEntry> ch = ((ResourceFolderEntry) loadedEntry).getChildren();
                for (ResourceEntry e : ch) {
                    addEntries(e, entries);
                }
            }
        }
        return entries;
    }

    public static Boolean compareEntries(IResourceEntry entry,
            IResourceEntry loadedEntry) {
        if (entry.getLRN().equals(loadedEntry.getLRN())
                && entry.getUID().equals(loadedEntry.getUID())) {
            return true;
        }
        return false;
    }

    private ResourceEntry loadNodeEntry(String fName) throws IOException,
            ClassNotFoundException {
        File f = new File(fName);
        if (!f.getName().startsWith(".")) {
            FileInputStream fis = new FileInputStream(f);
            ObjectInputStream in = new ObjectInputStream(fis);
            return (ResourceEntry) in.readObject();
        }
        return null;
    }

    private ResourceEntry loadNodeEntry(File file) throws IOException,
            ClassNotFoundException {
        return loadNodeEntry(file.getAbsolutePath());
    }

    public void registerResourceEntrys(List<ResourceEntry> topEntries)
            throws URISyntaxException, IOException {
        for (ResourceEntry e : topEntries) {
            this.registerResourceEntry(e);
        }

    }

    @Override
    public ResourceFolderEntry getRoot() throws Exception {
        ResourceFolderEntry root = new ResourceFolderEntry("/");
        if (!resourceEntryExists(root)) {
            ArrayList<ResourceEntry> topLevelEntries = getTopLevelResourceEntries();
            for (IResourceEntry e : topLevelEntries) {
                if (compareEntries(e, root)) {
                    topLevelEntries.remove(e);
                }
            }
            root.addChildren(topLevelEntries);
        }
        return root;
    }

    @Override
    public ArrayList<ResourceEntry> getTopLevelResourceEntries()
            throws Exception {

        File[] data = db.listFiles();
        ResourceEntry entry;
        ArrayList<ResourceEntry> topLevelEntries = new ArrayList<ResourceEntry>();
        for (int i = 0; i < data.length; i++) {
            entry = loadNodeEntry(data[i]);
            if (entry != null) {
                String[] parts = entry.getLRN().split("/");
                if (parts.length == 2) {
                    topLevelEntries.add(entry);
                }
            }

        }
        return topLevelEntries;
    }

    @Override
    public IResourceEntry getResourceEntryByUID(String UID) throws Exception {
        File[] data = db.listFiles();

        for (int i = 0; i < data.length; i++) {
            ResourceEntry e = loadNodeEntry(data[i]);
            if (e.getUID().equals(UID)) {
                return e;
            }
        }

        List<ResourceEntry> all = loadAllEntries();
        for (ResourceEntry e : all) {
            if (e.getUID().equals(UID)) {
                return e;
            }
        }
        return null;
    }

    public Boolean hasEnties() throws IOException, ClassNotFoundException {
        File[] enties = this.db.listFiles();
        if (enties != null && enties.length >= 1 && enties[0].exists()) {
            ResourceEntry entry = loadNodeEntry(enties[0]);
            if (entry.getUID() != null && !entry.getUID().equals("")) {
                return true;
            }
        }
        return false;
    }

    private IResourceEntry getParent(IResourceEntry entry) throws IOException, ClassNotFoundException, Exception {
        String parentLRN = getParentLRN(entry);
        IResourceEntry parentEntry = null;
        if (parentLRN != null) {
            parentEntry = getResourceEntryByLRN(parentLRN);
        }
        return parentEntry;
    }

    private String getParentLRN(IResourceEntry entry) {
        String[] parts = entry.getLRN().split("/");
//        debug("LRN: " + entry.getLRN());
        String parentLRN = null;
        if (parts != null && parts.length >= 1) {
            String childName = parts[parts.length - 1];
//            debug("childName: " + childName);
            parentLRN = entry.getLRN().substring(0, (entry.getLRN().length() - childName.length()));
//            debug("parentLRN: " + parentLRN);
        }
        return parentLRN;
    }
}