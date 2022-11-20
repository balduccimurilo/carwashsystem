package com.tcc.lavarapido.controllers;

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

import com.tcc.lavarapido.enums.IProfile;
import com.tcc.lavarapido.forms.LoginForm;
import com.tcc.lavarapido.forms.MessageResponse;
import com.tcc.lavarapido.forms.SignupRequest;
import com.tcc.lavarapido.forms.UserInfoResponse;
import com.tcc.lavarapido.impl.UserDetailsImpl;
import com.tcc.lavarapido.models.Profile;
import com.tcc.lavarapido.models.User;
import com.tcc.lavarapido.repositories.ProfileRepository;
import com.tcc.lavarapido.repositories.UserRepository;
import com.tcc.lavarapido.security.JwtUtils;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ProfileRepository profileRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

		List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString()).body(
				new UserInfoResponse(userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles));
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
		Set<Profile> roles = new HashSet<>();

		if (strProfiles == null) {
			Profile userRole = profileRepository.findByProfile(IProfile.CLIENT)
					.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
			roles.add(userRole);
		} else {
			strProfiles.forEach(role -> {
				switch (role) {
				case "admin":
					Profile adminRole = profileRepository.findByProfile(IProfile.ADMIN)
							.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
					roles.add(adminRole);

					break;
				default:
					Profile clientRole = profileRepository.findByProfile(IProfile.CLIENT)
							.orElseThrow(() -> new RuntimeException("Error: Profile is not found."));
					roles.add(clientRole);
				}
			});
		}

		user.setId_user(null);
		user.setProfiles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
		return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
				.body(new MessageResponse("You've been signed out!"));
	}
}
