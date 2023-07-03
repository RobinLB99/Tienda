package robinlb99.tienda.igu;

import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Usuario;
import robinlb99.tienda.logica.VariableDatosUsuario;

/**
 *
 * @author ROBINLB99
 */
public class NuevoUsuarioRecuperandoContrasena {
    
    private int idUsuario;
    private boolean recuperandoContrasena;
    private boolean nuevoUsuario;
    
    private boolean continuar = true;
    
    Window ventana = new Window();
    LogicController control = new LogicController();
    VariableDatosUsuario userData = new VariableDatosUsuario();
    
    public void nuveoUsuarioRecuperandoContrasena() {
        this.nuevoUsuario = userData.getNuevoUsuario();
        this.recuperandoContrasena = userData.getPasswordRecuperado();
        this.idUsuario = userData.getId();
        
        Usuario user = control.buscarUsuario(idUsuario);
        
        if (nuevoUsuario) {
            
            ventana.mensaje("Nueva contrasena", "info", "Necesitas crear una contrasena personal");
            ventana.cambiarContrasena(null, true, "nuevoUsuario");
            
        } else if (recuperandoContrasena) {
            
            ventana.mensaje("Recuperando acceso", "info", "Contraseña restablecida!.\nEstablece una nueva contraseña.");
            ventana.cambiarContrasena(null, true, "recuperandoContrasena");
        
        } else {
            
            ventana.menu();
            
        }
        
         
        
    }
    
}
