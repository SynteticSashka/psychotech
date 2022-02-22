package ru.psychotech.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.psychotech.model.Client;

@Component
public class ControllerUtils {

  public static Long getCurrentClientId() {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    if (auth == null) {
      return null;
    }
    Object principal = auth.getPrincipal();
    Client user = (principal instanceof Client) ? (Client) principal : null;
    return user.getId();
  }
}
