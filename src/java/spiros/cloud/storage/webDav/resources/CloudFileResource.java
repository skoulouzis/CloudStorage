/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import com.bradmcevoy.common.ContentTypeUtils;
import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.FileItem;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.exceptions.BadRequestException;
import com.bradmcevoy.http.exceptions.ConflictException;
import com.bradmcevoy.http.exceptions.NotAuthorizedException;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.Metadata;

/**
 *
 * @author S. Koulouzis
 */
public class CloudFileResource extends CloudResource implements
        com.bradmcevoy.http.FileResource {

    public CloudFileResource(ICRCatalogue catalogue, IResourceEntry entry) {
        super(catalogue, entry);
    }

    @Override
    public void copyTo(CollectionResource collectionResource, String name) {
        throw new RuntimeException(
                "Not Implemented yet. Args: CollectionResource: "
                + collectionResource + ", name: " + name);
    }

    @Override
    public void delete() {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public Long getContentLength() {
        Metadata meta = getNodeEntry().getMetadata();
        if (meta != null) {
            return meta.getLength();
        }
        return null;
    }

    @Override
    public String getContentType(String accepts) {
        debug("getContentType. accepts: " + accepts);
        String type = null;
        Metadata meta = getNodeEntry().getMetadata();
        if (meta != null) {
            String mime = meta.getMimeType();
            debug("getContentType: mime: " + mime);
            type = mime;
            if (accepts != null && !accepts.equals("")) {
                type = ContentTypeUtils.findAcceptableContentType(mime, accepts);
            }
        }else{
            debug("Metadata is NULL!!!");
        }

        debug("getContentType: type: " + type);

        return type;
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + "."+this.getName()+": "+ msg);
    }

    @Override
    public Long getMaxAgeSeconds(Auth auth) {
//        throw new RuntimeException("Not Implemented yet: Args: auth: " + auth);
        return null;
    }

    @Override
    public void sendContent(OutputStream out, Range range,
            Map<String, String> params, String contentType) throws IOException,
            NotAuthorizedException, BadRequestException {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public void moveTo(CollectionResource rDest, String name)
            throws ConflictException {
        // catalogue.renameEntry();
        throw new RuntimeException("Not Implemented yet. Args, DestName: "
                + rDest.getName() + " fileName?:" + name);
    }

    @Override
    public String processForm(Map<String, String> arg0,
            Map<String, FileItem> arg1) throws BadRequestException,
            NotAuthorizedException {
        throw new RuntimeException("Not Implemented yet");
    }
}
