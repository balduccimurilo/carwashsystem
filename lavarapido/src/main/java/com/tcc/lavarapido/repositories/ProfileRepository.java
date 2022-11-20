package com.tcc.lavarapido.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tcc.lavarapido.enums.IProfile;
import com.tcc.lavarapido.models.Profile;


public interface ProfileRepository extends JpaRepository<Profile, Long>{
	
	Optional<Profile> findByProfile(IProfile profile);
	
}
