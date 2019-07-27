package com.jlo.petclinic.bootstrap;

import com.jlo.petclinic.model.*;
import com.jlo.petclinic.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private  final OwnerService ownerService;
    private  final VetService vetService;
    private  final PetTypeService petTypeService;
    private final SpecialtyService specialtyService;
   /* public DataLoader() {
        ownerService = new OwnerServiceMap();
        vetService = new VetSerciceMap();
    }*/
   public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
       this.ownerService = ownerService;
       this.vetService = vetService;
       this.petTypeService = petTypeService;
       this.specialtyService = specialtyService;
   }

    @Override
    public void run(String... args) throws Exception {
       int count = petTypeService.findAll().size();
       if(count==0){
           loadData();
       }

    }

    private void loadData() {
        PetType dog = new PetType();
        dog.setName("dog");
        PetType saveDogPetType = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("cat");
        PetType saveCatPetType = petTypeService.save(cat);

        Speciality radiology = new Speciality();
        radiology.setDescription("Radiology");
        Speciality saveRadiology = specialtyService.save(radiology);

        Speciality surgery = new Speciality();
        surgery.setDescription("Surgery");
        Speciality saveSurgery = specialtyService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry");
        Speciality saveDentistry= specialtyService.save(dentistry);


        Owner owner1 = new Owner();
        // owner1.setId(1L);
        owner1.setFirstName("Michael");
        owner1.setLastName("Weston");
        owner1.setAddress("direccion 123");
        owner1.setCity("Miami");
        owner1.setTelephone("123-4567890");

        Pet mikePet = new Pet();
        mikePet.setPetType(saveDogPetType);
        mikePet.setOwner(owner1);
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setName("Rosco");
        owner1.getPets().add(mikePet);

        ownerService.save(owner1);

        Owner owner2 = new Owner();
        // owner2.setId(2L);
        owner2.setFirstName("Fiona");
        owner2.setLastName("Glenanne");
        owner2.setAddress("direccion 123");
        owner2.setCity("Miami");
        owner2.setTelephone("123-4567890");

        Pet fionaCat = new Pet();
        fionaCat.setPetType(saveCatPetType);
        fionaCat.setOwner(owner2);
        fionaCat.setBirthDate(LocalDate.now());
        fionaCat.setName("gato");
        owner2.getPets().add(fionaCat);
        ownerService.save(owner2);

        System.out.println("Loader Owners ");

        Vet vet1 = new Vet();
        // vet1.setId(1L);
        vet1.setFirstName("Sam");
        vet1.setLastName("Axe");
        vet1.getSpecialities().add(saveRadiology);
        vetService.save(vet1);

        Vet vet2 = new Vet();
        //  vet2.setId(2L);
        vet2.setFirstName("Jesse");
        vet2.setLastName("Porte");
        vet2.getSpecialities().add(saveSurgery);
        vetService.save(vet2);

        System.out.println("Loader Vets ");
    }
}
