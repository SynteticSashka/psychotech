package ru.psychotech.calculator;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.psychotech.mapper.DiagnosticMapper;
import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.ScaleWithValue;
import ru.psychotech.repository.DiagnosticRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

@Component
@Transactional
@RequiredArgsConstructor
public class AccentuationCalculator {
  private final DiagnosticRepository repository;
  private final DiagnosticMapper diagnosticMapper;
  private final Long diagnosticId = 1L;

  public DiagnosticResult getResults(Long clientId) {
    var diag = repository.getDiagnostic(diagnosticId);
    if (diag.isEmpty()) return null;

    String name = diag.get().getName();
    String description = diag.get().getDescription();
    var scalesAndValues = diagnosticMapper.mapAccentuationsResults(
        repository.getResults(clientId, diagnosticId), repository.getScales(diagnosticId));

    Collections.sort(scalesAndValues);

    var recommendations = getRecommendations(scalesAndValues);

    return new DiagnosticResult(name, description, recommendations, scalesAndValues);
  }

  private List<String> getRecommendations(List<ScaleWithValue> list) {
    List<String> recommendations = new ArrayList<>();
    return recommendations;
  }

  public void calculateAndWrite(Long clientId, List<Integer> answers) {
    List<Long> completed = repository.getCompleted(clientId);

    if (!completed.contains(diagnosticId)) {
      SortedMap<Long, Integer> result = new TreeMap<>(Comparator.comparingLong(o -> o));
      List<ScaleWithValue> scales = diagnosticMapper.mapScaleList(repository.getScales(diagnosticId));

      for (ScaleWithValue scale : scales) {
        Long scaleId = scale.getScaleId();
        result.put(scaleId, getScaleValue(scaleId, answers));
      }

      repository.saveResult(clientId, diagnosticId, result);
    }
  }

  private Integer getScaleValue(Long scaleId, List<Integer> answers) {
    switch (scaleId.intValue()) {
      case 1:
        return 2 * (answers.get(6)
            + answers.get(18)
            + answers.get(21)
            + answers.get(28)
            + answers.get(40)
            + answers.get(43)
            + answers.get(62)
            + answers.get(65)
            + answers.get(72)
            + answers.get(84)
            + answers.get(87)
            - answers.get(5));
      case 2:
        return 2 * (answers.get(1)
            + answers.get(14)
            + answers.get(23)
            + answers.get(33)
            + answers.get(36)
            + answers.get(55)
            + answers.get(67)
            + answers.get(77)
            - answers.get(11)
            - answers.get(45)
            - answers.get(58));
      case 3:
        return 2 * (answers.get(3)
            + answers.get(13)
            + answers.get(16)
            + answers.get(25)
            + answers.get(38)
            + answers.get(47)
            + answers.get(57)
            + answers.get(60)
            + answers.get(69)
            + answers.get(79)
            + answers.get(82)
            - answers.get(35));
      case 4:
        return 3 * (answers.get(7)
            + answers.get(19)
            + answers.get(29)
            + answers.get(41)
            + answers.get(51)
            + answers.get(63)
            + answers.get(72)
            + answers.get(85));
      case 5:
        return 3 * (answers.get(0)
            + answers.get(10)
            + answers.get(22)
            + answers.get(32)
            + answers.get(44)
            + answers.get(54)
            + answers.get(66)
            + answers.get(76));
      case 6:
        return 3 * (answers.get(8)
            + answers.get(20)
            + answers.get(42)
            + answers.get(74)
            + answers.get(86)
            - answers.get(30)
            - answers.get(52)
            - answers.get(64));
      case 7:
        return 3 * (answers.get(15)
            + answers.get(26)
            + answers.get(37)
            + answers.get(48)
            + answers.get(59)
            + answers.get(70)
            + answers.get(81)
            - answers.get(4));
      case 8:
        return 6 * (answers.get(9)
            + answers.get(31)
            + answers.get(53)
            + answers.get(75));
      case 9:
        return 3 * (answers.get(2)
            + answers.get(12)
            + answers.get(34)
            + answers.get(46)
            + answers.get(56)
            + answers.get(68)
            + answers.get(78));
      case 10:
        return 3 * (answers.get(5)
            + answers.get(17)
            + answers.get(27)
            + answers.get(39)
            + answers.get(49)
            + answers.get(61)
            + answers.get(71)
            + answers.get(83)
            - answers.get(24));
      default: return null;
    }
  }

}
