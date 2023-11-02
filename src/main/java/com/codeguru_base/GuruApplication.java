package com.codeguru_base;

import com.codeguru_base.models.Mdl_Account;
import com.codeguru_base.models.Mdl_account_category;
import com.codeguru_base.models.Mdl_profile;
import com.codeguru_base.repo.AccountRepository;
import com.codeguru_base.repo.Account_categoryRepository;
import com.codeguru_base.repo.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class GuruApplication implements CommandLineRunner {

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    ProfileRepository profileRepository;
    @Autowired
    Account_categoryRepository account_categoryRepository;

    public static void main(String[] args) {
        SpringApplication.run(GuruApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Mdl_profile mdlLastProfile = new Mdl_profile();
        Mdl_Account mdl_Account = new Mdl_Account();
        if (accountRepository.count() <= 0) {
            Mdl_account_category mdl_account_category = new Mdl_account_category();
            mdl_account_category.setName("admin");
//-------------------USER 1----------------------
            //Save category as admin
            account_categoryRepository.saveAndFlush(mdl_account_category);
            Mdl_account_category mdlLastCategory = account_categoryRepository.findLastcategory();
            profileRepository.saveAndFlush(new Mdl_profile("SANGWA", "Emmanuel", "Male", "0784113888", ""));
            mdlLastProfile = profileRepository.findLastprofile();
            mdl_Account = new Mdl_Account("sangwa", "123", mdlLastProfile, mdlLastCategory);
            accountRepository.saveAndFlush(mdl_Account);
//-------------------USER 2----------------------
            profileRepository.saveAndFlush(new Mdl_profile("Manager", "User", "Male", "", ""));
            mdlLastProfile = profileRepository.findLastprofile();
            account_categoryRepository.saveAndFlush(new Mdl_account_category("manager", null));
            Mdl_account_category acc2 = account_categoryRepository.findcategoryByName("manager");
            mdl_Account = new Mdl_Account("manager", "123", mdlLastProfile, acc2);
            accountRepository.saveAndFlush(mdl_Account);
//-------------------USER 3----------------------
            profileRepository.saveAndFlush(new Mdl_profile("user", "User", "Male", "", ""));
            mdlLastProfile = profileRepository.findLastprofile();
            account_categoryRepository.saveAndFlush(new Mdl_account_category("user", null));
            Mdl_account_category acc3 = account_categoryRepository.findcategoryByName("user");
            mdl_Account = new Mdl_Account("user", "123", mdlLastProfile, acc3);
            accountRepository.saveAndFlush(mdl_Account);

        }
//
    }

}
