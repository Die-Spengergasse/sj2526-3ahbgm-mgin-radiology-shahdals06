package at.spengergasse.spring_thymeleaf.entities;

import jakarta.persistence.*;

@Entity
public class Geraet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String bezeichnung;
    private String art;
    private String standort;    a
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getBezeichnung() { return bezeichnung; }
    public void setBezeichnung(String bezeichnung) { this.bezeichnung = bezeichnung; }

    public String getArt() { return art; }
    public void setArt(String art) { this.art = art; }

    public String getStandort() { return standort; }
    public void setStandort(String standort) { this.standort = standort; }
}