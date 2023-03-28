/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_desktop.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALPHA
 */
@Entity
@Table(name = "rendez_vous")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "RendezVous.findAll", query = "SELECT r FROM RendezVous r")
    , @NamedQuery(name = "RendezVous.findById", query = "SELECT r FROM RendezVous r WHERE r.id = :id")
    , @NamedQuery(name = "RendezVous.findByNom", query = "SELECT r FROM RendezVous r WHERE r.nom = :nom")
    , @NamedQuery(name = "RendezVous.findByPrenom", query = "SELECT r FROM RendezVous r WHERE r.prenom = :prenom")
    , @NamedQuery(name = "RendezVous.findByCause", query = "SELECT r FROM RendezVous r WHERE r.cause = :cause")
    , @NamedQuery(name = "RendezVous.findByDateRv", query = "SELECT r FROM RendezVous r WHERE r.dateRv = :dateRv")})
public class RendezVous implements Serializable {

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
    @Column(name = "prenom")
    private String prenom;
    @Basic(optional = false)
    @Column(name = "cause")
    private String cause;
    @Basic(optional = false)
    @Column(name = "date_rv")
    @Temporal(TemporalType.DATE)
    private Date dateRv;
    @Lob
    @Column(name = "description")
    private String description;
    @JoinColumn(name = "medecin_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Medecin medecinId;
    @JoinColumn(name = "cabinet_id", referencedColumnName = "id")
    @ManyToOne
    private Cabinet cabinetId;

    public RendezVous() {
    }

    public RendezVous(Integer id) {
        this.id = id;
    }

    public RendezVous(Integer id, String nom, String prenom, String cause, Date dateRv) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.cause = cause;
        this.dateRv = dateRv;
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

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public Date getDateRv() {
        return dateRv;
    }

    public void setDateRv(Date dateRv) {
        this.dateRv = dateRv;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Medecin getMedecinId() {
        return medecinId;
    }

    public void setMedecinId(Medecin medecinId) {
        this.medecinId = medecinId;
    }

    public Cabinet getCabinetId() {
        return cabinetId;
    }

    public void setCabinetId(Cabinet cabinetId) {
        this.cabinetId = cabinetId;
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
        if (!(object instanceof RendezVous)) {
            return false;
        }
        RendezVous other = (RendezVous) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.RendezVous[ id=" + id + " ]";
    }

}
