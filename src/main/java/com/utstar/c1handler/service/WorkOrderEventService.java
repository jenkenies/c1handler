package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.WorkOrderEvent;

import java.util.List;

public interface WorkOrderEventService {
    void saveWorkOrderEvent(WorkOrderEvent workOrderEvent);

    WorkOrderEvent getEventByCorrelateId(String correlateId);

    void updateWorkOrderEvent(WorkOrderEvent workOrderEvent);

    List<WorkOrderEvent> getByStatus(String status);
}
