/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.codeguru_base.repo;

import com.codeguru_base.models.DTO.AccountProfile;
import com.codeguru_base.models.Mdl_Account;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
public interface AccountRepository extends JpaRepository<Mdl_Account, Long> {

    @Query(value = "select id,username, password, profile, acc_category, profile_id "
            + " from account"
            + "  where username=? and password=? ", nativeQuery = true)
    Mdl_Account findAccountByUsernamePassword(String username, String password);

    
        
    
}
