package com.toyproject.hyeonworld.api.session.interfaces;

import com.toyproject.hyeonworld.api.session.event.LoginEvent;
import com.toyproject.hyeonworld.common.event.EventListener;

/**
 * @author : hyeonwoody@gmail.com
 * @since : 24. 9. 4.
 */
public interface LoginEventListener extends EventListener {

  void registerWaitingList(LoginEvent event);
  void registerSse(LoginEvent event);
}
