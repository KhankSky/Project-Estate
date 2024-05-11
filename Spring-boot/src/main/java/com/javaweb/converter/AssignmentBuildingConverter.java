package com.javaweb.converter;

import com.javaweb.entity.AssignmentBuildingEntity;
import com.javaweb.model.dto.AssignmentBuildingDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AssignmentBuildingConverter {
    public List<AssignmentBuildingEntity> toAssignmentBuildingEntity(AssignmentBuildingDTO assignmentBuildingDTO) {
        List<AssignmentBuildingEntity> result = new ArrayList<>();
        for(Long it : assignmentBuildingDTO.getStaffs()){
            AssignmentBuildingEntity assignmentBuildingEntity = new AssignmentBuildingEntity();
            assignmentBuildingEntity.setBuildingId(assignmentBuildingDTO.getBuildingId());
            assignmentBuildingEntity.setStaffId(it);
            result.add(assignmentBuildingEntity);
        }
        return result;
    }
}
