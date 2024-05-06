package com.javaweb.controller.admin;

import java.util.List;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.enums.typeCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.service.BuildingService;
import com.javaweb.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController(value="buildingControllerOfAdmin")
public class BuildingController {
    @Autowired
    private BuildingService buildingService;

    private final UserService userService;

    public BuildingController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/admin/building-list")
    public ModelAndView buildingList(@ModelAttribute("modelSearch")BuildingSearchRequest buildingSearchRequest){
        ModelAndView mav = new ModelAndView("admin/building/list");
        mav.addObject("staffs",userService.getStaffs());
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        List<BuildingSearchResponse> result = buildingService.findAll(buildingSearchRequest);
        mav.addObject("buildings", result);

        return mav;
    }

    @GetMapping(value = "/admin/building-edit")
    public ModelAndView addBuilding(@ModelAttribute("buildingEdit")BuildingDTO buildingDTO){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        mav.addObject("districtCode", districtCode.district());
        mav.addObject("typeCodes", typeCode.getTypeCode());
        return mav;
    }

    @GetMapping(value = "/admin/building-edit-{id}")
    public ModelAndView addBuilding(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("admin/building/edit");
        // findByBuildingId
        //BuildingDTO buildingDTO = new BuildingDTO();
        // ... set
        //mav.addObject("buildingEdit", buildingDTO);
        // buildingEdit là tên của modelAttribute đặt tên ở edit form:form
        return mav;
    }


}
