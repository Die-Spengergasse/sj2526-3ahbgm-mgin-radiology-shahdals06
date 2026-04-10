package at.spengergasse.spring_thymeleaf.entities;

import org.springframework.data.repository.CrudRepository;
import java.util.List;

public interface ReservierungRepository extends CrudRepository<Reservierung, Integer> {
    // Sucht alle Reservierungen für ein bestimmtes Gerät anhand dessen ID
    List<Reservierung> findByGeraet_Id(Integer geraetId);
}