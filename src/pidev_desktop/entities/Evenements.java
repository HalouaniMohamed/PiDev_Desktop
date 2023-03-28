/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_desktop.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ALPHA
 */
@Entity
@Table(name = "evenements")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evenements.findAll", query = "SELECT e FROM Evenements e")
    , @NamedQuery(name = "Evenements.findById", query = "SELECT e FROM Evenements e WHERE e.id = :id")
    , @NamedQuery(name = "Evenements.findByNomEvenement", query = "SELECT e FROM Evenements e WHERE e.nomEvenement = :nomEvenement")
    , @NamedQuery(name = "Evenements.findByLieuEvenement", query = "SELECT e FROM Evenements e WHERE e.lieuEvenement = :lieuEvenement")
    , @NamedQuery(name = "Evenements.findByDateEvenement", query = "SELECT e FROM Evenements e WHERE e.dateEvenement = :dateEvenement")
    , @NamedQuery(name = "Evenements.findByDescriptionEvenement", query = "SELECT e FROM Evenements e WHERE e.descriptionEvenement = :descriptionEvenement")
    , @NamedQuery(name = "Evenements.findByNbrDePlaces", query = "SELECT e FROM Evenements e WHERE e.nbrDePlaces = :nbrDePlaces")
    , @NamedQuery(name = "Evenements.findByType", query = "SELECT e FROM Evenements e WHERE e.type = :type")
    , @NamedQuery(name = "Evenements.findByImage", query = "SELECT e FROM Evenements e WHERE e.image = :image")
    , @NamedQuery(name = "Evenements.findByHeure", query = "SELECT e FROM Evenements e WHERE e.heure = :heure")
    , @NamedQuery(name = "Evenements.findByCreatedAt", query = "SELECT e FROM Evenements e WHERE e.createdAt = :createdAt")
    , @NamedQuery(name = "Evenements.findByUpdatedAt", query = "SELECT e FROM Evenements e WHERE e.updatedAt = :updatedAt")
    , @NamedQuery(name = "Evenements.findByLikes", query = "SELECT e FROM Evenements e WHERE e.likes = :likes")
    , @NamedQuery(name = "Evenements.findByDislikes", query = "SELECT e FROM Evenements e WHERE e.dislikes = :dislikes")})
public class Evenements implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nom_evenement")
    private String nomEvenement;
    @Basic(optional = false)
    @Column(name = "lieu_evenement")
    private String lieuEvenement;
    @Basic(optional = false)
    @Column(name = "date_evenement")
    @Temporal(TemporalType.DATE)
    private Date dateEvenement;
    @Basic(optional = false)
    @Column(name = "description_evenement")
    private String descriptionEvenement;
    @Column(name = "nbr_de_places")
    private Integer nbrDePlaces;
    @Column(name = "type")
    private String type;
    @Column(name = "image")
    private String image;
    @Column(name = "heure")
    @Temporal(TemporalType.TIME)
    private Date heure;
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "likes")
    private String likes;
    @Column(name = "dislikes")
    private String dislikes;
    @OneToMany(mappedBy = "evenementsId")
    private List<Reservation> reservationList;

    public Evenements() {
    }

    public Evenements(Integer id) {
        this.id = id;
    }

    public Evenements(Integer id, String nomEvenement, String lieuEvenement, Date dateEvenement, String descriptionEvenement) {
        this.id = id;
        this.nomEvenement = nomEvenement;
        this.lieuEvenement = lieuEvenement;
        this.dateEvenement = dateEvenement;
        this.descriptionEvenement = descriptionEvenement;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNomEvenement() {
        return nomEvenement;
    }

    public void setNomEvenement(String nomEvenement) {
        this.nomEvenement = nomEvenement;
    }

    public String getLieuEvenement() {
        return lieuEvenement;
    }

    public void setLieuEvenement(String lieuEvenement) {
        this.lieuEvenement = lieuEvenement;
    }

    public Date getDateEvenement() {
        return dateEvenement;
    }

    public void setDateEvenement(Date dateEvenement) {
        this.dateEvenement = dateEvenement;
    }

    public String getDescriptionEvenement() {
        return descriptionEvenement;
    }

    public void setDescriptionEvenement(String descriptionEvenement) {
        this.descriptionEvenement = descriptionEvenement;
    }

    public Integer getNbrDePlaces() {
        return nbrDePlaces;
    }

    public void setNbrDePlaces(Integer nbrDePlaces) {
        this.nbrDePlaces = nbrDePlaces;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getHeure() {
        return heure;
    }

    public void setHeure(Date heure) {
        this.heure = heure;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getDislikes() {
        return dislikes;
    }

    public void setDislikes(String dislikes) {
        this.dislikes = dislikes;
    }

    @XmlTransient
    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
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
        if (!(object instanceof Evenements)) {
            return false;
        }
        Evenements other = (Evenements) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.Evenements[ id=" + id + " ]";
    }

}
