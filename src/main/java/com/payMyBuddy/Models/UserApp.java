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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class UserApp extends AbstractGlobalClass {

    @Column(name = "user_name")
    private String userName;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @OneToOne(mappedBy = "user", orphanRemoval = true, 
    		   fetch = FetchType.LAZY, 
    		   cascade = CascadeType.ALL)
    private Account account;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "user") 
    @JsonIgnore
    private Set<Friend> friends;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        UserApp user = (UserApp ) o;
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

	

	
}