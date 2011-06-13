/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.test;

import com.bradmcevoy.http.Resource;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.Config;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFileEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;

/**
 *
 * @author S. Koulouzis
 */
public class TestUtil {

    public static boolean compareCloudResources(Resource expResult, Resource result) {
        if (expResult.getName().equals(result.getName()) && expResult.getUniqueId().equals(result.getUniqueId())) {
            return true;
        }
        return false;
    }

    public static boolean compareCloudListOfResources(List<Resource> expResult, List<Resource> result) {
        if (expResult.size() != result.size()) {
            return false;
        }
        int hits = 0;
        for (int i = 0; i < expResult.size(); i++) {
            for (int j = 0; j < result.size(); j++) {
                if (compareCloudResources(expResult.get(i), result.get(j))) {
                    hits++;
                }

            }
        }

        if (hits == expResult.size() && hits == result.size()) {
            return true;
        }

        return false;
    }
    private List<ResourceEntry> topEntries = new ArrayList<ResourceEntry>();
    private List<ResourceEntry> allEntries = new ArrayList<ResourceEntry>();
    private SimpleCRCatalogue instance;
    private String testDirUID;
    public static final String DIR_NAME1 = "/dir01";
    private ResourceFolderEntry dir01;
    private ResourceEntry dir01Child1;
    public static final String DIR1_CHILD_NAME1 = "/dir01/file11";
    public static final String DIR1_CHILD_NAME2 = "/dir01/dir11";
    private ResourceFolderEntry dir01Child2;
    private List<ResourceEntry> childResourcesOfDir1;

    public TestUtil() throws Exception {
        instance = new SimpleCRCatalogue();
        childResourcesOfDir1 = new ArrayList<ResourceEntry>();
    }

    public void initTestDB() throws Exception {
        initTestEnties();
        instance.registerResourceEntrys(topEntries);
    }

    private void initTestEnties() throws Exception {
        dir01 = (new ResourceFolderEntry(DIR_NAME1));
        dir01Child1 = (new ResourceFileEntry(DIR1_CHILD_NAME1));
        dir01.addChild(dir01Child1);
        dir01Child2 = (new ResourceFolderEntry(DIR1_CHILD_NAME2));

        ResourceFileEntry file21 = new ResourceFileEntry("/dir01/dir11/file21");
        dir01Child2.addChild(file21);
        dir01.addChild(dir01Child2);

        getChildResourcesOfDir1().add(dir01Child1);
        getChildResourcesOfDir1().add(dir01Child2);


        allEntries.add(file21);
        allEntries.add(getDir01Child2());
        allEntries.add(getDir01Child());
        allEntries.add(getDir01());
        topEntries.add(getDir01());

        ResourceFolderEntry dir02 = new ResourceFolderEntry("/dir02");
        ResourceFolderEntry dir0211 = new ResourceFolderEntry("/dir02/dir11");
        dir02.addChild(dir0211);
        ResourceFileEntry file0211 = new ResourceFileEntry("/dir02/file11");
        dir02.addChild(file0211);
        allEntries.add(file0211);
        allEntries.add(dir0211);
        allEntries.add(dir02);
        topEntries.add(dir02);

        ResourceFolderEntry dir03 = new ResourceFolderEntry("/dir03");
        ResourceFolderEntry dir0311 = new ResourceFolderEntry(dir03.getLRN() + "/dir11/");
        ResourceFolderEntry dir0321 = new ResourceFolderEntry(
                dir0311.getLRN() + "/dir21/");
        ResourceFolderEntry dir0331 = new ResourceFolderEntry(
                dir0321.getLRN() + "/dir31");
        ResourceFileEntry file41 = new ResourceFileEntry(
                dir0331.getLRN() + "/file41");
        dir0331.addChild(file41);
        dir0321.addChild(dir0331);
        dir0311.addChild(dir0321);
        dir03.addChild(dir0311);

        allEntries.add(file41);
        allEntries.add(dir0331);
        allEntries.add(dir0321);
        allEntries.add(dir0311);
        allEntries.add(dir03);
        topEntries.add(dir03);

        testDirUID = dir0211.getUID();
    }

    public void setTopEntries(List<ResourceEntry> topEntries) {
        this.topEntries = topEntries;
    }

    public List<ResourceEntry> getTopEntries() {
        return topEntries;
    }

    public void setAllEntries(List<ResourceEntry> allEntries) {
        this.allEntries = allEntries;
    }

    public List<ResourceEntry> getAllEntries() {
        return allEntries;
    }

    public boolean hasDuplicates(List<ResourceEntry> list) {
        Set<ResourceEntry> set = new HashSet<ResourceEntry>(list);
//        assertEquals(list.size(), set.size());
        if (list.size() == set.size()) {
            return false;
        }
        return true;
    }

    public boolean compareResourceEntryList(List<ResourceEntry> list1,
            List<ResourceEntry> list2) {

        if (list1.size() != list2.size()) {
            return false;
        }

        int matches = 0;
        for (int i = 0; i < list1.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if (SimpleCRCatalogue.compareEntries(list1.get(i), list2.get(j))) {
                    matches++;
                }
            }
        }

        if (matches == list1.size() && matches == list2.size()) {
            return true;
        }
        return false;

    }

    public String getTestDirUID() {
        return testDirUID;
    }

    public void deleteDB() throws URISyntaxException {
        File db = new File(new URI(Config.DB_LOC_URI));
        File[] entries = db.listFiles();
        if (entries != null && entries.length >= 1) {
            for (File f : entries) {
                f.delete();
            }
        }
    }

    /**
     * @return the dir01
     */
    public ResourceFolderEntry getDir01() {
        return dir01;
    }

    /**
     * @param dir01 the dir01 to set
     */
    public void setDir01(ResourceFolderEntry dir01) {
        this.dir01 = dir01;
    }

    /**
     * @return the dir01Child1
     */
    public ResourceEntry getDir01Child() {
        return dir01Child1;
    }

    /**
     * @param dir01Child1 the dir01Child1 to set
     */
    public void setDir01Child(ResourceEntry dir01Child) {
        this.dir01Child1 = dir01Child;
    }

    /**
     * @return the dir01Child2
     */
    public ResourceFolderEntry getDir01Child2() {
        return dir01Child2;
    }

    /**
     * @param dir01Child2 the dir01Child2 to set
     */
    public void setDir01Child2(ResourceFolderEntry dir01Child2) {
        this.dir01Child2 = dir01Child2;
    }

    /**
     * @return the childResourcesOfDir1
     */
    public List<ResourceEntry> getChildResourcesOfDir1() {
        return childResourcesOfDir1;
    }

    /**
     * @param childResourcesOfDir1 the childResourcesOfDir1 to set
     */
    public void setChildResourcesOfDir1(List<ResourceEntry> childResourcesOfDir1) {
        this.childResourcesOfDir1 = childResourcesOfDir1;
    }
}
