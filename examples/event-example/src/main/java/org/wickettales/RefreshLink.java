package org.wickettales;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.model.IModel;

public class RefreshLink extends AjaxLink<String> {

	public RefreshLink(String id, IModel<String> model) {
		super(id, model);
	}

	public RefreshLink(String id) {
		super(id);
	}

	@Override
	public void onClick(AjaxRequestTarget target) {
		send(getParent(), Broadcast.DEPTH, new RefreshClick(target));
	}

}
