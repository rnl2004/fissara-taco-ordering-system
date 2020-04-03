package com.fissara.taco.ordering.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "ingredient_tbl")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Ingredient extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name="taco_id", nullable=false)
    private Taco taco;

}
