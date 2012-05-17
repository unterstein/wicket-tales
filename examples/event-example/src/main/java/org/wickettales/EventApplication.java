package org.wickettales;

import org.apache.wicket.protocol.http.WebApplication;

/**
 * Basic application.
 * 
 * @author Michael Bruns
 */
public class EventApplication extends WebApplication {

    @Override
    public Class<EventPage> getHomePage() {
        return EventPage.class;
    }

    @Override
    public void init() {
        super.init();
    }
}
