package com.xavier.clients.service;

import com.xavier.clients.domain.Client;
import com.xavier.clients.dto.ClientDTO;
import com.xavier.clients.repository.ClientRepository;
import com.xavier.clients.service.exception.ResouceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository repository;

   @Transactional(readOnly = true)
   public ClientDTO findById(Long id) {
        Client client = repository.findById(id).orElseThrow(
                ()-> new ResouceNotFoundException("Recurso não encontrado"));
        return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> client = repository.findAll(pageable);
        return client.map(x-> new ClientDTO(x));
    }

    @Transactional
    public ClientDTO insert(ClientDTO dto) {
       Client entity = new Client();
       copyDtoEntity(dto, entity);
       entity = repository.save(entity);
       return new ClientDTO(entity);
    }

    @Transactional
    public ClientDTO update(Long id, ClientDTO dto) {
       try{
           Client entity = repository.getReferenceById(id);
           copyDtoEntity(dto, entity);
           entity = repository.save(entity);
           return new ClientDTO(entity);
       }
       catch (EntityNotFoundException e){
            throw new ResouceNotFoundException("Recurso não encontrado");
       }
    }

    private void copyDtoEntity(ClientDTO dto, Client entity){
        entity.setName(dto.getName());
        entity.setCpf(dto.getCpf());
        entity.setChildren(dto.getChildren());
        entity.setIncome(dto.getIncome());
        entity.setBirthDate(dto.getBirthDate());
    }
}
