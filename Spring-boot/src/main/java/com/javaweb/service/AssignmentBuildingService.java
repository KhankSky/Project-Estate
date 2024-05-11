package com.javaweb.service;

import com.javaweb.model.dto.AssignmentBuildingDTO;

public interface AssignmentBuildingService {
    void updateAssignment (AssignmentBuildingDTO assignmentBuildingDTO);
    void deleteByBuildingIds(Long[] ids);
}
