package com.fissara.taco.ordering.system.controller;

import com.fissara.taco.ordering.system.model.Taco;
import com.fissara.taco.ordering.system.service.TacoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/taco")
public class TacoController {

    @Autowired
    private TacoService tacoService;

    @PostMapping("/create")
    public Taco getAllTacos(@RequestBody String name) {
        Taco taco = new Taco();
        taco.setName(name);
        return tacoService.save(taco);
    }
}
