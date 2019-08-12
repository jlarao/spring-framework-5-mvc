package com.jlo.petclinic.services.map;

import com.jlo.petclinic.model.Speciality;
import com.jlo.petclinic.model.Vet;
import com.jlo.petclinic.services.SpecialtyService;
import com.jlo.petclinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","map"})
public class VetSerciceMap  extends AbstractMapService<Vet, Long> implements VetService {
    private final SpecialtyService specialtyService;

    public VetSerciceMap(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }


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

        if(object.getSpecialities().size()==0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId()==null){
                    Speciality savedSpecialty = specialtyService.save(speciality);
                    speciality.setId(savedSpecialty.getId());
                }
            }
            );
        }
        return super.save( object);
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
