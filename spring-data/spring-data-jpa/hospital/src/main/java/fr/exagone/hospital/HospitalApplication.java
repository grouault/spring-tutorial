package fr.exagone.hospital;

import fr.exagone.hospital.entities.Consultation;
import fr.exagone.hospital.entities.Medecin;
import fr.exagone.hospital.entities.Patient;
import fr.exagone.hospital.entities.RendezVous;
import fr.exagone.hospital.entities.StatusRDV;
import fr.exagone.hospital.repositories.ConsultationRepository;
import fr.exagone.hospital.repositories.MedecinRepository;
import fr.exagone.hospital.repositories.PatientRepository;
import fr.exagone.hospital.repositories.RendezVousRepository;
import fr.exagone.hospital.service.IHospitalService;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HospitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(HospitalApplication.class, args);
	}

	@Bean
	CommandLineRunner start(IHospitalService hospitalService, PatientRepository patientRepository,
			MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository){
		return args -> {

			Stream.of("Arthur", "Pascale", "Jean-Louis").forEach(name -> {
				Medecin medecin = new Medecin();
				medecin.setNom(name);
				medecin.setSpecialite(Math.random() > 0.5 ? "Cardio" : "Chirurgie-Dentiste");
				medecin.setEmail(name.toLowerCase() + "-hospital@fake.com");
				hospitalService.saveMedecin(medecin);
			});

			Stream.of("Mohamed", "Hassan", "Najat").forEach(name -> {
				Patient patient = new Patient();
				patient.setNom(name);
				patient.setDateNaissance(new Date());
				patient.setMalade(false);
				hospitalService.savePatient(patient);
			});

			// Créer des rendez-vous
			Patient patient1 = patientRepository.findById(1L).orElse(null);
			Patient patient2 = patientRepository.findByNom("Hassan");

			Medecin medecin1 = medecinRepository.findByNom("Arthur");
			Medecin medecin2 = medecinRepository.findByNom("Pascale");

			RendezVous rendezVous = new RendezVous();
			rendezVous.setDate(new Date());
			rendezVous.setStatus(StatusRDV.PENDING);
			rendezVous.setMedecin(medecin1);
			rendezVous.setPatient(patient1);
			hospitalService.saveRendezVous(rendezVous);

			RendezVous rendezVous1 = rendezVousRepository.findById(1L).orElse(null);
			Consultation consultation = new Consultation();
			consultation.setDateConsultation(rendezVous1.getDate());
			consultation.setRendezVous(rendezVous1);
			consultation.setRapport("Rapport de la consultation ...");
			hospitalService.saveConsutlation(consultation);

		};
	}

}
