package ru.psychotech.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewClient {
  private String name;
  private String lastname;
  private String email;
  private String password;
}
