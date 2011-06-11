/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import junit.framework.TestCase;

/**
 *
 * @author alogo
 */
public class ResourceEntryTest extends TestCase {

    private String name = "resource";
    private String path = "/dummy/path/to/" + name;
    private String[] testPaths = {"/" + path,
        path + "/",
        "/" + path + "/"};
    private ResourceEntry[] resources;

    public ResourceEntryTest(String testName) throws IOException {
        super(testName);
        resources = new ResourceEntry[testPaths.length];
        for (int i = 0; i < testPaths.length; i++) {
            resources[i] = new ResourceEntry(testPaths[i]);
        }
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testResourceEntry() throws IOException {
        for (int i = 0; i < testPaths.length; i++) {
            assertNotNull(resources[i]);
        }
    }

    /**
     * Test of getLRN method, of class ResourceEntry.
     */
    public void testGetLRN() throws IOException {
        for (int i = 0; i < testPaths.length; i++) {
            String lrn = resources[i].getLRN();
            // debug("LRN: " + lrn + ". Name:" + path);
            assertEquals("Failed at path :" + testPaths[i] + ". index: " + i, path, lrn);
        }

        String lrn = "aDir";
        ResourceEntry newDir = new ResourceEntry(lrn);
        assertEquals(lrn, newDir.getLRN());
    }

    /**
     * Test of getMetadata method, of class ResourceEntry.
     */
    public void testMetadata() throws IOException {
        for (int i = 0; i < testPaths.length; i++) {
            Metadata meta1 = new Metadata();
            resources[i].setMetadata(meta1);

            Metadata meta2 = resources[i].getMetadata();
            assertEquals(meta2, meta1);
        }

//		ResourceEntry newDir = new ResourceEntry("aDir");
//		
//		Metadata meta = newDir.getMetadata();
//		assertNotNull(meta);
//
//		Long create = meta.getCreateDate();
//		assertNotNull(create);
    }

    /**
     * Test of getUID method, of class ResourceEntry.
     */
    public void testGetUID() {
        for (int i = 0; i < testPaths.length; i++) {
            assertNotNull(resources[i].getUID());
        }
    }
}
