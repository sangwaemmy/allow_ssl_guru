/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.controller;

import com.codeguru_base.exception.ResourceNotFoundException;
import com.codeguru_base.models.DTO.StruDTO;
import com.codeguru_base.models.Mdl_structure;
import com.codeguru_base.models.Mdl_tuple;
import com.codeguru_base.models.Mdl_unit;
import com.codeguru_base.repo.AccountRepository;
import com.codeguru_base.repo.Account_categoryRepository;
import com.codeguru_base.repo.ProfileRepository;
import com.codeguru_base.repo.StructureRepository;
import com.codeguru_base.repo.TupleRepository;
import com.codeguru_base.repo.UnitRepository;
import com.codeguru_base.util.Utils;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.ResourceAccessException;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/guru/api/structure")
@Slf4j
public class StructureController {

    @Autowired
    TupleRepository tupleRepository;
    @Autowired
    StructureRepository structureRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    Account_categoryRepository account_categoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @ApiOperation("Getting all the structures Lazily")
    @GetMapping("/mappedDTO")
    public List<StruDTO> getAllLazy() {
        List<Mdl_structure> struc = new ArrayList<>();
        structureRepository.findAll().forEach(struc::add);
        return struc.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private StruDTO convertToDto(Mdl_structure mdl_structure) {
        return modelMapper.map(mdl_structure, StruDTO.class);
    }

    @ApiOperation("Getting all the structures only")
    @RequestMapping(value = "/")
    public ResponseEntity<List<Mdl_structure>> getAll() {
        List<Mdl_structure> struc = new ArrayList<>();
        structureRepository.findAll().forEach(struc::add);
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Getting a structure by id")
    @GetMapping("/{id}")
    public ResponseEntity<Mdl_structure> getStructureById(@PathVariable("id") long id) {
        Mdl_structure mdlmdl_structure = structureRepository.findById(id).orElseThrow(() -> new ResourceAccessException("The structure with id : " + id + " was not found "));
        return new ResponseEntity<>(mdlmdl_structure, HttpStatus.OK);
    }
    @ApiOperation("Creating a structure")
    @PostMapping("/")
    public ResponseEntity<Mdl_structure> createStructure(@RequestBody @Valid Mdl_structure mdl_structure) {
        Mdl_unit mdl_unit = null;
        structureRepository.save(mdl_structure);
        //Create the unit called account
        unitRepository.saveAndFlush(new Mdl_unit("account", 0, 0, mdl_structure));
        mdl_unit = unitRepository.findLastUnit();
        tupleRepository.saveAndFlush(new Mdl_tuple("id", "Integer", "Normal", "ID", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("username", "String", "Normal", "Username", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("password", "String", "Normal", "Username", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("account_category", "Integer", "Normal", "Account Category", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("profile", "Integer", "Normal", "Profile", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));

        //Create the unit called category
        unitRepository.saveAndFlush(new Mdl_unit("account_category", 0, 0, mdl_structure));
        mdl_unit = unitRepository.findLastUnit();
        tupleRepository.saveAndFlush(new Mdl_tuple("id", "Integer", "Normal", "ID", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("name", "String", "Normal", "Name", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));

        unitRepository.saveAndFlush(new Mdl_unit("profile", 0, 0, mdl_structure));
        mdl_unit = unitRepository.findLastUnit();
        tupleRepository.saveAndFlush(new Mdl_tuple("id", "Integer", "Normal", "ID", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("name", "String", "Normal", "Name", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("surname", "String", "Normal", "Name", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));
        tupleRepository.saveAndFlush(new Mdl_tuple("gender", "String", "Normal", "Gender", new Utils().getCurrentDate(), new Utils().getCurrentDate(), mdl_unit));

        //Create the unit called category
        //Create the unit called profile
        return new ResponseEntity<>(mdl_structure, HttpStatus.CREATED);
    }

    @ApiOperation("Units, Tuples By structure")
    @GetMapping("/unitsByStructure/{structure_id}")
    public ResponseEntity<List<Mdl_tuple>> getUnitByStructure(@PathVariable("structure_id") int structure_id) {
        List<Mdl_tuple> mdl_tuple = tupleRepository.findUnitByStructure(structure_id);
        return new ResponseEntity<>(mdl_tuple, HttpStatus.OK);
    }

    @ApiOperation(value = "Updating  a single Structure")
    @PutMapping("/{id}")
    public ResponseEntity<Mdl_structure> updateStructure(@PathVariable(value = "id") long id, @RequestBody Mdl_structure mdl_structure) {
        Mdl_structure mdl_structure1 = structureRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Structure not found"));
        mdl_structure1.setDb_name(mdl_structure.getDb_name());
        mdl_structure1.setDb_password(mdl_structure.getDb_password());
        mdl_structure1.setDb_user(mdl_structure.getDb_user());
        mdl_structure1.setDelivery_date(mdl_structure.getDelivery_date());
        mdl_structure1.setEntry_date(mdl_structure.getEntry_date());
        mdl_structure1.setPgm_language(mdl_structure.getPgm_language());
        mdl_structure1.setPlatform(mdl_structure.getPlatform());
        mdl_structure1.setStart_time(mdl_structure.getStart_time());
        
        mdl_structure1.setStart_time(mdl_structure.getStart_time());
        mdl_structure1.setStart_time(mdl_structure.getStart_time());
        mdl_structure1.setStart_time(mdl_structure.getStart_time());
        mdl_structure1.setUser(mdl_structure.getUser());
        //POM.XML
        mdl_structure.setSpringVersion(mdl_structure.getSpringVersion());
        mdl_structure.setAppgroup(mdl_structure.getAppgroup());
        mdl_structure.setArtifact(mdl_structure.getArtifact());
        mdl_structure.setArtifact(mdl_structure.getArtifact());
        mdl_structure.setAppVersion(mdl_structure.getAppVersion());
        mdl_structure.setDescription(mdl_structure.getDescription());
        mdl_structure.setJava_version(mdl_structure.getJava_version()) ;
        mdl_structure.setPort(mdl_structure.getPort()) ;
             
        return new ResponseEntity<>(structureRepository.save(mdl_structure), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        structureRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
