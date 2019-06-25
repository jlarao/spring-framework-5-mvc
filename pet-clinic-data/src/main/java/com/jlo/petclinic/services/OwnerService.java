package com.jlo.petclinic.services;

import com.jlo.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastName);
}
