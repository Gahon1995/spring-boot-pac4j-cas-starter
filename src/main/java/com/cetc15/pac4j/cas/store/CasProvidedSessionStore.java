package com.cetc15.pac4j.cas.store;

import org.pac4j.core.context.JEEContext;
import org.pac4j.core.util.CommonHelper;

import javax.servlet.http.HttpSession;

/**
 * @author Gahon
 */
public class CasProvidedSessionStore extends CasSessionStore {

    private final HttpSession session;

    public CasProvidedSessionStore(final HttpSession session) {
        CommonHelper.assertNotNull("session", session);
        this.session = session;
    }

    @Override
    protected HttpSession getHttpSession(final JEEContext context) {
        return session;
    }
}
