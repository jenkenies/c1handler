package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NodeDao extends JpaRepository<Node, Integer> {
    Node findNodeBySrcCspIdAndSrcLspId(String srcCspId, String SrcLspId);
}
