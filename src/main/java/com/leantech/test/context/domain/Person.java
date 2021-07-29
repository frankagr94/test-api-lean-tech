package com.leantech.test.context.domain;

import com.leantech.test.api.employee.create.CreatePersonRequest;
import com.leantech.test.api.employee.update.UpdatePersonRequest;
import com.leantech.test.persistence.entity.PersonEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Class that represents the person domain
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Person {

    private final String name;
    private final String lastName;
    private final String address;
    private final String cellphone;
    private final String cityName;

    /**
     * Load method
     *
     * @param personEntity the person entity parameter
     * @return a person domain class
     */
    public static Person load(PersonEntity personEntity) {
        return new Person(personEntity.getName(), personEntity.getLastName(), personEntity.getAddress(),
                personEntity.getCellphone(), personEntity.getCityName());
    }

    /**
     * From Person Entity To Person
     *
     * @param personEntity the person entity parameter
     * @return a person
     */
    public static Person fromPersonEntityToPerson(PersonEntity personEntity) {
        return Person.load(personEntity);
    }

    /**
     * From Create Person Request To Person
     *
     * @param createPersonRequest the create person request parameter
     * @return a person
     */
    public static Person fromCreatePersonRequestToPerson(CreatePersonRequest createPersonRequest) {
        return new Person(createPersonRequest.getName(), createPersonRequest.getLastName(), createPersonRequest.getAddress(),
                createPersonRequest.getCellphone(), createPersonRequest.getCityName());
    }

    /**
     * From Update Person Request To Person
     *
     * @param updatePersonRequest the update person request parameter
     * @return a person
     */
    public static Person fromUpdatePersonRequestToPerson(UpdatePersonRequest updatePersonRequest) {
        return new Person(updatePersonRequest.getName(), updatePersonRequest.getLastName(), updatePersonRequest.getAddress(),
                updatePersonRequest.getCellphone(), updatePersonRequest.getCityName());
    }
}
