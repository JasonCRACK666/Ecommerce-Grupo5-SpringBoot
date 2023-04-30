package com.group5.ecommerce.service.account;

import com.group5.ecommerce.dto.account.UpdateAccountDto;
import com.group5.ecommerce.exception.ResourceNotUploadedException;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.response.account.DetailAccountResponse;

public interface AccountService {
    DetailAccountResponse updateAccount(Long userId, Long accountId, UpdateAccountDto accountData)
            throws UserIsNotOwnerException, ResourceNotUploadedException;
}
