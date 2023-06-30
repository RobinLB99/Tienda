package robinlb99.tienda.logica;

import robinlb99.tienda.persistencia.PersistenceController;

import java.util.ArrayList;
import java.util.List;

public class LogicController {

    PersistenceController perController = new PersistenceController();

    // CLIENTE ------------------------------------------------
    public void crearCliente(Cliente cliente) {
        perController.crearCliente(cliente);
    }

    public void eliminarCliente(long id) {
        perController.eliminarCliente(id);
    }

    public void editarCliente(Cliente cliente) {
        perController.editarCliente(cliente);
    }

    public Cliente buscarCliente(long id) {
        return perController.buscarCliente(id);
    }

    public ArrayList<Cliente> listaClientes() {
        List<Cliente> lista = perController.listaClientes();
        ArrayList<Cliente> clientes = new ArrayList<>(lista);
        return  clientes;
    }

    // PEDIDO -------------------------------------------------
    public void crearPedido(Pedido pedido) {
        perController.crearPedido(pedido);
    }

    public void eliminarPedido(long id) {
        perController.eliminarPedido(id);
    }

    public void editarPedido(Pedido pedido) {
        perController.editarPedido(pedido);
    }

    public Pedido buscarPedido(long id) {
        return perController.buscarPedido(id);
    }

    public ArrayList<Pedido> listaPedidos() {
        List<Pedido> lista = perController.listaPedidos();
        ArrayList<Pedido> pedidos = new ArrayList<>(lista);
        return pedidos;
    }



    // PRODUCTO -----------------------------------------------
    public void crearProducto(Producto producto) {
        perController.crearProducto(producto);
    }

    public void eliminarProducto(long id) {
        perController.eliminarProducto(id);
    }

    public void editarProducto(Producto producto) {
        perController.editarProducto(producto);
    }

    public Producto buscarProducto(long id) {
        return perController.buscarProducto(id);
    }

    public ArrayList<Producto> listaProductos() {
        List<Producto> lista = perController.listaProductos();
        ArrayList<Producto> productos = new ArrayList<>(lista);
        return productos;
    }
    
    
    // Usuario ----------------------------------------------------------------
    public void crearUsuario(Usuario usuario) {
        perController.crearUsuario(usuario);
    }
    
    public void eliminarUsuario(int id) {
        perController.eliminarUsuario(id);
    }
    
    public void editarUsuario(Usuario ususario){
        perController.editarUsuario(ususario);
    }
    
    public Usuario buscarUsuario(int id) {
        return perController.buscarUsuario(id);
    }
    
    public ArrayList<Usuario> listaUsuarios() {
        List<Usuario> lista = perController.listaUsuarios();
        ArrayList<Usuario> usuarios = new ArrayList(lista);
        return usuarios;
    }


    // Usuario ----------------------------------------------------------------
    public void crearEmpleado(Empleado employ) {
        perController.crearEmpleado(employ);
    }

    public void eliminarEmpleado(long id) {
        perController.eliminarEmpleado(id);
    }

    public void editarEmpleado(Empleado employ) {
        perController.editarEmpleado(employ);
    }

    public Empleado buscarEmpleado(long id) {
        return perController.buscarEmpleado(id);
    }

    public ArrayList<Empleado> listaEmpleados() {
        List<Empleado> lista = perController.listaEmpleado();
        ArrayList<Empleado> empleados = new ArrayList(lista);
        return empleados;
    }
}
