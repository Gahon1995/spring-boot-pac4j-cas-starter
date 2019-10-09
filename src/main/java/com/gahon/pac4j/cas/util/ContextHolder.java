package com.gahon.pac4j.cas.util;


import org.pac4j.core.profile.BasicUserProfile;

/**
 * 线程内提供  Pac4jPrincipal 访问
 *
 * 同一线程内通过调用该类里边的方法能够在非controller层获取到
 * 当前登录的用户信息
 *
 * @author gahon
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
