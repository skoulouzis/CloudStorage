/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.resources;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.bradmcevoy.http.Auth;
import com.bradmcevoy.http.CollectionResource;
import com.bradmcevoy.http.Range;
import com.bradmcevoy.http.Resource;
import com.bradmcevoy.http.exceptions.BadRequestException;
import com.bradmcevoy.http.exceptions.ConflictException;
import com.bradmcevoy.http.exceptions.NotAuthorizedException;
import cryptix.util.core.Debug;
import java.util.Map.Entry;
import java.util.Set;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.ICRCatalogue;
import spiros.cloud.storage.webDav.VCResources.IResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFileEntry;
import spiros.cloud.storage.webDav.VCResources.ResourceFolderEntry;

public class CloudDirResource extends CloudResource implements
        com.bradmcevoy.http.FolderResource {

    public CloudDirResource(ICRCatalogue catalogue, IResourceEntry entry) {
        super(catalogue, entry);
    }

    @Override
    public CollectionResource createCollection(String newName)
            throws NotAuthorizedException, ConflictException {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public Resource child(String name) {
        IResourceEntry child = ((ResourceFolderEntry) getNodeEntry()).getChildByLRN(name);
        if (child instanceof ResourceFolderEntry) {
            return new CloudDirResource(getCatalogue(), child);
        }
        if (child instanceof ResourceFileEntry) {
            return new CloudFileResource(getCatalogue(), child);
        }
        return new CloudResource(getCatalogue(), child);
    }

    @Override
    public List<? extends Resource> getChildren() {
        List<ResourceEntry> children = ((ResourceFolderEntry) getNodeEntry()).getChildren();
        List<Resource> list = new ArrayList<Resource>();
        debug("Children len "+children.size());
        for (ResourceEntry r : children) {
            if(r.getMetadata() == null){
                debug("Child "+r.getLRN()+" has no metadata ");
            }
            if (r instanceof ResourceFileEntry) {
                list.add(new CloudFileResource(getCatalogue(), r));
            } else if (r instanceof ResourceFolderEntry) {
                list.add(new CloudFileResource(getCatalogue(), r));
            } else {
                list.add(new CloudResource(getCatalogue(), r));
            }
        }
        return list;
    }

    @Override
    public Resource createNew(String arg0, InputStream arg1, Long arg2,
            String arg3) throws IOException, ConflictException {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public void copyTo(CollectionResource arg0, String arg1) {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public void delete() {
        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public Long getContentLength() {
        if (getNodeEntry().getMetadata() == null) {
            return null;
        }
        return getNodeEntry().getMetadata().getLength();
    }

    @Override
    public String getContentType(String accepts) {
        return "folder";
    }

    @Override
    public Long getMaxAgeSeconds(Auth auth) {
//        throw new RuntimeException("Not Implemented yet");
        if (auth != null) {
            debug("getMaxAgeSeconds. getCnonce " + auth.getCnonce());
            debug("getMaxAgeSeconds. " + auth.getNc());
            debug("getMaxAgeSeconds. getNc " + auth.getNonce());
            debug("getMaxAgeSeconds. " + auth.getPassword());
            debug("getMaxAgeSeconds. getPassword " + auth.getQop());
            debug("getMaxAgeSeconds. getRealm " + auth.getRealm());
            debug("getMaxAgeSeconds. getResponseDigest " + auth.getResponseDigest());
            debug("getMaxAgeSeconds. getUri " + auth.getUri());
            debug("getMaxAgeSeconds. getUser " + auth.getUser());
            debug("getMaxAgeSeconds. getScheme " + auth.getScheme().name());
            debug("getMaxAgeSeconds. getTag " + auth.getTag());
        }

        return null;
    }

    @Override
    public void sendContent(OutputStream out, Range range,
            Map<String, String> params, String contentType) throws IOException,
            NotAuthorizedException, BadRequestException {
        if (range != null) {
            debug("sendContent. Start: " + range.getStart() + " Finish: " + range.getFinish());
            debug("sendContent. Start: " + range.getStart() + " Finish: " + range.getFinish());
        }

        Set<Entry<String, String>> set = params.entrySet();
        Iterator<Entry<String, String>> iter = set.iterator();
        while (iter.hasNext()) {
            Entry<String, String> e = iter.next();
            debug(e.getKey() + " : " + e.getValue());
        }
        debug("sendContent. contentType: " + contentType);



//        throw new RuntimeException("Not Implemented yet");
    }

    @Override
    public void moveTo(CollectionResource arg0, String arg1)
            throws ConflictException {
        throw new RuntimeException("Not Implemented yet");
    }

    private void debug(String msg) {
        System.err.println(this.getClass().getSimpleName() + ": " + msg);
    }
}