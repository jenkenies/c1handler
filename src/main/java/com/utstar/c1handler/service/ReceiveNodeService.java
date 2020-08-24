package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.ReceiveNode;

import java.util.List;

public interface ReceiveNodeService {

    ReceiveNode findReceiveNodeByReceiveNodeId(Integer receivenodeid);

    List<ReceiveNode> getByCspAAndLsp(String csp, String lsp);

    List<ReceiveNode> getByCspAndLspAndStatus(String csp, String lsp, String status);
}
