package com.jlo.petclinic.formatters;

import com.jlo.petclinic.model.PetType;
import com.jlo.petclinic.services.PetTypeService;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

@Component
public class PetTypeFormatters  implements Formatter<PetType> {

    private final PetTypeService petTypeService;

    public PetTypeFormatters(PetTypeService petTypeService) {
        this.petTypeService = petTypeService;
    }
    @Override
    public String print(PetType petType, Locale locale) {
        return petType.getName();
    }

    @Override
    public PetType parse(String text, Locale locale) throws ParseException {
        Collection<PetType> findPetType = petTypeService.findAll();

        for(PetType type: findPetType){
            if(type.getName().equals(text)){
                return type;
            }
        }
        throw new ParseException("type not found " + text,0);
    }

}
