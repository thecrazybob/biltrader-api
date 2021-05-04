package com.biltrader.api.appuser;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;

import com.biltrader.api.registration.token.ConfirmationToken;
import com.biltrader.api.registration.token.ConfirmationTokenService;
import com.biltrader.api.review.Review;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    public String signUpUser(AppUser appUser) {
        boolean userExists = appUserRepository.findByEmail(appUser.getEmail()).isPresent();

        if (userExists) {
            throw new IllegalStateException("email already taken");
        }

        String encodedPassword = bCryptPasswordEncoder.encode(appUser.getPassword());

        appUser.setPassword(encodedPassword);

        appUserRepository.save(appUser);

        String token = UUID.randomUUID().toString();

        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(),
                LocalDateTime.now().plusMinutes(15), appUser);

        confirmationTokenService.saveConfirmationToken(confirmationToken);

        // TODO: SEND EMAIL

        return token;
    }

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    public AppUser getUserById(Long id) {
        boolean exists = appUserRepository.findById(id).isPresent();

        if (!exists) {
            throw new IllegalStateException("user with id " + id + "does not exist");
        }

        return appUserRepository.findById(id).get();
    }

    // public void addReview(AppUser appUser, Review review) {
    // Long[] arr = Arrays.copyOf(appUser.getReviewsId(),
    // appUser.getReviewsId().length + 1);
    // arr[arr.length - 1] = review.getId();
    // appUser.setReviewsId(arr);
    // }
}
