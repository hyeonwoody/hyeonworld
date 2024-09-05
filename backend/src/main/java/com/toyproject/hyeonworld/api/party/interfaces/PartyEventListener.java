package com.toyproject.hyeonworld.api.party.interfaces;

import com.toyproject.hyeonworld.api.party.event.PartyEvent;
import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public interface PartyEventListener extends EventListener {
  void handlePartyEvent(PartyEvent event);

  void handlePartyBeginEvent(PartyEvent.Begin event);
}
