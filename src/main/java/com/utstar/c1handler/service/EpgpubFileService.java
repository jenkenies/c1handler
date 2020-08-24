package com.utstar.c1handler.service;

import com.utstar.c1handler.entities.EpgpubFile;
import com.utstar.c1handler.entities.EpgpubNode;

import java.util.Collection;
import java.util.List;

public interface EpgpubFileService {
    void saveEpgpubFile(Iterable<EpgpubFile> epgpubFiles);

    EpgpubFile save(EpgpubFile epgpubFile);

    EpgpubFile findEpgpubFileByEpgpubfileid(Integer epgpubfileid);
}
