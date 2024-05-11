package com.javaweb.service.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.RentAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentAreaServiceImpl implements RentAreaService {
    @Autowired
    RentAreaRepository rentAreaRepository;

    @Transactional
    @Override
    public void create(BuildingDTO buildingDTO, BuildingEntity buildingEntity) {
        List<Long> rentAreas = Arrays.stream(buildingDTO.getRentArea().split(",")).map(Long::valueOf).collect(Collectors.toList());
        rentAreaRepository.deleteByBuildingId(buildingDTO.getId());
        for(Long rentArea : rentAreas){
            RentAreaEntity rentAreaEntity = new RentAreaEntity();
            rentAreaEntity.setBuilding(buildingEntity);
            rentAreaEntity.setValue(rentArea);
            rentAreaRepository.save(rentAreaEntity);
        }
    }

    @Transactional
    @Override
    public void deleteByBuilidngIds(Long[] ids) {
        for(Long id : ids){
            rentAreaRepository.deleteByBuildingId(id);
        }
    }
}
