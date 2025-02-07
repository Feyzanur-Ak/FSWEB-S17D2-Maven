package com.workintech.s17d2.rest;


import com.workintech.s17d2.model.*;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/workintech")
public class DeveloperController {

    private final Map<Integer, Developer> developers=new HashMap<>();
    private final Taxable taxable;

    public DeveloperController(Taxable taxable) {
        this.taxable = taxable;
    }

    @PostConstruct
    public void init() {
        developers.put(1, new JuniorDeveloper(1, "Alice", 8500 * (1 - taxable.getSimpleTaxRate()),Experience.JUNIOR));
        developers.put(2, new MidDeveloper(2, "Bob", 12000 * (1 - taxable.getMiddleTaxRate()),Experience.MID));
        developers.put(3, new SeniorDeveloper(3, "Charlie", 20000 * (1 - taxable.getUpperTaxRate()),Experience.SENIOR));
    }

    @GetMapping("/developers")
    public List<Developer> fullShow(){
        return  developers.values().stream().toList();
    }

    @GetMapping("/developers/{id}")
    public Developer  getDeveloperById(@PathVariable int id) {
      return developers.get(id);

    }

    @PostMapping("/developers")
    public  String addDeveloper(@RequestBody Developer developer) {
        if(developers.containsKey((developer.getId()))){
            return "Developer with this ID already exists!";
        }

        Developer newDeveloper;
        switch (developer.getExperience()){
            case JUNIOR:
                newDeveloper=new JuniorDeveloper(developer.getId(), developer.getName(),
                        developer.getSalary()*(1- taxable.getSimpleTaxRate()), Experience.JUNIOR);
                break;

            case MID:
                newDeveloper=new MidDeveloper(developer.getId(), developer.getName(),
                        developer.getSalary()*(1- taxable.getMiddleTaxRate()), Experience.MID);
                break;

            case SENIOR:
                newDeveloper=new SeniorDeveloper(developer.getId(), developer.getName(),
                        developer.getSalary()*(1- taxable.getUpperTaxRate()), Experience.SENIOR);
                break;

            default:
                return "Invalid experience type!";
        }
        developers.put(developer.getId(), newDeveloper);
        return "Developer added successfully!";
    }

    @PutMapping("/{id}")
    public String updateDeveloper(@PathVariable int id, @RequestBody Developer developer) {
        if (!developers.containsKey(id)) {
            return "Developer not found!";
        }
        developers.put(id, developer);
        return "Developer updated successfully!";
    }

    @DeleteMapping("/{id}")
    public String deleteDeveloper(@PathVariable int id) {
        if (developers.remove(id) != null) {
            return "Developer deleted successfully!";
        } else {
            return "Developer not found!";
        }
    }
}
