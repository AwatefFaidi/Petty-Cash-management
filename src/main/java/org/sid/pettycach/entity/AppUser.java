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
@Data @NoArgsConstructor @AllArgsConstructor @ToString

public class AppUser
{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    //private String status;
    //private String verified;
    @Enumerated(EnumType.STRING)
    private UserStatus userstatus;
    @Enumerated(EnumType.STRING)
    private Userverified userverified;
    // @Temporal(TemporalType.TIMESTAMP)
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
/* public AppUser(String username, UserStatus userstatus, Userverified userverified, Date  last_activity, String name,Collection<App_Role> roles )
 {
	 this.username=username;
	 this.userstatus=userstatus;
	 this.userverified=userverified;
	 this.last_activity=last_activity;
	 this.name=name;
	 this.roles=roles;
 }*/
}
  
  
