package fr.exagone;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import fr.exagone.dao.TaskRepository;
import fr.exagone.entities.AppRole;
import fr.exagone.entities.AppUser;
import fr.exagone.entities.Task;
import fr.exagone.service.AccountService;

@SpringBootApplication
@EnableAutoConfiguration(exclude = {
	    org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
	})
public class SessionSpringSecApplication implements CommandLineRunner {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private AccountService accounteService;
	
	public static void main(String[] args) {
		SpringApplication.run(SessionSpringSecApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		// creation des users et des roles
		accounteService.save(new AppUser(null, "admin", "1234", null));
		accounteService.save(new AppUser(null, "user", "1234", null));
		
		accounteService.save(new AppRole(null, "ADMIN"));
		accounteService.save(new AppRole(null, "USER"));
		
		// association roles <==> users
		accounteService.addRoleToUser("admin", "ADMIN");
		accounteService.addRoleToUser("user", "USER");
		
		// creation de quelques tachesZ
		Stream.of("T1","T2","T3").forEach(t -> {
			this.taskRepository.save(new Task(null, t));
		});
		
		// affichage des taches
		taskRepository.findAll().forEach(t -> {
			System.out.println(t.getTaskName());
		});
		
	}

}
