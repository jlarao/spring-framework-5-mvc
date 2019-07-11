package com.jlo.petclinic.services.map;

import com.jlo.petclinic.model.Vet;
import com.jlo.petclinic.services.CrudService;
import com.jlo.petclinic.services.VetService;

import java.util.Set;

public class VetSerciceMap  extends AbstractMapService<Vet, Long> implements VetService {
    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
