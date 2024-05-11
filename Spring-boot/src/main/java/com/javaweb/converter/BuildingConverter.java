package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.enums.districtCode;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.utils.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;

    public BuildingSearchResponse toBuildingSearchResponse(BuildingEntity item) {
        if(StringUtils.check(item.getDistrict())) {
            String data = districtCode.valueOf(item.getDistrict()).getDistrictName();
            item.setDistrict(data);
        }
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet()+ ", " +item.getWard()+ ", " + item.getDistrict());
        String rentArea = item.getRentAreas().stream().
                map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(rentArea);
        return building;
    }

    public BuildingDTO toBuildingDTO(BuildingEntity item) {
        BuildingDTO buildingDTO = modelMapper.map(item, BuildingDTO.class);
        if(StringUtils.check(item.getDistrict())) {
            String data = districtCode.valueOf(item.getDistrict()).getDistrictName();
            item.setDistrict(data);
        }
        buildingDTO.setDistrict(item.getDistrict());
        List<String> typeCodes = Arrays.stream(item.getType().split(",")).collect(Collectors.toList());
        buildingDTO.setTypeCode(typeCodes);
        String listRentArea = item.getRentAreas().stream().map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        buildingDTO.setRentArea(listRentArea);
        return buildingDTO;
    }


}
