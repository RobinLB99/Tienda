package robinlb99.tienda.logica;

/**
 *
 * @author ROBINLB99
 */
public class VariableDatosUsuario {
    
    public static String nombres;
    public static String apellidos;
    public static String nombreUsuario;
    public static String contrasena;
    public static boolean esAdministrador;
    
    public void setNombres(String nombre) {
        this.nombres = nombre;
    }

    public String getNombres() {
        return nombres;
    }
    
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getApellidos() {
        return apellidos;
    }
    
    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setPassword(String password) {
        this.contrasena = password;
    }
    
    public String getPassword() {
        return contrasena;
    }
    
    public void setValor(boolean valor) {
        this.esAdministrador = valor;
    }
    
    public boolean getValor() {
        return esAdministrador;
    }
    
}
