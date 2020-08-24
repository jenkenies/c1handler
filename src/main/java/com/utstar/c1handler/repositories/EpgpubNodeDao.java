package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.EpgpubNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface EpgpubNodeDao extends JpaRepository<EpgpubNode, Integer> {
        List<EpgpubNode> findEpgpubNodesByDomainidIn(Collection<Integer> domains);
}
