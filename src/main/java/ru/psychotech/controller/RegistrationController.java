package ru.psychotech.controller;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.psychotech.model.Client;
import ru.psychotech.model.dto.NewClient;
import ru.psychotech.repository.ClientRepository;

import java.util.regex.Pattern;

@Controller
@RequiredArgsConstructor
public class RegistrationController {
  private final ClientRepository clientRepository;

  Logger logger = LoggerFactory.getLogger(RegistrationController.class);

  @GetMapping("/registration")
  public String getForm(Model model) {
    model.addAttribute("userForm", new Client());

    return "registration";
  }

  @PostMapping("/registration")
  public String addUser(@RequestBody MultiValueMap<String, String> formData, Model model) {

    String email = formData.get("email").get(0);
    String name = formData.get("name").get(0);
    String lastname = formData.get("lastname").get(0);
    String password = formData.get("password").get(0);
    String passwordConfirm = formData.get("passwordConfirm").get(0);

    logger.info("Receive user for registration {} ", formData);

    boolean hasError = false; // флаг, была ли найдена ошибка

    if (email == null) {
      model.addAttribute("emailError","Email - обязательное поле");
      hasError = true;
    }
    if (name == null) {
      model.addAttribute("nameError", "Имя - обязательное поле");
      hasError = true;
    }
    if (lastname == null) {
      model.addAttribute("lastnameError",  "Фамилия - обязательное поле");
      hasError = true;
    }
    if (password == null) {
      model.addAttribute("passwordError", "Пароль - обязательное поле");
      hasError = true;
    }
    if (passwordConfirm == null) {
      model.addAttribute("passwordConfirmError",  "Повтор пароля - обязательное поле");
      hasError = true;
    }
    if (email != null){
      if (!Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}\\b").matcher(email).matches()) {
        model.addAttribute ("emailError",  "Введите корректный email");
        hasError = true;
      }
    }
    if (password != null) {
      if (password.length() < 8) {
        model.addAttribute("passwordError",  "Длина пароля должна быть 8 и более символов");
        hasError = true;
      }
    }
    if (password != null && passwordConfirm != null) {
      if (!password.equals(passwordConfirm)) {
        model.addAttribute("passwordConfirmError",  "Введенные пароли должны совпадать");
        hasError = true;
      }
    }

    if (hasError) { //если есть хотя бы одна ошибка, возращаем форму обратно
      model.addAttribute("hasError", true);
      model.addAttribute("email", email);
      model.addAttribute("lastname", lastname);
      model.addAttribute("name", name);
      model.addAttribute("password", password);
      model.addAttribute("passwordConfirm", passwordConfirm);

      return "registration";
    }

    var client = new NewClient(name, lastname, email, password);

    if (clientRepository.create(client).isEmpty()) { // если пользователь уже существует
      model.addAttribute("emailAlreadyExistError", "Пользователь с таким именем уже существует");
      model.addAttribute("hasError", true);
      return "registration";
    }

    return "redirect:/";
  }
}
