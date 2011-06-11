/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import com.bradmcevoy.http.Resource;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import nl.uva.vlet.exception.VlException;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.test.TestUtil;

/**
 *
 * @author alogo
 */
public class CloudResourceFactoryTest extends TestCase {

    private final SimpleCRCatalogue catalogue;
    private final TestUtil u;

    public CloudResourceFactoryTest(String testName) throws MalformedURLException, URISyntaxException, Exception {
        super(testName);
        catalogue = new SimpleCRCatalogue();
        u = new TestUtil();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        u.initTestDB();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        u.deleteDB();
    }

    /**
     * Test of getResource method, of class CloudResourceFactory.
     */
    public void testGetResource() throws URISyntaxException, VlException, IOException, ClassNotFoundException {
        System.out.println("getResource");
        String host = "localhost";
        String lrn = u.getAllEntries().get(0).getLRN();
        String path = "/CloudStrage"+lrn;
        
        CloudResourceFactory instance = new CloudResourceFactory();
        IResourceEntry entry = catalogue.getResourceEntryByLRN(lrn);
        assertNotNull(entry);
        
        Resource expResult = new CloudResource(catalogue, entry);
        
        Resource result = instance.getResource(host, path);
        
        compareResources(expResult,result);
    }

    /**
     * Test of listRootResources method, of class CloudResourceFactory.
     */
    public void testListRootResources() throws Exception {
        System.out.println("listRootResources");
        CloudResourceFactory instance = new CloudResourceFactory();
        List<ResourceEntry> entryResources = u.getTopEntries();
        List<Resource> expResult = new ArrayList<Resource>();
        for(ResourceEntry r:entryResources){
            expResult.add(new CloudResource(catalogue, r));
        }
        
        List<Resource> result = (List<Resource>) instance.listRootResources();
       compareResources(expResult, result);
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }

    private void compareResources(Resource expResult, Resource result) {
        assertEquals(expResult.getUniqueId(), result.getUniqueId());
        assertEquals(expResult.getName(), result.getName());
    }

    private void compareResources(List<Resource> expResult, List<Resource> result) {
        assertEquals(expResult.size(), result.size());
        int hit = 0;
        for(int i=0;i<expResult.size();i++){
            for(int j=0;j<result.size();j++){
                if(expResult.get(i).getUniqueId().equals(result.get(j).getUniqueId())){
                    hit++;
                }
            }
        }
        assertEquals(expResult.size(), hit);
        assertEquals(result.size(), hit);
    }
}
