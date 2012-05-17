package org.wickettales;

import org.apache.wicket.Component;
import org.apache.wicket.ajax.AjaxRequestTarget;

public class RefreshClick {

	private final AjaxRequestTarget target;

	public RefreshClick(AjaxRequestTarget target) {
		this.target = target;
	}
	
	public void update(Component... components) {
		target.add(components);
	}

}
