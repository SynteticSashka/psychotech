package ru.psychotech.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {
  private Long id;
  private String name;
  private String lastname;
  private String email;
}
