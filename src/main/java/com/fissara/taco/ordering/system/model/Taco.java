package com.fissara.taco.ordering.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "taco_tbl")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Taco extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    @Length(min = 5, message = "*Taco name must have at least 5 characters")
    private String name;

    @ManyToOne
    @JoinColumn(name="order_id", nullable=false)
    private Order order;

    @OneToMany(targetEntity = Taco.class, cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

}
