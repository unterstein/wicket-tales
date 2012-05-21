package org.wickettales;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;

/**
 * Handles Events which have a {@link RefreshClick} as payload. Usually they are fired by a {@link RefreshLink}.
 * 
 * The Components to which this behavior is added are passed to {@link RefreshClick#refresh(Component...)}.
 * 
 * @author Michael Bruns
 */
public class RefreshOnClickBehavior extends Behavior {

    @Override
    public void onEvent(Component component, IEvent<?> event) {
        if (event.getPayload() != null && event.getPayload() instanceof RefreshClick) {
            ((RefreshClick) event.getPayload()).refresh(component);
        }
    }

}
