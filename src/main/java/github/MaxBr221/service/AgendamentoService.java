package github.MaxBr221.service;

import github.MaxBr221.dtos.agendamento.AgendamentoRequestDTO;
import github.MaxBr221.dtos.agendamento.AgendamentoResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Agendamento;
import github.MaxBr221.model.Barbeiro;
import github.MaxBr221.model.StatusAgendamento;
import github.MaxBr221.repository.AgendamentoRepository;
import github.MaxBr221.repository.BarbeiroRepository;
import github.MaxBr221.repository.ServicoRepository;
import github.MaxBr221.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

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
    public void delete(Long id){
        Agendamento agendamento  = agendamentoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Agendamento não existente!"));
        agendamentoRepository.delete(agendamento);

    }
    public AgendamentoResponseDTO findById(Long id){
        Agendamento agendamento  = agendamentoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Agendamento não existente!"));

        return new AgendamentoResponseDTO(agendamento);
    }
    public List<AgendamentoResponseDTO> findAll(){
        return agendamentoRepository.findAll()
                .stream()
                .map(agendamento -> new AgendamentoResponseDTO(agendamento))
                .toList();
    }
    public AgendamentoResponseDTO update(Long id, AgendamentoRequestDTO agendamentoRequestDTO){
        Agendamento agendamento  = agendamentoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Agendamento não existente!"));

        if(!agendamento.getStatusAgendamento().equals(StatusAgendamento.RESERVADO) && agendamento.getStatusAgendamento().equals(StatusAgendamento.EM_ESPERA)){
            throw new EventFullException("Você não pode editar agendamento que esteja AGENDADO, FINALIZADO ou CANCELADO!");
        }
        if(!agendamentoRepository.existsServicoById(agendamentoRequestDTO.idServico())){
            throw new EventFullException("Serviço não disponivel no momento!");
        }
        verificaExistenciaBarbeiro(agendamentoRequestDTO.idBarbeiro());
        verificaDataAgendamento(agendamentoRequestDTO.data());

        Agendamento agendamentoAtualizado = new Agendamento();
        BeanUtils.copyProperties(agendamentoRequestDTO, agendamentoAtualizado);
        Agendamento agendamentoSalvo = agendamentoRepository.save(agendamentoAtualizado);
        return new AgendamentoResponseDTO(agendamentoSalvo);
    }
    private void verificaDataAgendamento(LocalDateTime data){
        boolean agendamentoNessaData = agendamentoRepository.findByData(data);
        if(agendamentoNessaData){
            throw new EventFullException("Já existe agendamento marcado nesse horário!");
        }
    }
    private void verificaExistenciaBarbeiro(Long id){
        boolean barbeiro = agendamentoRepository.findBarbeiroById(id);
        if(!barbeiro) {
            throw new EventFullException("Barbeiro não encotrado!");
        }
    }
}
