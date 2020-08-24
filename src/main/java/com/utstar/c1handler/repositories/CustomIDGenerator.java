package com.utstar.c1handler.repositories;

import com.utstar.c1handler.entities.EpgpubEventlog;
import com.utstar.c1handler.entities.EpgpubFile;
import com.utstar.c1handler.entities.ReceiveEvent;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;


public class CustomIDGenerator extends IdentityGenerator {

    private static final Logger log = LoggerFactory.getLogger(CustomIDGenerator.class);
    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws MappingException {
        Object id = null;
        String sequenceName = "";
        if(object instanceof ReceiveEvent) {
            sequenceName = "RECEIVEEVENTID";
        } else if(object instanceof EpgpubFile) {
            sequenceName = "EPGPUBFILEID";
        } else if(object instanceof EpgpubEventlog) {
            sequenceName = "EPGPUBEVENTLOGID";
        }
        id = JdbcDao.getNextObjectIDFromSQ(sequenceName);
        if (id != null) {
            return (Serializable)  id;
        }
        return super.generate(session, object);
    }


}
