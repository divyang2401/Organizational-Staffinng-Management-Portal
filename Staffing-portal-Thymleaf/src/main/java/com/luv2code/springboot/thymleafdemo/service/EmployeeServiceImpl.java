package com.luv2code.springboot.thymleafdemo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.thymleafdemo.dao.EmployeeRepository;
import com.luv2code.springboot.thymleafdemo.entity.Employee;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	
	private EmployeeRepository employeeRepository;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository theEmployeeRepo) {
		employeeRepository=theEmployeeRepo;
	}
	
	@Override
	//@Transactional: no need to write..Spring Jpa provide it 
	public List<Employee> findAll() {
		return employeeRepository.findAll();
	}

	@Override
	public List<Employee> findAllByOrderByLastNameAsc() {
		return employeeRepository.findAllByOrderByLastNameAsc();
	}
	
	@Override
	//@Transactional
	public Employee findById(int theId) {
		Optional<Employee> result=employeeRepository.findById(theId);
		
		Employee theEmployee=null;
		
		if(result.isPresent()) {
			theEmployee=result.get();
		}else {
			//didn't find employee
			throw new RuntimeException("Did not find employee id- "+theId);
		}
		
		return theEmployee; 
	}

	@Override
	//@Transactional
	public void save(Employee theEmployee) {
		employeeRepository.save(theEmployee);
		
	}

	@Override
	//@Transactional
	public void deleteById(int theId) {
		employeeRepository.deleteById(theId);
	}

		
}
