package com.leantech.test.persistence.entity;

/**
 * Person Entity Mother
 */
public class PersonEntityMother {

    public static PersonEntity generate(){
        return PersonEntity.builder()
                .name("Frank")
                .lastName("Gonzalez")
                .cellphone("+58000000000")
                .address("Casa alta")
                .cityName("Barquisimeto")
                .build();
    }
}
