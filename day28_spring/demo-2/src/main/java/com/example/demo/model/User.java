package com.example.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@EqualsAndHashCode
public class User {
	private String id;
	private String name;
	private String email;
	
	public boolean isValid() {
		return name != null && !name.isEmpty() && email != null && !email.isEmpty();
	}
}
