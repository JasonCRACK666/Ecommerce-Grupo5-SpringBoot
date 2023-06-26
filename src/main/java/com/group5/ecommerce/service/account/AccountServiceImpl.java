package com.group5.ecommerce.service.account;

import com.group5.ecommerce.dto.account.UpdateAccountDto;
import com.group5.ecommerce.exception.NotFoundException;
import com.group5.ecommerce.exception.ResourceNotUploadedException;
import com.group5.ecommerce.exception.UserIsNotOwnerException;
import com.group5.ecommerce.repository.AccountRepository;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.response.account.AccountMapper;
import com.group5.ecommerce.response.account.DetailAccountResponse;
import com.group5.ecommerce.utils.CloudinaryUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final CloudinaryUtils cloudinaryUtils;

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;

    @Override
    public DetailAccountResponse accountDetail(Long userId) {
        var user = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NotFoundException("La cuenta no existe")
                );

        return AccountMapper.INSTANCE.toDetailResponse(user.getAccount(), user);
    }

    @Override
    public DetailAccountResponse updateAccount(
            Long userId,
            Long accountId,
            UpdateAccountDto accountData
    ) throws UserIsNotOwnerException, ResourceNotUploadedException {
        var user = this.userRepository
                .findById(userId)
                .orElseThrow(
                        () -> new NotFoundException("El usuario no existe")
                );

        var account = this.accountRepository
                .findById(accountId)
                .orElseThrow(
                        () -> new NotFoundException("La cuenta no existe")
                );

        if (!user.getId().equals(account.getUser().getId()))
            throw new UserIsNotOwnerException("Usted no tiene permiso de hacer cambios en la cuenta");

        if (accountData.getUsername() != null)
            user.setUserName(accountData.getUsername());

        if (accountData.getFirstName() != null)
            user.setFirstName(accountData.getFirstName());

        if (accountData.getLastName() != null)
            user.setLastName(accountData.getLastName());

        if (accountData.getEmail() != null)
            user.setEmail(accountData.getEmail());

        if (accountData.getTheme() != null)
            account.setTheme(accountData.getTheme());

        if (accountData.getAbout() != null)
            account.setAbout(accountData.getAbout());

        try {
            if (accountData.getAvatar() != null)
                account.setAvatar(this.cloudinaryUtils.uploadImage(accountData.getAvatar()));
        } catch (IOException e) {
            throw new ResourceNotUploadedException("No se ha podido actualizar el avatar");
        }

        try {
            if (accountData.getBanner() != null)
                account.setBanner(this.cloudinaryUtils.uploadImage(accountData.getBanner()));
        } catch (IOException e) {
            throw new ResourceNotUploadedException("No se ha podido actualizar el banner");
        }

        var savedAccount = this.accountRepository.save(account);
        var savedUser = this.userRepository.save(user);

        return AccountMapper.INSTANCE.toDetailResponse(savedAccount, savedUser);
    }
}
