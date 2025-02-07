package com.workintech.s17d2.rest;

import com.workintech.s17d2.model.Developer;
import com.workintech.s17d2.model.Experience;
import com.workintech.s17d2.tax.Taxable;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/developers")
public class DeveloperController {


   public Map<Integer, Developer> developers;
   private Taxable taxable;


   @Autowired
   public DeveloperController(Taxable taxable){ //Dependency injection yapıyoruz
       this.taxable=taxable;
       //aslında böyle yaparak DeveloperTax ı kullanılır hale getiriyorum
       //birden fazla olsaydı Qualifier("developerTax") Taxable taxable yapmalıydık
   }

   @PostConstruct   // uygulama açıldığı an oluşmasını istediğimiz değerler
    public void init(){
       this.developers=new HashMap<>();
       this.developers.put(1,new Developer(1,"FEYZA",100d, Experience.JUNIOR));
   }



}
