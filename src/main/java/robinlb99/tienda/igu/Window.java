package robinlb99.tienda.igu;

import robinlb99.tienda.igu.Nueva_venta.ConfirmarCompra;
import robinlb99.tienda.igu.Nueva_venta.TraerClientes;
import robinlb99.tienda.igu.Nueva_venta.SeleccionProductos;
import robinlb99.tienda.igu.Nueva_venta.NuevaVenta;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import robinlb99.tienda.igu.VerStockProductos.AgregarUnidadesAlProducto;
import robinlb99.tienda.igu.VerStockProductos.ConfirmarAgregadoUnidades;
import robinlb99.tienda.igu.VerStockProductos.ConfirmarModificacion;
import robinlb99.tienda.igu.VerStockProductos.ConfirmarNuevoProducto;
import robinlb99.tienda.igu.VerStockProductos.ModificarProducto;
import robinlb99.tienda.igu.VerStockProductos.NuevoProducto;
import robinlb99.tienda.igu.VerStockProductos.VerStock;
import robinlb99.tienda.igu.Ver_ventas.VerVentas;
import robinlb99.tienda.igu.gestionUsuarios.AgregarUsuario;
import robinlb99.tienda.igu.gestionUsuarios.ConfirmarNuevoUsuario;
import robinlb99.tienda.igu.gestionUsuarios.GestionUsuarios;
import robinlb99.tienda.logica.Cliente;
import robinlb99.tienda.logica.Producto;


public class Window {
    
    NuevaVenta newSale = null;
    
    public void theme() {
        EscogerTemaAplicacion themeMenu = new EscogerTemaAplicacion();
        themeMenu.setVisible(true);
        themeMenu.setLocationRelativeTo(null);
        themeMenu.setResizable(false);
        themeMenu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        themeMenu.setTitle("Seleccion de tema");
    }

    public void menu() {
        MenuPrincipal menu = new MenuPrincipal();
        menu.setVisible(true);
        menu.setLocationRelativeTo(null);
        menu.setResizable(false);
        menu.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        menu.setTitle("Menu principal");
    }
    
    public void nuevaVenta(boolean consumidorFinal, long idTomado, ArrayList<Producto> productos) {
        newSale = new NuevaVenta(consumidorFinal, idTomado, productos);
        newSale.setVisible(true);
        newSale.setLocationRelativeTo(null);
        newSale.setResizable(false);
        newSale.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        newSale.setTitle("Nueva venta");
    }
    
    public void traerClientes(boolean consumidorFinal, long idRecibido, ArrayList<Producto> productos) {
        TraerClientes clientes = new TraerClientes(consumidorFinal, idRecibido, productos);
        clientes.setVisible(true);
        clientes.setLocationRelativeTo(null);
        clientes.setResizable(false);
        clientes.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        clientes.setTitle("Seleccionar Cliente");
    }

    
    public void seleccionarProductos(boolean consumidorFinal, long idTomado) {
        SeleccionProductos selectProd = new SeleccionProductos(consumidorFinal, idTomado);
        selectProd.setVisible(true);
        selectProd.setLocationRelativeTo(null);
        selectProd.setResizable(false);
        selectProd.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        selectProd.setTitle("Seleccion de productos");
    }
    
    public void confirmarCompra(double value, Cliente cliente, ArrayList<Producto> productos, ArrayList<Producto> unidadesARestar, boolean canceladoInmediato, boolean cFinal, long idTomado) {
        ConfirmarCompra confirmarCompra = new ConfirmarCompra(value, cliente, productos, unidadesARestar, canceladoInmediato, cFinal, idTomado);
        confirmarCompra.setVisible(true);
        confirmarCompra.setLocationRelativeTo(null);
        confirmarCompra.setResizable(false);
        confirmarCompra.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        confirmarCompra.setTitle("Seleccion de productos");
    }
    
    public void mensaje(String titulo, String tipo, String mensaje) {
        JOptionPane optionPane = new JOptionPane(mensaje);
        if (tipo.equals("info")) {
            optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
        } else if (tipo.equals("error")) {
            optionPane.setMessageType(JOptionPane.ERROR_MESSAGE);
        } else if (tipo.equals("warning")) {
            optionPane.setMessageType(JOptionPane.WARNING_MESSAGE);
        }
        JDialog dialog = optionPane.createDialog(titulo);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
    }
    
    public void verVentas() {
        VerVentas verVentas = new VerVentas();
        verVentas.setVisible(true);
        verVentas.setLocationRelativeTo(null);
        verVentas.setResizable(false);
        verVentas.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        verVentas.setTitle("Ver ventas realizadas");                
    }
    
