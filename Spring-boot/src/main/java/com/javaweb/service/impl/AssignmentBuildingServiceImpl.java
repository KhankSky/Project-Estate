package com.javaweb.service.impl;

import com.javaweb.converter.AssignmentBuildingConverter;
import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.service.AssignmentBuildingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
@Service
public class AssignmentBuildingServiceImpl implements AssignmentBuildingService {
    @Autowired
    AssignmentBuildingConverter assignmentBuildingConverter;
    @Autowired
    AssignmentBuildingRepository assignmentBuildingRepository;

    @Transactional
    @Override
    public void updateAssignment(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<AssignmentBuildingEntity> assignmentBuildingEntityList = assignmentBuildingConverter.toAssignmentBuildingEntity(assignmentBuildingDTO);
        assignmentBuildingRepository.deleteByBuildingId(assignmentBuildingDTO.getBuildingId());
        for (AssignmentBuildingEntity it : assignmentBuildingEntityList) {
            assignmentBuildingRepository.save(it);
        }
    }

    @Override
    public void deleteByBuildingIds(Long[] ids) {
        for(Long id : ids){
            assignmentBuildingRepository.deleteByBuildingId(id);
        }
    }
}
