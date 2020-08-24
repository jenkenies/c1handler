package com.utstar.c1handler.service.impl;

import com.utstar.c1handler.entities.EpgpubEventlog;
import com.utstar.c1handler.entities.EpgpubFile;
import com.utstar.c1handler.repositories.EpgpubEventlogDao;
import com.utstar.c1handler.repositories.EpgpubFileDao;
import com.utstar.c1handler.service.EpgpubFileService;
import com.utstar.c1handler.service.EpgpublogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


@Service
@Transactional(rollbackFor = Exception.class)
public class EpgpublogServiceImpl implements EpgpublogService {
    @Resource
    private EpgpubEventlogDao epgpubEventlogDao;



    @Override
    public EpgpubEventlog save(EpgpubEventlog epgpubEventlog) {
        return epgpubEventlogDao.saveAndFlush(epgpubEventlog);
    }

    @Override
    public EpgpubEventlog findEpgpubEventlogByCorrelateid(String correlateid) {
        return epgpubEventlogDao.findEpgpubEventlogByCorrelateid(correlateid);
    }

    @Override
    public List<EpgpubEventlog> findEpgpubEventlogByStatusInAndReceiveeventid(Collection<String> status, Integer receiveeventid) {
        return epgpubEventlogDao.findEpgpubEventlogByStatusInAndReceiveeventid(status, receiveeventid);
    }

}
