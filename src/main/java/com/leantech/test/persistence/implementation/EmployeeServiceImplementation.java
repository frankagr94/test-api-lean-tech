package com.leantech.test.persistence.implementation;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.domain.Position;
import com.leantech.test.context.service.IEmployeeService;
import com.leantech.test.persistence.entity.EmployeeEntity;
import com.leantech.test.persistence.entity.PositionEntity;
import com.leantech.test.persistence.repository.IEmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * Employee Service Implementation
 */
@Service
public class EmployeeServiceImplementation implements IEmployeeService {

    private final IEmployeeRepository employeeRepository;

    /**
     * Constructor
     *
     * @param employeeRepository the employee repository
     */
    public EmployeeServiceImplementation(IEmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        var employeeEntity = EmployeeEntity.fromEmployeeToEmployeeEntity(employee);
        employeeEntity.setId(UUID.randomUUID().toString());
        employeeEntity.getPerson().setEmployee(new EmployeeEntity(employeeEntity.getId()));

        return EmployeeEntity.fromEmployeeEntityToEmployee(employeeRepository.save(employeeEntity));
    }

    @Override
    public Employee update(Employee employee, String id, Position position) {
        var employeeOptional = employeeRepository.findById(id);

        if(employeeOptional.isPresent()){
            var employeeToUpdate = employeeOptional.get();
            employeeToUpdate.setSalary(employee.getSalary());
            employeeToUpdate.getPerson().setName(employee.getPerson().getName());
            employeeToUpdate.getPerson().setLastName(employee.getPerson().getLastName());
            employeeToUpdate.getPerson().setAddress(employee.getPerson().getAddress());
            employeeToUpdate.getPerson().setCellphone(employee.getPerson().getCellphone());
            employeeToUpdate.getPerson().setCityName(employee.getPerson().getCityName());

            if(!employeeToUpdate.getPosition().getId().equals(position.getId())){
                employeeToUpdate.setPosition(PositionEntity.fromPositionToPositionEntity(position));
            }

            return EmployeeEntity.fromEmployeeEntityToEmployee(employeeRepository.save(employeeToUpdate));
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll().stream().parallel()
                .map(EmployeeEntity::fromEmployeeEntityToEmployee)
                .collect(Collectors.toList());
    }

    @Override
    public boolean existById(String id) {
        return employeeRepository.existsById(id);
    }

    @Override
    public void deleteById(String id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public List<Employee> findByParams(String position, String name) {

        return employeeRepository.findByPosition_NameContainingIgnoreCaseOrPerson_NameContainingIgnoreCase(position, name).stream().parallel()
                .map(EmployeeEntity::fromEmployeeEntityToEmployee)
                .collect(Collectors.toList());
    }
}
