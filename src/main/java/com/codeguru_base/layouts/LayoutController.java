/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import com.codeguru_base.exception.ResourceNotFoundException;
import com.codeguru_base.models.Mdl_unit;
import com.codeguru_base.repo.UnitRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
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

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@RestController
@RequestMapping("/guru/api/layout")
@Slf4j
public class LayoutController {

    @Autowired
    LayoutRepo layoutrepository;

    @Autowired
    ParentRepository parentreposiroty;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    UnitRepository unitRepository;

    @ApiOperation(value = "Retrieve all layouts")
    @GetMapping()
    public List<Mdl_Layout> getLayouts() {
        List<Mdl_Layout> allLayout = new ArrayList<>();
        layoutrepository.findAll().forEach(allLayout::add);
        return allLayout;
    }

    @ApiOperation(value = "Retrieve duplciated tuple names by layout and structure")
    @GetMapping("/{structureId}/layoutId")
    public List<Mdl_Layout> getDuplicateTupleNames(@PathVariable("structureId") long structureId, @PathVariable("layoutId") long layoutId) {
        List<Mdl_Layout> allLayout = new ArrayList<>();
        layoutrepository.findAll().forEach(allLayout::add);
        return allLayout;
    }

    @ApiOperation(value = "Retrieve parents of a unit")
    @GetMapping("/{unitId}/structureId")
    public List<Mdl_Layout> getParentsUnits(@PathVariable("unitId") long unitId, @PathVariable("structureId") long structureId) {
        List<Mdl_Layout> allLayout = new ArrayList<>();
        layoutrepository.findAll().forEach(allLayout::add);
        return allLayout;
    }

    @PostMapping("/add")
    public ResponseEntity<Mdl_Layout> createLayout(@RequestBody @Valid Mdl_Layout mdl_layout) {
        return new ResponseEntity<>(layoutrepository.save(mdl_layout), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single layout")
    public ResponseEntity<Mdl_Layout> updateLayout(@PathVariable(value = "id") long id, @RequestBody Mdl_Layout mdl_layout) {
        Mdl_Layout mdl_layout2 = layoutrepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Layout with id " + id + "  not found"));
        mdl_layout.setName(mdl_layout.getName());
        return new ResponseEntity<>(layoutrepository.save(null), HttpStatus.OK);
    }

    @DeleteMapping("/layouts/{id}")
    @ApiOperation(value = "Deleting  a single layout")
    public ResponseEntity<HttpStatus> deleteLayout(@PathVariable("id") long id) {
        layoutrepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //<editor-fold defaultstate="collapsed" desc="--------------Parent and Child--------------">
    @PostMapping("/saveChildandParent")
    public ResponseEntity<String> parentChildFields(@RequestBody @Valid ParentChildFields parentChildFields) {
        //get the child unit
        Mdl_unit ChildUnit = unitRepository.findById(parentChildFields.getUnit_id()).orElseThrow(() -> new ResourceNotFoundException("The unit with id " + parentChildFields.getUnit_id()));
        Mdl_parents p = new Mdl_parents(parentChildFields.getParentField_Id(), ChildUnit);
        parentreposiroty.save(p);
        //-------
        Mdl_unit parentUnit = unitRepository.findById(parentChildFields.getParentField_Id()).orElseThrow(() -> new ResourceNotFoundException("The unit with id " + parentChildFields.getUnit_id()));
        Mdl_children c = new Mdl_children(parentChildFields.getUnit_id(), parentUnit);
        childRepository.save(c);
        return new ResponseEntity<>("save", HttpStatus.CREATED);
    }

//    @ApiOperation(value = "Retrieve all layouts")
//    @GetMapping("/{structureId}/{unitId}")
//    public List<DTOParentChild> getParentAndChilds(@PathVariable("v") long structureId, @PathVariable("unitId") long unitId) {
//        List<DTOParentChild> parentchildrend = new ArrayList<>();
//        layoutrepository.findParentsOfAUnit(structureId, unitId).forEach(parentchildrend::add);
//        return parentchildrend;
//    }

//</editor-fold>
}
