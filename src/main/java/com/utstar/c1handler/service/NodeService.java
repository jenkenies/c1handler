package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.Node;

public interface NodeService {

    Node getBySrcCspIdAndSrcLspId(String srcCspId, String srcLspId);
}
