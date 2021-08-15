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
@Data @NoArgsConstructor @AllArgsConstructor 
public class ExpenseHead {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
	 private String description;
	public ExpenseHead(String description) {
		super();
		this.description = description;
	}
	@Override
	public String toString() {
		return this.description;
	}
	

}
