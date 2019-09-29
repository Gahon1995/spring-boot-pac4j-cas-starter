package com.cetc15.pac4j.cas.util;


import org.pac4j.core.profile.BasicUserProfile;

/**
 * 线程内提供  Pac4jPrincipal 访问
 *
 * @author ssss
 */
public class ContextHolder {

    private static final ThreadLocal<BasicUserProfile> THREAD_LOCAL = new ThreadLocal<>();

    public static void setProfile(final BasicUserProfile profile) {
        THREAD_LOCAL.set(profile);
    }

    public static BasicUserProfile getProfile() {
        return THREAD_LOCAL.get();
    }

    public static void clear() {
        THREAD_LOCAL.remove();
    }

    public static Object getAttribute(String key) {
        final BasicUserProfile profile = THREAD_LOCAL.get();
        if (profile != null) {
            return profile.getAttribute(key);
        }
        return null;
    }

    public static Object getAttributes() {
        final BasicUserProfile profile = THREAD_LOCAL.get();
        if (profile != null) {
            return profile.getAttributes();
        }
        return null;
    }
}
