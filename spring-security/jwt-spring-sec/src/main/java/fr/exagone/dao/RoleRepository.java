package fr.exagone.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.exagone.entities.AppRole;

public interface RoleRepository extends JpaRepository<AppRole, Long> {

	public AppRole findByRoleName(String roleName);
	
}
