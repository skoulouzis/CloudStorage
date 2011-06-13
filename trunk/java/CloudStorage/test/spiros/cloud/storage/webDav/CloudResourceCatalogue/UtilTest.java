/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import junit.framework.TestCase;
import nl.uva.vlet.vrs.VRSClient;

/**
 *
 * @author alogo
 */
public class UtilTest extends TestCase {

    private static int fileCount;

    public UtilTest(String testName) {
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

    public void testTestLocations() throws URISyntaxException {

        File f = new File(new URI(Config.TEST_DATA_URI));
        assertTrue(f.exists());

        for (String s : Config.CLOUD_LOCATIONS_URI) {
            f = new File(new URI(s));
            if (!f.exists()) {
                fail("Could not find files in " + f.getAbsolutePath());
            }
            assertTrue(f.exists());
        }

        f = new File(new URI(Config.DB_LOC_URI));
        assertTrue(f.exists());

    }

    public void testGetLogicalName() throws URISyntaxException {
        File f = new File(new URI(Config.CLOUD_LOC1_URI));
        String[] fNames = new String[]{"newFile1", ".dotName"};
        String dirName = "/L1Dir";

        for (int i = 0; i < fNames.length; i++) {
            File aFile = new File(f.getAbsolutePath() + File.separator
                    + dirName + File.separator + fNames[i]);
            // message("Base URI: "+Config.CLOUD_LOC1_URI);
            // message("Absolute URI: "+aFile.getAbsolutePath());
            String lrn = Util.getLogicalName(Config.CLOUD_LOC1_URI,
                    aFile.getAbsolutePath());
            // message("LRN: "+lrn);
            String expectedName = dirName + File.separator + fNames[i];
            assertEquals(expectedName, lrn);
        }

        for (int i = 0; i < fNames.length; i++) {
            File aFile = new File(new URI("file://" + f.getAbsolutePath()
                    + File.separator + dirName + File.separator + fNames[i]));
            // message("Base URI: "+Config.CLOUD_LOC1_URI);
            // message("Absolute URI: "+aFile.getAbsolutePath());
            String lrn = Util.getLogicalName(Config.CLOUD_LOC1_URI,
                    aFile.getAbsolutePath());
            // message("LRN: "+lrn);
            String expectedName = dirName + File.separator + fNames[i];
            assertEquals(expectedName, lrn);
        }
    }
    

//    public void testInitTestCatalouge() throws Exception {
//        System.out.println("initTestCatalouge");
//
//        deleteAllDBFiles();
//
//        Util.initTestCatalouge();
//        File f = new File(new URI(Config.DB_LOC_URI));
//        assertTrue(f.exists());
//        fileCount = 0;
//        listAllNodes(f);
//        int dbFiles = fileCount;
//
//        fileCount = 0;
//        for (String s : Config.CLOUD_LOCATIONS_URI) {
//            f = new File(new URI(s));
//            assertTrue(f.exists());
//            listAllNodes(f);
//        }
//        int files = fileCount;
//        assertEquals(files, dbFiles);
//    }

    private void deleteAllDBFiles() throws URISyntaxException {
        File f = new File(new URI(Config.DB_LOC_URI));

        assertTrue(f.exists());

        File[] dbFiles = f.listFiles();

        for (File dbF : dbFiles) {
            dbF.delete();
        }

    }

    private static void listAllNodes(File cloudFiles) {
        if (!cloudFiles.getName().startsWith(".")) {
            File[] list = cloudFiles.listFiles();
            for (File f : list) {
                fileCount++;
                // debug("Files: "+f.getAbsolutePath());
                if (f.isDirectory()) {
                    listAllNodes(f);
                }
            }
        }
    }
}
