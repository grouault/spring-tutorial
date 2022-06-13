package fr.exagone.hospital.service;

import fr.exagone.hospital.entities.Consultation;
import fr.exagone.hospital.entities.Medecin;
import fr.exagone.hospital.entities.Patient;
import fr.exagone.hospital.entities.RendezVous;
import fr.exagone.hospital.repositories.ConsultationRepository;
import fr.exagone.hospital.repositories.MedecinRepository;
import fr.exagone.hospital.repositories.PatientRepository;
import fr.exagone.hospital.repositories.RendezVousRepository;
import javax.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class HospitalServiceImpl implements IHospitalService {

    private PatientRepository patientRepository;

    private MedecinRepository medecinRepository;

    private RendezVousRepository rendezVousRepository;

    private ConsultationRepository consultationRepository;

    public HospitalServiceImpl(PatientRepository patientRepository, MedecinRepository medecinRepository, RendezVousRepository rendezVousRepository, ConsultationRepository consultationRepository) {
        this.patientRepository = patientRepository;
        this.medecinRepository = medecinRepository;
        this.rendezVousRepository = rendezVousRepository;
        this.consultationRepository = consultationRepository;
    }

    @Override
    public Patient savePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    @Override
    public Medecin saveMedecin(Medecin medecin) {
        return medecinRepository.save(medecin);
    }

    @Override
    public RendezVous saveRendezVous(RendezVous rendezVous) {
        return rendezVousRepository.save(rendezVous);
    }

    @Override
    public Consultation saveConsutlation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

}
