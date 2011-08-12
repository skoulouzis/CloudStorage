/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFileEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;
import spiros.cloud.storage.webDav.test.TestUtil;

/**
 *
 * @author alogo
 */
public class SimpleCRCatalogueTest extends TestCase {

    private final TestUtil u;

    public SimpleCRCatalogueTest(String testName) throws Exception {
        super(testName);
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

    public void testUIDs() throws MalformedURLException, URISyntaxException, Exception {
        System.out.println("testUIDs");

        for (ResourceEntry entry : u.getAllEntries()) {
            if (entry.getUID() == null || entry.getUID().equals("")) {
                fail("Entry " + entry.getLRN() + " has null or empty UID: "
                        + entry.getUID());
            }
        }

        boolean duplicates = u.hasDuplicates(u.getAllEntries());
        assertFalse(duplicates);
    }

    /**
     * Test of deleteAllEntries method, of class SimpleCRCatalogue.
     */
    public void testDeleteAllEntries() throws MalformedURLException, URISyntaxException, IOException, ClassNotFoundException {
        System.out.println("deleteAllEntries");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();

        Boolean hasEntries = instance.hasEnties();
        assertTrue(hasEntries);

        instance.deleteAllEntries();
        hasEntries = instance.hasEnties();
        assertFalse(hasEntries);
    }

    /**
     * Test of getDBLocation method, of class SimpleCRCatalogue.
     */
    public void testGetDBLocation() throws MalformedURLException, URISyntaxException {
        System.out.println("getDBLocation");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        String expResult = new File(new URI(Config.DB_LOC_URI)).getAbsolutePath();
        String result = instance.getDBLocation();
        assertEquals(expResult, result);

    }

    /**
     * Test of registerResourceEntry method, of class SimpleCRCatalogue.
     */
    public void testRegisterResourceEntry() throws Exception {
        System.out.println("registerResourceEntry");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        String lrn = "aDir";
        ResourceFolderEntry newDir = new ResourceFolderEntry(lrn);
        assertEquals(lrn, newDir.getLRN());
        instance.registerResourceEntry(newDir);

        IResourceEntry loaded = instance.getResourceEntryByLRN(newDir.getLRN());
        Boolean theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);

        loaded = instance.getResourceEntryByUID(newDir.getUID());
        theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);
    }
    
       /**
     * Test of registerResourceEntry method, of class SimpleCRCatalogue.
     */
    public void testRegisterChildEntry() throws Exception {
        System.out.println("testRegisterChildsEntry");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        String lrn = "/dir1/dir2/file";
        ResourceEntry newDir = new ResourceFileEntry(lrn);
        assertEquals(lrn, newDir.getLRN());
        instance.registerResourceEntry(newDir);
        
        IResourceEntry loaded = instance.getResourceEntryByLRN(newDir.getLRN());
        
        Boolean theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);

        loaded = instance.getResourceEntryByUID(newDir.getUID());
        theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);
    }

    /**
     * Test of getResourceEntryByLRN method, of class SimpleCRCatalogue.
     */
    public void testGetResourceEntryByLRN() throws Exception {
        System.out.println("getResourceEntryByLRN");
        String logicalResourceName = "name";
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        IResourceEntry expResult = new ResourceEntry(logicalResourceName);
        instance.registerResourceEntry(expResult);

        IResourceEntry result = instance.getResourceEntryByLRN(logicalResourceName);
        Boolean same = SimpleCRCatalogue.compareEntries(expResult, result);
        assertTrue(same);

    }

    /**
     * Test of unregisterResourceEntry method, of class SimpleCRCatalogue.
     */
    public void testUnregisterResourceEntry() throws MalformedURLException, URISyntaxException, IOException, Exception {
        System.out.println("unregisterResourceEntry");
        IResourceEntry entry = new ResourceEntry("resource");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        instance.registerResourceEntry(entry);

        instance.unregisterResourceEntry(entry);
        IResourceEntry resEntry = instance.getResourceEntryByUID(entry.getUID());
        assertNull(resEntry);
    }

    /**
     * Test of resourceEntryExists method, of class SimpleCRCatalogue.
     */
    public void testResourceEntryExists() throws Exception {
        System.out.println("resourceEntryExists");

        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        for (ResourceEntry entry : u.getAllEntries()) {
            assertTrue(instance.resourceEntryExists(entry));
        }
    }

    /**
     * Test of loadAllEntries method, of class SimpleCRCatalogue.
     */
    public void testLoadAllEntries() throws Exception {
        System.out.println("loadAllEntries");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        List<ResourceEntry> expResult = u.getAllEntries();
        List<ResourceEntry> result = instance.loadAllEntries();
        testSameElements(expResult, result);

    }

    /**
     * Test of compareEntries method, of class SimpleCRCatalogue.
     */
    public void testCompareEntries() throws IOException, MalformedURLException, URISyntaxException, Exception {
        System.out.println("compareEntries");
        IResourceEntry entry = new ResourceEntry("e1");
        SimpleCRCatalogue c = new SimpleCRCatalogue();
        c.registerResourceEntry(entry);

        IResourceEntry loadedEntry = c.getResourceEntryByUID(entry.getUID());
        Boolean expResult = true;
        Boolean result = SimpleCRCatalogue.compareEntries(entry, loadedEntry);
        assertEquals(expResult, result);

    }

    /**
     * Test of registerResourceEntrys method, of class SimpleCRCatalogue.
     */
    public void testRegisterResourceEntrys() throws Exception {
        System.out.println("registerResourceEntrys");

        SimpleCRCatalogue instance = new SimpleCRCatalogue();

        String lrn = "aDir";
        ResourceFolderEntry newDir = new ResourceFolderEntry(lrn);
        assertEquals(lrn, newDir.getLRN());
        instance.registerResourceEntry(newDir);

        IResourceEntry loaded = instance.getResourceEntryByLRN(newDir.getLRN());
        Boolean theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);

        loaded = instance.getResourceEntryByUID(newDir.getUID());
        theSame = SimpleCRCatalogue.compareEntries(newDir, loaded);
        assertTrue(theSame);
    }

    /**
     * Test of getRoot method, of class SimpleCRCatalogue.
     */
    public void testGetRoot() throws Exception {
        System.out.println("getRoot");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();

        ResourceFolderEntry result = instance.getRoot();
        assertEquals("/", result.getLRN());
    }

    /**
     * Test of getTopLevelResourceEntries method, of class SimpleCRCatalogue.
     */
    public void testGetTopLevelResourceEntries() throws Exception {
        System.out.println("getTopLevelResourceEntries");
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        ArrayList<ResourceEntry> expResult = (ArrayList<ResourceEntry>) u.getTopEntries();
        ArrayList<ResourceEntry> result = instance.getTopLevelResourceEntries();
        testSameElements(expResult, result);
    }

    /**
     * Test of getResourceEntryByUID method, of class SimpleCRCatalogue.
     */
    public void testGetResourceEntryByUID() throws Exception {
        System.out.println("getResourceEntryByUID");
        String UID = "";
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        IResourceEntry expResult = new ResourceEntry("entry");
        instance.registerResourceEntry(expResult);
        IResourceEntry result = instance.getResourceEntryByUID(expResult.getUID());
        assertEquals(expResult.getUID(), result.getUID());
    }

    private void testSameElements(List<ResourceEntry> expResult, List<ResourceEntry> result) {
        assertEquals(expResult.size(), result.size());


        int hits = 0;
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < expResult.size(); j++) {
                if (SimpleCRCatalogue.compareEntries(result.get(i), expResult.get(j))) {
                    hits++;
                }
            }
        }

        assertEquals(expResult.size(), hits);
        assertEquals(result.size(), hits);
    }

    public void testRegisterChild() throws Exception {
        SimpleCRCatalogue cat = new SimpleCRCatalogue();

        ResourceFolderEntry theDir = (ResourceFolderEntry) cat.getResourceEntryByUID(u.getTestDirUID());
        ResourceEntry child = new ResourceEntry(theDir.getLRN() + "/child");
        theDir.addChild(child);

        IResourceEntry loadedChild = theDir.getChildByLRN(child.getLRN());
        Boolean theSame = SimpleCRCatalogue.compareEntries(child, loadedChild);
        assertTrue(theSame);

        String uid1 = child.getUID();
        loadedChild = theDir.getChildByUID(uid1);

//		debug("Is the same? "+child.getLRN()+":"+child.getUID());
//		debug("Is the same? "+loadedChild.getLRN()+":"+loadedChild.getUID());

        theSame = SimpleCRCatalogue.compareEntries(child, loadedChild);
        assertTrue(theSame);


        loadedChild = theDir.getChildByLRN(child.getLRN());
//		debug("Is the same? "+child.getLRN()+":"+child.getUID());
//		debug("Is the same? "+loadedChild.getLRN()+":"+loadedChild.getUID());
        theSame = SimpleCRCatalogue.compareEntries(child, loadedChild);
        assertTrue(theSame);

        boolean exceptionThrown = false;
        try {
            child = new ResourceEntry("/someOtherDir/child");
            theDir.addChild(child);
            loadedChild = theDir.getChildByLRN(child.getLRN());
            theSame = SimpleCRCatalogue.compareEntries(child, loadedChild);
            assertTrue(theSame);
        } catch (Exception e) {
            exceptionThrown = true;
        }
        assertTrue(exceptionThrown);
    }

    public void testLoadAll() throws URISyntaxException, IOException,
            ClassNotFoundException {
        SimpleCRCatalogue instance = new SimpleCRCatalogue();
        List<ResourceEntry> loadedAll = instance.loadAllEntries();

        boolean duplicates = u.hasDuplicates(loadedAll);
        assertFalse(duplicates);

        boolean matsch = u.compareResourceEntryList(u.getAllEntries(), loadedAll);
        assertTrue(matsch);
    }

    private void debug(String msg) {
        System.err.println(this.getName()+": "+msg);
    }
    
}
