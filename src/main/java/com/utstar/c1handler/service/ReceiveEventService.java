package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.ReceiveEvent;
import com.utstar.c1handler.enums.ReceiveEventStatus;

import java.util.List;

public interface ReceiveEventService {
    void save(ReceiveEvent receiveEvent);

    void saveAll(Iterable<ReceiveEvent> receiveEvent);

    List<ReceiveEvent> findByStatus(ReceiveEventStatus status);

    List<ReceiveEvent> findReceiveEventByCorrelateId(String correlateid);

}
