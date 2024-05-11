package com.javaweb.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "building")
@Getter
@Setter
public class BuildingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "street")
    private String street;

    @Column(name = "ward")
    private String ward;

    @Column(name = "district")
    private String district;

    @Column(name = "managername")
    private String managerName;

    @Column(name = "managerphone")
    private String managerPhoneNumber;

    @Column(name = "numberofbasement")
    private Long numberOfBasement;

    @Column(name = "rentprice")
    private Long rentPrice;

    @Column(name = "servicefee")
    private Long serviceFee;

    @Column(name = "brokeragefee")
    private Long brokerageFee;

    @Column(name = "floorarea")
    private Long floorArea ;

    @Column(name = "type")
    private String type;

    @ManyToMany (mappedBy = "buildingList", fetch = FetchType.LAZY)
    private List<UserEntity> user = new ArrayList<>();

    @OneToMany (mappedBy = "building", fetch = FetchType.LAZY)
    private List<RentAreaEntity> rentAreas = new ArrayList<>();


}
