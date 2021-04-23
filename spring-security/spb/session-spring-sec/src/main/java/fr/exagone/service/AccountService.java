package fr.exagone.service;

import fr.exagone.entities.AppRole;
import fr.exagone.entities.AppUser;

/**
 * permet de centraliser la gestion des comptes utilisateurs et des rôles.
 * @author grouault
 *
 */
public interface AccountService {

	public AppUser save(AppUser user);
	
	public AppRole save(AppRole role);
	
	public void addRoleToUser(String userName, String role);
	
	public AppUser findUserByUserName(String userName);
	
}
