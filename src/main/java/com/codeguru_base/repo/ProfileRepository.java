/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.codeguru_base.repo;

import com.codeguru_base.models.Mdl_profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface ProfileRepository  extends JpaRepository<Mdl_profile, Long>{
   
    @Query(value="select p.* from profile p order by p.id desc limit 1",nativeQuery = true)
    public Mdl_profile findLastprofile();
}
