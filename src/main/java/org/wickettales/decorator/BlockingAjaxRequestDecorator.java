package org.wickettales.decorator;

import org.apache.wicket.Application;
import org.apache.wicket.Component;
import org.apache.wicket.ajax.IAjaxCallDecorator;

/**
 * Wicket itself provides exactly one concurrent AJAX connection from the
 * browser to the wicket {@link Application}. But when triggering the same AJAX
 * connection (e.g. an AJAX button) again, while this AJAX request is not
 * finished, the new trigger (e.g. AJAX button click) will be submitted, when
 * the last request is finished. This behavior is not wanted at any time.<br/>
 * Example:<br/>
 * A button provides functionality to buy something and needs (for any reason)
 * an AJAX request before the buy process could be completed. If this request
 * needs a longer time to be completed, the user could click again on the button
 * and trigger a new request (which he did not recognize). This second click
 * will be carried to the wicket {@link Application} when the first is finished.<br/>
 * 
 * This behavior is not wanted and the following implementation blocks this
 * behavior. Only one AJAX event can be performed concurrently on the added
 * component. AJAX events which are triggered during an other AJAX request of
 * the same component is running, are not accepted. <br/>
 * <br/>
 * 
 * <b>Important:</b> <br/>
 * You need jQuery to use this behavior! <br/>
 * <br/>
 * 
 * This behavior is implemented through temporarily storage of the current AJAX
 * request status "running" (true|false) as HTML data attribute at the according
 * DOM node.<br/>
 * <br/>
 * <b>Usage:</b> <br/>
 * 
 * <pre>
 * AjaxLink&lt;Void&gt; blockingLink = new AjaxLink&lt;Void&gt;(&quot;blockingLink&quot;) {
 *     private static final long serialVersionUID = 6361089658947809022L;
 * 
 *     &#064;Override
 *     public void onClick(AjaxRequestTarget target) {
 *         // do something reasonable
 *     }
 * 
 *     &#064;Override
 *     protected IAjaxCallDecorator getAjaxCallDecorator() {
 *         return new BlockingAjaxCallDecorator(); // &lt;&lt;--- This is important ;)
 *     }
 * };
 * add(blockingLink);
 * </pre>
 * 
 * @author <a href="unterstein@me.com">Johannes Unterstein</a>
 * 
 */
public class BlockingAjaxRequestDecorator implements IAjaxCallDecorator {
    private static final long serialVersionUID = -9138813452891716252L;

    @Override
    public CharSequence decorateScript(Component component, CharSequence script) {
        // 1.) if the current DOM node has no HTML data attribute "running",
        // the new AJAX request could be performed
        // 2.) if the current DOM node has an HTML data attribute "running", the
        // new AJAX request only be performed, if the value of this attribute is
        // "false".
        return "var r = $(this).data('running'); if(r==null||r==false) { "
                + "$(this).data('running', true); $(this).prop('disabled', true); "
                + script + "; return false; } else { return false; }";
    }

    @Override
    public CharSequence decorateOnFailureScript(Component component, CharSequence script) {
        // remove running HTML data attribute on failure case as well
        return decorateOnSuccessScript(component, script);
    }

    @Override
    public CharSequence decorateOnSuccessScript(Component component, CharSequence script) {
        // remove running HTML data attribute
        return script + "$(this).data('running', false); $(this).prop('disabled', false);";
    }

}
