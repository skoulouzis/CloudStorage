package spiros.cloud.storage.webDav.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.bradmcevoy.common.Path;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.ResourceFactory;
import java.io.IOException;
import java.net.URISyntaxException;
import nl.uva.vlet.exception.VlException;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.Metadata;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFileEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;

public class CloudResourceFactory implements ResourceFactory {

    private Logger log = LoggerFactory.getLogger(CloudResourceFactory.class);
    public static final String REALM = "MyCompany";
    private ICRCatalogue catalogue;

    public CloudResourceFactory() throws URISyntaxException, VlException, IOException {
        catalogue = new SimpleCRCatalogue();
//		Util.initTestCatalouge();
    }

    @Override
    public Resource getResource(String host, String p) {
        try {
            Path path1 = Path.path(p).getStripFirst();
            debug("getResource: to Path" + path1);
            
    //        if (path1.isRoot() || path1.toString().equals("")) {
    //            
    //            return new TopLevelCloudStorageResource(this);
    //        } else {
    //            try {
    //                
    //            } catch (Exception ex) {
    //                ex.printStackTrace();
    ////                java.util.logging.Logger.getLogger(CloudResourceFactory.class.getName()).log(Level.SEVERE, null, ex);
    //            }
    //        }
            
            return getResource(path1);
        } catch (IOException ex) {
            java.util.logging.Logger.getLogger(CloudResourceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CloudResourceFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            java.util.logging.Logger.getLogger(CloudResourceFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
        log.debug(msg);
    }

    private Resource getResource(Path path) throws IOException,
            ClassNotFoundException,
            Exception {

        ResourceEntry entry = (ResourceEntry) catalogue.getResourceEntryByLRN(path.toString());
        if(entry == null){
            throw new NullPointerException("Path "+path+" doesn't exist");
        }
        
        if (entry instanceof ResourceFolderEntry) {
//            List<ResourceEntry> children = ((ResourceFolderEntry) entry).getChildren();
            return new CloudDirResource(catalogue, entry);
        }
        if (entry instanceof ResourceFileEntry) {
            return new CloudFileResource(catalogue, entry);
        }
        debug("Unknown Type: "+entry.getLRN());
        return new CloudResource(catalogue, entry);
    }

    public List<? extends Resource> listRootResources() throws IOException,
            ClassNotFoundException,
            Exception {
        List<ResourceEntry> topLevel = catalogue.getTopLevelResourceEntries();
        ArrayList<Resource> topResources = new ArrayList<Resource>();
        for (int i = 0; i < topLevel.size(); i++) {
//            debug("top level: "+topLevel.get(i).getLRN());
            if (topLevel.get(i) instanceof ResourceFolderEntry) {
                topResources.add(new CloudDirResource(catalogue, topLevel.get(i)));
            } else if (topLevel.get(i) instanceof ResourceFileEntry) {
                topResources.add(new CloudFileResource(catalogue, topLevel.get(i)));
            } else {
                topResources.add(new CloudResource(catalogue, topLevel.get(i)));
            }
        }
        return topResources;
    }
}
