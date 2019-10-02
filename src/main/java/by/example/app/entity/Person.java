package by.example.app.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "persons", schema = "public")
@NamedQuery(name = "Person.getAll", query = "SELECT u from Person u")
public class Person implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "employee_id_seq")
	@GenericGenerator(name = "increment", strategy = "increment")
	@Column(name = "person_id")
	private long id;

	@NotNull
	@Column(name = "person_name", nullable = false)
	private String name;

	@NotNull
	@Column(name = "person_surName", nullable = false)
	private String surName;

	public Person() {
	}

	public Person(long id) {
		this.id = id;
	}

	public Person(String name, String sdfsf) {
		this.name = name;
		this.surName = sdfsf;
	}

	public Person(long id, String name, String surName) {
		this.id = id;
		this.name = name;
		this.surName = surName;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	@Override
	public String toString() {
		return "Person{" +
				"id=" + id +
				", name='" + name + '\'' +
				", surName='" + surName + '\'' +
				'}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Person)) return false;
		Person person = (Person) o;
		return id == person.id &&
				Objects.equals(name, person.name) &&
				Objects.equals(surName, person.surName);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, surName);
	}
}