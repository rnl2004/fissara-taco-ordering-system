package com.fissara.taco.ordering.system.model;

import com.fissara.taco.ordering.system.model.audit.UserDateAudit;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@ToString
@Entity
@Table(name = "order_tbl")
public class Order extends UserDateAudit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="customer_id", nullable=false)
    private Customer customer;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order")
    private List<Taco> tacos;
}
