package com.michal.shopapp.components.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "shop_order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String orderDate;
    private String orderCompletionDate;
    @OneToMany(mappedBy = "order")
    private List<Product> products;
    @ManyToOne
    private User user;

}
