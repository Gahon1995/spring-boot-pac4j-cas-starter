package com.gahon.pac4j.cas.store;

import org.pac4j.core.context.JEEContext;
import org.pac4j.core.context.session.JEESessionStore;
import org.pac4j.core.context.session.SessionStore;

import javax.servlet.http.HttpSession;
import java.util.Optional;

/**
 * @author han
 */
public class CasSessionStore extends JEESessionStore {

    @Override
    public Optional<SessionStore<JEEContext>> buildFromTrackableSession(final JEEContext context, final Object trackableSession) {
        Optional optionalSession = (Optional) trackableSession;
        if (optionalSession.isPresent()) {
            return Optional.of(new CasProvidedSessionStore((HttpSession) optionalSession.get()));
        } else {
            return Optional.empty();
        }
    }
}
