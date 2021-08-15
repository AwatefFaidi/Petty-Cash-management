package org.sid.pettycach.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor 
public class App_Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String roleName;
    public App_Role(String roleName)
    {
    	this.roleName=roleName;
    }
    @Override
	public String toString() {
		return this.roleName;
	}

}
