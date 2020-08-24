package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.ReceiveEvent;
import com.utstar.c1handler.enums.ReceiveEventStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveEventDao extends JpaRepository<ReceiveEvent, Integer> {
    List<ReceiveEvent> findByStatus(ReceiveEventStatus status);

    List<ReceiveEvent> findReceiveEventByCorrelateId(String correlateid);

}