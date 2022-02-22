package ru.psychotech.mapper;

import org.springframework.stereotype.Component;
import ru.psychotech.model.diagnistic.DiagnosticType;
import ru.psychotech.model.diagnistic.Scale;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiagnosticMapper {

  public DiagnosticType mapType(String type) {
    switch (type) {
      case "TRUE_FALSE": return DiagnosticType.TRUE_FALSE;
      case "MULTIPLE_CHOICE": return DiagnosticType.MULTIPLE_CHOICE;
      default: return DiagnosticType.MULTIPLE_CHOICE;
    }
  }

  public List<Scale> mapScaleList(List<jooq_generated.tables.pojos.Scales> list) {
    return list.stream().map(this::mapScale).collect(Collectors.toList());
  }

  public Scale mapScale(jooq_generated.tables.pojos.Scales scale) {
    return Scale.builder()
        .name(scale.getName())
        .description(scale.getDescription())
        .build();
  }
}
