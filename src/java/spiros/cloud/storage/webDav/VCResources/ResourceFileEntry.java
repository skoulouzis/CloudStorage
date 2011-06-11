/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author alogo
 */
public class ResourceFileEntry extends ResourceEntry {

    /**
     * 
     */
    private static final long serialVersionUID = 6019657958412257303L;
    private List<AccessLocation> accessLocations;

    public ResourceFileEntry(String logicalResourceName) throws IOException {
        super(logicalResourceName);
    }

    public void addAccessLocations(List<AccessLocation> accessLocations) {
        if (this.accessLocations == null) {
            this.accessLocations = new ArrayList<AccessLocation>();
        }
        this.accessLocations.addAll(accessLocations);
    }

    public void removeAccessLocations(List<AccessLocation> accessLocations) {
        if (this.accessLocations != null && !this.accessLocations.isEmpty()) {
            this.accessLocations.removeAll(accessLocations);
        }
    }

    public void removeAllAccessLocations() {
        if (this.accessLocations != null && !this.accessLocations.isEmpty()) {
            this.accessLocations.clear();
        }
    }

    public List<AccessLocation> getAccessLocations() {
        return accessLocations;
    }

    public void addAccessLocation(AccessLocation accessLocation) {
        if (this.accessLocations == null) {
            this.accessLocations = new ArrayList<AccessLocation>();
        }
        this.accessLocations.add(accessLocation);
    }
}
