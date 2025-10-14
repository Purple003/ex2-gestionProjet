package ma.projet.classes;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "projet")
public class Projet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    @Temporal(TemporalType.DATE)
    private Date dateDebut;

    @Temporal(TemporalType.DATE)
    private Date dateFin;

    // Chef de projet: ManyToOne vers Employe
    @ManyToOne
    @JoinColumn(name = "chef_id")
    private Employe chefProjet;

    @OneToMany(mappedBy = "projet", cascade = CascadeType.ALL)
    private Set<Tache> taches = new HashSet<>();

    public Projet() {}
    public Projet(String nom, Date dateDebut, Date dateFin) {
        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
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
    public Employe getChefProjet() { return chefProjet; }
    public void setChefProjet(Employe chefProjet) { this.chefProjet = chefProjet; }
    public Set<Tache> getTaches() { return taches; }

    @Override
    public String toString() {
        return "Projet{" + "id=" + id + ", nom='" + nom + '\'' + '}';
    }
}
