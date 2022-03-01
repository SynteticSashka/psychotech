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
import java.util.stream.Collectors;

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

  // Система рекомендаций
  private List<String> getRecommendations(List<ScaleWithValue> list) {
    List<String> recommendations = new ArrayList<>();
    List<String> fromDB = repository.getRecommendationsText(diagnosticId);
    // Большинство показателей меньше или равно 6
    if (list.stream().filter(r -> r.getValue() <= 6).count() >= 8) {
      recommendations.add(fromDB.get(0));
    }
    // Более 5 показателей 19+ баллов
    if (list.stream().filter(r -> r.getValue() >= 19).count() >= 5) {
      recommendations.add(fromDB.get(1));
    }
    // Гипертимность, циклотимность и демонстративность <=7
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add(fromDB.get(2));
    }
    // Гипертимность, циклотимность и демонстративность >=18
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add(fromDB.get(3));
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) <=7
    if (list.stream()
        .filter(r -> r.getScaleId() == 2 || r.getScaleId() == 4 || r.getScaleId() == 7 || r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add(fromDB.get(4));
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) >=18
    if (list.stream()
        .filter(r -> r.getScaleId() == 2 || r.getScaleId() == 4 || r.getScaleId() == 7 || r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add(fromDB.get(5));
    }
    // Демонстративность и гипертимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(fromDB.get(6));
    }
    // Педантичность и гипертимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(fromDB.get(7));
    }
    // Педантичность и дистимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 6)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(fromDB.get(8));
    }
    // Педантичность и тревожность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 7)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add(fromDB.get(9));
    }
    // Гипертимность и дистимность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 5 || r.getScaleId() == 6)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(10));
    }
    // Гипертимность 16+ дистимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() >= 16) || (r.getScaleId() == 6 && r.getValue() <= 8))
        .count() >= 2) {
      recommendations.add(fromDB.get(11));
    }
    // Дистимность 12+ гипертимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8) || (r.getScaleId() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(fromDB.get(12));
    }
    // Дистимность и гипертимность 8-, циклотимность 8+, застревание 15+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 6 && r.getValue() <= 8)
            || (r.getScaleId() == 10 && r.getValue() >= 8)
            || (r.getScaleId() == 2 && r.getValue() >= 15)
        ).count() >= 4) {
      recommendations.add(fromDB.get(13));
    }
    // Дистимность и гипертимность 8-, циклотимность 8+, застревание 15+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 6 && r.getValue() <= 8)
            || (r.getScaleId() == 9 && r.getValue() >= 8)
            || (r.getScaleId() == 7 && r.getValue() >= 15)
        ).count() >= 4) {
      recommendations.add(fromDB.get(14));
    }
    // Гипертимность и циклотимность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(15));
    }
    // Гипертимность 16+ циклотимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() >= 16) || (r.getScaleId() == 10 && r.getValue() <= 8))
        .count() >= 2) {
      recommendations.add(fromDB.get(16));
    }
    // Циклотимность 16+ гипертимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() <= 8) || (r.getScaleId() == 5 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(fromDB.get(17));
    }
    // Циклотимность и гипертимность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(18));
    }
    // Дистимность 12+ циклотимность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() >= 16) || (r.getScaleId() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(fromDB.get(19));
    }
    // Дистимность 12+ циклотимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() <= 8) || (r.getScaleId() == 6 && r.getValue() >= 12))
        .count() >= 2) {
      recommendations.add(fromDB.get(20));
    }
    // Дистимность 6- циклотимность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() >= 16) || (r.getScaleId() == 6 && r.getValue() <= 6))
        .count() >= 2) {
      recommendations.add(fromDB.get(21));
    }
    // Дистимность 6- циклотимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() <= 8) || (r.getScaleId() == 6 && r.getValue() <= 6))
        .count() >= 2) {
      recommendations.add(fromDB.get(22));
    }
    // Экзальтированность и циклотимность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 10)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(23));
    }
    // Экзальтированность 16+ циклотимность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 8 && r.getValue() <= 8) || (r.getScaleId() == 10 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(fromDB.get(24));
    }
    // Экзальтированность и циклотимность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 10)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(25));
    }
    // Экзальтированность и эмотивность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(26));
    }
    // Экзальтированность 8- эмотивность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 8 && r.getValue() <= 8) || (r.getScaleId() == 9 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(fromDB.get(27));
    }
    // Экзальтированность 16+ эмотивность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 9 && r.getValue() <= 8) || (r.getScaleId() == 8 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(fromDB.get(28));
    }
    // Экзальтированность и эмотивность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(29));
    }
    // Гипертимность, эмотивность тревожность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 5 || r.getScaleId() == 7 || r.getScaleId() == 9)
        .filter(r -> r.getValue() >= 16)
        .count() >= 3) {
      recommendations.add(fromDB.get(30));
    }
    // Гипертимность, эмотивность 16+, тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 3) {
      recommendations.add(fromDB.get(31));
    }
    // Гипертимность, эмотивность , дистимность, застревание 16+, циклотимность и воздубимость 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 10 && r.getValue() <= 8)
            || (r.getScaleId() == 4 && r.getValue() <= 8)
        )
        .count() >= 6) {
      recommendations.add(fromDB.get(32));
    }
    // Гипертимность 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() <= 8) || (r.getScaleId() == 5 && r.getValue() >= 16))
        .count() >= 2) {
      recommendations.add(fromDB.get(33));
    }
    // Гипертимность 8- тревожность 16+, дистимность 12+ застревание 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 10 && r.getValue() >= 16)
        )
        .count() >= 5) {
      recommendations.add(fromDB.get(34));
    }

    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 4 && r.getValue() <= 10)
            || (r.getScaleId() == 8 && r.getValue() <= 10)
        )
        .count() >= 5) {
      recommendations.add(fromDB.get(35));
    }

    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
        )
        .count() >= 4) {
      recommendations.add(fromDB.get(36));
    }

    if (list.stream()
        .filter(r -> (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 1 && r.getValue() >= 15)
            || (r.getScaleId() == 8 && r.getValue() >= 15)
        )
        .count() >= 4) {
      recommendations.add(fromDB.get(37));
    }
    if (list.stream()
        .filter(r -> r.getScaleId() == 5 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(38));
    }
    // Дистимность 12+ тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(39));
    }
    // Дистимность 12+ тревожность 8+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(40));
    }
    // Дистимность 6- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 6 && r.getValue() <= 6)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(41));
    }
    // Дистимность 6- тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 6 && r.getValue() <= 6)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(42));
    }
    // Циклотимность и тревожность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 10 || r.getScaleId() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(43));
    }
    // Циклотимность 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 10 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(44));
    }
    // Циклотимность 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 10 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(45));
    }
    // Циклотимность и тревожность 8+
    if (list.stream()
        .filter(r -> r.getScaleId() == 10 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(46));
    }
    // Эмотивность и тревожность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 9 || r.getScaleId() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(47));
    }
    // Эмотивность 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 9 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(48));
    }
    // Эмотивность 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(49));
    }
    // Эмотивность и тревожность 8+
    if (list.stream()
        .filter(r -> r.getScaleId() == 9 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(50));
    }
    // Экзальтированность и тревожность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(51));
    }
    // Экзальтированность 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 8 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(52));
    }
    // Экзальтированность 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 8 && r.getValue() <= 8)
            || (r.getScaleId() == 5 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
        )
        .count() >= 4) {
      recommendations.add(fromDB.get(53));
    }
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 8 && r.getValue() <= 8)
            || (r.getScaleId() == 6 && r.getValue() >= 12)
            || (r.getScaleId() == 5 && r.getValue() <= 8)
            || (r.getScaleId() == 9 && r.getValue() <= 8)
            || (r.getScaleId() == 4 && r.getValue() <= 8)
        )
        .count() >= 6) {
      recommendations.add(fromDB.get(54));
    }
    // Экзальтированность и тревожность 8+
    if (list.stream()
        .filter(r -> r.getScaleId() == 8 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(55));
    }
    // Застревание и тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
            || (r.getScaleId() == 10 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() <=10)
        )
        .count() >= 5) {
      recommendations.add(fromDB.get(56));
    }
    if (list.stream()
        .filter(r -> (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() <= 8)
            || (r.getScaleId() == 10 && r.getValue() <= 8)
            || (r.getScaleId() == 6 && r.getValue() >=10)
        )
        .count() >= 5) {
      recommendations.add(fromDB.get(57));
    }
    // Застревание 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(58));
    }
    // Застревание 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 2 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(59));
    }
    // Застревание и тревожность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 2 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(60));
    }
    // Педантичность и тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 3 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 5 && r.getValue() >= 16)
            || (r.getScaleId() == 9 && r.getValue() >= 16)
        )
        .count() >= 4)  {
      recommendations.add(fromDB.get(61));
    }
    if (list.stream()
        .filter(r -> (r.getScaleId() == 3 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 2 && r.getValue() >= 16)
            || (r.getScaleId() == 6 && r.getValue() >= 10)
        )
        .count() >= 4) {
      recommendations.add(fromDB.get(62));
    }
    // Педантичность 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 3 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(63));
    }
    // Педантичность и тревожность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(64));
    }
    // Демонстративность и тревожность 16+
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 7)
        .filter(r -> r.getValue() >= 16)
        .count() >= 2) {
      recommendations.add(fromDB.get(65));
    }
    // Тревожность 8- демонстративность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 1 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(66));
    }
    // Демонстративность 8-тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 1 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(67));
    }
    // Демонстративность и тревожность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(68));
    }
    // Возбудимость и тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 4 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 2 && r.getValue() >= 16)
        )
        .count() >= 3) {
      recommendations.add(fromDB.get(69));
    }
    if (list.stream()
        .filter(r -> (r.getScaleId() == 4 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 2 && r.getValue() <= 8)
        )
        .count() >= 3) {
      recommendations.add(fromDB.get(70));
    }
    // Возбудимость 16+ тревожность 8-
    if (list.stream()
        .filter(r -> (r.getScaleId() == 4 && r.getValue() >= 16)
            || (r.getScaleId() == 7 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(71));
    }
    // Возбудимость 8- тревожность 16+
    if (list.stream()
        .filter(r -> (r.getScaleId() == 7 && r.getValue() >= 16)
            || (r.getScaleId() == 4 && r.getValue() <= 8)
        )
        .count() >= 2) {
      recommendations.add(fromDB.get(72));
    }
    // Возбудимость и тревожность 8-
    if (list.stream()
        .filter(r -> r.getScaleId() == 4 || r.getScaleId() == 7)
        .filter(r -> r.getValue() <= 8)
        .count() >= 2) {
      recommendations.add(fromDB.get(73));
    }
    return recommendations;
  }

}
