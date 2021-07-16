package org.sid.pettycach.entity.master;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor @ToString
public class Receivers {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	private String receiver_name;

}
