package org.sid.pettycach.entity.master;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

import org.sid.pettycach.entity.AppUser;

import java.util.ArrayList;
import java.util.Collection;


@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountname;
    private double openingbalance;
    @ManyToMany(cascade = {
            CascadeType.ALL
        })
        @JoinTable(
            name = "accounts_users",
            joinColumns = {
                @JoinColumn(name = "account_id")
            },
            inverseJoinColumns = {
                @JoinColumn(name = "user_id")
            }
        )
    private Collection<AppUser> users=new ArrayList<>();

}
