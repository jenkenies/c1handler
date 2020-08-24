package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.ReceiveEvent;
import com.utstar.c1handler.enums.ReceiveEventStatus;
import com.utstar.c1handler.repositories.ReceiveEventDao;
import com.utstar.c1handler.service.ReceiveEventService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReceiveEventServiceImpl implements ReceiveEventService {
    @Resource
    private ReceiveEventDao receiveEventDao;

    @Override
    public void save(ReceiveEvent receiveEvent) {

        receiveEventDao.saveAndFlush(receiveEvent);
    }

    @Override
    public void saveAll(Iterable<ReceiveEvent> receiveEvent) {
        receiveEventDao.saveAll(receiveEvent);
    }

    @Override
    public List<ReceiveEvent> findByStatus(ReceiveEventStatus status) {
        return receiveEventDao.findByStatus(status);
    }

    @Override
    public List<ReceiveEvent> findReceiveEventByCorrelateId(String correlateid) {
        return receiveEventDao.findReceiveEventByCorrelateId(correlateid);
    }


}
