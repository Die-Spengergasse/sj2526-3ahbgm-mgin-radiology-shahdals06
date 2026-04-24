package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Patient;
import at.spengergasse.spring_thymeleaf.entities.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/patient")
public class PatientController {

    private final PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/list")
    public String list(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        return "patlist";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("patient", new Patient());
        return "add_patient";
    }

    @PostMapping("/add")
    public String save(@ModelAttribute Patient patient,
                       org.springframework.validation.BindingResult result,
                       Model model) {

        if (result.hasErrors()) {

            String fehler =
                    result.getFieldError().getDefaultMessage();

            if (fehler.contains("Geburtsdatum")) {
                fehler = "Geburtsdatum darf nicht in der Zukunft liegen";
            }

            if (fehler.contains("Sozialversicherungsnummer")) {
                fehler = "Ungültige Sozialversicherungsnummer";
            }

            model.addAttribute("patient", patient);
            model.addAttribute("error", fehler);

            return "add_patient";
        }

        patientRepository.save(patient);
        return "redirect:/patient/list";
    }
    @ExceptionHandler(Exception.class)
    public String error(Exception e, Model model) {

        model.addAttribute("error",
                "Datenbankfehler: MySQL läuft nicht oder Verbindung fehlgeschlagen");

        return "error";
    }
}