package com.workintech.s17d2.model;

import org.springframework.stereotype.Component;

@Component
public class JuniorDeveloper extends  Developer {
    public JuniorDeveloper(int id, String name, Double salary, Experience experience) {
        super(id, name, salary, Experience.JUNIOR);
    }
}
