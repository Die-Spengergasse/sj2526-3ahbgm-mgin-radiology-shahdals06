package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/reservierung")
public class ReservierungController {

    private final ReservierungRepository reservierungRepository;
    private final PatientRepository patientRepository;
    private final GeraetRepository geraetRepository;

    public ReservierungController(ReservierungRepository reservierungRepository,
                                  PatientRepository patientRepository,
                                  GeraetRepository geraetRepository) {
        this.reservierungRepository = reservierungRepository;
        this.patientRepository = patientRepository;
        this.geraetRepository = geraetRepository;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("reservierung", new Reservierung());
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("geraete", geraetRepository.findAll());
        return "add_reservierung";
    }


    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer geraetId,
                       Model model) {

        model.addAttribute("geraete", geraetRepository.findAll());

        if (geraetId != null) {
            model.addAttribute("reservierungen",
                    reservierungRepository.findByGeraet_Id(geraetId));
            model.addAttribute("selectedGeraetId", geraetId);
        }

        return "reservierung_list";
    }
    @PostMapping("/add")
    public String addSave(@ModelAttribute Reservierung reservierung,
                          org.springframework.validation.BindingResult result,
                          Model model) {

        if (result.hasErrors()) {

            String fehler =
                    result.getFieldError().getDefaultMessage();

            if (fehler.contains("Reservierung")) {
                fehler = "Reservierung darf nicht in der Vergangenheit liegen";
            }

            model.addAttribute("reservierung", reservierung);
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("geraete", geraetRepository.findAll());
            model.addAttribute("error", fehler);

            return "add_reservierung";
        }

        boolean geraetBelegt =
                reservierungRepository.existsByGeraet_IdAndReservierungszeit(
                        reservierung.getGeraet().getId(),
                        reservierung.getReservierungszeit()
                );

        if (geraetBelegt) {
            model.addAttribute("reservierung", reservierung);
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("geraete", geraetRepository.findAll());
            model.addAttribute("error",
                    "Dieses Gerät ist zu diesem Zeitpunkt bereits reserviert");
            return "add_reservierung";
        }

        boolean patientBelegt =
                reservierungRepository.existsByPatient_IdAndReservierungszeit(
                        reservierung.getPatient().getId(),
                        reservierung.getReservierungszeit()
                );

        if (patientBelegt) {
            model.addAttribute("reservierung", reservierung);
            model.addAttribute("patients", patientRepository.findAll());
            model.addAttribute("geraete", geraetRepository.findAll());
            model.addAttribute("error",
                    "Dieser Patient hat zu diesem Zeitpunkt bereits einen Termin");
            return "add_reservierung";
        }

        reservierungRepository.save(reservierung);
        return "redirect:/reservierung/list";
    }
    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model) {

        model.addAttribute("error",
                "Datenbankfehler: MySQL läuft nicht oder Verbindung fehlgeschlagen");

        return "error";
    }
}