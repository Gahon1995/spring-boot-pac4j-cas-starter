package com.gahon.pac4j.cas.properties;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Gahon
 * @date 2019/9/29
 */
@Data
@Slf4j
@ConfigurationProperties(prefix = "cas")
public class CasProperties {

    /**
     * project-url
     */
    @NotNull
    private String projectUrl;

    /**
     * cas server url
     */
    @NotNull
    private String serverUrlPrefix;

    /**
     * cas server login url, default: serverUrlPrefix+"login"
     */
    private String serverLoginUrl;

    /**
     * cas server logout url, default: serverUrlPrefix+"logout"
     */
    private String serverLogoutUrl;

    /**
     * project clientName, for cas callback
     */
    @NotNull
    private String clientName;

    private Logout logout = new Logout();

    private Callback callback = new Callback();

    /**
     * the path patterns that need login
     */
    private List<String> includePath = new ArrayList<>();

    /**
     * the path patterns that not need login
     */
    private List<String> excludePath = new ArrayList<>();


    @Data
    public static class Logout {
        /**
         * default url for logout callback
         */
        private String defaultUrl;

        /**
         * pattern that logout urls must match
         */
        private String logoutUrlPattern;

        /**
         * whether the application logout must be performed
         */
        private Boolean localLogout = false;

        /**
         * whether we must destroy the web session during the local logout
         */
        private Boolean destroySession = true;

        /**
         * whether the centralLogout must be performed
         */
        private Boolean centralLogout = true;

        /**
         * the URL path to the logout controller default: /logout
         */
        private String path = "/logout";
    }


    @Data
    public static class Callback {

        /**
         * default url after login if none was requested
         */
        private String defaultUrl;

        /**
         * whether multiple profiles should be kept
         */
        private Boolean multiProfile;

        /**
         * whether profile should be saved in session
         */
        private Boolean saveInSession;

        /**
         * whether the session must be renewed after login
         */
        private Boolean renewSession;

        /**
         * the default client
         */
        private String defaultClient;
        /**
         * the URL path to the callback controller that will receive the redirection request
         */
        private String path = "/callback";

    }

    public void init() {
        if (!StringUtils.endsWithIgnoreCase(serverUrlPrefix, "/")) {
            serverUrlPrefix = serverUrlPrefix + "/";
        }
        if (StringUtils.isEmpty(serverLoginUrl)) {
            serverLoginUrl = serverUrlPrefix + "login";
        }
        if (StringUtils.isEmpty(serverLogoutUrl)) {
            serverLogoutUrl = serverUrlPrefix + "logout";
        }

        if (StringUtils.endsWithIgnoreCase(projectUrl, "/")) {
            projectUrl = projectUrl.substring(0, projectUrl.length() - 2);
        }

        if (StringUtils.isEmpty(logout.defaultUrl)) {
            logout.defaultUrl = projectUrl;
        }

        if (includePath.isEmpty()) {
            includePath.add("/**");
        }

        excludePath.add(logout.path);
        excludePath.add(callback.path);

        log.debug("CasProperties: {}", this);

    }


}
