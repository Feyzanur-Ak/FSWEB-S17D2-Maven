package com.workintech.s17d2.model;

import com.workintech.s17d2.tax.Taxable;

public class DeveloperFactory {

    public static Developer createDeveloper(Developer developer, Taxable taxable) {
        Developer createDeveloper = null;

  if (developer.getExperience().equals(Experience.JUNIOR)) {
       createDeveloper=new JuniorDeveloper(developer.getId(),
      developer.getName(),
     developer.getSalary()-(developer.getSalary()*taxable.getSimpleTaxRate()/100));

        }

        if (developer.getExperience().equals(Experience.JUNIOR)) {
            createDeveloper=new MidDeveloper(developer.getId(),
                    developer.getName(),
                    developer.getSalary()-(developer.getSalary()*taxable.getMiddleTaxRate()/100));

        }


        if (developer.getExperience().equals(Experience.JUNIOR)) {
            createDeveloper=new SeniorDeveloper(developer.getId(),
                    developer.getName(),
                    developer.getSalary()-(developer.getSalary()*taxable.getUpperTaxRate()/100));

        } else {
            System.out.println("unknow ecperience");
        }

          return  createDeveloper;
    }

}