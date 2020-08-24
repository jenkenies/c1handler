package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.ReceiveNode;
import com.utstar.c1handler.repositories.ReceiveNodeDao;
import com.utstar.c1handler.service.ReceiveNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class ReceiveNodeServiceImpl implements ReceiveNodeService {
    @Resource
    private ReceiveNodeDao receiveNodeDao;

    @Override
    public ReceiveNode findReceiveNodeByReceiveNodeId(Integer receivenodeid) {
        return receiveNodeDao.findReceiveNodeByReceiveNodeId(receivenodeid);
    }

    @Override
    public List<ReceiveNode> getByCspAAndLsp(String csp, String lsp) {
        return receiveNodeDao.findByCspAndLsp(csp, lsp);
    }

    @Override
    public List<ReceiveNode> getByCspAndLspAndStatus(String csp, String lsp, String status) {
        return receiveNodeDao.getByCspAndLspAndStatus(csp, lsp, status);
    }
}
