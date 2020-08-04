package com.xdomain.user.entities;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "usuario")
public class UserEntity {

	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(columnDefinition = "UUID")
	private UUID id;
	private String name;
	private String email;
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)	
	@JoinColumn(name = "user_id")
	private List<PhoneEntity> phones;
	
	@Column(name="user_created")
	private LocalDateTime  userCreated;
	
	@Column(name="user_modified")
	private LocalDateTime userModified;
	
	@Column(name="is_active")
	private Boolean isActive;

	public UserEntity(String name, String email, String password, List<PhoneEntity> phones, LocalDateTime userCreated,
			LocalDateTime userModified, Boolean isActive) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.phones = phones;
		this.userCreated = userCreated;
		this.userModified = userModified;
		this.isActive = isActive;
	}

}
