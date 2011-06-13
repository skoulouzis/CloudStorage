/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.Date;
import junit.framework.TestCase;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.Metadata;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;

/**
 *
 * @author alogo
 */
public class CloudResourceTest extends TestCase {
    protected final SimpleCRCatalogue catalogue;
    protected final ResourceEntry entry;
    
    public CloudResourceTest(String testName) throws MalformedURLException, URISyntaxException, IOException {
        super(testName);
//        u = new TestUtil();
        catalogue = new SimpleCRCatalogue();
        entry = new ResourceEntry("resource1");
        entry.setMetadata(new Metadata());
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    /**
     * Test of authenticate method, of class CloudResource.
     */
//    public void testAuthenticate() {
//        System.out.println("authenticate");
//        String user = "";
//        String pwd = "";
//        CloudResource instance = null;
//        Object expResult = null;
//        Object result = instance.authenticate(user, pwd);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of authorise method, of class CloudResource.
//     */
//    public void testAuthorise() {
//        System.out.println("authorise");
//        Request arg0 = null;
//        Method arg1 = null;
//        Auth arg2 = null;
//        CloudResource instance = null;
//        boolean expResult = false;
//        boolean result = instance.authorise(arg0, arg1, arg2);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of checkRedirect method, of class CloudResource.
//     */
//    public void testCheckRedirect() {
//        System.out.println("checkRedirect");
//        Request arg0 = null;
//        CloudResource instance = null;
//        String expResult = "";
//        String result = instance.checkRedirect(arg0);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getModifiedDate method, of class CloudResource.
     */
    public void testGetModifiedDate() throws IOException {
        System.out.println("getModifiedDate");
        
        long now = System.currentTimeMillis();
        Metadata meta = new Metadata();
        meta.setModifiedDate(now);
        entry.setMetadata(meta);
        
        CloudResource instance = new CloudDirResource(catalogue, entry);
        Date expResult = new Date(now);
        Date result = instance.getModifiedDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of getName method, of class CloudResource.
     */
    public void testGetName() {
        System.out.println("getName");
        CloudResource instance = new CloudDirResource(catalogue, entry);
        String expResult = entry.getLRN();
        String result = instance.getName();
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getRealm method, of class CloudResource.
//     */
//    public void testGetRealm() {
//        System.out.println("getRealm");
//        CloudResource instance = null;
//        String expResult = "";
//        String result = instance.getRealm();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getUniqueId method, of class CloudResource.
     */
    public void testGetUniqueId() {
        System.out.println("getUniqueId");
        CloudResource instance = new CloudDirResource(catalogue, entry);
        String expResult = entry.getUID();
        String result = instance.getUniqueId();
        assertEquals(expResult, result);
    }

    /**
     * Test of getCreateDate method, of class CloudResource.
     */
    public void testGetCreateDate() {
        System.out.println("getCreateDate");
        long now = System.currentTimeMillis();
        Metadata meta = new Metadata();
        meta.setCreateDate(now);
        entry.setMetadata(meta);
        CloudResource instance = new CloudDirResource(catalogue, entry);
        
        
        Date expResult = new Date(now);
        Date result = instance.getCreateDate();
        assertEquals(expResult, result);
    }

    /**
     * Test of setNodeEntry method, of class CloudResource.
     */
    public void testSetNodeEntry() throws IOException {
        System.out.println("setNodeEntry");
        IResourceEntry nodeEntry = new ResourceEntry("resource2");
        CloudResource instance = new CloudDirResource(catalogue, entry);
        instance.setNodeEntry(nodeEntry);
        IResourceEntry loaded = instance.getNodeEntry();
        assertEquals(loaded, nodeEntry);
    }

    /**
     * Test of getNodeEntry method, of class CloudResource.
     */
    public void testGetNodeEntry() {
        System.out.println("getNodeEntry");
        CloudResource instance = new CloudDirResource(catalogue, entry);
        IResourceEntry result = instance.getNodeEntry();
        assertEquals(entry, result);
        
    }

    /**
     * Test of setCatalogue method, of class CloudResource.
     */
    public void testSetCatalogue() throws MalformedURLException, URISyntaxException {
        System.out.println("setCatalogue");
        ICRCatalogue catalogue1 = new SimpleCRCatalogue();
        CloudResource instance = new CloudDirResource(catalogue, entry);
        instance.setCatalogue(catalogue1);
        assertEquals(catalogue1, instance.getCatalogue());
    }
}
