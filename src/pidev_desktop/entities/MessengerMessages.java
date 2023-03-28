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
import javax.persistence.Lob;
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
@Table(name = "messenger_messages")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MessengerMessages.findAll", query = "SELECT m FROM MessengerMessages m")
    , @NamedQuery(name = "MessengerMessages.findById", query = "SELECT m FROM MessengerMessages m WHERE m.id = :id")
    , @NamedQuery(name = "MessengerMessages.findByQueueName", query = "SELECT m FROM MessengerMessages m WHERE m.queueName = :queueName")
    , @NamedQuery(name = "MessengerMessages.findByCreatedAt", query = "SELECT m FROM MessengerMessages m WHERE m.createdAt = :createdAt")
    , @NamedQuery(name = "MessengerMessages.findByAvailableAt", query = "SELECT m FROM MessengerMessages m WHERE m.availableAt = :availableAt")
    , @NamedQuery(name = "MessengerMessages.findByDeliveredAt", query = "SELECT m FROM MessengerMessages m WHERE m.deliveredAt = :deliveredAt")})
public class MessengerMessages implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Long id;
    @Basic(optional = false)
    @Lob
    @Column(name = "body")
    private String body;
    @Basic(optional = false)
    @Lob
    @Column(name = "headers")
    private String headers;
    @Basic(optional = false)
    @Column(name = "queue_name")
    private String queueName;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Basic(optional = false)
    @Column(name = "available_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date availableAt;
    @Column(name = "delivered_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveredAt;

    public MessengerMessages() {
    }

    public MessengerMessages(Long id) {
        this.id = id;
    }

    public MessengerMessages(Long id, String body, String headers, String queueName, Date createdAt, Date availableAt) {
        this.id = id;
        this.body = body;
        this.headers = headers;
        this.queueName = queueName;
        this.createdAt = createdAt;
        this.availableAt = availableAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getAvailableAt() {
        return availableAt;
    }

    public void setAvailableAt(Date availableAt) {
        this.availableAt = availableAt;
    }

    public Date getDeliveredAt() {
        return deliveredAt;
    }

    public void setDeliveredAt(Date deliveredAt) {
        this.deliveredAt = deliveredAt;
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
        if (!(object instanceof MessengerMessages)) {
            return false;
        }
        MessengerMessages other = (MessengerMessages) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pidev_desktop.entities.MessengerMessages[ id=" + id + " ]";
    }

}
