package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.EpgpubNode;
import com.utstar.c1handler.entities.Node;
import com.utstar.c1handler.repositories.EpgpubNodeDao;
import com.utstar.c1handler.repositories.NodeDao;
import com.utstar.c1handler.service.EpgpubNodeService;
import com.utstar.c1handler.service.NodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class EpgpubNodeServiceImpl implements EpgpubNodeService {
    @Resource
    private EpgpubNodeDao epgpubNodeDao;

    public List<EpgpubNode> findEpgpubNodesByDomainidIn(Collection<Integer> domains) {
        return epgpubNodeDao.findEpgpubNodesByDomainidIn(domains);
    }

}
