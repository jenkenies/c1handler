package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.EpgpubFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EpgpubFileDao extends JpaRepository<EpgpubFile, Integer> {
    EpgpubFile findEpgpubFileByEpgpubfileid(Integer epgpubfileid);
}
