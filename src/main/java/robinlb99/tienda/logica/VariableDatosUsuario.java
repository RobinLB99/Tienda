package robinlb99.tienda.logica;

/**
 *
 * @author ROBINLB99
 */
public class VariableDatosUsuario {
    
    public static int id;
    public static String nombres;
    public static String apellidos;
    public static String nombreUsuario;
    public static String contrasena;
    public static boolean esAdministrador;
    public static boolean passwordRecuperado;
    public static boolean nuevoUsuario;
    
    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
    
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
    
    public void setPasswordRecuperado(boolean recuperado) {
        this.passwordRecuperado = recuperado;
    }

    public boolean getPasswordRecuperado() {
        return passwordRecuperado;
    }
    
    public void setNuevoUsuario(boolean nuevoUsuario) {
        this.nuevoUsuario = nuevoUsuario;
    }

    public boolean getNuevoUsuario() {
        return nuevoUsuario;
    }
    
}
