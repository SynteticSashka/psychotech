package ru.psychotech.model.client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientSummaryDto {
  private Long clientId;
  private Integer energyLevel;
}
