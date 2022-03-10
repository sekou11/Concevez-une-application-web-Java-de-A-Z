package com.payMyBuddy.Models;

import java.util.Objects;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.Hibernate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Table(name = "user")
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserApp extends AbstractGloblaEntity {
	

	@Column(name = "user_name", nullable = false, length = 20)
	private String userName;

	@Column(nullable = false, unique = true, length = 45)
	private String email;
	
	private String role;
	private Boolean enabled=true;

	@Column(nullable = false, length = 64)
	private String password;
	@OneToOne(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user") 
    @JsonIgnore
    private Set<Friend> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserApp user = (UserApp) o;
        return Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return 0;
    }

	public UserApp(String userName, String email, String password) {
		super();
		this.userName = userName;
		this.email = email;
		this.password = password;
	}

	public UserApp(String email, String password) {
		super();
		this.email = email;
		this.password = password;
	}

	

}
