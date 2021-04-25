package fr.exagone.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import fr.exagone.entities.AppUser;
import fr.exagone.service.AccountService;

@RestController
public class AccountRestController {

	@Autowired
	private AccountService accountService;
	
	@PostMapping("/register")
	public AppUser register(@RequestBody RegisterForm userForm) {
		
		// pwd === confirmPwd
		if (!userForm.getPassword().equals(userForm.getConfirmPwd())) {
			throw new RuntimeException("You must confirm your password!");
		}
		
		AppUser user = accountService.findUserByUserName(userForm.getUsername());
		if (user != null) {
			throw new RuntimeException("This user already exists");
		}
		
		// creation du AppUser
		AppUser appUser = new AppUser();
		appUser.setUsername(userForm.getUsername());
		appUser.setPassword(userForm.getPassword());
		accountService.save(appUser);
		// ajout du rôle User par défaut.
		accountService.addRoleToUser(userForm.getUsername(), "USER");
		
		return appUser;
		
	}
	
}
