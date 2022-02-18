package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.psychotech.repository.ClientRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {
  private final ClientRepository clientRepository;

  @Override
  public UserDetails loadUserByUsername(String username) {
    return clientRepository.findByEmail(username).get();
  }
}
