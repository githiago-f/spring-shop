package com.medicines.vendor.domain.laboratory;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.medicines.vendor.domain.users.vo.EmployeeData;
import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;
import com.medicines.vendor.domain.users.Employee;

import javax.persistence.*;
import java.util.List;

@Getter @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "laboratories")
public class Laboratory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String name;

	@CNPJ
	@Column(unique = true, nullable = false)
	private String cnpj;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "laboratory")
	private List<Employee> employees;

	public Employee createLabAdmin(EmployeeData employeeData) {
		Employee employee = new Employee(employeeData, this);
		this.employees.add(employee);
		return employee;
	}
}
