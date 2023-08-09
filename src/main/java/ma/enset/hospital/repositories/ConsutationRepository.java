package ma.enset.hospital.repositories;

import ma.enset.hospital.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsutationRepository extends JpaRepository<Consultation, Long> {

}
