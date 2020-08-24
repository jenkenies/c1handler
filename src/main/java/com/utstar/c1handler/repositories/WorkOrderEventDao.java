package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.WorkOrderEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface WorkOrderEventDao extends JpaRepository<WorkOrderEvent, Integer> {
    WorkOrderEvent findWorkOrderEventByCorrelateId(String correlateId);

    List<WorkOrderEvent> findByStatus(String status);
}
