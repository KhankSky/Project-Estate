package com.javaweb.service.impl;

import com.javaweb.converter.BuildingConverter;
import com.javaweb.entity.BuildingEntity;
import com.javaweb.entity.RentAreaEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import com.javaweb.repository.AssignmentBuildingRepository;
import com.javaweb.repository.BuildingRepository;
import com.javaweb.repository.RentAreaRepository;
import com.javaweb.service.AssignmentBuildingService;
import com.javaweb.service.BuildingService;
import com.javaweb.service.RentAreaService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuildingServiceImpl implements BuildingService {
    @Autowired
    private BuildingRepository buildingRepository;
    @Autowired
    private BuildingConverter buildingConverter;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    private RentAreaService rentAreaService;
    @Autowired
    private AssignmentBuildingService assignmentBuildingService;

    @Override
    public List<BuildingSearchResponse> findAll(BuildingSearchRequest buildingSearchRequest) {
        List<BuildingEntity> buildingEntities = buildingRepository.findAll(buildingSearchRequest);
        List<BuildingSearchResponse> result = new ArrayList<>();
        for(BuildingEntity item : buildingEntities){
            BuildingSearchResponse building = buildingConverter.toBuildingSearchResponse(item);
            result.add(building);
        }
        return result;
    }

    @Override
    public BuildingDTO findById(Long id) {
        BuildingEntity building = buildingRepository.findById(id).get();
        return buildingConverter.toBuildingDTO(building);
    }

    @Transactional
    @Override
    public void deleteByIdIn(Long[] ids) {
        rentAreaService.deleteByBuilidngIds(ids);
        assignmentBuildingService.deleteByBuildingIds(ids);
        buildingRepository.deleteByIdIn(ids);
    }

    @Transactional
    @Override
    public void createOrUpdate(BuildingDTO buildingDTO) {
        BuildingEntity buildingEntity = modelMapper.map(buildingDTO, BuildingEntity.class);
        buildingEntity.setType(String.join(",", buildingDTO.getTypeCode()));
        buildingRepository.save(buildingEntity);
        System.out.println("hello");
        rentAreaService.create(buildingDTO, buildingEntity);
    }


}
