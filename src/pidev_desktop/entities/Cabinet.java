/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_desktop.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALPHA
 */
@Entity
@Table(name = "cabinet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cabinet.findAll", query = "SELECT c FROM Cabinet c")
    , @NamedQuery(name = "Cabinet.findById", query = "SELECT c FROM Cabinet c WHERE c.id = :id")
    , @NamedQuery(name = "Cabinet.findByNom", query = "SELECT c FROM Cabinet c WHERE c.nom = :nom")
    , @NamedQuery(name = "Cabinet.findByAdresse", query = "SELECT c FROM Cabinet c WHERE c.adresse = :adresse")})
public class Cabinet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom")
    private String nom;
    @Basic(optional = false)
    @Column(name = "adresse")
    private String adresse;
    @OneToMany(mappedBy = "cabinetId")
    private List<RendezVous> rendezVousList;
    @OneToOne(mappedBy = "cabinetId")
    private Medecin medecin;

    public Cabinet() {
    }

    public Cabinet(Integer id) {
        this.id = id;
    }

    public Cabinet(Integer id, String nom, String adresse) {
        this.id = id;
        this.nom = nom;
        this.adresse = adresse;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    @XmlTransient
    public List<RendezVous> getRendezVousList() {
        return rendezVousList;
    }

    public void setRendezVousList(List<RendezVous> rendezVousList) {
        this.rendezVousList = rendezVousList;
    }

    public Medecin getMedecin() {
        return medecin;
    }

    public void setMedecin(Medecin medecin) {
        this.medecin = medecin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cabinet)) {
            return false;
        }
        Cabinet other = (Cabinet) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.Cabinet[ id=" + id + " ]";
    }

}
