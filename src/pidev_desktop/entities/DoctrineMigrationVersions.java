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
import javax.persistence.Id;
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
@Table(name = "doctrine_migration_versions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "DoctrineMigrationVersions.findAll", query = "SELECT d FROM DoctrineMigrationVersions d")
    , @NamedQuery(name = "DoctrineMigrationVersions.findByVersion", query = "SELECT d FROM DoctrineMigrationVersions d WHERE d.version = :version")
    , @NamedQuery(name = "DoctrineMigrationVersions.findByExecutedAt", query = "SELECT d FROM DoctrineMigrationVersions d WHERE d.executedAt = :executedAt")
    , @NamedQuery(name = "DoctrineMigrationVersions.findByExecutionTime", query = "SELECT d FROM DoctrineMigrationVersions d WHERE d.executionTime = :executionTime")})
public class DoctrineMigrationVersions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "version")
    private String version;
    @Column(name = "executed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date executedAt;
    @Column(name = "execution_time")
    private Integer executionTime;

    public DoctrineMigrationVersions() {
    }

    public DoctrineMigrationVersions(String version) {
        this.version = version;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getExecutedAt() {
        return executedAt;
    }

    public void setExecutedAt(Date executedAt) {
        this.executedAt = executedAt;
    }

    public Integer getExecutionTime() {
        return executionTime;
    }

    public void setExecutionTime(Integer executionTime) {
        this.executionTime = executionTime;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (version != null ? version.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DoctrineMigrationVersions)) {
            return false;
        }
        DoctrineMigrationVersions other = (DoctrineMigrationVersions) object;
        if ((this.version == null && other.version != null) || (this.version != null && !this.version.equals(other.version))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.DoctrineMigrationVersions[ version=" + version + " ]";
    }

}
