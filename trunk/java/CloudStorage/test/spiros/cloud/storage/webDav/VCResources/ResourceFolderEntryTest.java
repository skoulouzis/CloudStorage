/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;

/**
 *
 * @author alogo
 */
public class ResourceFolderEntryTest extends TestCase {

    public ResourceFolderEntryTest(String testName) {
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
     * Test of getChildren method, of class ResourceFolderEntry.
     */
    public void testAddGetChildren() throws IOException, Exception {
        System.out.println("getChildren");
        ResourceFolderEntry instance = new ResourceFolderEntry("/SomeDir");
        List<ResourceEntry> children = new ArrayList<ResourceEntry>();
        ResourceEntry ch1 = new ResourceEntry("child1");
        children.add(ch1);
        ResourceEntry ch2 = new ResourceEntry("child2");
        children.add(ch2);
        instance.addChildren(children);


        List<ResourceEntry> result = instance.getChildren();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), children.size());

        Boolean same;
        int hits = 0;
        for (int i = 0; i < result.size(); i++) {
            for (int j = 0; j < children.size(); j++) {
                if (result.get(i).getLRN().equals(children.get(j).getLRN())) {
                    same = SimpleCRCatalogue.compareEntries(result.get(i), children.get(j));
                    assertTrue(same);
                    hits++;
                }
            }
        }
        assertEquals(hits, children.size());
        assertEquals(hits, result.size());
    }

    /**
     * Test of addChild method, of class ResourceFolderEntry.
     */
    public void testAddGetChild() throws Exception {
        System.out.println("addChild");

        ResourceEntry child = new ResourceEntry("someChild");
        ResourceFolderEntry instance = new ResourceFolderEntry("someResource");
        instance.addChild(child);

        Boolean same = SimpleCRCatalogue.compareEntries(child, instance.getChildByLRN(child.getLRN()));
        assertTrue(same);
        same = SimpleCRCatalogue.compareEntries(child, instance.getChildByUID(child.getUID()));
        assertTrue(same);
    }

    /**
     * Test of removeChildren method, of class ResourceFolderEntry.
     */
    public void testRemoveChildren() throws IOException, Exception {
        System.out.println("removeChildren");
        List<ResourceEntry> children = new ArrayList<ResourceEntry>();
        ResourceEntry ch1 = new ResourceEntry("child1");
        children.add(ch1);
        ResourceEntry ch2 = new ResourceEntry("child2");
        children.add(ch2);
        ResourceEntry ch3 = new ResourceEntry("child3");
        children.add(ch3);


        ResourceFolderEntry instance = new ResourceFolderEntry("SomeEntry");
        instance.addChildren(children);
        List<ResourceEntry> result = instance.getChildren();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), children.size());

        children.remove(ch3);

        instance.removeChildren(children);
        result = instance.getChildren();

        int left = 1;
        assertEquals(result.size(), left);
        Boolean same = SimpleCRCatalogue.compareEntries(ch3, instance.getChildren().get(0));

        assertTrue(same);

    }

    /**
     * Test of removeAllChildren method, of class ResourceFolderEntry.
     */
    public void testRemoveAllChildren() throws IOException, Exception {
        System.out.println("removeAllChildren");
        List<ResourceEntry> children = new ArrayList<ResourceEntry>();
        ResourceEntry ch1 = new ResourceEntry("child1");
        children.add(ch1);
        ResourceEntry ch2 = new ResourceEntry("child2");
        children.add(ch2);
        ResourceEntry ch3 = new ResourceEntry("child3");
        children.add(ch3);


        ResourceFolderEntry instance = new ResourceFolderEntry("SomeEntry");
        instance.addChildren(children);
        List<ResourceEntry> result = instance.getChildren();
        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(result.size(), children.size());



        instance.removeAllChildren();
        result = instance.getChildren();

        assertTrue(result.isEmpty());

    }

    /**
     * Test of childExists method, of class ResourceFolderEntry.
     */
    public void testChildExists() throws IOException, Exception {
        System.out.println("childExists");
        ResourceEntry child = new ResourceEntry("child");
        ResourceFolderEntry instance = new ResourceFolderEntry("resource");
        instance.addChild(child);

        boolean expResult = true;
        boolean result = instance.childExists(child);
        assertEquals(expResult, result);
    }

    /**
     * Test of getChildByLRN method, of class ResourceFolderEntry.
     */
    public void testGetChildByLRN() throws IOException, Exception {
        System.out.println("getChildByLRN");
        String name = "child";
        ResourceFolderEntry instance = new ResourceFolderEntry("resource");
        ResourceEntry expResult = new ResourceEntry(name);
        instance.addChild(expResult);
        
        IResourceEntry result = instance.getChildByLRN(expResult.getLRN());
        
        assertEquals(expResult.getLRN(), result.getLRN());
        
        Boolean same = SimpleCRCatalogue.compareEntries(expResult, result);
        assertTrue(same);
    }

    /**
     * Test of getChildByUID method, of class ResourceFolderEntry.
     */
    public void testGetChildByUID() throws IOException, Exception {
        System.out.println("getChildByUID");
        String name = "child";
        ResourceFolderEntry instance = new ResourceFolderEntry("resource");
        ResourceEntry expResult = new ResourceEntry(name);
        instance.addChild(expResult);
        
        IResourceEntry result = instance.getChildByUID(expResult.getUID());
        assertEquals(expResult.getUID(), result.getUID());
        Boolean same = SimpleCRCatalogue.compareEntries(expResult, result);
        assertTrue(same);
    }

    /**
     * Test of hasChildren method, of class ResourceFolderEntry.
     */
    public void testHasChildren() throws IOException {
        System.out.println("hasChildren");
        ResourceFolderEntry instance = new ResourceFolderEntry("resource");
        Boolean expResult = false;
        Boolean result = instance.hasChildren();
        assertEquals(expResult, result);
    }
}
