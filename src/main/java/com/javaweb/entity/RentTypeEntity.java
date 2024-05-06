//package com.javaweb.entity;
//
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "renttype")
//@Getter
//@Setter
//public class RentTypeEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @Column(name = "code")
//    private String code;
//
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany
//    @JoinTable(
//            name = "buildingrenttype",
//            joinColumns = @JoinColumn(name = "renttypeid"),
//            inverseJoinColumns = @JoinColumn(name = "buildingid")
//    )
//    private List<BuildingEntity> buildings = new ArrayList<>();
//}
