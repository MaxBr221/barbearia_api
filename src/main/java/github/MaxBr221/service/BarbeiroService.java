package github.MaxBr221.service;

import github.MaxBr221.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BarbeiroService {
    private BarbeiroRepository barbeiroRepository;

}
