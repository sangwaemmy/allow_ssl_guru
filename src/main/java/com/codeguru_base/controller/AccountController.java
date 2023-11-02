package com.codeguru_base.controller;


import com.codeguru_base.dto.MultipleAccounts;
import com.codeguru_base.exception.ResourceNotFoundException;
import com.codeguru_base.models.DTO.AccountProfile;
import com.codeguru_base.models.Mdl_Account;
import com.codeguru_base.models.Mdl_account_category;
import com.codeguru_base.models.Mdl_profile;
import com.codeguru_base.repo.AccountRepository;
import com.codeguru_base.repo.Account_categoryRepository;
import com.codeguru_base.repo.ProfileRepository;
import io.swagger.annotations.ApiOperation;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
@RequestMapping("/guru/api/account")
@CrossOrigin("*")
@Slf4j
public class AccountController {

    @Autowired
    AccountRepository accountRepository;
   
    @Autowired
    Account_categoryRepository account_categoryRepository;
    
    @Autowired
    ProfileRepository profileRepository;

    @ApiOperation("Getting all the Account only")
    @GetMapping("/")

    public ResponseEntity<List<Mdl_Account>> getAll() {
        List<Mdl_Account> struc = new ArrayList<>();
        accountRepository.findAll().forEach(struc::add);
        if (struc.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(struc, HttpStatus.OK);
    }
    @ApiOperation("Creating a user account")
    @PostMapping("/{categoryId}")
    public ResponseEntity<Mdl_Account> createStructure(@PathVariable(value = "categoryId") long categoryId, @RequestBody AccountProfile ap) {

        Mdl_account_category mdl_account_category = account_categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceAccessException("Not cateogry foudnd: " + categoryId));
        Mdl_Account mdl_account= new Mdl_Account();
        Mdl_profile mdl_profile= new Mdl_profile(ap.getName(), ap.getSurname(), ap.getGender(), ap.getTel(), ap.getEmail());
        
        mdl_profile.setName(ap.getName());
        mdl_profile.setSurname(ap.getSurname());
        mdl_profile.setGender(ap.getGender());
        mdl_profile.setTel(ap.getTel());
        mdl_profile.setEmail(ap.getEmail());
        
        profileRepository.save(mdl_profile);
        profileRepository.flush();
        Mdl_profile mdlLast_profile= profileRepository.findLastprofile();
        
        mdl_account.setPassword(ap.getPassword());
        mdl_account.setUsername(ap.getUsername());
        mdl_account.setMdl_account_category(mdl_account_category);
        mdl_account.setMdl_profile(mdlLast_profile);
        
        System.out.println("Got the username: "+ mdl_account.getUsername()+" got the passowrd: "+ mdl_account.getPassword());
        return new ResponseEntity<>(accountRepository.save(mdl_account), HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{password}")
    public ResponseEntity<Mdl_Account> Login(@PathVariable(name = "username") String username,
            @PathVariable(name = "password") String password) {
        Mdl_Account mdl_account = accountRepository.findAccountByUsernamePassword(username, password);
        return new ResponseEntity<>(mdl_account, HttpStatus.OK);
    }

    @PutMapping("/{id}/{categoryId}")
    @ApiOperation(value = "Updating  a single Structure")
    public ResponseEntity<Mdl_Account> updateStructure(@PathVariable(value = "id") long id, @PathVariable(value = "categoryId") long categoryId, @RequestBody Mdl_Account mdl_account) {
        Mdl_Account mdl_account1 = accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account with id =" + id + " not found"));
        mdl_account1.setId(mdl_account.getId());
        mdl_account1.setUsername(mdl_account.getUsername());
        mdl_account1.setPassword(mdl_account.getPassword());
        Mdl_account_category mdl_account_category = account_categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceAccessException("Not cateogry foudnd: " + categoryId));
//        mdl_account1.setMdl_account_category(mdl_account_category);
        return new ResponseEntity<>(accountRepository.save(mdl_account), HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteAccount(@PathVariable("id") long id) {
        accountRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    Adding more items at the same time
    @PostMapping("/multiaccount")
    public ResponseEntity<String> multipleaccounts(@RequestBody MultipleAccounts multipleAccounts) {
        List<Mdl_Account> accountsList = multipleAccounts.getMultiAccounts();
        try {
            for (Mdl_Account mdl_account : accountsList) {
                accountRepository.save(mdl_account);
            }
            return new ResponseEntity<>("Saved", HttpStatus.OK);
        
        } catch (Exception e) {
            System.out.println("Error " + e.toString());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
