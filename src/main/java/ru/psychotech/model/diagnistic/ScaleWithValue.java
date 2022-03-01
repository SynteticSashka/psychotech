package ru.psychotech.model.diagnistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ScaleWithValue implements Comparable<ScaleWithValue> {
  private Long scaleId;
  private String name;
  private String description;
  private String detailedDescription;
  private Integer value;
  private String comment;

  @Override
  public int compareTo(ScaleWithValue s) {
    return s.getValue() - this.getValue();
  }
}
