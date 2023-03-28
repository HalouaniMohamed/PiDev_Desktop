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
@Table(name = "post")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Post.findAll", query = "SELECT p FROM Post p")
    , @NamedQuery(name = "Post.findById", query = "SELECT p FROM Post p WHERE p.id = :id")
    , @NamedQuery(name = "Post.findByIdUser", query = "SELECT p FROM Post p WHERE p.idUser = :idUser")
    , @NamedQuery(name = "Post.findByDescription", query = "SELECT p FROM Post p WHERE p.description = :description")
    , @NamedQuery(name = "Post.findByPublication", query = "SELECT p FROM Post p WHERE p.publication = :publication")
    , @NamedQuery(name = "Post.findByNomUtilisateur", query = "SELECT p FROM Post p WHERE p.nomUtilisateur = :nomUtilisateur")
    , @NamedQuery(name = "Post.findByCreatedAt", query = "SELECT p FROM Post p WHERE p.createdAt = :createdAt")
    , @NamedQuery(name = "Post.findByUpdatedAt", query = "SELECT p FROM Post p WHERE p.updatedAt = :updatedAt")
    , @NamedQuery(name = "Post.findByLikes", query = "SELECT p FROM Post p WHERE p.likes = :likes")
    , @NamedQuery(name = "Post.findByDislike", query = "SELECT p FROM Post p WHERE p.dislike = :dislike")
    , @NamedQuery(name = "Post.findByRate", query = "SELECT p FROM Post p WHERE p.rate = :rate")})
public class Post implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "id_user")
    private int idUser;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "publication")
    private String publication;
    @Basic(optional = false)
    @Column(name = "nom_utilisateur")
    private String nomUtilisateur;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "likes")
    private String likes;
    @Column(name = "dislike")
    private String dislike;
    @Column(name = "rate")
    private Integer rate;
    @OneToMany(mappedBy = "commentairesId")
    private List<Commentaire> commentaireList;

    public Post() {
    }

    public Post(Integer id) {
        this.id = id;
    }

    public Post(Integer id, int idUser, String description, String publication, String nomUtilisateur, Date createdAt, Date updatedAt) {
        this.id = id;
        this.idUser = idUser;
        this.description = description;
        this.publication = publication;
        this.nomUtilisateur = nomUtilisateur;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
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

    public String getDislike() {
        return dislike;
    }

    public void setDislike(String dislike) {
        this.dislike = dislike;
    }

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
        this.rate = rate;
    }

    @XmlTransient
    public List<Commentaire> getCommentaireList() {
        return commentaireList;
    }

    public void setCommentaireList(List<Commentaire> commentaireList) {
        this.commentaireList = commentaireList;
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
        if (!(object instanceof Post)) {
            return false;
        }
        Post other = (Post) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.Post[ id=" + id + " ]";
    }

}
