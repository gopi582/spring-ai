package com.spring.ai.converter;

import java.util.List;
import java.util.Map;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.ai.converter.ListOutputConverter;
import org.springframework.ai.converter.MapOutputConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

	private final ChatClient chatClient;

	public ConverterService(ChatClient.Builder builder) {
		this.chatClient = builder.build();
	}

	public Employee beanResponse() {

		BeanOutputConverter<Employee> converter = new BeanOutputConverter<>(Employee.class);

		String result = chatClient.prompt().user("""
				Generate Employee:
				Name=John
				Age=30
				Department=IT

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public EmployeeRecord recordResponse() {

		BeanOutputConverter<EmployeeRecord> converter = new BeanOutputConverter<>(EmployeeRecord.class);

		String result = chatClient.prompt().user("""
				Generate Employee Record

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public EmployeeWithEnum enumResponse() {

		BeanOutputConverter<EmployeeWithEnum> converter = new BeanOutputConverter<>(EmployeeWithEnum.class);

		String result = chatClient.prompt().user("""
				Generate Employee

				Department should be:
				IT,HR,SALES,FINANCE

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public Employee nestedResponse() {

		BeanOutputConverter<Employee> converter = new BeanOutputConverter<>(Employee.class);

		String result = chatClient.prompt().user("""
				Generate Employee with Address

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public List<String> cityList() {

		ListOutputConverter converter = new ListOutputConverter();

		String result = chatClient.prompt().user("""
				Give Top 10 Indian Cities

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public List<Integer> marksList() {

		ListOutputConverter converter = new ListOutputConverter();

		String result = chatClient.prompt().user("""
				Generate 10 student marks as numbers only.

				%s
				""".formatted(converter.getFormat())).call().content();

		List<String> marks = converter.convert(result);

		return marks.stream().map(Integer::parseInt).toList();
	}

	public Map<String, Object> mapResponse() {

		MapOutputConverter converter = new MapOutputConverter();

		String result = chatClient.prompt().user("""
				Generate Employee Details

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

	public List<Employee> employeeList() {

		BeanOutputConverter<List<Employee>> converter = new BeanOutputConverter<>(
				new ParameterizedTypeReference<List<Employee>>() {
				});

		String result = chatClient.prompt().user("""
				Generate 5 Employees

				%s
				""".formatted(converter.getFormat())).call().content();

		return converter.convert(result);
	}

}
