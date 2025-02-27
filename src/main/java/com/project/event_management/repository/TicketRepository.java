package com.project.event_management.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.event_management.entities.Ticket;

import jakarta.transaction.Transactional;

public interface TicketRepository extends CrudRepository<Ticket, Long>{
        Optional<Ticket> findByEventIdAndUserId(Long eventId, Long userId);
        List<Ticket> findByEventId(Long eventId);
        List<Ticket> findByUserId(Long userId);

        @Transactional
        void deleteByEventIdAndUserId(Long eventId, Long userId);

}
