package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psychotech.calculator.AccentuationCalculator;
import ru.psychotech.mapper.DiagnosticMapper;
import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.DiagnosticDto;
import ru.psychotech.repository.DiagnosticRepository;

@Service
@RequiredArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {
  private final AccentuationCalculator accentuationCalculator;
  private final DiagnosticRepository repository;
  private final DiagnosticMapper mapper;

  @Override
  public DiagnosticDto getDiagnostic(Long id) {
    var commonDesc = repository.getCommonDescription(id);
    if (commonDesc.isEmpty()) return null;

    return DiagnosticDto.builder()
        .id(id)
        .diagnosticType(mapper.mapType(commonDesc.get().getType()))
        .name(commonDesc.get().getName())
        .description(commonDesc.get().getDescription())
        .questions(repository.getQuestions(id))
        .build();
  }

  @Override
  public void postResult(Long diagnosticId, Long clientId, DiagnosticResult result) {
    if (diagnosticId == 1) {
      accentuationCalculator.calculateAndWrite(clientId, result.getAnswers());
    }
  }

}
