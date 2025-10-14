package ma.projet.classes;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "employe")
public class Employe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;
    private String prenom;
    private String telephone;

    // Relation: un employé peut gérer plusieurs projets (chef de projet)
    @OneToMany(mappedBy = "chefProjet")
    private Set<Projet> projetsGerés = new HashSet<>();

    // Relation: association avec EmployeTache
    @OneToMany(mappedBy = "employe")
    private Set<EmployeTache> employeTaches = new HashSet<>();

    // Constructors, getters, setters, toString
    public Employe() {}
    public Employe(String nom, String prenom, String telephone) {
        this.nom = nom;
        this.prenom = prenom;
        this.telephone = telephone;
    }

    // getters/setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public String getTelephone() { return telephone; }
    public void setTelephone(String telephone) { this.telephone = telephone; }
    public Set<Projet> getProjetsGerés() { return projetsGerés; }
    public Set<EmployeTache> getEmployeTaches() { return employeTaches; }

    @Override
    public String toString() {
        return "Employe{" + "id=" + id + ", nom='" + nom + '\'' + ", prenom='" + prenom + '\'' + '}';
    }
}
