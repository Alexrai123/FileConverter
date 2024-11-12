package com.example.demo.SubscriptionType;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/subscriptionTypes")
public class SubscriptionTypeController {

    private final SubscriptionTypeService subscriptionTypeService;


    /////////////////////////////////////////////////////////////
    @GetMapping("/test")
    public String getSubscriptionTypes(Model model) {
        List<SubscriptionType> subscriptionTypes = subscriptionTypeService.getAll();
        model.addAttribute("subscriptionTypes", subscriptionTypes);
        return "subscriptionTypes";
    }
    ////////////////////////////////////////////////////////////

    @GetMapping
    public List<SubscriptionType> getAll() {
        return subscriptionTypeService.getAll();
    }

    @GetMapping("/all")
    public List<SubscriptionTypeDTO> getAllDTO() {
        return subscriptionTypeService.getAllDTO();
    }

    @GetMapping("/{typeName}")
    public SubscriptionType getSubscriptionTypeByName(@PathVariable String typeName) {
        return subscriptionTypeService.getSubscriptionTypeByName(typeName);
    }

    @PostMapping()
    public SubscriptionType create(@RequestBody SubscriptionTypeDTO subscriptionType) {
        return subscriptionTypeService.create(subscriptionType);
    }

    @PutMapping("/{idSubscriptionType}")
    public SubscriptionType update(@PathVariable Integer idSubscriptionType, @RequestBody SubscriptionTypeDTO subscriptionTypeDTO) {
        return subscriptionTypeService.update(idSubscriptionType, subscriptionTypeDTO);
    }


    @DeleteMapping("/{idSubscriptionType}")
    public void delete(@PathVariable Integer idSubscriptionType) throws Exception {
        subscriptionTypeService.delete(idSubscriptionType);
    }
}
