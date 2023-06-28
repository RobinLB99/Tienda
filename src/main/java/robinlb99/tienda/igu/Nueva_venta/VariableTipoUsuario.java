package robinlb99.tienda.igu.Nueva_venta;

/**
 *
 * @author ROBINLB99
 */
public class VariableTipoUsuario {
    
    public static String nombreUsuario;
    public static boolean esAdministrador;
    
    public void setValor(boolean valor) {
        this.esAdministrador = valor;
    }
    
    public boolean getValor() {
        return esAdministrador;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    
    public void setNombreUsuario(String nombre) {
        this.nombreUsuario = nombre;
    }
    
}
