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
        model.addAttribute("geraete", geraetRepository.findAll()); // Hier werden die Geräte geladen
        return "add_reservierung";
    }

    @PostMapping("/add")
    public String addSave(@ModelAttribute Reservierung reservierung) {
        reservierungRepository.save(reservierung);
        return "redirect:/reservierung/list";
    }

    @GetMapping("/list")
    public String list(@RequestParam(required = false) Integer geraetId, Model model) {
        model.addAttribute("geraete", geraetRepository.findAll());

        if (geraetId != null) {
            model.addAttribute("reservierungen", reservierungRepository.findByGeraet_Id(geraetId));
            model.addAttribute("selectedGeraetId", geraetId);
        } else {

        }

        return "reservierung_list";
    }
}