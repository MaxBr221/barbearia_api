package github.MaxBr221.service;

import github.MaxBr221.dtos.servico.ServicoRequestDTO;
import github.MaxBr221.dtos.servico.ServicoResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Servico;
import github.MaxBr221.repository.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class ServicoService {
    private ServicoRepository servicoRepository;

    //adm que vai criar o servico
    public ServicoResponseDTO create(ServicoRequestDTO servicoRequestDTO){
        Servico servico = servicoRepository.findByNome(servicoRequestDTO.nome())
                .orElseThrow(()-> new EventFullException("Serviço já cadastrado!"));

        Servico novoServico = new Servico();
        BeanUtils.copyProperties(servicoRequestDTO, novoServico);
        Servico servicoSalvo = servicoRepository.save(novoServico);

        return new ServicoResponseDTO(servicoSalvo);
    }
    public void delete(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Serviço não existente!"));
        servicoRepository.delete(servico);
    }
    public ServicoResponseDTO findById(Long id){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Serviço não existente!"));

        return new ServicoResponseDTO(servico);
    }
    public List<ServicoResponseDTO> findAll(){
        return servicoRepository.findAll()
                .stream()
                .map(servico -> new ServicoResponseDTO(servico))
                .toList();
    }
    public ServicoResponseDTO update(Long id, ServicoRequestDTO servicoRequestDTO){
        Servico servico = servicoRepository.findById(id)
                .orElseThrow(()-> new EventFullException("Serviço não existente!"));

        Servico servicoBuscado = new Servico();
        BeanUtils.copyProperties(servicoRequestDTO, servicoBuscado);
        Servico servicoSalvo = servicoRepository.save(servicoBuscado);

        return new ServicoResponseDTO(servicoSalvo);
    }


}
