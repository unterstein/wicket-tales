package org.wickettales;

import java.util.Date;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.model.AbstractReadOnlyModel;
import org.apache.wicket.request.mapper.parameter.PageParameters;

/**
 * This page demonstrates the loose coupling of components via Events.
 * 
 * It contains a generic {@link RefreshLink} which fires an Event with a {@link RefreshClick} as payload.
 * This Event is handled by a {@link RefreshOnClickBehavior}, which is added to two Labels displaying the current time and a counter.
 * Neither the {@link RefreshLink} knows of the Labels' existance, nor do the Labels know anything about the {@link RefreshLink}.
 * Thus, they are loosely coupled and can be moved to sub-components etc. without changing the behavior.
 * 
 * @author Michael Bruns
 */
public class EventPage extends WebPage {

    public EventPage(final PageParameters parameters) {
        // a clock on page level
        Label clock = new Label("clock", new AbstractReadOnlyModel<String>() {
            @Override
            public String getObject() {
                return new Date().toString();
            }
        });
        clock.setOutputMarkupId(true);
        clock.add(new RefreshOnClickBehavior());
        add(clock);

        // a counter in a child component
        WebMarkupContainer container = new WebMarkupContainer("container");
        Label counter = new Label("counter", new AbstractReadOnlyModel<String>() {
            private int numberOfCalls = 0;
            
            @Override
            public String getObject() {
                return String.valueOf(numberOfCalls++);
            }
        });
        counter.setOutputMarkupId(true);
        counter.add(new RefreshOnClickBehavior());
        container.add(counter);
        add(container);

        // one link to refresh both the clock and the counter
        add(new RefreshLink("refreshLink"));
    }

}
