/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.codeguru_base.layouts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author HP
 */
@Repository
public interface ParentRepository extends JpaRepository<Mdl_parents, Long> {

    @Query(  " SELECT new com.codeguru_base.layouts.DTOParent"
            + "(p.id, p.unitParentId , u.name) from Mdl_parents p"
            + " join p.unitChildId u "
            + " join u.mdl_structure s "
            + " where s.id=?1 and u.id=?2" )
    public List<DTOParent> findAllParentsByUnitId(long structureId, long unitId);

    
    
    
    
}
