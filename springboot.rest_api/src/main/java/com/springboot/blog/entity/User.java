package com.springboot.blog.entity;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.DialectOverride.GeneratedColumns;
import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "User")
public class User {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Set<Role> getRole() {
		return role;
	}
	public void setRole(Set<Role> role) {
		this.role = role;
	}
	
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	@NotNull
	@Column(unique = true)
	private String username;
	
//	public Set<Role> getRoles() {
//		return roles;
//	}
//	public void setRoles(Set<Role> roles) {
//		this.roles = roles;
//	}

	@NotNull
	@Column(unique = true)
	private String email;
	@NotNull
	@Column(unique = true)
	private String password;
	
	@ManyToMany(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "user_role",
	joinColumns=@JoinColumn(name = "user_id",referencedColumnName = "id"),
	inverseJoinColumns=@JoinColumn(name="role_id",referencedColumnName = "id")
	)
	private Set<Role> role;

	

}
