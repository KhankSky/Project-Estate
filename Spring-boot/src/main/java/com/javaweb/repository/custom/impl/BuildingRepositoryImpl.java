package com.javaweb.repository.custom.impl;

import com.javaweb.entity.BuildingEntity;
import com.javaweb.model.request.BuildingSearchRequest;
import com.javaweb.repository.custom.BuildingRepositoryCustom;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BuildingRepositoryImpl implements BuildingRepositoryCustom {
    public void Join(BuildingSearchRequest builder, StringBuilder sql) {
        if (builder.getAreaFrom() != null || builder.getAreaTo() != null) {
            sql.append(" join rentarea ra on bd.id = ra.buildingid ");
        }
        if (builder.getStaffId() != null) {
            sql.append(" join assignmentbuilding asm on bd.id = asm.buildingid\r\n"
                    + "join user on user.id = asm.staffid ");
        }
//        List<String> typdeCode = builder.getTypeCode();
//        if (typdeCode != null && !typdeCode.isEmpty()) {
//            sql.append(" join buildingrenttype bdrt on bd.id = bdrt.buildingid\r\n"
//                    + "join renttype rt on bdrt.renttypeid = rt.id");
//        }
    }

    public void whereNormal(BuildingSearchRequest builder, StringBuilder where) {

        try {
            Field[] fields = BuildingSearchRequest.class.getDeclaredFields();
            for (Field item : fields) {
                item.setAccessible(true);
                String fieldName = item.getName();
                if (!fieldName.equals("staffId") && !fieldName.equals("typeCode") && !fieldName.startsWith("area")
                        && !fieldName.startsWith("rentPrice")) {
                    Object value = item.get(builder);
                    if (value != null) {
                        if (item.getType() == Long.class) {
                            where.append(" And bd." + fieldName + " = " + value);
                        } else if (item.getType() == String.class && !value.toString().equals("")) {
                            where.append(" And bd." + fieldName + " like '%" + value + "%' ");

                        }
                    }
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

    }

    public void whereSpecial(BuildingSearchRequest builder, StringBuilder where) {
        Long staffId = builder.getStaffId();
        if (staffId != null) {
            where.append(" and user.id =" + staffId);
        }
        Long rentAreaFrom = builder.getAreaFrom();
        Long rentAreaTo = builder.getAreaTo();
        ;
        if (rentAreaFrom != null) {
            where.append(" and ra.value >=" + rentAreaFrom);
        }
        if (rentAreaTo != null) {
            where.append(" and ra.value <=" + rentAreaTo);
        }

        Long rentPriceFrom = builder.getRentPriceFrom();
        Long rentPriceTo = builder.getRentPriceTo();
        if (rentPriceFrom != null) {
            where.append(" and bd.rentPrice >=" + rentPriceFrom);
        }
        if (rentPriceTo != null) {
            where.append(" and bd.rentPrice <=" + rentPriceTo);
        }
        List<String> typeCode = builder.getTypeCode();
        if (typeCode != null && !typeCode.isEmpty()) {
            where.append(" and (");
            String sqlJoin = typeCode.stream().map(item -> "bd.type like '%" + item + "%'")
                    .collect(Collectors.joining(" or "));
            where.append(sqlJoin + ") ");
        }

    }

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BuildingEntity> findAll(BuildingSearchRequest builder) {
        StringBuilder sql = new StringBuilder("Select bd.* from building bd");
        Join(builder, sql);
        sql.append(" where 1=1 ");
        whereNormal(builder, sql);
        whereSpecial(builder, sql);
        sql.append(" group by bd.id");
        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        Query query = entityManager.createNativeQuery(sql.toString(), BuildingEntity.class);
//        List<BuildingEntity> result = query.getResultList();
        List<BuildingEntity> result = query.getResultList();
//        System.out.println("hello");
        return result;
    }

}

