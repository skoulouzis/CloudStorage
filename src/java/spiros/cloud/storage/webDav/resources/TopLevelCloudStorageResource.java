package spiros.cloud.storage.webDav.resources;

import java.util.Date;
import java.util.List;


import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.PropFindableResource;
import com.bradmcevoy.http.Request;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.Request.Method;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TopLevelCloudStorageResource implements PropFindableResource, CollectionResource {

    private final CloudResourceFactory resourceFactory;

    public TopLevelCloudStorageResource(CloudResourceFactory resourceFactory) {
        this.resourceFactory = resourceFactory;
    }

    @Override
    public Date getCreateDate() {
        // Unknown
        return null;
    }

    @Override
    public Object authenticate(String user, String pwd) {
        debug("User: " + user + " Password: " + pwd);
        return user;
    }

    @Override
    public boolean authorise(Request arg0, Method arg1, Auth arg2) {
        debug("authorise Method: " + arg1 + " Auth: " + arg2);
        debug("authorise getAbsolutePath: " + arg0.getAbsolutePath());
        debug("authorise getAbsoluteUrl: " + arg0.getAbsoluteUrl());
        debug("authorise getAcceptEncodingHeader: " + arg0.getAcceptEncodingHeader());
        debug("authorise getAcceptHeader: " + arg0.getAcceptHeader());
        return true;
    }

    @Override
    public String checkRedirect(Request arg0) {
        debug("Request: " + arg0);
        return null;
    }

    @Override
    public Date getModifiedDate() {
        // Unknown
        return null;
    }

    @Override
    public String getName() {
        return "";
    }

    @Override
    public String getRealm() {
        return CloudResourceFactory.REALM;
    }

    @Override
    public String getUniqueId() {
        return null;
    }

    @Override
    public Resource child(String name) {
        return null;
    }

    @Override
    public List<? extends Resource> getChildren() {
        try {
            List<? extends Resource> nodes = resourceFactory.listRootResources();
            return nodes;
        } catch (IOException ex) {
            Logger.getLogger(TopLevelCloudStorageResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TopLevelCloudStorageResource.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(TopLevelCloudStorageResource.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
//        Logger.getLogger(TopLevelCloudStorageResource.class.getName()).log(Level.INFO, msg);
    }
}
