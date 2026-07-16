package github.MaxBr221.service;

import github.MaxBr221.dtos.agendamento.AgendamentoResponseDTO;
import github.MaxBr221.dtos.barbeiro.BarbeiroRequestDTO;
import github.MaxBr221.dtos.barbeiro.BarbeiroResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Agendamento;
import github.MaxBr221.model.Barbeiro;
import github.MaxBr221.repository.AgendamentoRepository;
import github.MaxBr221.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {
    private BarbeiroRepository barbeiroRepository;
    private AgendamentoRepository agendamentoRepository;

    public BarbeiroResponseDTO create(BarbeiroRequestDTO barbeiroDTO){
        if(barbeiroRepository.existsByLogin(barbeiroDTO.login())){
            throw new EventFullException("Barbeiro já existente!");
        }
        Barbeiro barbeiro = new Barbeiro();
        BeanUtils.copyProperties(barbeiroDTO, barbeiro);
        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiro);

        return new BarbeiroResponseDTO(barbeiroSalvo);
    }
    public void delete(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));
        barbeiroRepository.delete(barbeiro);
    }
    public BarbeiroResponseDTO findById(Long id){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));
        return new BarbeiroResponseDTO(barbeiro);
    }
    public List<BarbeiroResponseDTO> findAll(){
        return barbeiroRepository.findAll().stream()
                .map(barbeiro -> new BarbeiroResponseDTO(barbeiro))
                .toList();

    }
    public BarbeiroResponseDTO update(Long id, BarbeiroRequestDTO barbeiroDTO){
        Barbeiro barbeiro = barbeiroRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));

        Barbeiro barbeiroAtualizado = new Barbeiro();
        BeanUtils.copyProperties(barbeiroDTO, barbeiroAtualizado);
        Barbeiro barbeiroSalvo = barbeiroRepository.save(barbeiroAtualizado);

        return new BarbeiroResponseDTO(barbeiroSalvo);
    }

    public List<AgendamentoResponseDTO> agendamentosDoDia(Long idBarbeiro, LocalDate dia){
        Barbeiro barbeiro = barbeiroRepository.findById(idBarbeiro)
                .orElseThrow(()-> new EventFullException("Barbeiro não existente!"));

        if(!agendamentoRepository.findBarbeiroById(barbeiro.getId())){
            throw new EventFullException("Barbeiro não tem agendamento ainda!");
        }

        LocalDateTime inicio = dia.atStartOfDay();
        LocalDateTime fim = dia.atTime(LocalTime.MAX);

        List<Agendamento> agendamentos = agendamentoRepository.findByBarbeiroIdAndDataHoraInicioBetween(idBarbeiro, inicio, fim);
        if(agendamentos.isEmpty()){
            throw new EventFullException("Não existe agendamentos nesse dia!");
        }

        return agendamentos
                .stream()
                .map(agendamento -> new AgendamentoResponseDTO(agendamento))
                .toList();

    }

}
