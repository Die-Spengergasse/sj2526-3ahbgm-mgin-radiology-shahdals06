package at.spengergasse.spring_thymeleaf.entities;

import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservierungRepository extends CrudRepository<Reservierung, Integer> {

    List<Reservierung> findByGeraet_Id(Integer geraetId);

    boolean existsByGeraet_IdAndReservierungszeit(Integer geraetId, LocalDateTime zeit);

    boolean existsByPatient_IdAndReservierungszeit(Integer patientId, LocalDateTime zeit);
}