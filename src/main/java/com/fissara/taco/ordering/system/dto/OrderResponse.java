package com.fissara.taco.ordering.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class OrderResponse {

    private Long orderId;
    private Date createAt;
    private String message;
}
