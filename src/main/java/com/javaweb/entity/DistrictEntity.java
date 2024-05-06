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
//@Table(name = "district")
//@Getter
//@Setter
//public class DistrictEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id ;
//
//    @Column(name ="name")
//    private String name ;
//
//    @Column(name = "code")
//    private String code;
//
//    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
//    private List<BuildingEntity> buildings = new ArrayList<>();
//}
