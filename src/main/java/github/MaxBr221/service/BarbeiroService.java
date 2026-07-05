package github.MaxBr221.service;

import github.MaxBr221.dtos.barbeiro.BarbeiroRequestDTO;
import github.MaxBr221.dtos.barbeiro.BarbeiroResponseDTO;
import github.MaxBr221.exception.EventFullException;
import github.MaxBr221.model.Barbeiro;
import github.MaxBr221.repository.BarbeiroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarbeiroService {
    private BarbeiroRepository barbeiroRepository;

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

}
