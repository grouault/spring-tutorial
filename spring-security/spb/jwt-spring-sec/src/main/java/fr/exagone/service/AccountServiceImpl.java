package fr.exagone.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import fr.exagone.dao.RoleRepository;
import fr.exagone.dao.UserRepository;
import fr.exagone.entities.AppRole;
import fr.exagone.entities.AppUser;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public AppUser save(AppUser user) {
		String hashPwd = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(hashPwd);
		return userRepository.save(user);
	}

	@Override
	public AppRole save(AppRole role) {
		return roleRepository.save(role);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		AppRole role = roleRepository.findByRoleName(roleName);
		AppUser user = userRepository.findByUserName(userName);
		user.getRoles().add(role);
	}

	@Override
	public AppUser findUserByUserName(String userName) {
		return userRepository.findByUserName(userName);
	}

}
