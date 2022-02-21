package ru.psychotech.model.diagnistic.accentuations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.psychotech.model.diagnistic.DiagnosticType;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Accentuations {
  private Long id;
  private final DiagnosticType diagnosticType = DiagnosticType.TRUE_FALSE;
  private String name;
  private String description;
  private List<AccentuationScale> scales;
  private List<String> questions;
}
