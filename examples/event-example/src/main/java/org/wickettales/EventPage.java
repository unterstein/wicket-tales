package org.wickettales;

import java.util.Date;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

public class EventPage extends WebPage {
	private static final long serialVersionUID = 1L;
	
    public EventPage(final PageParameters parameters) {
    	Label clock = new Label("clock", new AbstractReadOnlyModel<String>() {
			@Override
			public String getObject() {
				return new Date().toString();
			}
		});
    	clock.setOutputMarkupId(true);
    	clock.add(new RefreshOnClickBehavior());
		add(clock);
		
		add(new RefreshLink("updateClockLink"));
    }
    
}
