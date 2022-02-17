package ru.psychotech.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import ru.psychotech.model.Client;
import ru.psychotech.model.Role;
import ru.psychotech.repository.ClientRepository;
import ru.psychotech.service.ClientService;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
  private final ClientService service;
  private final ClientRepository repository;

  Logger logger = LoggerFactory.getLogger(AdminController.class);

  @GetMapping("/")
  public String admin(Model model, Authentication authentication) {
    if (authentication != null) { // получение текущего пользователя (id)
      UserDetails userDetails = (UserDetails) authentication.getPrincipal();
      if (userDetails instanceof Client) {
        model.addAttribute("id",((Client) userDetails).getId());
      }
    }
    model.addAttribute("clients", service.getClients());
    return "admin";
  }

  @GetMapping("/edit/{id}")
  public String editForm(Model model, @PathVariable Long id) {
    var client = Optional.ofNullable(service.getClient(id));
    if (client.isPresent()) {
      model.addAttribute(client);
    }
    return "editForm";
  }

  @PostMapping("/edit")
  public String edit(@RequestBody MultiValueMap<String, String> formData, Model model) {
    Long id = Long.valueOf(formData.get("id").get(0));
    String name = formData.get("name").get(0);
    String lastname = formData.get("lastname").get(0);
    String role = formData.get("role").get(0);

    logger.info("Receive user for to edit {} ", formData);

    boolean hasError = false; // флаг, была ли найдена ошибка

    if (name == null) {
      model.addAttribute("nameError", "Имя - обязательное поле");
      hasError = true;
    }
    if (lastname == null) {
      model.addAttribute("lastnameError",  "Фамилия - обязательное поле");
      hasError = true;
    }

    if (hasError) { //если есть хотя бы одна ошибка, возращаем форму обратно
      model.addAttribute("hasError", true);
      model.addAttribute("lastname", lastname);
      model.addAttribute("name", name);

      return "registration";
    }

    var optionalClient = repository.get(id);
    if (optionalClient.isPresent()) {
      var client = optionalClient.get();
      client.setName(name);
      client.setLastname(lastname);
      client.setRole(Role.valueOf(role));
      repository.update(client);
    }

    return "redirect:/admin/";
  }

  @PostMapping("/remove")
  public String remove(@RequestParam Long id) {
    service.delete(id);
    return "redirect:/admin/";
  }
}
