package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.ReceiveNode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiveNodeDao extends JpaRepository<ReceiveNode, Integer> {

    ReceiveNode findReceiveNodeByReceiveNodeId(Integer receivenodeid);

    List<ReceiveNode> findByCspAndLsp(String csp, String lsp);

    List<ReceiveNode> getByCspAndLspAndStatus(String csp, String lsp, String status);
}
