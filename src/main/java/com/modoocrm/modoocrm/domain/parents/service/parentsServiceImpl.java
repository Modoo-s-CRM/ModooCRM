package com.modoocrm.modoocrm.domain.parents.service;

import com.modoocrm.modoocrm.domain.client.entity.Client;
import com.modoocrm.modoocrm.domain.client.service.ClientServiceImpl;
import com.modoocrm.modoocrm.domain.parents.entity.Parents;
import com.modoocrm.modoocrm.domain.parents.repository.ParentsRepository;
import com.modoocrm.modoocrm.global.error.exception.BusinessLogicException;
import com.modoocrm.modoocrm.global.error.exception.ExceptionCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service
public class parentsServiceImpl implements ParentsService {

    private final ClientServiceImpl clientService;
    private final ParentsRepository parentsRepository;

    public parentsServiceImpl(ClientServiceImpl clientService, ParentsRepository parentsRepository) {
        this.clientService = clientService;
        this.parentsRepository = parentsRepository;
    }

    @Override
    public void registerParents(Parents parents, Long clientId) {
        Client client = clientService.findVerifiedClient(clientId);
        parents.setClient(client);
        parentsRepository.save(parents);
    }

    @Override
    public void updateParents(Parents updateParentsInfo, Long parentsId) {
        Parents findParents = this.findVerifiedParents(parentsId);
        Parents updateParents = this.updateParentsMethod(findParents,updateParentsInfo);
        parentsRepository.save(updateParents);
    }

    @Override
    public Parents getParents(Long clientId) {
        Client client = clientService.findVerifiedClient(clientId);
        Parents findParents = client.getParents();
        return findParents;
    }

    private Parents findVerifiedParents(Long parentsId) {
        return parentsRepository.findById(parentsId).orElseThrow(
                () -> new BusinessLogicException(ExceptionCode.PARENTS_NOT_FOUND)
        );
    }

    private Parents updateParentsMethod(Parents findParents, Parents updateParents){
        Optional.ofNullable(updateParents.getFatherName())
                .ifPresent(fatherName -> findParents.setFatherName(fatherName));
        Optional.ofNullable(updateParents.getMotherName())
                .ifPresent(motherName -> findParents.setMotherName(motherName));
        Optional.ofNullable(updateParents.getFatherAge())
                .ifPresent(fatherAge -> findParents.setFatherAge(fatherAge));
        Optional.ofNullable((updateParents.getMotherAge()))
                .ifPresent(motherAge -> findParents.setMotherAge(motherAge));
        Optional.ofNullable(updateParents.getFatherEducationInfo())
                .ifPresent(fatherEducationInfo -> findParents.setFatherEducationInfo(fatherEducationInfo));
        Optional.ofNullable(updateParents.getMotherEducationInfo())
                .ifPresent(motherEducationInfo -> findParents.setMotherEducationInfo(motherEducationInfo));
        Optional.ofNullable(updateParents.getFatherJob())
                .ifPresent(fatherJob -> findParents.setFatherJob(fatherJob));
        Optional.ofNullable(updateParents.getMotherJob())
                .ifPresent(motherJob -> findParents.setMotherJob(motherJob));
        Optional.ofNullable(updateParents.getTogether())
                .ifPresent(together -> findParents.setTogether(together));
        return findParents;
    }
}
