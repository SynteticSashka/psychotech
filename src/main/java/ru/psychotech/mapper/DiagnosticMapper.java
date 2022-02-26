package ru.psychotech.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import jooq_generated.tables.pojos.Scales;
import jooq_generated.tables.pojos.DiagnosticResults;
import org.springframework.stereotype.Component;
import ru.psychotech.model.diagnistic.DiagnosticDto;
import ru.psychotech.model.diagnistic.DiagnosticType;
import ru.psychotech.model.diagnistic.ScaleWithValue;

@Component
public class DiagnosticMapper {

  public List<DiagnosticDto> mapDiagnosticList(List<jooq_generated.tables.pojos.Diagnostic> list) {
    return list.stream().map(this::mapDiagnostic).collect(Collectors.toList());
  }

  public DiagnosticDto mapDiagnostic(jooq_generated.tables.pojos.Diagnostic diagnostic) {
    return DiagnosticDto.builder()
        .id(diagnostic.getId())
        .diagnosticType(mapType(diagnostic.getType()))
        .name(diagnostic.getName())
        .description(diagnostic.getDescription())
        .build();
  }

  public DiagnosticDto mapDiagnosticWithQuestions(
      jooq_generated.tables.pojos.Diagnostic diagnostic,
      List<String> questions) {
    return DiagnosticDto.builder()
        .id(diagnostic.getId())
        .diagnosticType(mapType(diagnostic.getType()))
        .name(diagnostic.getName())
        .description(diagnostic.getDescription())
        .questions(questions)
        .build();
  }

  public DiagnosticType mapType(String type) {
    switch (type) {
      case "TRUE_FALSE": return DiagnosticType.TRUE_FALSE;
      case "MULTIPLE_CHOICE": return DiagnosticType.MULTIPLE_CHOICE;
      default: return DiagnosticType.MULTIPLE_CHOICE;
    }
  }

  public List<ScaleWithValue> mapScaleList(List<Scales> list) {
    return list.stream().map(this::mapScale).collect(Collectors.toList());
  }

  private ScaleWithValue mapScale(Scales scale) {
    return ScaleWithValue.builder()
        .scaleId(scale.getId())
        .name(scale.getName())
        .description(scale.getDescription())
        .detailedDescription(scale.getDetailedDescription())
        .build();
  }

  public List<ScaleWithValue> mapAccentuationsResults(List<DiagnosticResults> results, List<Scales> scales) {
    List<ScaleWithValue> list = new ArrayList<>();
    for (DiagnosticResults dr : results) {
      var scale = scales.stream().filter(e -> e.getId().equals(dr.getScaleId())).findFirst();
      scale.ifPresent(value -> list.add(new ScaleWithValue(
          value.getId(),
          value.getName(),
          value.getDescription(),
          value.getDetailedDescription(),
          dr.getResult(),
          ""
      )));
    }
    return list;
  }

}
