package ru.psychotech.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.psychotech.model.Client;
import ru.psychotech.repository.ClientRepository;

import java.security.Principal;

@Controller
public class MainController {

  @Autowired
  ClientRepository clientRepository;

  @GetMapping("/")
  public String main(Authentication authentication, Model model) {
    if (authentication != null) {
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      if (userDetails instanceof Client) {
        model.addAttribute("id",((Client) userDetails).getId());
      }
    }

    return "index";
  }

  @GetMapping("/login")
  public String login() {
    return "login";
  }

  @GetMapping("/client/{id}")
  public String client(Model model, @PathVariable Long id) {
    var optionalClient = clientRepository.get(id);
    optionalClient.ifPresent(client -> model.addAttribute("client", client));
    return "client";
  }
}
