package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;

public interface RentAreaService {
    void create (BuildingDTO buildingDTO, BuildingEntity buildingEntity);
    void deleteByBuilidngIds (Long[] ids);
}
