package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psychotech.calculator.AccentuationCalculator;
import ru.psychotech.mapper.DiagnosticMapper;
import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.DiagnosticAnswers;
import ru.psychotech.model.diagnistic.DiagnosticDto;
import ru.psychotech.repository.ClientSummaryRepository;
import ru.psychotech.repository.DiagnosticRepository;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {
  private final AccentuationCalculator accentuationCalculator;
  private final DiagnosticRepository diagnosticRepository;
  private final ClientSummaryRepository summaryRepository;
  private final DiagnosticMapper mapper;

  @Override
  public List<DiagnosticDto> getCompleted(Long clientId) {
    List<Long> completed = diagnosticRepository.getCompleted(clientId);
    List<DiagnosticDto> all = mapper.mapDiagnosticList(diagnosticRepository.getDiagnosticList());
    return all
        .stream()
        .filter(d -> completed.contains(d.getId()))
        .collect(Collectors.toList());
  }

  @Override
  public List<DiagnosticDto> getAvailable(Long clientId) {
    List<Long> completed = diagnosticRepository.getCompleted(clientId);
    List<DiagnosticDto> all = mapper.mapDiagnosticList(diagnosticRepository.getDiagnosticList());
    return all
        .stream()
        .filter(d -> !completed.contains(d.getId()))
        .collect(Collectors.toList());
  }

  @Override
  public DiagnosticResult getResults(Long clientId, Long diagnosticId) {
    if (diagnosticId == 1) {
      return accentuationCalculator.getResults(clientId);
    } else {
      return null;
    }
  }

  @Override
  public DiagnosticDto getDiagnostic(Long id) {
    var diagnostic = diagnosticRepository.getDiagnostic(id);
    if (diagnostic.isEmpty()) {
      return null;
    } else {
      return mapper.mapDiagnosticWithQuestions(diagnostic.get(),
          diagnosticRepository.getQuestions(id));
    }
  }

  @Override
  public void postResult(Long diagnosticId, Long clientId, DiagnosticAnswers result) {
    if (diagnosticId == 1) {
      accentuationCalculator.calculateAndWrite(clientId, result.getAnswers());
    }
  }

  @Override
  public void clearResults(Long clientId, Long diagnosticId) {
    diagnosticRepository.clearResults(clientId, diagnosticId);
    summaryRepository.clearSummary(clientId, diagnosticId);
  }
}
