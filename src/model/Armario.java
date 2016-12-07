
package model;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class Armario implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private int numero;
    private String localizacao;
    private boolean estaDisponivel;
    private String MostrarDisponivel;

    public String getMostrarDisponivel() {
        return MostrarDisponivel;
    }

    public void setMostrarDisponivel(String MostrarDisponivel) {
        this.MostrarDisponivel = MostrarDisponivel;
    }
    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public boolean isEstaDisponivel() {
        return estaDisponivel;
    }

    public void setEstaDisponivel(boolean estaDisponivel) {
        this.estaDisponivel = estaDisponivel;
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
        if (!(object instanceof Armario)) {
            return false;
        }
        Armario other = (Armario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "model.Armario[ id=" + id + " ]";
    }
    
}
