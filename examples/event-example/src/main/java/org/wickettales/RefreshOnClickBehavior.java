package org.wickettales;

import org.apache.wicket.Component;
import org.apache.wicket.behavior.Behavior;
import org.apache.wicket.event.IEvent;

public class RefreshOnClickBehavior extends Behavior {

	@Override
	public void onEvent(Component component, IEvent<?> event) {
		if (event.getPayload() != null && event.getPayload() instanceof RefreshClick) {
			((RefreshClick) event.getPayload()).update(component);
		}
	}

}
