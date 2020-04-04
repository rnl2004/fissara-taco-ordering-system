package com.fissara.taco.ordering.system.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "ingredient_tbl")
public class Ingredient extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="taco_id", nullable=false)
    private Taco taco;

}
