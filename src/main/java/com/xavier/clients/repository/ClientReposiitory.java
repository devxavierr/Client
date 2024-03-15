package com.xavier.clients.repository;

import com.xavier.clients.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientReposiitory extends JpaRepository<Client, Long> {
}
