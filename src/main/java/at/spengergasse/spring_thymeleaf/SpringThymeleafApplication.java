package at.spengergasse.spring_thymeleaf;

import at.spengergasse.spring_thymeleaf.entities.Geraet;
import at.spengergasse.spring_thymeleaf.entities.GeraetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringThymeleafApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringThymeleafApplication.class, args);
    }

    @Bean
    public CommandLineRunner dataLoader(GeraetRepository geraetRepository) {
        return args -> {
            if (geraetRepository.count() == 0) {
                // Gerät 1: MRT
                saveGeraet(geraetRepository, "MRT-01 Hochfeld", "Magnetresonanz", "Raum 101 (EG)");

                // Gerät 2: CT
                saveGeraet(geraetRepository, "CT-Scanner Alpha", "Computertomographie", "Raum 102 (EG)");

                // Gerät 3: Röntgen
                saveGeraet(geraetRepository, "Röntgen Digital X2", "Röntgen", "Raum 105 (1. Stock)");

                // Gerät 4: Ultraschall
                saveGeraet(geraetRepository, "Sonographie-Station", "Ultraschall", "Raum 201 (2. Stock)");

                // Gerät 5: Mammographie
                saveGeraet(geraetRepository, "Mammo-Scan", "Röntgen spezial", "Raum 108 (EG)");

                // Gerät 6: PET-CT
                saveGeraet(geraetRepository, "PET-CT Hybrid", "Nuklearmedizin", "Raum 005 (Keller)");
            }

        };
    }

    private void saveGeraet(GeraetRepository repo, String bez, String art, String ort) {
        Geraet g = new Geraet();
        g.setBezeichnung(bez);
        g.setArt(art);
        g.setStandort(ort);
        repo.save(g);
    }
}