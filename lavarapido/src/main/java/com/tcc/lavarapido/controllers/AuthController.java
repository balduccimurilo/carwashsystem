package com.tcc.lavarapido.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tcc.lavarapido.enums.Role;
import com.tcc.lavarapido.forms.LoginForm;
import com.tcc.lavarapido.forms.MessageResponse;
import com.tcc.lavarapido.forms.SignupRequest;
import com.tcc.lavarapido.forms.UserInfoResponse;
import com.tcc.lavarapido.models.Profile;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.models.dto.LoginDto;
import com.tcc.lavarapido.repositories.ProfileRepository;
import com.tcc.lavarapido.repositories.UserRepository;
import com.tcc.lavarapido.security.TokenService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthenticationManager authManager;
	private final TokenService tokenService;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	

	@Autowired
	ProfileRepository profileRepository;


	public AuthController(AuthenticationManager authManager, TokenService tokenService) {
		super();
		this.authManager = authManager;
		this.tokenService = tokenService;
	}

	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByusername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}
		
		if(userRepository.existsByCpf(signUpRequest.getCpf().replaceAll("[./-]", "").trim())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Cpf is already in use!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(), 
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getCpf().replaceAll("[./-]", "").trim(), 
						signUpRequest.getCel().replaceAll("-", "").trim());

		Set<String> strProfiles = signUpRequest.getProfiles();
		List<Profile> roles = new ArrayList<>();

		if (strProfiles == null) {
			user.setRole(Role.CLIENT);
			Profile userRole = profileRepository.findByProfile(Role.CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
			roles.add(userRole);
		} else {
			strProfiles.forEach(role -> {
				switch (role) {
				case "admin":
					user.setRole(Role.ADMIN);
					Profile adminRole = profileRepository.findByProfile(Role.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
					roles.add(adminRole);

					break;
				default:
					user.setRole(Role.CLIENT);
					Profile clientRole = profileRepository.findByProfile(Role.CLIENT)
							.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
					roles.add(clientRole);
				}
			});
		}

		user.setId(null);
		user.setProfiles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

//	@PostMapping("/signout")
//	public ResponseEntity<?> logoutUser() {
//		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//				.body(new MessageResponse("You've been signed out!"));
//	}

	@PostMapping("/signin")
	public ResponseEntity<LoginDto> authenticate(@RequestBody @Valid LoginForm form) {

		UsernamePasswordAuthenticationToken loginData = form.convert();

		try {

			Authentication authentication = authManager.authenticate(loginData);

			String token = tokenService.generateToken(authentication);

			User loggedUser = (User) authentication.getPrincipal();

			return ResponseEntity.ok(new LoginDto(token, "Bearer", loggedUser.getId(), loggedUser.getRole()));
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
