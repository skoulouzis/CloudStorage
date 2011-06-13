/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.Resource;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import junit.framework.TestCase;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.Metadata;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.test.TestUtil;

/**
 *
 * @author alogo
 */
public class CloudDirResourceTest extends CloudResourceTest {

    private final TestUtil u;
    private CloudDirResource testDir = null;
    private CloudResource testChildOfDir;
    private List<Resource> childResources;

    public CloudDirResourceTest(String testName) throws MalformedURLException, URISyntaxException, IOException, Exception {
        super(testName);
        u = new TestUtil();
        childResources = new ArrayList<Resource>();
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        u.initTestDB();
        u.getDir01().setMetadata(new Metadata());
        testDir = new CloudDirResource(catalogue,u.getDir01());
        u.getDir01Child().setMetadata(new Metadata());
        testChildOfDir = new CloudResource(catalogue, u.getDir01Child());
        for (ResourceEntry r : u.getChildResourcesOfDir1()) {
            r.setMetadata(new Metadata());
            childResources.add(new CloudResource(catalogue, r));
        }
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        u.deleteDB();
        childResources.clear();
    }

//    /**
//     * Test of createCollection method, of class CloudDirResource.
//     */
//    public void testCreateCollection() throws Exception {
//        System.out.println("createCollection");
//        String newName = "newCollection";
//        CloudDirResource instance = new CloudDirResource(catalogue, entry);
//        CollectionResource expResult = new CloudDirResource(catalogue, new ResourceEntry(newName));
//        CollectionResource result = instance.createCollection(newName);
//        assertEquals(expResult, result);
//    }
    /**
     * Test of child method, of class CloudDirResource.
     */
    public void testChild() {
        System.out.println("child");
        CloudDirResource instance = testDir;

        Resource expResult = this.testChildOfDir;
        Resource result = instance.child(TestUtil.DIR1_CHILD_NAME1);

        boolean same = TestUtil.compareCloudResources(expResult, result);
        assertTrue(same);
    }

    /**
     * Test of getChildren method, of class CloudDirResource.
     */
    public void testGetChildren() {
        System.out.println("getChildren");
        CloudDirResource instance = testDir;
        List<Resource> expResult = childResources;
        List<Resource> result = (List<Resource>) instance.getChildren();
        
        boolean same = TestUtil.compareCloudListOfResources(expResult, result);
        assertTrue(same);
    }

//    /**
//     * Test of createNew method, of class CloudDirResource.
//     */
//    public void testCreateNew() throws Exception {
//        System.out.println("createNew");
//        String arg0 = "";
//        InputStream arg1 = null;
//        Long arg2 = null;
//        String arg3 = "";
//        CloudDirResource instance = null;
//        Resource expResult = null;
//        Resource result = instance.createNew(arg0, arg1, arg2, arg3);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of copyTo method, of class CloudDirResource.
//     */
//    public void testCopyTo() {
//        System.out.println("copyTo");
//        CollectionResource arg0 = null;
//        String arg1 = "";
//        CloudDirResource instance = null;
//        instance.copyTo(arg0, arg1);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of delete method, of class CloudDirResource.
//     */
//    public void testDelete() {
//        System.out.println("delete");
//        CloudDirResource instance = null;
//        instance.delete();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of getContentLength method, of class CloudDirResource.
//     */
//    public void testGetContentLength() {
//        System.out.println("getContentLength");
//        CloudDirResource instance = null;
//        Long expResult = null;
//        Long result = instance.getContentLength();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

    /**
     * Test of getContentType method, of class CloudDirResource.
     */
    public void testGetContentType() {
        System.out.println("getContentType");
        String accepts = "folder,dir";
        
        CloudDirResource instance = new CloudDirResource(catalogue, entry);
        String expResult = "folder";
        String result = instance.getContentType(accepts);
        assertEquals(expResult, result);
    }

//    /**
//     * Test of getMaxAgeSeconds method, of class CloudDirResource.
//     */
//    public void testGetMaxAgeSeconds() {
//        System.out.println("getMaxAgeSeconds");
//        Auth auth = null;
//        CloudDirResource instance = null;
//        Long expResult = null;
//        Long result = instance.getMaxAgeSeconds(auth);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of sendContent method, of class CloudDirResource.
//     */
//    public void testSendContent() throws Exception {
//        System.out.println("sendContent");
//        OutputStream out = null;
//        Range range = null;
//        Map<String, String> params = null;
//        String contentType = "";
//        CloudDirResource instance = null;
//        instance.sendContent(out, range, params, contentType);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }

//    /**
//     * Test of moveTo method, of class CloudDirResource.
//     */
//    public void testMoveTo() throws Exception {
//        System.out.println("moveTo");
//        CollectionResource arg0 = null;
//        String arg1 = "";
//        CloudDirResource instance = null;
//        instance.moveTo(arg0, arg1);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
}
