package com.javaweb.api.admin;


import com.javaweb.model.dto.AssignmentBuildingDTO;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.ResponseDTO;
import com.javaweb.model.response.StaffResponseDTO;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @Autowired
    private BuildingService buildingService;
    @Autowired
    private UserService userService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        // xuong db them data___
        buildingService.createOrUpdate(buildingDTO);
        return new String("Thêm hoặc sửa tòa nhà thành công!");
    }

    @DeleteMapping
    public String deleteBuildings(@RequestBody Long [] ids){
        buildingService.deleteByIdIn(ids);
        return new String("Xóa tòa thành công");
    }

    @GetMapping({"/{id}/staffs"})
    public ResponseDTO loadStaff(@PathVariable("id") Long ids){
        List<StaffResponseDTO> staffResponseDTOS = userService.getAllStaffs();
        List<StaffResponseDTO> staffResponseDTOByBuildingId = userService.getStaffsByBuildingId(ids);

        Set<StaffResponseDTO> staffResponseDTOSet = new HashSet<>(staffResponseDTOByBuildingId);

        staffResponseDTOS.stream().filter(staffResponseDTOSet::contains).forEach(it -> it.setChecked("checked"));

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setData(staffResponseDTOS);
        responseDTO.setMessage("Success");
        return responseDTO;
    }

    @PutMapping
    public void updateAssignmentBuilding(@RequestBody AssignmentBuildingDTO assignmentBuildingDTO){
        // xuong service xu ly
        assignmentBuildingService.updateAssignment(assignmentBuildingDTO);
    }
}
 