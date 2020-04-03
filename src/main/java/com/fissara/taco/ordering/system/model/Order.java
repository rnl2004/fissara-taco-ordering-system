package com.fissara.taco.ordering.system.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode
@ToString
@Entity
@Table(name = "order_tbl")
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Order extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(targetEntity = Order.class, cascade = CascadeType.ALL)
    private List<Taco> tacos;
}
