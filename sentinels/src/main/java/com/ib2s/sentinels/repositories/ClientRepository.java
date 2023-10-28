package com.ib2s.sentinels.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ib2s.sentinels.models.ClientModel;

public interface ClientRepository extends JpaRepository<ClientModel, UUID>{
    
}
