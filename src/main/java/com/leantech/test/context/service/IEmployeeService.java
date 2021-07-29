package com.leantech.test.context.service;

import com.leantech.test.context.domain.Employee;
import com.leantech.test.context.domain.Position;

import java.util.List;

/**
 * Employee Service Interface
 */
public interface IEmployeeService {

    /**
     * Save method
     *
     * @param employee the employee parameter
     * @return the employee saved
     */
    Employee save(Employee employee);

    /**
     * Update method
     *
     * @param employee the employee parameter
     * @param position the position parameter
     * @return the employee updated
     */
    Employee update(Employee employee, String id, Position position);

    /**
     * Find All method
     *
     * @return an employee list
     */
    List<Employee> findAll();

    /**
     * Exist By ID
     *
     * @param id the id parameter
     * @return a boolear
     */
    boolean existById(String id);

    /**
     * Delete By ID
     *
     * @param id the id parameter
     */
    void deleteById(String id);

    List<Employee> findByParams(String position, String name);
}
