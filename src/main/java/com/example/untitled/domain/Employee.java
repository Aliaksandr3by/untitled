package com.example.untitled.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.PositiveOrZero;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "dbo")
@Access(AccessType.PROPERTY)
//@NamedQuery(name = "getAll",
//		query = "SELECT u from com.example.untitled.domain.Employee u order by u.employeeId")
//@NamedQuery(name = "getAllStartFrom",
//		query = "SELECT u from com.example.untitled.domain.Employee u where u.employeeId between :startId AND :fromId order by u.employeeId")
@NamedQueries({
		@NamedQuery(name = "getAll",
				query = "SELECT u from com.example.untitled.domain.Employee u order by u.employeeId"),
		@NamedQuery(name = "getAllStartFrom",
				query = "SELECT u1 from com.example.untitled.domain.Employee u1 where u1.employeeId between :startId AND :fromId order by u1.employeeId")
})
public class Employee implements Serializable {

	//TODO delete
	public boolean IsEmpty() {
		return this.getFirstName() == null
				&& this.getLastName() == null
				&& this.getGender() == null
				&& this.getJobTitle() == null;
	}

	public Employee patcherEmployee(Employee patch) {

		if (patch.getFirstName() != null) this.setFirstName(patch.getFirstName());
		if (patch.getLastName() != null) this.setLastName(patch.getLastName());
		if (patch.getDepartmentId() != null) this.setDepartmentId(patch.getDepartmentId());
		if (patch.getJobTitle() != null) this.setJobTitle(patch.getJobTitle());
		if (patch.getGender() != null) this.setGender(patch.getGender());
		if (patch.getDateOfBirth() != null) this.setDateOfBirth(patch.getDateOfBirth());

		return this;
	}

	public Employee() {
	}

	private Long employeeId;
	private String firstName;
	private String lastName;
	private Integer departmentId;
	private String jobTitle;
	private Gender gender;
	private LocalDate dateOfBirth;

	public Employee(Long employeeId) {
		this.employeeId = employeeId;
	}

	public Employee(String firstName, String lastName,
					Integer departmentId, String jobTitle,
					Gender gender,
					LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.jobTitle = jobTitle;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public Employee(
			Long employeeId,
			String firstName, String lastName,
			Integer departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
		this.employeeId = employeeId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.jobTitle = jobTitle;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_id_seq")
	@Column(name = "employee_id", columnDefinition = "BIGINT", nullable = false, updatable = false)
	public Long getEmployeeId() {
		return employeeId;
	}

	@NotBlank(message = "first name cannot be Blank")
	@Column(name = "first_name", nullable = false)
	@Type(type = "text")
	public String getFirstName() {
		return firstName;
	}

	@NotBlank(message = "last name cannot be Blank")
	@Column(name = "last_name", nullable = false)
	@Type(type = "text")
	@Basic
	public String getLastName() {
		return lastName;
	}

	@PositiveOrZero
	@Column(name = "department_id", nullable = false)
	@Basic
	public Integer getDepartmentId() {
		return departmentId;
	}

	@NotBlank(message = "cannot be Blank")
	@Column(name = "job_title", nullable = false)
	@Basic
	@Type(type = "text")
	public String getJobTitle() {
		return jobTitle;
	}

	@Column(name = "gender", nullable = false)
	@Enumerated(EnumType.ORDINAL)
	public Gender getGender() {
		return gender;
	}

	@PastOrPresent(message = "must be past time or present")
	@Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	@JsonDeserialize(using = LocalDateDeserializer.class)
	@JsonSerialize(using = LocalDateSerializer.class)
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public void setDepartmentId(Integer department) {
		this.departmentId = department;
	}

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	@Override
	public String toString() {
		return "Employee{" +
				"employeeId=" + employeeId +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", departmentId=" + departmentId +
				", jobTitle='" + jobTitle + '\'' +
				", gender=" + gender +
				", dateOfBirth=" + dateOfBirth +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Employee)) return false;
		Employee employee = (Employee) o;
		return firstName.equals(employee.firstName) &&
				lastName.equals(employee.lastName) &&
				departmentId.equals(employee.departmentId) &&
				jobTitle.equals(employee.jobTitle) &&
				gender == employee.gender &&
				dateOfBirth.equals(employee.dateOfBirth);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, departmentId, jobTitle, gender, dateOfBirth);
	}
}