package by.example.app.domain;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "employee", schema = "dbo")
@Access(AccessType.PROPERTY)
@NamedQuery(name = "Employee.getAll", query = "SELECT u from Employee u")
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
		if (patch.getGender() != null) this.setGender(patch.getGender());
		if (patch.getJobTitle() != null) this.setJobTitle(patch.getJobTitle());

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

	public Employee(String firstName, String lastName, Integer departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.departmentId = departmentId;
		this.jobTitle = jobTitle;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}

	public Employee(Long employeeId, String firstName, String lastName, Integer departmentId, String jobTitle, Gender gender, LocalDate dateOfBirth) {
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

	@Column(name = "first_name", nullable = false)
	@Type(type = "text")
	public String getFirstName() {
		return firstName;
	}

	@Column(name = "last_name", nullable = false)
	@Type(type = "text")
	@Basic
	public String getLastName() {
		return lastName;
	}

	@Column(name = "department_id", nullable = false)
	@Basic
	public Integer getDepartmentId() {
		return departmentId;
	}

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

	@Column(name = "date_of_birth", nullable = false, columnDefinition = "DATE")
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