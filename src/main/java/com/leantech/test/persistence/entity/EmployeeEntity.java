package com.leantech.test.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.leantech.test.context.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

/**
 * Class that represents the position entity
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity(name = "employee")
@Table(name = "EMPLOYEE")
public class EmployeeEntity {

    @Id
    @Column(unique = true, updatable = false, name = "id", nullable = false, length=36)
    private String id;

    @Column(nullable = false)
    private double salary;

    @OneToOne(fetch = FetchType.LAZY,
            cascade =  CascadeType.ALL,
            mappedBy = "employee")
    private PersonEntity person;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "position_id", nullable = false)
    @JsonIgnore
    private PositionEntity position;

    public EmployeeEntity(String id){
        this.id = id;
    }

    /**
     * From Employee Entity to Employee
     * @param employeeEntity the employee entity parameter
     * @return an Employee
     */
    public static Employee fromEmployeeEntityToEmployee(EmployeeEntity employeeEntity){
        return Employee.load(employeeEntity);
    }

    /**
     * From Employee To Employee Entity
     * @param employee the employee parameter
     * @return an Employee Entity
     */
    public static EmployeeEntity fromEmployeeToEmployeeEntity(Employee employee){
        return EmployeeEntity.builder()
                .salary(employee.getSalary())
                .person(PersonEntity.fromPersonToPersonEntity(employee))
                .position(employee.getPosition() != null ? PositionEntity.fromPositionToPositionEntity(employee.getPosition()) : null)
                .build();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Double.compare(that.salary, salary) == 0 && id.equals(that.id) && Objects.equals(position, that.position);
    }
}
