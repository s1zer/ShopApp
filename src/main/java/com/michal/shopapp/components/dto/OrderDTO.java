package com.michal.shopapp.components.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    private Long id;
    private String orderStatus;
    private String orderDate;
    private String orderCompletionDate;
    private List<ProductDTO> products;
    private UserDTO userDTO;
}
