package fr.exagone.hospital.service;

import fr.exagone.hospital.entities.Consultation;
import fr.exagone.hospital.entities.Medecin;
import fr.exagone.hospital.entities.Patient;
import fr.exagone.hospital.entities.RendezVous;

public interface IHospitalService {

    Patient savePatient(Patient patient);

    Medecin saveMedecin(Medecin medecin);

    RendezVous saveRendezVous(RendezVous rendezVous);

    Consultation saveConsutlation(Consultation consultation);

}
