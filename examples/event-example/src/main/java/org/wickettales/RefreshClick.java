package org.wickettales;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

/**
 * This is the payload of Events fired by a {@link RefreshLink}.
 * 
 * Components which shall be refreshed can be added via {@link RefreshClick#refresh(Component...)}.
 * They will be added to the current AjaxRequestTarget.
 * 
 * @author Michael Bruns
 */
public class RefreshClick {

    private final AjaxRequestTarget target;

    public RefreshClick(AjaxRequestTarget target) {
        this.target = target;
    }

    /**
     * Adds the given Components to the current AjaxRequestTarget.
     * 
     * @param components
     *            the components to refresh
     */
    public void refresh(Component... components) {
        target.add(components);
    }

}
