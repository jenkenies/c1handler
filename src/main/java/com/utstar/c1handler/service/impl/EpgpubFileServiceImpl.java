package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.EpgpubFile;
import com.utstar.c1handler.entities.EpgpubNode;
import com.utstar.c1handler.repositories.EpgpubFileDao;
import com.utstar.c1handler.repositories.EpgpubNodeDao;
import com.utstar.c1handler.service.EpgpubFileService;
import com.utstar.c1handler.service.EpgpubNodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class EpgpubFileServiceImpl implements EpgpubFileService {
    @Resource
    private EpgpubFileDao epgpubFileDao;


    @Override
    public void saveEpgpubFile(Iterable<EpgpubFile> epgpubFiles) {
        epgpubFileDao.saveAll(epgpubFiles);
    }

    @Override
    public EpgpubFile save(EpgpubFile epgpubFile) {
        EpgpubFile epg = epgpubFileDao.saveAndFlush(epgpubFile);
        return epg;
    }

    @Override
    public EpgpubFile findEpgpubFileByEpgpubfileid(Integer epgpubfileid) {
        return epgpubFileDao.findEpgpubFileByEpgpubfileid(epgpubfileid);
    }
}
