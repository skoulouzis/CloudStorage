/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import com.bradmcevoy.http.ResourceFactory;
import com.bradmcevoy.http.webdav.WebDavResponseHandler;
import junit.framework.TestCase;

/**
 *
 * @author alogo
 */
public class CloudResourceFactoryFactoryTest extends TestCase {

    public CloudResourceFactoryFactoryTest(String testName) {
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
     * Test of init method, of class CloudResourceFactoryFactory.
     */
    public void testInit() {
        System.out.println("init");
        CloudResourceFactoryFactory instance = new CloudResourceFactoryFactory();
        instance.init();

        WebDavResponseHandler result = instance.createResponseHandler();
        assertNotNull(result);


        ResourceFactory result2 = instance.createResourceFactory();
        assertNotNull(result2);

    }
}
