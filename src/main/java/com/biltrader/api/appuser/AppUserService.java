package com.biltrader.api.appuser;

import java.time.LocalDateTime;
import java.util.List;
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
/**
 * Service class for the AppUser, recives requests from the controller or other
 * classes that will edit the AppUser class and applies the necessary logic by
 * using the repository interface and editing the AppUser
 */
public class AppUserService implements UserDetailsService {

    private final static String USER_NOT_FOUND_MSG = "user with email %s not found";

    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    /**
     * Searches for user by email
     * 
     * @param email
     * @return UserDetails of the found user
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
    }

    /**
     * Signs up new user
     * 
     * @param appUser to sign up
     * @return String sign up token
     */

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

    /**
     * Enables AppUser
     * 
     * @param email of the user to enable
     * @return int
     */

    public int enableAppUser(String email) {
        return appUserRepository.enableAppUser(email);
    }

    /**
     * @param id of the user to be searched
     * @return AppUser with the given id
     */

    public AppUser getUserById(Long id) {
        boolean exists = appUserRepository.findById(id).isPresent();

        if (!exists) {
            throw new IllegalStateException("user with id " + id + "does not exist");
        }

        return appUserRepository.findById(id).get();
    }

    /**
     * Adds review to the AppUser's reviews List
     * 
     * @param appUser that the review will be added to
     * @param review  to be added
     */

    public void addReview(AppUser appUser, Review review) {
        List<Review> updatedReviews = appUser.getReviews();

        updatedReviews.add(review);

        appUser.setReviews(updatedReviews);
    }

    /**
     * Sets AppUser's loggedIn to true
     * 
     * @param user to login
     */

    public void loginUser(AppUser user) {
        if (!user.getEnabled()) {
            throw new IllegalStateException("user not activated");
        }

        user.setLoggedIn(true);

        appUserRepository.setLoggedIn(user.getEmail());
    }

    /**
     * Sets AppUser's loggedIn to false
     * 
     * @param id of user to logout
     */

    public void logoutUser(Long id) {
        AppUser user = appUserRepository.findById(id).get();

        user.setLoggedIn(false);
    }
}
