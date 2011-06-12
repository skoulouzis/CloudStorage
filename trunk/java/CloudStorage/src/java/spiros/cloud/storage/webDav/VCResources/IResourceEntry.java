/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

/**
 *
 * @author alogo
 */
public interface IResourceEntry {

    public String getLRN();

    public Metadata getMetadata();

    public void setMetadata(Metadata metadata);

    public String getUID();

    public void setLRN(String string);

    public Boolean isRoot();

    public boolean isTopLevel();
}
