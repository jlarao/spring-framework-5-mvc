package com.jlo.petclinic.bootstrap;

import com.jlo.petclinic.model.Owner;
import com.jlo.petclinic.model.Vet;
import com.jlo.petclinic.services.OwnerService;
import com.jlo.petclinic.services.VetService;
import com.jlo.petclinic.services.map.OwnerServiceMap;
import com.jlo.petclinic.services.map.VetSerciceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.tools.jar.CommandLine;

@Component
public class DataLoader implements CommandLineRunner {
    private  final OwnerService ownerService;
    private  final VetService vetService;

   /* public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetSerciceMap();
    }*/
   public DataLoader(OwnerService ownerService, VetService vetService) {
       this.ownerService = ownerService;
       this.vetService = vetService;
   }

    @Override
    public void run(String... args) throws Exception {
        Owner owner1 = new Owner();
       // owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        ownerService.save(owner1);

        Owner owner2 = new Owner();
       // owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        ownerService.save(owner2);

        System.out.println("Loader Owners ");

        Vet vet1 = new Vet();
       // vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vetService.save(vet1);

        Vet vet2 = new Vet();
      //  vet2.setId(2L);
        vet2.setFirstName("Jesse");
        vet2.setLastName("Porte");
        vetService.save(vet2);

        System.out.println("Loader Vets ");
    }
}
