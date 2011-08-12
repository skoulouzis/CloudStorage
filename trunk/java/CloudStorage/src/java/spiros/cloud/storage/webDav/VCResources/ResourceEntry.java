/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import java.io.Serializable;
import java.net.URI;
import java.net.URISyntaxException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

public class ResourceEntry implements IResourceEntry, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -1529997963561059214L;
    private String UID = null;
    private String lrn;
    private Metadata metadata;

    public ResourceEntry() {
    }

    public ResourceEntry(String logicalResourceName) throws IOException {
        setLRN(logicalResourceName);
        if (this.UID == null) {
            this.UID = java.util.UUID.randomUUID().toString();
        }
    }

    private String removeLastChar(String logicalResourceName) {

        if (logicalResourceName == null) {
            return null;
        }
        int strLen = logicalResourceName.length();
        if (strLen < 2) {
            return "";
        }
        int lastIdx = strLen - 1;
        String ret = logicalResourceName.substring(0, lastIdx);
        char last = logicalResourceName.charAt(lastIdx);
        if (last == '\n') {
            if (ret.charAt(lastIdx - 1) == '\r') {
                return ret.substring(0, lastIdx - 1);
            }
        }
        return ret;
    }

    @Override
    public String getLRN() {
        return lrn;
    }

    @Override
    public Metadata getMetadata() {
        return metadata;
    }

    @Override
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    @Override
    public String getUID() {
        return this.UID;
    }

    protected void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }

    public void setLRN(String newLRN) {
        if (!newLRN.contains("/")) {
            this.lrn = newLRN;
        } else {
            String formated = null;
            String tmp = newLRN;
            if (newLRN.equals("/")) {
                this.lrn = newLRN;
            } else {
                if (newLRN.endsWith("/")) {
                    tmp = removeLastChar(newLRN);
                }
                try {
                    URI uri = new URI("file://" + tmp);
                    formated = uri.getPath();
                    tmp = formated.replaceAll("//", "/");
                    formated = tmp;

                    // debug("Formated: "+formated);
                } catch (URISyntaxException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                this.lrn = formated;
            }

        }
    }

    @Override
    public Boolean isRoot() {
        if (getLRN().equals("/")) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isTopLevel() {
        String[] parts = getLRN().split("/");
        if (parts.length <= 2) {
            return true;
        }
        return false;
    }
}
