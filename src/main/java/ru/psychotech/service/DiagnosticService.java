package ru.psychotech.service;

import ru.psychotech.model.diagnistic.DiagnosticResult;
import ru.psychotech.model.diagnistic.DiagnosticDto;

public interface DiagnosticService {

  DiagnosticDto getDiagnostic(Long id);

  void postResult(Long diagnosticId, Long clientId, DiagnosticResult result);

}
