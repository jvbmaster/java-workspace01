package com.ib2s.sentinels.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ib2s.sentinels.models.ClientModel;
import com.ib2s.sentinels.repositories.ClientRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class ClientController {

    @Autowired
    ClientRepository clientRepository;

    @GetMapping("/clients")
    public ResponseEntity<List<ClientModel>> getAll() {
        List<ClientModel> list = clientRepository.findAll();

        if(!list.isEmpty()){
            for(ClientModel client : list){
                UUID id = client.getIdClient();
                client.add(linkTo(methodOn(ClientController.class).getOne(id)).withSelfRel());
            }
       }

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(list);
    }

    @GetMapping("/client/{id}")
    public ResponseEntity<Object> getOne(@PathVariable(value = "id") UUID id){
        Optional<ClientModel> client = clientRepository.findById(id);
        if(!client.isEmpty()){
            return ResponseEntity
                    .status(HttpStatus.OK)    
                    .body(client);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Objeto não encontrado");
    }
    
}
