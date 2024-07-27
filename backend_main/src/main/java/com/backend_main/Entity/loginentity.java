package com.backend_main.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="login")
@Getter
@Setter
public class loginentity {
	@Id
	private String userid;
	private String password;
	private String name;
	private String phoneno;

}
