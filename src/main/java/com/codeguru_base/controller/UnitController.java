/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.controller;

import com.codeguru_base.exception.ResourceNotFoundException;
import com.codeguru_base.models.DTO.MultipleUnits;
import com.codeguru_base.models.DTO.UnitIdUnitNameDTO;
import com.codeguru_base.models.DTO.UnitsTuplesDTO;
import com.codeguru_base.models.DTO.UnitsTuplesNoIdNoNameDTO;
import com.codeguru_base.models.Mdl_structure;
import com.codeguru_base.models.Mdl_tuple;
import com.codeguru_base.models.Mdl_unit;
import com.codeguru_base.repo.StructureRepository;
import com.codeguru_base.repo.TupleRepository;
import com.codeguru_base.repo.UnitRepository;
import com.codeguru_base.util.Utils;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
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
@RequestMapping("/guru/api/unit")
@Slf4j
public class UnitController {

    @Autowired
    UnitRepository unitRepository;
    @Autowired
    StructureRepository structureRepository;

    @Autowired
    TupleRepository tupleRepository;

    @ApiOperation(value = "Retrieve all units")
    @GetMapping()
    public List<Mdl_unit> getAll() {
        List<Mdl_unit> units = new ArrayList<>();
        unitRepository.findAll().forEach(units::add);
        return units;
    }

    @ApiOperation(value = "Get a single Unit by id")
    @GetMapping("/{id}")
    public ResponseEntity< Mdl_unit> getUnitById(@PathVariable(name = "id") long id) {
        Mdl_unit unit = unitRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Unit with id" + id + " was not found"));
        return new ResponseEntity<>(unit, HttpStatus.OK);
    }

    @ApiOperation(value = "Get a single Unit by strcture id")
    @GetMapping("/unitBystructure/{structure_id}")
    public ResponseEntity<Map<String, Object>> findUnitByStrcture(@PathVariable(name = "structure_id") long structure_id) {
        System.out.println("-----------------------Did we get the units by structure??? ----------------------------------");
        Map<String, Object> response = new HashMap<>();
        List<UnitIdUnitNameDTO> unitWithNotTuples = unitRepository.findUnitWithoutTuples(structure_id);
        String allUnitsSupplied = unitWithNotTuples.size() > 0 ? "hasUnitsWithNoTuples" : "AllUnitsSupplied";
        response.put("res_status", allUnitsSupplied);
        response.put("unsupliedUnits", unitWithNotTuples);
        List<Mdl_unit> units = unitRepository.findUnitsByStrctureid(structure_id);
        response.put("otherUnits", units);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @ApiOperation(value = "Update the unit bu its name")
    @GetMapping("/update/unitbyid/{name}/{unit_id}")
    public ResponseEntity<Mdl_unit> findUnitByname(@PathVariable(name = "name") String name, @PathVariable("unit_id") long unit_id) {
        Mdl_unit mdl_unit = unitRepository.findById(unit_id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit with id= " + unit_id + " not found"));
        mdl_unit.setName(name);
        unitRepository.save(mdl_unit);
        return new ResponseEntity<>(unitRepository.save(mdl_unit), HttpStatus.OK);
    }

    @ApiOperation("Creating a unit")
    @PostMapping("/{structureId}")
    public ResponseEntity<Map<String, Object>> createUnit(@RequestBody @Valid Mdl_unit mdl_unit, @PathVariable(value = "structureId") long structureId) {
        Map<String, Object> response = new HashMap<>();
        if (unitRepository.findUnitNameByStructure(structureId, mdl_unit.getName()) != null) {
            if (!"".equals(unitRepository.findUnitNameByStructure(structureId, mdl_unit.getName()).getName())) {
                response.put("status", "unit already exists");
            }
        } else {
            response.put("status", "OK");
            response.put("data", mdl_unit);
            Mdl_structure mdl_structure = structureRepository.findById(structureId)
                    .orElseThrow(() -> new ResourceNotFoundException("Not found Structure with id = " + structureId));
            mdl_unit.setMdl_structure(mdl_structure);
            unitRepository.saveAndFlush(mdl_unit);
            Mdl_unit LastMdl_unit = unitRepository.findLastUnit();
            tupleRepository.saveAndFlush(new Mdl_tuple("id", "Integer", "Normal", "ID", new Utils().getCurrentDate(), new Utils().getCurrentDate(), LastMdl_unit));
        }
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Updating  a single Unit")
    @PutMapping("/{id}")
    public ResponseEntity<Mdl_unit> updateUnit(@PathVariable(value = "id") long id, @RequestBody Mdl_unit unitRequest) {
        Mdl_unit mdl_unit = unitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Unit with id= " + id + " not found"));
        mdl_unit.setName(unitRequest.getName());
        mdl_unit.setNumber_children(unitRequest.getNumber_children());
        mdl_unit.setNumber_parent(unitRequest.getNumber_parent());
        return new ResponseEntity<>(unitRepository.save(mdl_unit), HttpStatus.OK);
    }

    @ApiOperation("Deleting a single Unit")
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUnit(@PathVariable("id") long id
    ) {
        unitRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("multiUnits")
    public ResponseEntity<String> new_f_layout_unit_template(@RequestBody MultipleUnits multipleUnits
    ) {
        System.out.println("WE are saving the unit_template: " + this.getClass().getName());
        List<Mdl_unit> unitsList = multipleUnits.getMultiUnits();
        try {
            for (Mdl_unit mdl_unit : unitsList) {
                unitRepository.save(mdl_unit);
            }
            return new ResponseEntity<>("Saved", HttpStatus.OK);

        } catch (Exception e) {
            System.out.println("Error in: " + UnitController.class.getName() + " - " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
