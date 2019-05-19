/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ceiba.biblioteca.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

/**
 *
 * @author willi
 */
@Entity
@Table(name = "usuario")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u")
    , @NamedQuery(name = "Usuario.findByUsuaId", query = "SELECT u FROM Usuario u WHERE u.usuaId = :usuaId")
    , @NamedQuery(name = "Usuario.findByUsuaDocumento", query = "SELECT u FROM Usuario u WHERE u.usuaDocumento = :usuaDocumento")
    , @NamedQuery(name = "Usuario.findByUsuaNombre", query = "SELECT u FROM Usuario u WHERE u.usuaNombre = :usuaNombre")})
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usua_id")
    private Integer usuaId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 12)
    @Column(name = "usua_documento")
    private String usuaDocumento;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usua_nombre")
    private String usuaNombre;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuaId")
    private Collection<Prestamo> prestamoCollection;

    public Usuario() {
    }

    public Usuario(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public Usuario(Integer usuaId, String usuaDocumento, String usuaNombre) {
        this.usuaId = usuaId;
        this.usuaDocumento = usuaDocumento;
        this.usuaNombre = usuaNombre;
    }

    public Integer getUsuaId() {
        return usuaId;
    }

    public void setUsuaId(Integer usuaId) {
        this.usuaId = usuaId;
    }

    public String getUsuaDocumento() {
        return usuaDocumento;
    }

    public void setUsuaDocumento(String usuaDocumento) {
        this.usuaDocumento = usuaDocumento;
    }

    public String getUsuaNombre() {
        return usuaNombre;
    }

    public void setUsuaNombre(String usuaNombre) {
        this.usuaNombre = usuaNombre;
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
        hash += (usuaId != null ? usuaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.usuaId == null && other.usuaId != null) || (this.usuaId != null && !this.usuaId.equals(other.usuaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.ceiba.biblioteca.models.Usuario[ usuaId=" + usuaId + " ]";
    }
    
}
