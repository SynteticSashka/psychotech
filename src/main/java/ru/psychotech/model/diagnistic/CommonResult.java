package ru.psychotech.model.diagnistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult {
  private Long resultId;
  private Long diagId;
  private Long userId;
  private Map<Scale, Integer> result;
}
