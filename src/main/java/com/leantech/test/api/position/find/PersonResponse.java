package com.leantech.test.api.position.find;

import com.leantech.test.context.domain.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

/**
 * Person response
 */
@AllArgsConstructor
@Getter
@Builder
public class PersonResponse {

    private String name;
    private String lastName;
    private String address;
    private String cellphone;
    private String cityName;

    public static PersonResponse personToPersonResponse(Person person){
        return PersonResponse.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .cellphone(person.getCellphone())
                .cityName(person.getCityName())
                .build();
    }
}
