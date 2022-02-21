package ru.psychotech.model.diagnistic.accentuations;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccentuationsResult {
  private Long resultId;
  private Long diagId;
  private Long userId;
  private Map<AccentuationScale, Integer> result;
}
