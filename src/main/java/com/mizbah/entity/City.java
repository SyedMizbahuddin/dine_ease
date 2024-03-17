package com.mizbah.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "cities")
@Entity
public class City {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String city;

	@ManyToMany
	@JoinTable(name = "branches", joinColumns = @JoinColumn(name = "city_id"), inverseJoinColumns = @JoinColumn(name = "restaurant_id"))
	private List<Restaurant> restaurants;

	@Version
	private Long version;

}
