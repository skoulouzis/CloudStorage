/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import java.util.Date;
import junit.framework.TestCase;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;

/**
 *
 * @author alogo
 */
public class CloudResourceTest extends TestCase {
    
    public CloudResourceTest(String testName) {
        super(testName);
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
    public void testAuthenticate() {
        System.out.println("authenticate");
        String user = "";
        String pwd = "";
        CloudResource instance = null;
        Object expResult = null;
        Object result = instance.authenticate(user, pwd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of authorise method, of class CloudResource.
     */
    public void testAuthorise() {
        System.out.println("authorise");
        Request arg0 = null;
        Method arg1 = null;
        Auth arg2 = null;
        CloudResource instance = null;
        boolean expResult = false;
        boolean result = instance.authorise(arg0, arg1, arg2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkRedirect method, of class CloudResource.
     */
    public void testCheckRedirect() {
        System.out.println("checkRedirect");
        Request arg0 = null;
        CloudResource instance = null;
        String expResult = "";
        String result = instance.checkRedirect(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModifiedDate method, of class CloudResource.
     */
    public void testGetModifiedDate() {
        System.out.println("getModifiedDate");
        CloudResource instance = null;
        Date expResult = null;
        Date result = instance.getModifiedDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class CloudResource.
     */
    public void testGetName() {
        System.out.println("getName");
        CloudResource instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRealm method, of class CloudResource.
     */
    public void testGetRealm() {
        System.out.println("getRealm");
        CloudResource instance = null;
        String expResult = "";
        String result = instance.getRealm();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUniqueId method, of class CloudResource.
     */
    public void testGetUniqueId() {
        System.out.println("getUniqueId");
        CloudResource instance = null;
        String expResult = "";
        String result = instance.getUniqueId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCreateDate method, of class CloudResource.
     */
    public void testGetCreateDate() {
        System.out.println("getCreateDate");
        CloudResource instance = null;
        Date expResult = null;
        Date result = instance.getCreateDate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNodeEntry method, of class CloudResource.
     */
    public void testSetNodeEntry() {
        System.out.println("setNodeEntry");
        IResourceEntry nodeEntry = null;
        CloudResource instance = null;
        instance.setNodeEntry(nodeEntry);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNodeEntry method, of class CloudResource.
     */
    public void testGetNodeEntry() {
        System.out.println("getNodeEntry");
        CloudResource instance = null;
        IResourceEntry expResult = null;
        IResourceEntry result = instance.getNodeEntry();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCatalogue method, of class CloudResource.
     */
    public void testSetCatalogue() {
        System.out.println("setCatalogue");
        ICRCatalogue catalogue = null;
        CloudResource instance = null;
        instance.setCatalogue(catalogue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCatalogue method, of class CloudResource.
     */
    public void testGetCatalogue() {
        System.out.println("getCatalogue");
        CloudResource instance = null;
        ICRCatalogue expResult = null;
        ICRCatalogue result = instance.getCatalogue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
