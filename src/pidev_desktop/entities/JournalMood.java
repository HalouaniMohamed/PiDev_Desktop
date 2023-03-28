/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pidev_desktop.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ALPHA
 */
@Entity
@Table(name = "journal_mood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "JournalMood.findAll", query = "SELECT j FROM JournalMood j")
    , @NamedQuery(name = "JournalMood.findById", query = "SELECT j FROM JournalMood j WHERE j.id = :id")
    , @NamedQuery(name = "JournalMood.findByIdUser", query = "SELECT j FROM JournalMood j WHERE j.idUser = :idUser")})
public class JournalMood implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "id_user")
    private Integer idUser;
    @JoinColumn(name = "moods_id", referencedColumnName = "id")
    @ManyToOne
    private Mood moodsId;

    public JournalMood() {
    }

    public JournalMood(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdUser() {
        return idUser;
    }

    public void setIdUser(Integer idUser) {
        this.idUser = idUser;
    }

    public Mood getMoodsId() {
        return moodsId;
    }

    public void setMoodsId(Mood moodsId) {
        this.moodsId = moodsId;
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
        if (!(object instanceof JournalMood)) {
            return false;
        }
        JournalMood other = (JournalMood) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.JournalMood[ id=" + id + " ]";
    }

}
