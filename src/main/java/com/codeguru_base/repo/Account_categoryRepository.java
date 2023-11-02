/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.repo;

import com.codeguru_base.models.DTO.Mdl_categoryDTo;
import com.codeguru_base.models.Mdl_account_category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface Account_categoryRepository extends JpaRepository<Mdl_account_category, Long> {

    @Query(value = "select id, name from account_category  order by id desc limit 1", nativeQuery = true)
    public Mdl_account_category findLastcategory();
    
    @Query(value = "select c.* from account_category c where c.name=? ", nativeQuery = true)
    public Mdl_account_category findcategoryByName(String name);
    
    @Query(value = "select c.* from account_category c  ", nativeQuery = true)
    public List<Mdl_categoryDTo> findCategoryNoAccounts();
    
    
    
    
}
