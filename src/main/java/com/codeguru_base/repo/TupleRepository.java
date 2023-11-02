/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.repo;

import com.codeguru_base.models.Mdl_tuple;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface TupleRepository extends JpaRepository<Mdl_tuple, Long> {

    @Query(value = "SELECT unit.id, unit.name, unit.structure_id, tuple.id, tuple.name as tuple_name FROM unit "
            + "         join structure on structure.id=unit.structure_id "
            + "         join tuple on unit.id=tuple.unit_id "
            + "         where unit.structure_id=?", nativeQuery = true)
    public List<Mdl_tuple> findUnitByStructure(long structure_id);
    
    @Query(value="SELECT t.* FROM tuple t where t.unit_id=?", nativeQuery = true)
    public List<Mdl_tuple> findTuplesByUnitid(long unitOd);
    
    @Query(value = "select t.* from tuple t"
            + " join unit u on u.id=t.unit_id"
            + " where u.id=? and t.name=?", nativeQuery = true)
    public Mdl_tuple findUniqueTupleByStructureUnit( long unitId,String tupleName);
    
}
