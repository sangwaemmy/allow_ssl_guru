/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.controller;
import com.codeguru_base.exception.ResourceNotFoundException;
import com.codeguru_base.models.Mdl_tuple;
import com.codeguru_base.models.Mdl_unit;
import com.codeguru_base.repo.TupleRepository;
import com.codeguru_base.repo.UnitRepository;
import com.codeguru_base.util.Utils;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
@RequestMapping("/guru/api/tuple")
@Slf4j
public class TupleController {

    @Autowired
    TupleRepository tupleRepository;
    @Autowired
    UnitRepository unitRepository;

    @ApiOperation(value = "Retrieve all Tuples")
    @GetMapping()
    public ResponseEntity< List<Mdl_tuple>> getVehicle() {
        List<Mdl_tuple> alltuples = new ArrayList<>();
        tupleRepository.findAll().forEach(alltuples::add);
        return new ResponseEntity<>(alltuples, HttpStatus.OK);
    }

    @ApiOperation("Retreving a single tuple")
    @GetMapping("/singleTuple/{tupleId}")
    public ResponseEntity<  Mdl_tuple> updateTupleById(@PathVariable(name = "tupleId") long tupleId) {
        Mdl_tuple mdl_tuple = tupleRepository.findById(tupleId).orElseThrow(() -> new ResourceAccessException("Tuple with the fiven id " + tupleId + " was not found!!"));
        return new ResponseEntity<>(mdl_tuple, HttpStatus.CREATED);
    }

    @ApiOperation("Create tuple")
    @GetMapping("/tupleByunitid/{unitId}")
    public ResponseEntity<List<Mdl_tuple>> findTuplesByUnitid(@PathVariable(name = "unitId") long unitId) {
        
        return new ResponseEntity<>(tupleRepository.findTuplesByUnitid(unitId), HttpStatus.CREATED);
    }

    @ApiOperation("Create tuple")
    @PostMapping("/{unitId}")
    public ResponseEntity<Map<String, Object>> createUser(@PathVariable(name = "unitId") long unitId, @RequestBody @Valid Mdl_tuple mdl_tuple) {
        Map<String, Object> tupleFound = new HashMap<>();
        if (tupleRepository.findUniqueTupleByStructureUnit(unitId, mdl_tuple.getName()) !=null  ) {
            tupleFound.put("status", "tuple already exists");
        } else {
            Mdl_unit mdl_unit = unitRepository.findById(unitId).orElseThrow(() -> new ResourceAccessException("The unit with an id of " + unitId + " was not found "));
            mdl_tuple.setMdl_unit(mdl_unit);
            mdl_tuple.setToday_date(new Utils().getCurrentDateTime());
            mdl_tuple.setDisplay_caption(Utils.get_CapRemoveUnderscore(mdl_tuple.getName()));// change to the proper display caption that will be displayed to the UI
            tupleRepository.save(mdl_tuple);
            tupleFound.put("status", "OK");
            tupleFound.put("data", tupleRepository.save(mdl_tuple));
        }
        return new ResponseEntity<>(tupleFound, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Updating  a single Mdl_tuple")
    public ResponseEntity<Mdl_tuple> updateMdl_tuple(@PathVariable(value = "id") long id, @RequestBody Mdl_tuple mdl_tupleRequest) {
        Mdl_tuple mdl_tuple = tupleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("tuple not found"));
        mdl_tuple.setCategory(mdl_tupleRequest.getCategory());
        mdl_tuple.setData_type(mdl_tupleRequest.getData_type());
        mdl_tuple.setDisplay_caption(mdl_tupleRequest.getDisplay_caption());
        mdl_tuple.setName(mdl_tupleRequest.getName());
        
        return new ResponseEntity<>(tupleRepository.save(mdl_tuple), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTag(@PathVariable("id") long id) {
        tupleRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
