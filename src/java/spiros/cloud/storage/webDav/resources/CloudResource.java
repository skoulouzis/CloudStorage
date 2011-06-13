package spiros.cloud.storage.webDav.resources;

import java.util.Date;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Request.Method;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CloudResource implements PropFindableResource {

    private IResourceEntry nodeEntry;
    private ICRCatalogue catalogue;
    private Logger log = LoggerFactory.getLogger(CloudResource.class);

    public CloudResource(ICRCatalogue catalogue, IResourceEntry entry) {
        this.setNodeEntry(entry);
        this.setCatalogue(catalogue);
        if (entry.getMetadata() == null) {
            throw new RuntimeException(entry.getLRN() + " has no metadata!");
        }
    }

    @Override
    public Object authenticate(String user, String pwd) {
        debug("User: " + user + " Password: " + pwd);
        return user;
    }

    @Override
    public boolean authorise(Request arg0, Method arg1, Auth arg2) {
        debug("Request: " + arg0 + " Method: " + arg1 + " Auth: " + arg2);
        return true;
    }

    @Override
    public String checkRedirect(Request arg0) {
        debug("Request: " + arg0);
        return null;
    }

    @Override
    public Date getModifiedDate() {
        if (getNodeEntry().getMetadata() == null) {
            return null;
        }
        return new Date(getNodeEntry().getMetadata().getModifiedDate());
    }

    @Override
    public String getName() {
        return getNodeEntry().getLRN();
    }

    @Override
    public String getRealm() {
        return "relm";
    }

    @Override
    public String getUniqueId() {
        return "" + getNodeEntry().getUID();
    }

    @Override
    public Date getCreateDate() {
        if (getNodeEntry().getMetadata() == null) {
            return null;
        }
        return new Date(getNodeEntry().getMetadata().getCreateDate());
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
        log.debug(msg);
    }

    protected void setNodeEntry(IResourceEntry nodeEntry) {
        this.nodeEntry = nodeEntry;
    }

    protected IResourceEntry getNodeEntry() {
        return nodeEntry;
    }

    protected void setCatalogue(ICRCatalogue catalogue) {
        this.catalogue = catalogue;
    }

    protected ICRCatalogue getCatalogue() {
        return catalogue;
    }
}
