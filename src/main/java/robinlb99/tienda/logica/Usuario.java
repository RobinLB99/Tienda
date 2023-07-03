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
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private boolean isAdministrador;
    private boolean passwordRecuperado;
    private boolean nuevoUsuario;

    public Usuario() {
    }

    public Usuario(int id, String cedula, String nombres, String apellidos, String usuario, String contrasena, boolean isAdministrador, boolean passwordRecuperado, boolean nuevoUsuario) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.usuario = usuario;
        this.contrasena = contrasena;
        this.isAdministrador = isAdministrador;
        this.passwordRecuperado = passwordRecuperado;
        this.nuevoUsuario = nuevoUsuario;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
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

    public boolean isPasswordRecuperado() {
        return passwordRecuperado;
    }

    public void setPasswordRecuperado(boolean passwordRecuperado) {
        this.passwordRecuperado = passwordRecuperado;
    }

    public boolean isNuevoUsuario() {
        return nuevoUsuario;
    }

    public void setNuevoUsuario(boolean nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + 
                ", cedula=" + cedula + 
                ", nombres=" + nombres + 
                ", apellidos=" + apellidos + 
                ", usuario=" + usuario + 
                ", contrasena=" + contrasena + 
                ", isAdministrador=" + isAdministrador + 
                ", passwordRecuperado=" + passwordRecuperado + '}';
    }
    
}
