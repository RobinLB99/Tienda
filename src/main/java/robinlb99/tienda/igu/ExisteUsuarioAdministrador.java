package robinlb99.tienda.igu;

import java.util.ArrayList;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Usuario;

/**
 *
 * @author ROBINLB99
 */
public class ExisteUsuarioAdministrador {
    
    Window ventana = new Window();
    LogicController control = new LogicController();
    
    public ExisteUsuarioAdministrador() {
        
        ArrayList<Usuario> usuarios = control.listaUsuarios();
        
        if (usuarios.size() != 0) {
            // Pantalla de login
            ventana.inicioSessionRegistro();
            
        } else {
            ventana.mensaje("Usuarios inexistentes", "warning", "No existen usuarios registrados y se va ha crear uno.");
            ventana.crearAdministrador();
        }
        
        
    }
    
}
