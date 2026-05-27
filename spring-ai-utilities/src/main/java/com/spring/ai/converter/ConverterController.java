package com.spring.ai.converter;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/converter")
public class ConverterController {

	private final ConverterService converterService;

	public ConverterController(ConverterService converterService) {
		super();
		this.converterService = converterService;
	}

	@GetMapping("/bean")
	public Employee bean() {
		return converterService.beanResponse();
	}

	@GetMapping("/record")
	public EmployeeRecord record() {
		return converterService.recordResponse();
	}

	@GetMapping("/enum")
	public EmployeeWithEnum enumResponse() {
		return converterService.enumResponse();
	}

	@GetMapping("/nested")
	public Employee nested() {
		return converterService.nestedResponse();
	}

	@GetMapping("/cities")
	public List<String> cities() {
		return converterService.cityList();
	}

	@GetMapping("/marks")
	public List<Integer> marks() {
		return converterService.marksList();
	}

	@GetMapping("/map")
	public Map<String, Object> map() {
		return converterService.mapResponse();
	}

	@GetMapping("/employees")
	public List<Employee> employees() {
		return converterService.employeeList();
	}

}
