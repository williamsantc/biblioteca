/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.models;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "prestamo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Prestamo.findAll", query = "SELECT p FROM Prestamo p")
    , @NamedQuery(name = "Prestamo.findByPresId", query = "SELECT p FROM Prestamo p WHERE p.presId = :presId")
    , @NamedQuery(name = "Prestamo.findByPresFecha", query = "SELECT p FROM Prestamo p WHERE p.presFecha = :presFecha")
    , @NamedQuery(name = "Prestamo.findByPresFechalimite", query = "SELECT p FROM Prestamo p WHERE p.presFechalimite = :presFechalimite")})
public class Prestamo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "pres_id")
    private Integer presId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "pres_fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date presFecha;
    @Column(name = "pres_fechalimite")
    @Temporal(TemporalType.TIMESTAMP)
    private Date presFechalimite;
    @JoinColumn(name = "libr_id", referencedColumnName = "libr_id")
    @ManyToOne(optional = false)
    private Libro librId;
    @JoinColumn(name = "usua_id", referencedColumnName = "usua_id")
    @ManyToOne(optional = false)
    private Usuario usuaId;

    public Prestamo() {
    }

    public Prestamo(Integer presId) {
        this.presId = presId;
    }

    public Prestamo(Integer presId, Date presFecha) {
        this.presId = presId;
        this.presFecha = presFecha;
    }

    public Integer getPresId() {
        return presId;
    }

    public void setPresId(Integer presId) {
        this.presId = presId;
    }

    public Date getPresFecha() {
        return presFecha;
    }

    public void setPresFecha(Date presFecha) {
        this.presFecha = presFecha;
    }

    public Date getPresFechalimite() {
        return presFechalimite;
    }

    public void setPresFechalimite(Date presFechalimite) {
        this.presFechalimite = presFechalimite;
    }

    public Libro getLibrId() {
        return librId;
    }

    public void setLibrId(Libro librId) {
        this.librId = librId;
    }

    public Usuario getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Usuario usuaId) {
        this.usuaId = usuaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (presId != null ? presId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Prestamo)) {
            return false;
        }
        Prestamo other = (Prestamo) object;
        if ((this.presId == null && other.presId != null) || (this.presId != null && !this.presId.equals(other.presId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ceiba.biblioteca.models.Prestamo[ presId=" + presId + " ]";
    }
    
    @Transient
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
}
