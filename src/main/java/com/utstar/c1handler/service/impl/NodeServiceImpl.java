package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.Node;
import com.utstar.c1handler.repositories.NodeDao;
import com.utstar.c1handler.service.NodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(rollbackFor = Exception.class)
public class NodeServiceImpl implements NodeService {
    @Resource
    private NodeDao nodeDao;

    @Override
    public Node getBySrcCspIdAndSrcLspId(String srcCspId, String srcLspId) {
        return nodeDao.findNodeBySrcCspIdAndSrcLspId(srcCspId, srcLspId);
    }
}