    public void verStock() {
        VerStock verStock = new VerStock();
        verStock.setVisible(true);
        verStock.setLocationRelativeTo(null);
        verStock.setResizable(false);
        verStock.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        verStock.setTitle("Inventario de productos");
    }
    
    
    public void nuevoProducto(String nombre, double precio, int unidades) {
        NuevoProducto nuevoProducto = new NuevoProducto(nombre, precio, unidades);
        nuevoProducto.setVisible(true);
        nuevoProducto.setLocationRelativeTo(null);
        nuevoProducto.setResizable(false);
        nuevoProducto.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        nuevoProducto.setTitle("Crear nuevo registro de producto");
    }

    public void confirmarNuevoProducto(String nombre, double precio, int unidades) {
        ConfirmarNuevoProducto confirmar = new ConfirmarNuevoProducto(nombre, precio, unidades);
        confirmar.setVisible(true);
        confirmar.setLocationRelativeTo(null);
        confirmar.setResizable(false);
        confirmar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        confirmar.setTitle("Confirmar datos de producto");
    }
    
    public void modificarProducto(long id) {
        ModificarProducto modifcarProducto = new ModificarProducto(id);
        modifcarProducto.setVisible(true);
        modifcarProducto.setLocationRelativeTo(null);
        modifcarProducto.setResizable(false);
        modifcarProducto.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        modifcarProducto.setTitle("Modicar producto");
    }
    
    
    public ConfirmarModificacion confirmarModificacion(java.awt.Frame parent, boolean modal, Producto pModificar, String nombre, String precio) {
        ConfirmarModificacion confirmDialog = new ConfirmarModificacion(parent, modal, pModificar, nombre, precio);
        confirmDialog.setLocationRelativeTo(null);
        confirmDialog.setResizable(false);
        confirmDialog.setTitle("Confirmar modificacion");
        confirmDialog.pack();
        confirmDialog.setVisible(true);
        return confirmDialog;
    }
    
    
    public void agregarUnidadesProducto(long id) {
        AgregarUnidadesAlProducto agregarUnidades = new AgregarUnidadesAlProducto(id);
        agregarUnidades.setLocationRelativeTo(null);
        agregarUnidades.setResizable(false);
        agregarUnidades.pack();
        agregarUnidades.setVisible(true); 
    }
    
    
    public ConfirmarAgregadoUnidades confirmarAgregadoUnidades(java.awt.Frame parent, boolean modal, String nombre, String precio,String unidades, String unidAgregar) {
        ConfirmarAgregadoUnidades confirmarAgregadoUnidades = new ConfirmarAgregadoUnidades(parent, modal, nombre, precio,unidades, unidAgregar);
        confirmarAgregadoUnidades.setLocationRelativeTo(null);
        confirmarAgregadoUnidades.setResizable(false);
        confirmarAgregadoUnidades.setTitle("Confirmar modificacion");
        confirmarAgregadoUnidades.pack();
        confirmarAgregadoUnidades.setVisible(true);
        return confirmarAgregadoUnidades;
    }
    
    public void crearAdministrador() {
        CrearAdministrador createAdmin = new CrearAdministrador();
        createAdmin.setLocationRelativeTo(null);
        createAdmin.setResizable(false);
        createAdmin.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        createAdmin.pack();
        createAdmin.setVisible(true);
    }
    
    public void inicioSessionRegistro() {
        Login session = new Login();
        session.setLocationRelativeTo(null);
        session.setResizable(false);
        session.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        session.pack();
        session.setVisible(true);
    }
    
    
    public void gestionarUsuarios() {
        GestionUsuarios gestionarUsuarios = new GestionUsuarios();
        gestionarUsuarios.setLocationRelativeTo(null);
        gestionarUsuarios.setResizable(false);
        gestionarUsuarios.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        gestionarUsuarios.pack();
        gestionarUsuarios.setVisible(true);
    }
    
    
    public void agregarUsuario() {
        AgregarUsuario agregarUsuario = new AgregarUsuario();
        agregarUsuario.setLocationRelativeTo(null);
        agregarUsuario.setResizable(false);
        agregarUsuario.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        agregarUsuario.pack();
        agregarUsuario.setVisible(true);
    }
    
    
    public ConfirmarNuevoUsuario confirmarNuevoUsuario(java.awt.Frame parent, boolean modal) {
        ConfirmarNuevoUsuario confirmarUsuario = new ConfirmarNuevoUsuario(parent, modal);
        confirmarUsuario.setLocationRelativeTo(null);
        confirmarUsuario.setResizable(false);
        confirmarUsuario.setTitle("Confirmar usuario");
        confirmarUsuario.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        confirmarUsuario.pack();
        confirmarUsuario.setVisible(true);
        return confirmarUsuario;
    }
}
