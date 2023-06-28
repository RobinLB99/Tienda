package robinlb99.tienda.logica;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author ROBINLB99
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String usuario;
    private String contrasena;
    private boolean isAdministrador;

    public Usuario() {
    }

    public Usuario(int id, String usuario, String contrasena, boolean isAdministrador) {
        this.id = id;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.isAdministrador = isAdministrador;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public boolean isIsAdministrador() {
        return isAdministrador;
    }

    public void setIsAdministrador(boolean isAdministrador) {
        this.isAdministrador = isAdministrador;
    }

    @Override
    public String toString() {
        return "Usuario{" + 
                "id=" + id + 
                ", usuario=" + usuario + 
                ", contrasena=" + contrasena + 
                ", isAdministrador=" + isAdministrador + '}';
    }
    
    
}
