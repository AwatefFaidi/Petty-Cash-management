package org.sid.pettycach.entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor 

public class AppUser
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    @Enumerated(EnumType.STRING)
    private UserStatus userstatus;
    @Enumerated(EnumType.STRING)
    private Userverified userverified;
    private Date last_activity;	
    private String name;
    
    
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Transient
    private String passwordConfirm;
    
    @ManyToMany(fetch=FetchType.EAGER)
        @JoinTable(
            name = "users_roles",
            joinColumns = {
                @JoinColumn(name = "user_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "role_id")
            }
        )
    private Collection<App_Role> roles=new ArrayList<>();

	@Override
	public String toString() {
		return this.username;
	}

}
  
  
