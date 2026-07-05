package github.MaxBr221.service;

import github.MaxBr221.dtos.agendamento.AgendamentoRequestDTO;
import github.MaxBr221.dtos.agendamento.AgendamentoResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.repository.AgendamentoRepository;
import github.MaxBr221.repository.BarbeiroRepository;
import github.MaxBr221.repository.ServicoRepository;
import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AgendamentoService {
    private AgendamentoRepository agendamentoRepository;
    private UsuarioRepository usuarioRepository;
    private BarbeiroRepository barbeiroRepository;
    private ServicoRepository servicoRepository;

    public AgendamentoResponseDTO create(AgendamentoRequestDTO agendamentoRequestDTO){
        if(agendamentoRepository.findByData(agendamentoRequestDTO.data())){
            throw new EventFullException("Data ocupada!");
        }
    }
}
