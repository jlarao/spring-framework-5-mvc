package com.jlo.petclinic.controllers;

import com.jlo.petclinic.model.Owner;
import com.jlo.petclinic.services.OwnerService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RequestMapping("/owners")
@Controller
public class OwnerController {

    private static final String VIEWS_OWNER_CREATE_OR_UPDATE_FORM ="owners/createOrUpdateOwnerForm";

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @InitBinder
    public void setAllowedField(WebDataBinder dataBinder ){
        dataBinder.setDisallowedFields("id");
    }

    //@RequestMapping({"","/","/index","/index.html"})
   // public String listOwners(Model model){
   //     model.addAttribute("owners", ownerService.findAll());
   //     return "owners/index";
  //  }

    @RequestMapping("/find")
    public String findOwners(Model model){

        model.addAttribute("owner", Owner.builder().build());
        return "owners/findOwners";
    }

    @GetMapping
    public String processFindForm(Owner owner, BindingResult result, Model model){
        if(owner.getLastName()==null){
            owner.setLastName("");//empty string signifies broadest possible search
        }
        //find owners by last name
        //List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        //List<Owner> results = ownerService.findAllByLastNameLike("%"+owner.getLastName()+"%");
        List<Owner> results = ownerService.findAllByLastNameLike(owner.getLastName());
        if(results.isEmpty()){
            result.rejectValue("lastName","notFound","not found");
            return "owners/findOwners";
        }else if(results.size()==1){
          //  owner = results.iterator().next();
            owner = results.get(0);
            return "redirect:/owners/"+ owner.getId();
        }else{
            model.addAttribute("selections",results);
            return "owners/ownersList";
        }
    }
    @GetMapping("/{ownerId}")
    public ModelAndView showOwner(@PathVariable Long ownerId){
        ModelAndView mav = new ModelAndView("owners/ownerDetails");
        mav.addObject(ownerService.findById(ownerId));

        return mav;
    }

    @GetMapping("/new")
    public  String initCreationForm(Model model){
        model.addAttribute("owner",Owner.builder().build());
        return  VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/new")
    public String processCreationForm(@Valid Owner owner, BindingResult result){
        if(result.hasErrors()){
            return  VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            Owner saveOwner = ownerService.save(owner);
            return "redirect:/owners/"+ saveOwner.getId();
        }
    }

    @GetMapping("/{ownerId}/edit")
    public String initUpdateOwnerform(@PathVariable Long ownerId, Model model){
        model.addAttribute(ownerService.findById(ownerId));
        return VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
    }

    @PostMapping("/{ownerId}/edit")
    public String processUpdateForm(@Valid Owner owner, BindingResult result, @PathVariable Long ownerId){
        if(result.hasErrors()){
            return  VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
        }else {
            owner.setId(ownerId);
            Owner savedOwner = ownerService.save(owner);
            return "redirect:/owners/" + savedOwner.getId();
        }
    }

}
