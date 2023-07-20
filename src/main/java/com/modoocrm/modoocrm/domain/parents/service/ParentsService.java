package com.modoocrm.modoocrm.domain.parents.service;

import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import org.springframework.stereotype.Service;

public interface ParentsService {

    void registerParents(Parents parents,Long clientId);

    Parents getParents(Long clientId);

    void updateParents(Parents parents, Long parentsId);
}
