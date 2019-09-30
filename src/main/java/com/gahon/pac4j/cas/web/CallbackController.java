package com.gahon.pac4j.cas.web;

import com.gahon.pac4j.cas.properties.CasProperties;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.engine.CallbackLogic;
import org.pac4j.core.engine.DefaultCallbackLogic;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.pac4j.core.util.CommonHelper.assertNotNull;

/**
 * <p>This controller finishes the login process for an indirect client, based on the {@link #callbackLogic}.</p>
 *
 * <p>The configuration can be defined via property keys: <code>cas.callback.defaultUrl</code> (default url after login if none was requested),
 * <code>cas.callback.multiProfile</code> (whether multiple profiles should be kept) and
 * <code>cas.callback.renewSession</code> (whether the session must be renewed after login).
 * <code>cas.callback.path</code> (the URL path to the callback controller that will receive the redirection request).</p>
 *
 * <p>Or it can be defined via setter methods: {@link #setDefaultUrl(String)}, {@link #setMultiProfile(Boolean)} and ({@link #setRenewSession(Boolean)}.</p>
 *
 * @author Jerome Leleu
 * @since 1.0.0
 */
@Controller
public class CallbackController {

    private CallbackLogic<Object, JEEContext> callbackLogic = new DefaultCallbackLogic<>();

    private String defaultUrl;

    private Boolean multiProfile;

    private Boolean saveInSession;

    private Boolean renewSession;

    private String defaultClient;

    @Autowired
    private Config config;


    public CallbackController(CasProperties casProperties) {
        defaultUrl = casProperties.getCallback().getDefaultUrl();
        multiProfile = casProperties.getCallback().getMultiProfile();
        saveInSession = casProperties.getCallback().getSaveInSession();
        renewSession = casProperties.getCallback().getRenewSession();
        defaultClient = casProperties.getCallback().getDefaultClient();
    }

    @RequestMapping("${cas.callback.path:/callback}")
    public void callback(final HttpServletRequest request, final HttpServletResponse response) {

        assertNotNull("callbackLogic", callbackLogic);
        assertNotNull("config", config);
        final JEEContext context = new JEEContext(request, response, config.getSessionStore());
        callbackLogic.perform(context, config, JEEHttpActionAdapter.INSTANCE, this.defaultUrl,
                this.saveInSession, this.multiProfile, this.renewSession,
                this.defaultClient);
    }

    @RequestMapping("${cas.callback.path/{cn}:/callback/{cn}}")
    public void callbackWithClientName(final HttpServletRequest request, final HttpServletResponse response, @PathVariable("cn") final String cn) {

        callback(request, response);
    }

    public String getDefaultUrl() {
        return defaultUrl;
    }

    public void setDefaultUrl(final String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public CallbackLogic<Object, JEEContext> getCallbackLogic() {
        return callbackLogic;
    }

    public void setCallbackLogic(final CallbackLogic<Object, JEEContext> callbackLogic) {
        this.callbackLogic = callbackLogic;
    }

    public Boolean getMultiProfile() {
        return multiProfile;
    }

    public void setMultiProfile(final Boolean multiProfile) {
        this.multiProfile = multiProfile;
    }

    public Boolean getRenewSession() {
        return renewSession;
    }

    public void setRenewSession(final Boolean renewSession) {
        this.renewSession = renewSession;
    }

    public String getDefaultClient() {
        return defaultClient;
    }

    public void setDefaultClient(final String client) {
        this.defaultClient = client;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(final Config config) {
        this.config = config;
    }
}
