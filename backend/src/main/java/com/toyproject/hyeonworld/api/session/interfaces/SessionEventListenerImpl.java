package com.toyproject.hyeonworld.api.session.interfaces;

import static org.springframework.transaction.event.TransactionPhase.AFTER_COMMIT;

import com.toyproject.hyeonworld.api.session.event.GameInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.GameOutSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogInSessionEvent;
import com.toyproject.hyeonworld.api.session.event.LogOutSessionEvent;
import com.toyproject.hyeonworld.api.sse.application.SsePartyFacade;
import com.toyproject.hyeonworld.api.sse.domain.SseService;
import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import java.util.Map;
import java.util.function.Consumer;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
@Component
@RequiredArgsConstructor
public class SessionEventListenerImpl implements SessionEventListener {

    private final SsePartyFacade ssePartyFacade;

    private final Map<Class<? extends SessionEvent>, Consumer<SessionEvent>>
            eventHandlers = Map.of(
            LogInSessionEvent.class, this::handleGameInSessionEvent,
            GameInSessionEvent.class, this::handleGameInSessionEvent,
            GameOutSessionEvent.class, this::handleGameOutSessionEvent,
            LogOutSessionEvent.class, this::handleLogoutSessionEvent
    );

    @Override
    @Async
    @TransactionalEventListener(phase = AFTER_COMMIT)
    public void handleSessionEvent(SessionEvent event) {
        eventHandlers.getOrDefault(event.getClass(), this::handleUnknownEvent).accept(event);
    }

    @Override
    public void handleLoginSessionEvent(SessionEvent event) {
        LogInSessionEvent logInSessionEvent = (LogInSessionEvent) event;
        ssePartyFacade.logIn(logInSessionEvent.relationType(), logInSessionEvent.userId(),
                logInSessionEvent.userName());
    }

    @Override
    public void handleGameOutSessionEvent(SessionEvent event) {
        GameOutSessionEvent gameOutSessionEvent = (GameOutSessionEvent) event;
        ssePartyFacade.gameOut(gameOutSessionEvent.relationType(), gameOutSessionEvent.userId(),
                gameOutSessionEvent.userName());
    }

    @Override
    public void handleGameInSessionEvent(SessionEvent event) {
        GameInSessionEvent gameInSessionEvent = (GameInSessionEvent) event;
        ssePartyFacade.gameIn(gameInSessionEvent.relationType(), gameInSessionEvent.userId(),
                gameInSessionEvent.userName());
    }

    @Override
    public void handleLogoutSessionEvent(SessionEvent event) {
        LogOutSessionEvent logOutSessionEvent = (LogOutSessionEvent) event;
        ssePartyFacade.logOut(logOutSessionEvent.relationType(), logOutSessionEvent.userId(),
                logOutSessionEvent.userName());
    }

    private void handleUnknownEvent(SessionEvent event) {
        throw new IllegalArgumentException("Unexpected event type: " + event.getClass());
    }

}
