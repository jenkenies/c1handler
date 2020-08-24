package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.EpgpubNode;

import java.util.Collection;
import java.util.List;

public interface EpgpubNodeService {
    List<EpgpubNode> findEpgpubNodesByDomainidIn(Collection<Integer> domains);
}
