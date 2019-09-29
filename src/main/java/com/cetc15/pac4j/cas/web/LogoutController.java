package com.cetc15.pac4j.cas.web;

import com.cetc15.pac4j.cas.properties.CasProperties;
import org.pac4j.core.config.Config;
import org.pac4j.core.context.JEEContext;
import org.pac4j.core.engine.DefaultLogoutLogic;
import org.pac4j.core.engine.LogoutLogic;
import org.pac4j.core.http.adapter.JEEHttpActionAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.pac4j.core.util.CommonHelper.assertNotNull;

/**
 * <p>This controller handles the (application + identity provider) logout process, based on the {@link #logoutLogic}.</p>
 *
 * <p>The configuration can be provided via property keys: <code>cas.logout.defaultUrl</code> (default logourl url),
 * <code>cas.logout.logoutUrlPattern</code> (pattern that logout urls must match), <code>cas.logout.localLogout</code> (whether the application logout must be performed)
 * <code>cas.logout.destroySession</code> (whether we must destroy the web session during the local logout), <code>cas.logout.centralLogout</code> (whether the centralLogout must be performed).
 * <code>cas.logout.path</code> (the URL path to the logout controller).</p>
 *
 * <p>Or it can be defined via setter methods: {@link #setDefaultUrl(String)}, {@link #setLogoutUrlPattern(String)}, {@link #setLocalLogout(Boolean)},
 * {@link #setDestroySession(Boolean)} and {@link #setCentralLogout(Boolean)}.</p>
 *
 * @author Jerome Leleu
 * @since 1.0.0
 */
@Controller
public class LogoutController {

    private LogoutLogic<Object, JEEContext> logoutLogic = new DefaultLogoutLogic<>();

    private String defaultUrl;

    private String logoutUrlPattern;

    private Boolean localLogout;

    private Boolean destroySession;

    private Boolean centralLogout;

    public LogoutController(CasProperties casProperties) {
        defaultUrl = casProperties.getLogout().getDefaultUrl();
        logoutUrlPattern = casProperties.getLogout().getLogoutUrlPattern();
        localLogout = casProperties.getLogout().getLocalLogout();
        destroySession = casProperties.getLogout().getDestroySession();
        centralLogout = casProperties.getLogout().getCentralLogout();
    }

    @Autowired
    private Config config;

    @RequestMapping("${cas.logout.path:/logout}")
    public void logout(final HttpServletRequest request, final HttpServletResponse response) {

        assertNotNull("logoutLogic", logoutLogic);
        assertNotNull("config", config);
        final JEEContext context = new JEEContext(request, response, config.getSessionStore());

        logoutLogic.perform(context, config, JEEHttpActionAdapter.INSTANCE, this.defaultUrl,
                this.logoutUrlPattern, this.localLogout, this.destroySession, this.centralLogout);
    }

    public String getDefaultUrl() {
        return this.defaultUrl;
    }

    public void setDefaultUrl(final String defaultUrl) {
        this.defaultUrl = defaultUrl;
    }

    public String getLogoutUrlPattern() {
        return logoutUrlPattern;
    }

    public void setLogoutUrlPattern(final String logoutUrlPattern) {
        this.logoutUrlPattern = logoutUrlPattern;
    }

    public LogoutLogic<Object, JEEContext> getLogoutLogic() {
        return logoutLogic;
    }

    public void setLogoutLogic(final LogoutLogic<Object, JEEContext> logoutLogic) {
        this.logoutLogic = logoutLogic;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(final Config config) {
        this.config = config;
    }

    public Boolean getLocalLogout() {
        return localLogout;
    }

    public void setLocalLogout(final Boolean localLogout) {
        this.localLogout = localLogout;
    }

    public Boolean getCentralLogout() {
        return centralLogout;
    }

    public void setCentralLogout(final Boolean centralLogout) {
        this.centralLogout = centralLogout;
    }

    public Boolean getDestroySession() {
        return destroySession;
    }

    public void setDestroySession(final Boolean destroySession) {
        this.destroySession = destroySession;
    }
}
