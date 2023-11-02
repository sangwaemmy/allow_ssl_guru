/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface ChildRepository extends JpaRepository<Mdl_children, Long> {

    @Query( "SELECT new com.codeguru_base.layouts.DTOChild"
            + " (u.id, c.unitChildId, u.name)"
            + " from Mdl_children c"
            + " join c.unitParentId u"
            + " join  u.mdl_structure s"
            + " where s.id=?1 and u.id=?2")
    public List<DTOChild> findAllchildrenByUnitId( long structureId,long unitId);
}
