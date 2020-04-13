package com.fissara.taco.ordering.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fissara.taco.ordering.system.model.audit.DateAudit;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity
@Table(name = "customer_tbl")
public class Customer extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

}
