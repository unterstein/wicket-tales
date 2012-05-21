package org.wickettales;

import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.IAjaxCallDecorator;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.WebPage;
import org.wickettales.decorator.BlockingAjaxRequestDecorator;

public class HomePage extends WebPage {
    private static final long serialVersionUID = 1L;

    public HomePage(final PageParameters parameters) {
        add(new Label("version", getApplication().getFrameworkSettings().getVersion()));
        AjaxLink<Void> blockingLink = new AjaxLink<Void>("blockingLink") {
            private static final long serialVersionUID = 6361089658947809022L;

            @Override
            public void onClick(AjaxRequestTarget target) {
                // do something reasonable
                System.out.println("ajax request was performed");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            @Override
            protected IAjaxCallDecorator getAjaxCallDecorator() {
                return new BlockingAjaxRequestDecorator();
            }
        };
        add(blockingLink);
    }
}
