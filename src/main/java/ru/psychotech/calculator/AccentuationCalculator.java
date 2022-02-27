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
    // Большинство показателей меньше или равно 6
    if (list.stream().filter(r -> r.getValue() <= 6).count() >= 8) {
      recommendations.add("Чаще всего такое сочетание показателей бывает у человека, который старается показать себя социально нормативным, «хорошим», как ему это кажется. Обычно такие люди демонстрируют сниженную самокритичность, ведут себя претенциозно, неискренни. \n" +
          "Так же, подобные результаты может дать пассивный человек, который старается быть неприметным и не стремится к высоким достижениям. Такой человек вряд ли станет хозяином своей судьбы, лидером в коллективе, предпринимателем или борцом за идеи. Скорее, он окунется в мистику, веру в Бога, чем станет решительно менять свою судьбу. Исследования позволяют утверждать, что подобные люди не могут противостоять трудным жизненным обстоятельствам.");
    }
    // Более 5 показателей 19+ баллов
    if (list.stream().filter(r -> r.getValue() >= 19).count() >= 5) {
      recommendations.add("Чаще всего такое сочетание показателей бывает у по-настоящему яркой личности, но обладающей многими «острыми» углами. Если при этом отдельные акцентуации достигли отметки в 22 балла – то могут быть проблемы в общении с другими людьми из-за вашей яркости и бескомпромиссности.");
    }
    // Гипертимность, циклотимность и демонстративность <=7
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add("Ваши результаты свидетельствуют о недостатке энергетических ресурсов психики, необходимых для активной деятельности. Это может дополнительно усиливаться флегматическим или меланхолическим темпераментом.");
    }
    // Гипертимность, циклотимность и демонстративность >=18
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5 || r.getScaleId() == 10)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add("Ваши результаты говорят, что вы - личность с мощными жизненными силами. Это может дополнительно усиливаться сангвиническим или холерическим темпераментом.");
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) <=7
    if (list.stream()
        .filter(r -> r.getScaleId() == 2 || r.getScaleId() == 4 || r.getScaleId() == 7 || r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() <= 7)
        .count() >= 3) {
      recommendations.add("Результаты свидетельствуют об отсутствии выразительных реакций на происходящее, что, как правило, приводит к низкой контактности с окружающими.");
    }
    // Застревание, возбудимость, эмотивность, тревожность, экзальтированность (3 из них) >=18
    if (list.stream()
        .filter(r -> r.getScaleId() == 2 || r.getScaleId() == 4 || r.getScaleId() == 7 || r.getScaleId() == 8 || r.getScaleId() == 9)
        .filter(r -> r.getValue() >= 18)
        .count() >= 3) {
      recommendations.add("Результаты характерны для личности, у которой эмоционально-чувственная жизнь настолько разнообразна, что становится неподвластной разуму. У нее могут возникать коммуникативные проблемы, поскольку эмоции и чувства могут проявляться слишком ярко и неадекватно ситуациям.");
    }
    // Демонстративность и гипертимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 1 || r.getScaleId() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add("У вас сильно выражена демонстративность и гипертимность. У взрослых людей такая комбинация часто ослабляет различные аморальные проявления. Открытые натуры, которыми большей частью являются носители гипертимной акцентуации, избирают прямые пути. Хитрость, неискренность, притворство не вяжутся с их жизненной установкой.\n" +
          "Детям, в сравнении со взрослыми людьми, менее свойственно торможение побуждений, в том числе и нечестных, особенно если речь идет об исполнении желаний. Кроме того, у детей иногда наблюдается безудержное стремление хотя бы в чем-то себя проявить – своеобразное детское тщеславие. У взрослых одновременное проявление таких разноплановых свойств почти не встречается.\n" +
          "Такое сочетание акцентуаций часто наблюдается у журналистов, политиков, эстрадных актеров и других публичных деятелей, для которых важны открытость миру, новым впечатлениям, а также богатство воображения и фантазии.");
    }
    // Педантичность и гипертимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 5)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add("У вас сильно выражена гипертимность и педантичность. Поскольку эти черты различны по своей природе, они проявляются не как компенсирующие друг друга, а как взаимодополняющие и вполне совместимые. Так, гипертиму ничто не помешает быть хорошим работником, если он обладает и педантическими чертами. При этом гипертимные черты, совместно с педантичными, способствуют продвижению в жизнь заранее подготовленных и добросовестно спланированных дел.\n" +
          "В целом гипертимная акцентуация оберегает педантическую личность от невроза навязчивого страха, но не от фобий, которые появляются временами, хотя серьезно на образе жизни человека не отражаются – навязчивый страх не носит острого характера и не охватывает личность в целом.");
    }
    // Педантичность и дистимность >= 15
    if (list.stream()
        .filter(r -> r.getScaleId() == 3 || r.getScaleId() == 6)
        .filter(r -> r.getValue() >= 15)
        .count() >= 2) {
      recommendations.add("У вас сильно выражена педантичность и дистимность. Возможно, сами по себе эти акцентуированные черты не были бы столь серьезны, если бы одна не усиливалась за счет другой. Обычно сочетание педантичности и дистимии отражается на всем образе жизни. Дистимический темперамент, которому свойственны недостаток энергии и активности, способствует пессимистическому восприятию жизни, а склонность к навязчивому раздумыванию, характерная для педантов, приводит к депрессиям.\n" +
          "У данной комбинации черт есть и положительная сторона – для педантичных и одновременно дистимных людей характерны такие качества как добросовестность, вдумчивость, серьезное отношение к жизни.");
    }


    return recommendations;
  }

}
