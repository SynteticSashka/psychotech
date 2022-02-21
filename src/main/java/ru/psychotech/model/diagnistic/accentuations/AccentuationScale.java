package ru.psychotech.model.diagnistic.accentuations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccentuationScale {
  private Long scaleId;
  private String description;
}
