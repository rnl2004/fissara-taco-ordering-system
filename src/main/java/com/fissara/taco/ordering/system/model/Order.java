package com.fissara.taco.ordering.system.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "order_tbl")
public class Order extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(targetEntity = Order.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Taco> tacos;
}
