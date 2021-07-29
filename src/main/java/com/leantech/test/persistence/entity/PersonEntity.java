package com.leantech.test.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leantech.test.context.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class that represents the person entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "person")
@Table(name = "PERSON")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String lastName;
    private String address;
    private String cellphone;
    private String cityName;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private EmployeeEntity employee;

    /**
     * From Person to Person Entity
     *
     * @param employee the employee parameter
     * @return a person entity
     */
    public static PersonEntity fromPersonToPersonEntity(Employee employee) {
        return PersonEntity.builder()
                .name(employee.getPerson().getName())
                .lastName(employee.getPerson().getLastName())
                .address(employee.getPerson().getAddress())
                .cellphone(employee.getPerson().getCellphone())
                .cityName(employee.getPerson().getCityName())
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity that = (PersonEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(lastName, that.lastName) && Objects.equals(address, that.address) && Objects.equals(cellphone, that.cellphone) && Objects.equals(cityName, that.cityName) && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, address, cellphone, cityName);
    }
}
