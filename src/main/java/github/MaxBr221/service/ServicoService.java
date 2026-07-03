package github.MaxBr221.service;

import github.MaxBr221.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class ServicoService {
    private ServicoRepository servicoRepository;
}
