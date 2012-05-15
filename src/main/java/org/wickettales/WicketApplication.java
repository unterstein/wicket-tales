package org.wickettales;

import org.apache.wicket.protocol.http.WebApplication;
import org.wickettales.request.mapper.PageParameterAwareMountedMapper;

/**
 * Application object for your web application. If you want to run this
 * application without deploying, run the Start class.
 * 
 * @see org.wickettales.Start#main(String[])
 */
public class WicketApplication extends WebApplication {
    /**
     * @see org.apache.wicket.Application#getHomePage()
     */
    @Override
    public Class<HomePage> getHomePage() {
        return HomePage.class;
    }

    /**
     * @see org.apache.wicket.Application#init()
     */
    @Override
    public void init() {
        super.init();

        // Use our own mapper to mount the mountpage
        mount(new PageParameterAwareMountedMapper("Home", HomePage.class));
    }
}
