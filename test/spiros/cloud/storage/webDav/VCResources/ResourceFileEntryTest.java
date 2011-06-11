/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import junit.framework.TestCase;

/**
 *
 * @author alogo
 */
public class ResourceFileEntryTest extends TestCase {
    
    public ResourceFileEntryTest(String testName) {
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
     * Test of addAccessLocations method, of class ResourceFileEntry.
     */
    public void testAddGetAccessLocations() throws IOException {
        System.out.println("addAccessLocations");
        List<AccessLocation> accessLocations = new ArrayList<AccessLocation>();
        AccessLocation a1 = new AccessLocation("Loc1");
        accessLocations.add(a1);
        AccessLocation a2 = new AccessLocation("Loc2");
        accessLocations.add(a2);
        
        ResourceFileEntry instance = new ResourceFileEntry("file");
        instance.addAccessLocations(accessLocations);
        List<AccessLocation> result = instance.getAccessLocations();
        
        assertEquals(accessLocations.size(), result.size());
        int hits = 0;
        for(int i=0;i<accessLocations.size();i++){
            for(int j=0;j<result.size();j++){
                if(accessLocations.get(i).equals(result.get(j))){
                    hits++;
                }
            }
        }
        
        assertEquals(hits, result.size());
        assertEquals(accessLocations.size(), hits);
        
    }

    /**
     * Test of removeAccessLocations method, of class ResourceFileEntry.
     */
    public void testRemoveAccessLocations() throws IOException {
        System.out.println("removeAccessLocations");
List<AccessLocation> accessLocations = new ArrayList<AccessLocation>();
        AccessLocation a1 = new AccessLocation("Loc1");
        accessLocations.add(a1);
        AccessLocation a2 = new AccessLocation("Loc2");
        accessLocations.add(a2);
        AccessLocation a3 = new AccessLocation("Loc3");
        accessLocations.add(a3);
        
        ResourceFileEntry instance = new ResourceFileEntry("file");
        instance.addAccessLocations(accessLocations);
        List<AccessLocation> result = instance.getAccessLocations();
        
        accessLocations.remove(a3);
        
        instance.removeAccessLocations(accessLocations);
        int left = 1;
        assertEquals(result.size(), left);
        
        result = instance.getAccessLocations();
        
        assertEquals(result.get(0), a3);
        
    }

    /**
     * Test of removeAllAccessLocations method, of class ResourceFileEntry.
     */
    public void testRemoveAllAccessLocations() throws IOException {
        System.out.println("removeAllAccessLocations");
        ResourceFileEntry instance = new ResourceFileEntry("file");
        List<AccessLocation> accessLocations = new ArrayList<AccessLocation>();
        AccessLocation a1 = new AccessLocation("Loc1");
        accessLocations.add(a1);
        AccessLocation a2 = new AccessLocation("Loc2");
        accessLocations.add(a2);
        AccessLocation a3 = new AccessLocation("Loc3");
        accessLocations.add(a3);
        instance.addAccessLocations(accessLocations);
        
        
        instance.removeAllAccessLocations();
        List<AccessLocation> result = instance.getAccessLocations();
        assertTrue(result.isEmpty());
    }

    /**
     * Test of addAccessLocation method, of class ResourceFileEntry.
     */
    public void testAddAccessLocation() throws IOException {
        System.out.println("addAccessLocation");
        AccessLocation accessLocation = new AccessLocation("Loc1");
        ResourceFileEntry instance = new ResourceFileEntry("file");
        instance.addAccessLocation(accessLocation);
        List<AccessLocation> result = instance.getAccessLocations();
        
        assertEquals(result.size(), 1);
        
        assertEquals(result.get(0), accessLocation);
    }
}
