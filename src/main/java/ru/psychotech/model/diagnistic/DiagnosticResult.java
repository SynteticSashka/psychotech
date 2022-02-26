package ru.psychotech.model.diagnistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DiagnosticResult {
  private String name;
  private String description;
  private List<String> recommendations;
  private List<ScaleWithValue> scales;
}
