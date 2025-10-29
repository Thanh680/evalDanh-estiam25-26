package edu.fbansept.demosecuritye42426.controllers;

import edu.fbansept.demosecuritye42426.dao.AppUserDao;
import edu.fbansept.demosecuritye42426.models.AppUser;
import edu.fbansept.demosecuritye42426.security.AppUserDetails;
import edu.fbansept.demosecuritye42426.security.JwtService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    protected final AppUserDao appUserDao;
    protected final PasswordEncoder passwordEncoder;
    protected final AuthenticationProvider authenticationProvider;
    protected final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody @Valid AppUser user) {

        Optional<AppUser> optionalAppUser = appUserDao.findByEmail(user.getEmail());
        if(optionalAppUser.isPresent()) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        appUserDao.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);

    }


    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody AppUser user) {

        try {
            AppUserDetails userDetails = (AppUserDetails) authenticationProvider
                    .authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    user.getEmail(),
                                    user.getPassword()))
                    .getPrincipal();

            return new ResponseEntity<>(jwtService.generateToken(userDetails), HttpStatus.OK);

        } catch (AuthenticationException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

    }

}
