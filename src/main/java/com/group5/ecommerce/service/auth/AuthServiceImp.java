package com.group5.ecommerce.service.auth;

import com.group5.ecommerce.dto.auth.LoginDto;
import com.group5.ecommerce.dto.auth.RegisterUserDto;

import com.group5.ecommerce.entity.Account;
import com.group5.ecommerce.entity.Cart;
import com.group5.ecommerce.entity.User;
import com.group5.ecommerce.entity.WishList;
import com.group5.ecommerce.entity.enums.Role;

import com.group5.ecommerce.repository.AccountRepository;
import com.group5.ecommerce.repository.CartRepository;
import com.group5.ecommerce.repository.UserRepository;
import com.group5.ecommerce.repository.WishListRepository;

import com.group5.ecommerce.response.auth.LoginResponse;
import com.group5.ecommerce.response.auth.RegisterUserResponse;

import com.group5.ecommerce.utils.JwtUtils;

import lombok.RequiredArgsConstructor;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {
    private final PasswordEncoder passwordEncoder;
    private final JwtUtils jwtUtils;
    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;
    private final CartRepository cartRepository;
    private final AccountRepository accountRepository;
    private final WishListRepository wishListRepository;

    @Override
    public LoginResponse login(LoginDto loginData) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginData.getEmail(),
                        loginData.getPassword()
                )
        );

        var user = this.userRepository.findByEmail(loginData.getEmail())
                .orElseThrow();

        var jwtToken = this.jwtUtils.generateToken(user);

        return new LoginResponse(jwtToken);
    }

    @Override
    public RegisterUserResponse registerUser(RegisterUserDto userData) {
        var account = new Account();
        this.accountRepository.save(account);

        var cart = new Cart();
        this.cartRepository.save(cart);

        var wishList = new WishList();
        this.wishListRepository.save(wishList);

        var user = User.builder()
                .userName(userData.getUsername())
                .firstName(userData.getFirstName())
                .lastName(userData.getLastName())
                .email(userData.getEmail())
                .password(this.passwordEncoder.encode(userData.getPassword()))
                .DNI(userData.getDni())
                .role(Role.USER)
                .activateCode(UUID.randomUUID())
                .account(account)
                .cart(cart)
                .wishList(wishList)
                .build();

        var savedUser = this.userRepository.save(user);

        return new RegisterUserResponse("Se ha enviado a " + savedUser.getUsername() + " un link de activación");
    }

}
