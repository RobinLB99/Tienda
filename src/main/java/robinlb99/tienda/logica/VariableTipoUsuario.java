package robinlb99.tienda.logica;

/**
 *
 * @author ROBINLB99
 */
public class VariableTipoUsuario {
    
    public static String nombreUsuario;
    public static String contrasena;
    public static boolean esAdministrador;
    
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
