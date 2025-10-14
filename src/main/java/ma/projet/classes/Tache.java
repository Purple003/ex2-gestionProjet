package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tache")
@NamedQuery(name = "Tache.findByPrixGreaterThan", query = "FROM Tache t WHERE t.prix > :prix")
public class Tache {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    private Double prix;

    @ManyToOne
    @JoinColumn(name = "projet_id")
    private Projet projet;

    @OneToMany(mappedBy = "tache")
    private Set<EmployeTache> employeTaches = new HashSet<>();

    public Tache() {}
    public Tache(String nom, Date dateDebut, Date dateFin, Double prix) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.prix = prix;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public Date getDateDebut() { return dateDebut; }
    public void setDateDebut(Date dateDebut) { this.dateDebut = dateDebut; }
    public Date getDateFin() { return dateFin; }
    public void setDateFin(Date dateFin) { this.dateFin = dateFin; }
    public Double getPrix() { return prix; }
    public void setPrix(Double prix) { this.prix = prix; }
    public Projet getProjet() { return projet; }
    public void setProjet(Projet projet) { this.projet = projet; }
    public Set<EmployeTache> getEmployeTaches() { return employeTaches; }

    @Override
    public String toString() {
        return "Tache{" + "id=" + id + ", nom='" + nom + '\'' + ", prix=" + prix + '}';
    }
}
