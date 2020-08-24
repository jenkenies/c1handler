package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.WorkOrderEvent;
import com.utstar.c1handler.repositories.WorkOrderEventDao;
import com.utstar.c1handler.service.WorkOrderEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class WorkOrderEventServiceImpl implements WorkOrderEventService {
    @Resource
    private WorkOrderEventDao workOrderEventDao;

    @Override
    public void saveWorkOrderEvent(WorkOrderEvent workOrderEvent) {
        workOrderEventDao.saveAndFlush(workOrderEvent);
    }

    @Override
    public WorkOrderEvent getEventByCorrelateId(String correlateId) {
        return workOrderEventDao.findWorkOrderEventByCorrelateId(correlateId);
    }

    @Override
    public void updateWorkOrderEvent(WorkOrderEvent workOrderEvent) {
        workOrderEventDao.saveAndFlush(workOrderEvent);
    }

    @Override
    public List<WorkOrderEvent> getByStatus(String status) {
        return workOrderEventDao.findByStatus(status);
    }
}
