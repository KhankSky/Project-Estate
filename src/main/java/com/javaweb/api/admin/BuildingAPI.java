package com.javaweb.api.admin;


import com.javaweb.model.dto.BuildingDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/buildings")
public class BuildingAPI {
    @PostMapping
    public String addOrUpdateBuilding(@RequestBody BuildingDTO buildingDTO){
        // xuong db lay data___
        return new String("Add Building Success");
    }

    @DeleteMapping
    public String deleteBuilding(@RequestBody List<String> ids){
        // xuong db xoa data
        return new String("Delete Building Success");
    }
}
