package com.bidder.userservice.model;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class User implements UserDetails {

	public User() {

	}

	public enum Role {
		USER, ADMIN;

		public List<SimpleGrantedAuthority> getAuthorities() {
			return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + this.name()));
		}
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private String firstName;


	@Column(nullable = false)
	private String lastName;
	@Column(nullable = false)
	private String address;

	@Column(nullable = false)
	private Integer streetNumber;

	@Column(nullable = false)
	private String postalCode;

	@Column(nullable = false)
	private String city;

	@Column(nullable = false)
	private String country;


	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private Role role;

	public User(String username, String email, String password, String firstName, Role role) {}

	public User(String username, String email, String password, String firstName,
				String lastName, String address, Integer streetNumber, String postalCode, String city
				, String country,Role role) {
		this.username = username;
		this.email = email;
		this.password = password;
		this.firstName = firstName;
		this.role = role;
		this.lastName = lastName;
		this.address = address;
		this.streetNumber = streetNumber;
		this.postalCode = postalCode;
		this.city = city;
		this.country = country;

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public static UserBuilder builder() {
		return new UserBuilder();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return role.getAuthorities();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public static class UserBuilder {
		private String username;
		private String email;
		private String password;
		private Role role;
		private String firstName;
		private String lastName;
		private String address;
		private Integer streetNumber;
		private String postalCode;
		private String city;
		private String country;


		UserBuilder() {
		}

		public UserBuilder username(String username) {
			this.username = username;
			return this;
		}

		public UserBuilder email(String email) {
			this.email = email;
			return this;
		}

		public UserBuilder password(String password) {
			this.password = password;
			return this;
		}

		public UserBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public UserBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public UserBuilder address (String address)
		{
			this.address = address; return this;
		}

		public UserBuilder streetNumber (Integer streetNumber)
		{
			this.streetNumber = streetNumber; return this;
		}

		public UserBuilder postalCode (String postalCode)
		{
			this.postalCode = postalCode; return this;
		}

		public UserBuilder city (String city)
		{
			this.city = city; return this;
		}

		public UserBuilder country (String country)
		{
			this.country = country; return this;
		}




		public UserBuilder role(Role role) {
			this.role = role;
			return this;
		}

		public User build() {
			return new User(username, email, password, firstName,lastName,
					address, streetNumber, postalCode, city, country, role);
		}

		@Override
		public String toString() {
			return "User.UserBuilder(username=" + this.username +
					", email=" + this.email +
					", password=" + this.password +
					", firstName=" + this.firstName +
					", lastName=" + this.lastName +
					", address=" + this.address +
					", streetNumber=" + this.streetNumber +
					", postalCode=" + this.postalCode +
					", city=" + this.city +
					", country=" + this.country +
					", role=" + this.role + ")";
		}
	}

}
