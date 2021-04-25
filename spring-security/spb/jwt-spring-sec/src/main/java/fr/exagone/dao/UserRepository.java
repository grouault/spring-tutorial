package fr.exagone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.exagone.entities.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {

	public AppUser findByUsername(String userName);
	
}
