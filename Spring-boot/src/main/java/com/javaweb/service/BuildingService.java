package com.javaweb.service;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.dto.BuildingDTO;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.model.response.BuildingSearchResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingService {
    List<BuildingSearchResponse> findAll(BuildingSearchRequest buldingSearchRequest);
    BuildingDTO findById(Long id);
    void deleteByIdIn (Long [] ids);
    void createOrUpdate(BuildingDTO buildingDTO);

}
