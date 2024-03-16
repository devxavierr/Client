package com.xavier.clients.service;

import com.xavier.clients.domain.Client;
import com.xavier.clients.dto.ClientDTO;
import com.xavier.clients.repository.ClientRepository;
import com.xavier.clients.service.exception.ResouceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
}
