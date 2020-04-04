package com.fissara.taco.ordering.system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TacoDetail {

    private Long id;
    private String name;
    private Date createdAt;
    private List<IngredientDetail> ingredients;
}
