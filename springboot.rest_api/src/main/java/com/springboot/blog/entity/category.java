package com.springboot.blog.entity;

import java.util.ArrayList;
import java.util.Set;
import java.util.List;
import org.apache.logging.log4j.util.StringBuilderFormattable;
import org.hibernate.annotations.GeneratorType;
import org.hibernate.boot.jaxb.mapping.marshall.LockModeTypeMarshalling;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class category {
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Id
	@GeneratedValue(
			strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@OneToMany(mappedBy = "category",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<post> posts;
	public List<post> getPosts() {
		return posts;
	}
	public void setPosts(List<post> posts) {
		this.posts = posts;
	}

	

}
