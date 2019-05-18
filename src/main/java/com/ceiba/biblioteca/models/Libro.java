/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.models;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author willi
 */
@Entity
@Table(name = "libro")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Libro.findAll", query = "SELECT l FROM Libro l")
    , @NamedQuery(name = "Libro.findByLibrId", query = "SELECT l FROM Libro l WHERE l.librId = :librId")
    , @NamedQuery(name = "Libro.findByLibrIsbn", query = "SELECT l FROM Libro l WHERE l.librIsbn = :librIsbn")
    , @NamedQuery(name = "Libro.findByLibrNombre", query = "SELECT l FROM Libro l WHERE l.librNombre = :librNombre")
    , @NamedQuery(name = "Libro.findByLibrCantejemplares", query = "SELECT l FROM Libro l WHERE l.librCantejemplares = :librCantejemplares")})
public class Libro implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "libr_id")
    private Integer librId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libr_isbn")
    private String librIsbn;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "libr_nombre")
    private String librNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "libr_cantejemplares")
    private int librCantejemplares;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "librId")
    private Collection<Prestamo> prestamoCollection;

    public Libro() {
    }

    public Libro(Integer librId) {
        this.librId = librId;
    }

    public Libro(Integer librId, String librIsbn, String librNombre, int librCantejemplares) {
        this.librId = librId;
        this.librIsbn = librIsbn;
        this.librNombre = librNombre;
        this.librCantejemplares = librCantejemplares;
    }

    public Integer getLibrId() {
        return librId;
    }

    public void setLibrId(Integer librId) {
        this.librId = librId;
    }

    public String getLibrIsbn() {
        return librIsbn;
    }

    public void setLibrIsbn(String librIsbn) {
        this.librIsbn = librIsbn;
    }

    public String getLibrNombre() {
        return librNombre;
    }

    public void setLibrNombre(String librNombre) {
        this.librNombre = librNombre;
    }

    public int getLibrCantejemplares() {
        return librCantejemplares;
    }

    public void setLibrCantejemplares(int librCantejemplares) {
        this.librCantejemplares = librCantejemplares;
    }

    @XmlTransient
    @JsonIgnore
    public Collection<Prestamo> getPrestamoCollection() {
        return prestamoCollection;
    }

    public void setPrestamoCollection(Collection<Prestamo> prestamoCollection) {
        this.prestamoCollection = prestamoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (librId != null ? librId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Libro)) {
            return false;
        }
        Libro other = (Libro) object;
        if ((this.librId == null && other.librId != null) || (this.librId != null && !this.librId.equals(other.librId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ceiba.biblioteca.models.Libro[ librId=" + librId + " ]";
    }
    
}
