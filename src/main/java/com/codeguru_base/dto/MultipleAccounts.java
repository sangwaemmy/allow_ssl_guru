 

package com.codeguru_base.dto;


import com.codeguru_base.models.Mdl_Account;
import com.codeguru_base.models.Mdl_account_category;
import java.util.List;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author SANGWA Emmanuel code [CODEGURU - info@codeguru.com]
 */
@Setter @Getter
public class MultipleAccounts {

    List<Mdl_Account> multiAccounts;
}

