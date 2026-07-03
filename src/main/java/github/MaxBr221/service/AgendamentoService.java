package github.MaxBr221.service;

import github.MaxBr221.repository.AgendamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class AgendamentoService {
    private AgendamentoRepository agendamentoRepository;
}
