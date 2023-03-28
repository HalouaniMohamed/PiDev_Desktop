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
@Table(name = "mood")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mood.findAll", query = "SELECT m FROM Mood m")
    , @NamedQuery(name = "Mood.findById", query = "SELECT m FROM Mood m WHERE m.id = :id")
    , @NamedQuery(name = "Mood.findByMoodId", query = "SELECT m FROM Mood m WHERE m.moodId = :moodId")
    , @NamedQuery(name = "Mood.findByUserId", query = "SELECT m FROM Mood m WHERE m.userId = :userId")
    , @NamedQuery(name = "Mood.findByMood", query = "SELECT m FROM Mood m WHERE m.mood = :mood")
    , @NamedQuery(name = "Mood.findByDescription", query = "SELECT m FROM Mood m WHERE m.description = :description")
    , @NamedQuery(name = "Mood.findByDate", query = "SELECT m FROM Mood m WHERE m.date = :date")})
public class Mood implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "mood_id")
    private int moodId;
    @Basic(optional = false)
    @Column(name = "user_id")
    private int userId;
    @Basic(optional = false)
    @Column(name = "mood")
    private String mood;
    @Basic(optional = false)
    @Column(name = "description")
    private String description;
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @OneToMany(mappedBy = "moodsId")
    private List<JournalMood> journalMoodList;

    public Mood() {
    }

    public Mood(Integer id) {
        this.id = id;
    }

    public Mood(Integer id, int moodId, int userId, String mood, String description) {
        this.id = id;
        this.moodId = moodId;
        this.userId = userId;
        this.mood = mood;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int moodId) {
        this.moodId = moodId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @XmlTransient
    public List<JournalMood> getJournalMoodList() {
        return journalMoodList;
    }

    public void setJournalMoodList(List<JournalMood> journalMoodList) {
        this.journalMoodList = journalMoodList;
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
        if (!(object instanceof Mood)) {
            return false;
        }
        Mood other = (Mood) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.Mood[ id=" + id + " ]";
    }

}
