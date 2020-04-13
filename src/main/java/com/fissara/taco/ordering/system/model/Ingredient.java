package com.fissara.taco.ordering.system.model;

import com.fissara.taco.ordering.system.model.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "ingredient_tbl")
public class Ingredient extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="taco_id", nullable=false)
    private Taco taco;

}
