package com.javaweb.converter;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.response.BuildingSearchResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class BuildingConverter {

    @Autowired
    private ModelMapper modelMapper;
    public BuildingSearchResponse toBuildingDTO(BuildingEntity item) {
        BuildingSearchResponse building = modelMapper.map(item, BuildingSearchResponse.class);
        building.setAddress(item.getStreet()+ ", " +item.getWard()+ ", " + item.getDistrict());
        String rentArea = item.getRentAreas().stream().
                map(it -> it.getValue().toString()).collect(Collectors.joining(","));
        building.setRentArea(rentArea);
        return building;
    }
}
