/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.repo;

import com.codeguru_base.models.DTO.UnitIdUnitNameDTO;
import com.codeguru_base.models.DTO.UnitsTuplesDTO;
import com.codeguru_base.models.Mdl_unit;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface UnitRepository extends JpaRepository<Mdl_unit, Long> {

    @Query(value = "select u.* from unit u order by u.id desc limit 1", nativeQuery = true)
    public Mdl_unit findLastUnit();

    @Query(value = " SELECT   u.*,"
            + "  s.* from unit u "
            + " join structure s on s.id = u.structure_id "
            + "   where s.id=? ", nativeQuery = true)
    List<Mdl_unit> findUnitsByStrctureid(long strctureId);

    //Check if two units in the same structure
    @Query(value = "select u.* from unit u join structure s on u.structure_id =s.id"
            + "  where s.id=? and u.name=? ", nativeQuery = true)
    Mdl_unit findUnitNameByStructure(long structureId, String unitName);

    @Query("select new com.codeguru_base.models.DTO.UnitsTuplesDTO"
            + "(u.id,u.name, s.db_name,t.id as tupleId, t.name as tuple_name, t.data_type, t.category, t.display_caption, t.today_date, t.curr_date  ) "
            + " FROM Mdl_tuple t "
            + " JOIN t.mdl_unit u "
            + " JOIN u.mdl_structure s "
            + " WHERE s.id=?1 ")
    public List<UnitsTuplesDTO> unitsTuplesByStrucIdDTO(long structureId);

    /*
    Find the units that dont have other tuples other than id. that means they should have more.
     */
    @Query("SELECT new com.codeguru_base.models.DTO.UnitIdUnitNameDTO(   u.id, u.name)  "
            + " from Mdl_tuple t  "
            + " right join t.mdl_unit u \n"
            + " join  u.mdl_structure s"
            + " where s.id=?1  "
            + " group by u.id\n"
            + " having count(t.id)< 2")
    public List<UnitIdUnitNameDTO> findUnitWithoutTuples(long structureId);

    //<editor-fold defaultstate="collapsed" desc="------Get a single parent and a single child unit by its id  and structure id">
    @Query(value = "SELECT u.id ,  u.name  FROM unit u \n"
            + " join `structure` s on u.structure_id =s.id "
            + " where s.id =?  and u.id =? ", nativeQuery = true)
    public Mdl_unit findparentUnitNameById(long structureId, long unitId);

    //get the children of a unit
    @Query(value = " SELECT u.id ,  u.name  FROM unit u \n"
            + " join `structure` s on u.structure_id =s.id "
            + " where s.id =? and u.id =? ", nativeQuery = true)
    public Mdl_unit findChildNameById(long structureId, long unitId);
//</editor-fold>

}
