/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.layouts;

import com.codeguru_base.models.Mdl_unit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */

public interface LayoutRepo extends JpaRepository<Mdl_Layout, Long> {

    //this can be used in checking of the duplicated tuples of the same layout and structure
    @Query("select new com.codeguru_base.layouts.DTOUnitsTuples"
            + " (u.name, u.number_children, u.number_parent, "
            + "  t.name as tuple_name, t.data_type, t.category, t.display_caption, t.today_date  )"
            + " FROM Mdl_tuple t"
            + " JOIN t.mdl_unit u"
            + " JOIN u.mdl_structure s"
            + " JOIN u.mdl_layout l "
            + " WHERE s.id=?1 and l.id=?2 "
            + " GROUP BY t.name"
            + " HAVING count(t.name)>1"
            + "  ")
    public List<DTOUnitsTuples> findDuplicatedtuples(long structureId, long layoutId);
   
     
}
