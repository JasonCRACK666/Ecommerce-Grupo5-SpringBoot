package com.group5.ecommerce.controller;

import com.group5.ecommerce.dto.account.UpdateAccountDto;
import com.group5.ecommerce.exception.ResourceNotUploadedException;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.response.account.DetailAccountResponse;
import com.group5.ecommerce.service.account.AccountService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @PatchMapping(path = "{accountId}")
    public ResponseEntity<DetailAccountResponse> updateAccount(
            @RequestAttribute("user") Long userId,
            @PathVariable("accountId") Long accountId,
            @Valid UpdateAccountDto accountData
    ) throws UserIsNotOwnerException, ResourceNotUploadedException {
        return new ResponseEntity<>(
                this.accountService.updateAccount(userId, accountId, accountData),
                HttpStatus.OK
        );
    }

}
