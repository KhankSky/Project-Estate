package com.javaweb.enums;

import java.util.HashMap;
import java.util.Map;

public enum typeCode {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên căn"),
    NOI_THAT("Nội thất");

    private final String typeCodeName;

    typeCode(String typeCodeName){
        this.typeCodeName = typeCodeName;
    }

    public String getTypeCodeName(){
        return this.typeCodeName;
    }
    public static Map<String,String> getTypeCode(){
        Map<String,String> listDistrict = new HashMap<String,String>();
        for (typeCode item : typeCode.values()){
            listDistrict.put(item.toString(),item.getTypeCodeName());
        }
        return listDistrict;
    }
}
