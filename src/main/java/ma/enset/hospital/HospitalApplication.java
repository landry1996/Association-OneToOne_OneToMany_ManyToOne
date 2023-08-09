package ma.enset.hospital;

import ma.enset.hospital.entities.*;
import ma.enset.hospital.repositories.ConsutationRepository;
import ma.enset.hospital.repositories.MedecinRepository;
import ma.enset.hospital.repositories.PatientRepository;
import ma.enset.hospital.repositories.RendezVousRepository;
import ma.enset.hospital.services.HospitalService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.stream.Stream;

@SpringBootApplication
public class HospitalApplication  {

    public static void main(String[] args) {
        SpringApplication.run(HospitalApplication.class, args);
    }

    @Bean
    CommandLineRunner start(HospitalService hospitalService,
                            PatientRepository patientRepository,
                            RendezVousRepository rendezVousRepository,
                            ConsutationRepository consutationRepository,
                            MedecinRepository medecinRepository){
        return args -> {
            Stream.of("Toto","Titi","Tutu")
                    .forEach(name->{

                     var patient = new Patient();
            patient.setName(name );
            patient.setBirthday(new Date());
            if (Math.random()>0.5)
            {
                patient.setSick(true);
            }
            else {
                patient.setSick(false);
            }
                        hospitalService.savePatient(patient);

        });
            Stream.of("Joel","Placide","Landry")
                    .forEach(name->{

                        var medecin = new Medecin();
                        medecin.setName(name);
                        medecin.setEmail(name+"@gmail.com");
                        medecin.setSpeciality(Math.random()>0.5?"CARDIO":"DENTIST");
                        hospitalService.saveMedecin(medecin);

                    });
            var patient = patientRepository.findById(1L).orElse(null);
            var patient1 = patientRepository.findByName("Toto");
            System.out.println("-----------------------------------------");
            var medecin = medecinRepository.findByName("Joel");
            var medecin2 = medecinRepository.findById(2L).orElse(null);


            var rendezVous = new RendezVous();
            rendezVous.setDate(new Date());
            rendezVous.setStatus(StatusRDV.PENDING);
            rendezVous.setMedecin(medecin);
            rendezVous.setPatient(patient);
            hospitalService.saveRendezVous(rendezVous);

            var rendezvous1 = rendezVousRepository.findById(1L).orElse(null);
            var consultation = new Consultation();
            consultation.setDateConsultation(rendezvous1.getDate());
            consultation.setRendezVous(rendezvous1);
            consultation.setRapport("rapport de la Consultation.......");
            hospitalService.saveConsultation(consultation);

    };

}
}
