/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.test;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.Config;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFileEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;

/**
 *
 * @author S. Koulouzis
 */
public class TestUtil {

    private List<ResourceEntry> topEntries = new ArrayList<ResourceEntry>();
    private List<ResourceEntry> allEntries = new ArrayList<ResourceEntry>();
    private SimpleCRCatalogue instance;
    private String testDirUID;

    public TestUtil() throws Exception {
        instance = new SimpleCRCatalogue();
    }
    
    public void initTestDB() throws Exception{
        initTestEnties();
        instance.registerResourceEntrys(topEntries);
    }

    private void initTestEnties() throws Exception {
        ResourceFolderEntry dir01 = new ResourceFolderEntry("/dir01");
        ResourceFileEntry file11 = new ResourceFileEntry("/dir01/file11");
        dir01.addChild(file11);
        ResourceFolderEntry dir11 = new ResourceFolderEntry("/dir01/dir11");
        ResourceFileEntry file21 = new ResourceFileEntry("/dir01/dir11/file21");
        dir11.addChild(file21);
        dir01.addChild(dir11);

        allEntries.add(file21);
        allEntries.add(dir11);
        allEntries.add(file11);
        allEntries.add(dir01);
        topEntries.add(dir01);

        ResourceFolderEntry dir02 = new ResourceFolderEntry("/dir02");
        ResourceFolderEntry dir0211 = new ResourceFolderEntry("/dir02/dir11");
        dir02.addChild(dir0211);
        ResourceFileEntry file0211 = new ResourceFileEntry("/dir02/file11");
        dir02.addChild(file0211);
        allEntries.add(file0211);
        allEntries.add(dir0211);
        allEntries.add(dir02);
        topEntries.add(dir02);

        ResourceFolderEntry dir03 = new ResourceFolderEntry("/dir03");
        ResourceFolderEntry dir0311 = new ResourceFolderEntry(dir03.getLRN() + "/dir11/");
        ResourceFolderEntry dir0321 = new ResourceFolderEntry(
                dir0311.getLRN() + "/dir21/");
        ResourceFolderEntry dir0331 = new ResourceFolderEntry(
                dir0321.getLRN() + "/dir31");
        ResourceFileEntry file41 = new ResourceFileEntry(
                dir0331.getLRN() + "/file41");
        dir0331.addChild(file41);
        dir0321.addChild(dir0331);
        dir0311.addChild(dir0321);
        dir03.addChild(dir0311);

        allEntries.add(file41);
        allEntries.add(dir0331);
        allEntries.add(dir0321);
        allEntries.add(dir0311);
        allEntries.add(dir03);
        topEntries.add(dir03);

        testDirUID = dir0211.getUID();
    }

    public void setTopEntries(List<ResourceEntry> topEntries) {
        this.topEntries = topEntries;
    }

    public List<ResourceEntry> getTopEntries() {
        return topEntries;
    }

    public void setAllEntries(List<ResourceEntry> allEntries) {
        this.allEntries = allEntries;
    }

    public List<ResourceEntry> getAllEntries() {
        return allEntries;
    }

    public boolean hasDuplicates(List<ResourceEntry> list) {
        Set<ResourceEntry> set = new HashSet<ResourceEntry>(list);
//        assertEquals(list.size(), set.size());
        if (list.size() == set.size()) {
            return false;
        }
        return true;
    }

    public boolean compareResourceEntryList(List<ResourceEntry> list1,
            List<ResourceEntry> list2) {

        if (list1.size() != list2.size()) {
            return false;
        }

        int matches = 0;
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (SimpleCRCatalogue.compareEntries(list1.get(i), list2.get(j))) {
                    matches++;
                }
            }
        }

        if (matches == list1.size() && matches == list2.size()) {
            return true;
        }
        return false;

    }

    public String getTestDirUID() {
        return testDirUID;
    }

    public void deleteDB() throws URISyntaxException {
        File db = new File(new URI(Config.DB_LOC_URI));
        File[] entries = db.listFiles();
        for (File f : entries) {
            f.delete();
        }

    }
}
