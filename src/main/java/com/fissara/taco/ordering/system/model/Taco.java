package com.fissara.taco.ordering.system.model;

import com.fissara.taco.ordering.system.model.audit.DateAudit;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "taco_tbl")
public class Taco extends DateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Length(min = 5, message = "*Taco name must have at least 5 characters")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @OneToMany(targetEntity = Taco.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

}
