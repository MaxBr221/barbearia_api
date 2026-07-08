package github.MaxBr221.service;

import github.MaxBr221.dtos.agendamento.AgendamentoRequestDTO;
import github.MaxBr221.dtos.agendamento.AgendamentoResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Agendamento;
import github.MaxBr221.model.StatusAgendamento;
import github.MaxBr221.repository.AgendamentoRepository;
import github.MaxBr221.repository.BarbeiroRepository;
import github.MaxBr221.repository.ServicoRepository;
import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
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
        if(!agendamentoRepository.findBarbeiroById(agendamentoRequestDTO.idBarbeiro())) {
            throw new EventFullException("Barbeiro não existente!");
        }
        if(agendamentoRepository.existsServicoById(agendamentoRequestDTO.idServico())){
            throw new EventFullException("Serviço não cadastrado!");
        }

        Agendamento agendamento = new Agendamento();
        agendamento.setStatusAgendamento(StatusAgendamento.AGENDADO);
        BeanUtils.copyProperties(agendamentoRequestDTO, agendamento);
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamento);

        return new AgendamentoResponseDTO(agendamentoSalvo);

    }
}
