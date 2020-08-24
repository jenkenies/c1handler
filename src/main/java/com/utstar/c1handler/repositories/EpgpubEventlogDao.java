package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.EpgpubEventlog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EpgpubEventlogDao extends JpaRepository<EpgpubEventlog, Integer> {

    EpgpubEventlog findEpgpubEventlogByCorrelateid(String correlateid);

    List<EpgpubEventlog> findEpgpubEventlogByStatusInAndReceiveeventid(Collection<String> status, Integer receiveeventid);
}
