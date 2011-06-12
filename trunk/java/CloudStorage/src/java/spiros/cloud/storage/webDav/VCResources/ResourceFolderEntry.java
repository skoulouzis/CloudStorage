/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spiros.cloud.storage.webDav.VCResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import spiros.cloud.storage.webDav.CloudResourceCatalogue.SimpleCRCatalogue;

/**
 *
 * @author alogo
 */
public class ResourceFolderEntry extends ResourceEntry {

    /**
     * 
     */
    private static final long serialVersionUID = -2765285570381475790L;
    private List<ResourceEntry> children;

    public ResourceFolderEntry(String logicalResourceName) throws IOException {
        super(logicalResourceName);
    }

    public List<ResourceEntry> getChildren() {
        return this.children;
    }

    public void addChildren(List<ResourceEntry> children) throws Exception {
        if (this.children == null) {
            this.children = new ArrayList<ResourceEntry>();
        }
        for (ResourceEntry e : children) {
            addChild(e);
        }
    }

    public void addChild(ResourceEntry child) throws Exception {
        if (this.children == null) {
            this.children = new ArrayList<ResourceEntry>();
        }
        String resolvedLRN = resolveChildName(child.getLRN());
        child.setLRN(resolvedLRN);
        this.children.add(child);
    }

    public void removeChildren(List<ResourceEntry> children) {
        if (this.children != null && !this.children.isEmpty()) {
            this.children.removeAll(children);
        }
    }

    public void removeAllChildren() {
        if (this.children != null && !this.children.isEmpty()) {
            this.children.clear();
        }
    }

    public boolean childExists(ResourceEntry child) {

        if (children != null && !children.isEmpty()) {
            for (ResourceEntry e : children) {
                if (SimpleCRCatalogue.compareEntries(e, child)) {
                    return true;
                }
            }
        }
        return false;
    }

    private String resolveChildName(String lrn) throws Exception {
        String theChildName;
        String parentLRN;
        String tmp;

        if (lrn.contains("/")) {
            String[] parts = lrn.split("/");
            if (parts != null && parts.length >= 1) {
                theChildName = parts[parts.length - 1];
                

                int endIndex = (lrn.length() - theChildName.length());
                parentLRN = lrn.substring(0, endIndex - 1);
                if (parentLRN.equals("")) {
                    parentLRN = "/";
                }

                if (parentLRN.endsWith("/") && parentLRN.length() > 1) {
                    parentLRN = parentLRN.substring(parentLRN.length() - 2,
                            parentLRN.length() - 1);
                }
                if(!parentLRN.startsWith("/")){
                    tmp ="/"+parentLRN;
                    parentLRN = tmp;
                }
                if (!parentLRN.equals(this.getLRN())) {
                    throw new Exception(
                            "Child path " + lrn + " is wrong. Added child's parent is: "
                            + parentLRN + " while parent should be: "
                            + getLRN());
                }
                return lrn;
            }
        }
        return getLRN() + "/" + lrn;
    }

    public IResourceEntry getChildByLRN(String name) {
        for (IResourceEntry r : children) {
            if (r.getLRN().equals(name)) {
                return r;
            }
        }
        return null;
    }

    public IResourceEntry getChildByUID(String uid1) {
        for (IResourceEntry r : children) {
            if (r.getUID().equals(uid1)) {
                return r;
            }
        }
        return null;
    }

    public Boolean hasChildren() {
        if (this.children == null || this.children.isEmpty()
                || children.size() <= 0) {
            return false;
        }
        return true;
    }
}
