/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.Serializable;

/**
 *
 * @author alogo
 */
public class AccessLocation implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2552461454620784560L;
    private String location;

    public AccessLocation(String uriString) {
        this.setLocations(uriString);
    }

    public void setLocations(String location) {
        this.location = location;
    }

    public String getLocations() {
        return location;
    }
}
