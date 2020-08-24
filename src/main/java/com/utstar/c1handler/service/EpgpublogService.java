package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.EpgpubEventlog;

import java.util.Collection;
import java.util.List;

public interface EpgpublogService {
    EpgpubEventlog save(EpgpubEventlog epgpubEventlog);

    EpgpubEventlog findEpgpubEventlogByCorrelateid(String correlateid);

    List<EpgpubEventlog> findEpgpubEventlogByStatusInAndReceiveeventid(Collection<String> status, Integer receiveeventid);

}
