/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.CloudResourceCatalogue;

import java.util.List;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;

/**
 *
 * @author S. Koulouzis
 */
public interface ICRCatalogue {

    public void registerResourceEntry(IResourceEntry entry) throws Exception;

    public IResourceEntry getResourceEntryByLRN(String logicalResourceName)
            throws Exception;

    public IResourceEntry getResourceEntryByUID(String UID)
            throws Exception;

    public void unregisterResourceEntry(IResourceEntry entry) throws Exception;

    public Boolean resourceEntryExists(IResourceEntry entry) throws Exception;

    public IResourceEntry getRoot() throws Exception;

    public List<ResourceEntry> getTopLevelResourceEntries() throws Exception;
}
