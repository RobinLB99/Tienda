package robinlb99.tienda.logica;

import robinlb99.tienda.persistencia.PersistenceController;
import robinlb99.tienda.persistencia.exceptions.NonexistentEntityException;

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


}
