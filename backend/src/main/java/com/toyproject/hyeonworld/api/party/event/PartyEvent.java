package com.toyproject.hyeonworld.api.party.event;

import com.toyproject.hyeonworld.api.session.event.SessionEvent;
import com.toyproject.hyeonworld.common.event.CustomEvent;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 5.
 */
public sealed interface PartyEvent extends CustomEvent {
  long partyId();

  record Begin(long partyId) implements PartyEvent {}
}
