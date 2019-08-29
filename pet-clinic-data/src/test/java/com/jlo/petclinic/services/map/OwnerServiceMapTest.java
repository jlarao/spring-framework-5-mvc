package com.jlo.petclinic.services.map;

import com.jlo.petclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {
    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Smith";

    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetServiceMap());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
        //ownerServiceMap.save(Owner.builder().firstName("' Smith '").address("direccion").build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1,ownerSet.size());
    }

    @Test
    void findById() {
        Owner owner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, owner.getId());
    }

    @Test
    void saveExistingId() {
        Long id2 = 2L;
        Owner owner2 = Owner.builder().id(id2).build();
        Owner saveOwner = ownerServiceMap.save(owner2);
        assertEquals(id2, saveOwner.getId());
    }
    @Test
    void saveNoId(){
        Owner owner = Owner.builder().build();
        Owner owner1 = ownerServiceMap.save(owner);
        assertNotNull(owner1);
        assertNotNull(owner1.getId());
    }

    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0,ownerServiceMap.findAll().size());
    }

   /* @Test
    void findByLastName() {
        Owner ownLN = ownerServiceMap.findByLastName(lastName);
        assertNotNull(ownLN);
        assertEquals(ownerId,ownLN.getId());
    }*/

   /* @Test
    void findByLastNameNotFound() {
        Owner smith = ownerServiceMap.findByLastName("not");
        assertNull(smith);
    }*/
}