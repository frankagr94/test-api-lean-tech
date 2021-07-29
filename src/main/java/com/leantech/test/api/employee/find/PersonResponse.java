package com.leantech.test.api.employee.find;

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

    private final String name;
    private final String lastName;
    private final String address;
    private final String cellphone;
    private final String cityName;

    /**
     * Person To Person Response
     *
     * @param person the person parameter
     * @return a person response
     */
    public static PersonResponse personToPersonResponse(Person person) {
        return PersonResponse.builder()
                .name(person.getName())
                .lastName(person.getLastName())
                .address(person.getAddress())
                .cellphone(person.getCellphone())
                .cityName(person.getCityName())
                .build();
    }
}
