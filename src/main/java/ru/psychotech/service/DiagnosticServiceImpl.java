package ru.psychotech.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.psychotech.mapper.DiagnosticMapper;
import ru.psychotech.model.diagnistic.Diagnostic;
import ru.psychotech.repository.DiagnosticRepository;

@Service
@RequiredArgsConstructor
public class DiagnosticServiceImpl implements DiagnosticService {
  private final DiagnosticRepository repository;
  private final DiagnosticMapper mapper;

  @Override
  public Diagnostic getDiagnostic(Long id) {
    var commonDesc = repository.getCommonDescription(id);
    if (commonDesc.isEmpty()) return null;

    return Diagnostic.builder()
        .id(id)
        .diagnosticType(mapper.mapType(commonDesc.get().getType()))
        .name(commonDesc.get().getName())
        .description(commonDesc.get().getDescription())
        .scales(mapper.mapScaleList(repository.getScales(id)))
        .questions(repository.getQuestions(id))
        .build();
  }
}
