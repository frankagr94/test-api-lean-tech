package com.leantech.test.persistence.implementation;

import com.leantech.test.context.domain.EmployeeMother;
import com.leantech.test.context.domain.PositionMother;
import com.leantech.test.context.service.IEmployeeService;
import com.leantech.test.persistence.entity.EmployeeEntity;
import com.leantech.test.persistence.entity.EmployeeEntityMother;
import com.leantech.test.persistence.repository.IEmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Employee Service Implementation Tests
 */
class EmployeeServiceImplementationTests {

    private final IEmployeeService employeeService;

    @Captor
    private ArgumentCaptor<EmployeeEntity> employeeEntityArgumentCaptor;

    @Mock
    private IEmployeeRepository employeeRepository;

    EmployeeServiceImplementationTests() {
        MockitoAnnotations.openMocks(this);
        this.employeeService = new EmployeeServiceImplementation(this.employeeRepository);
    }

    @Test
    void Save_Employee(){
        var employee = EmployeeMother.valid();
        var employeeEntity = EmployeeEntityMother.valid();

        when(employeeRepository.save(Mockito.any(EmployeeEntity.class))).thenReturn(employeeEntity);
        employeeService.save(employee);
        verify(employeeRepository,times(1)).save(employeeEntityArgumentCaptor.capture());

        var response = employeeEntityArgumentCaptor.getValue();

        assertThat(response.getSalary()).isEqualTo(employee.getSalary());
        assertThat(response.getPerson().getName()).isEqualTo(employee.getPerson().getName());
        assertThat(response.getPerson().getLastName()).isEqualTo(employee.getPerson().getLastName());
        assertThat(response.getPerson().getCellphone()).isEqualTo(employee.getPerson().getCellphone());
        assertThat(response.getPerson().getCityName()).isEqualTo(employee.getPerson().getCityName());
        assertThat(response.getPerson().getAddress()).isEqualTo(employee.getPerson().getAddress());
    }

    @Test
    void Update_Employee_When_Optional_Is_Empty(){
        var employeeId = "ed7b3f84-c757-44e8-9512-8413943a2d58";
        var employee = EmployeeMother.valid();
        var position = PositionMother.valid();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());
        var result = employeeService.update(employee, employeeId, position);
        verify(employeeRepository,times(1)).findById(employeeId);

        assertThat(result).isNull();
    }

    @Test
    void Update_Employee_When_Optional_Is_Present(){
        var employeeId = "ed7b3f84-c757-44e8-9512-8413943a2d58";
        var employee = EmployeeMother.valid();
        var employeeEntity = EmployeeEntityMother.valid();
        var position = PositionMother.valid();

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employeeEntity));
        when(employeeRepository.save(employeeEntity)).thenReturn(employeeEntity);

        employeeService.update(employee, employeeId, position);

        verify(employeeRepository,times(1)).save(employeeEntityArgumentCaptor.capture());
        var response = employeeEntityArgumentCaptor.getValue();

        assertThat(response.getSalary()).isEqualTo(employee.getSalary());
        assertThat(response.getPerson().getName()).isEqualTo(employee.getPerson().getName());
        assertThat(response.getPerson().getLastName()).isEqualTo(employee.getPerson().getLastName());
        assertThat(response.getPerson().getCellphone()).isEqualTo(employee.getPerson().getCellphone());
        assertThat(response.getPerson().getCityName()).isEqualTo(employee.getPerson().getCityName());
        assertThat(response.getPerson().getAddress()).isEqualTo(employee.getPerson().getAddress());
    }

    @Test
    void Exist_Employee_By_Id_When_Is_True(){
        var employeeId = "ed7b3f84-c757-44e8-9512-8413943a2d58";

        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        var result = employeeService.existById(employeeId);
        verify(employeeRepository, times(1)).existsById(employeeId);

        assertTrue(result);
    }

    @Test
    void Exist_Employee_By_Id_When_Is_False(){
        var employeeId = "ed7b3f84-c757-44e8-9512-8413943a2d58";

        when(employeeRepository.existsById(employeeId)).thenReturn(false);
        var result = employeeService.existById(employeeId);
        verify(employeeRepository, times(1)).existsById(employeeId);

        assertFalse(result);
    }

    @Test
    void Delete_Employee_By_Id(){
        var employeeId = "ed7b3f84-c757-44e8-9512-8413943a2d58";

        employeeService.deleteById(employeeId);
        verify(employeeRepository,times(1)).deleteById(employeeId);
    }

    @Test
    void Find_All(){
        var employees = EmployeeEntityMother.generateEmployeeEntitiesList();

        when(employeeRepository.findAll()).thenReturn(employees);
        var result = employeeService.findAll();

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getSalary()).isEqualTo(employees.get(0).getSalary());
        assertThat(result.get(0).getPerson().getName()).isEqualTo(employees.get(0).getPerson().getName());

        verify(employeeRepository,times(1)).findAll();
    }

    @Test
    void Find_Employees_By_Params(){
        var position = "dev";
        var name = "jose";
        var employees = EmployeeEntityMother.generateEmployeeEntitiesList();

        doReturn(employees).when(employeeRepository)
                .findByPosition_NameContainingIgnoreCaseOrPerson_NameContainingIgnoreCase(position, name);

        var result = employeeService.findByParams(position, name);

        assertThat(result).isNotNull();
        assertThat(result).isNotEmpty();
        assertThat(result.get(0).getSalary()).isEqualTo(employees.get(0).getSalary());
        assertThat(result.get(0).getPerson().getName()).isEqualTo(employees.get(0).getPerson().getName());

        verify(employeeRepository,times(1))
                .findByPosition_NameContainingIgnoreCaseOrPerson_NameContainingIgnoreCase(position, name);
    }
}
