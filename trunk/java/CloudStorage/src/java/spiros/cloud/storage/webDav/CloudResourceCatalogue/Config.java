/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

import java.net.MalformedURLException;
import java.net.URL;
import nl.uva.vlet.GlobalConfig;
import nl.uva.vlet.vrs.VRS;
import nl.uva.vlet.vrs.VRSClient;
import nl.uva.vlet.vrs.VRSContext;

/**
 * 
 * @author S. Koulouzis
 */
public class Config {

    public static final String TEST_DATA_URI = "file://"
            + System.getProperty("user.home")
            + "/workspace/CloudStorage/test/testData";
    public static final String CLOUD_LOC1_URI = TEST_DATA_URI + "/remoteFS1";
    public static final String CLOUD_LOC2_URI = TEST_DATA_URI + "/remoteFS2";
    public static final String[] CLOUD_LOCATIONS_URI = {CLOUD_LOC1_URI,
        CLOUD_LOC2_URI};
    public static final String DB_LOC_URI = TEST_DATA_URI + "/db";

    public static VRSClient initVRS() throws MalformedURLException {

        GlobalConfig.setHasUI(false);
        GlobalConfig.setInitURLStreamFactory(false);
        GlobalConfig.setIsApplet(true);
        GlobalConfig.setBaseLocation(new URL("file://dummy/path"));
        VRSContext cont = VRS.getDefaultVRSContext();
        
        return new VRSClient(cont);
    }
}
