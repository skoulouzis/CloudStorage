/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

import nl.uva.vlet.vrs.VRS;

/**
 *
 * @author alogo
 */
public class InitDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
       Util.initTestCatalouge();
       
       VRS.exit();
       
       System.exit(0);
    }
}
