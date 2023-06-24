package robinlb99.tienda.persistencia;

import robinlb99.tienda.logica.Cliente;
import robinlb99.tienda.logica.Pedido;
import robinlb99.tienda.logica.Producto;
import robinlb99.tienda.persistencia.exceptions.NonexistentEntityException;

import java.util.List;

public class PersistenceController {

    ClienteJpaController clienteJpa = new ClienteJpaController();
    PedidoJpaController pedidoJpa = new PedidoJpaController();
    ProductoJpaController productoJpa = new ProductoJpaController();

    // CLIENTE ----------------------------------------------------------
    public void crearCliente(Cliente cliente) {
        clienteJpa.create(cliente);
    }

    public void eliminarCliente(long id) {
        try {
            clienteJpa.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarCliente(Cliente cliente) {
        try {
            clienteJpa.edit(cliente);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Cliente buscarCliente(long id) {
        return clienteJpa.findCliente(id);
    }

    public List<Cliente> listaClientes() {
        return clienteJpa.findClienteEntities();
    }

    // PEDIDO -----------------------------------------------------------
    public void crearPedido(Pedido pedido) {
        pedidoJpa.create(pedido);
    }

    public void eliminarPedido(long id) {
        try {
            pedidoJpa.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarPedido(Pedido pedido) {
        try {
            pedidoJpa.edit(pedido);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Pedido buscarPedido(long id) {
        return pedidoJpa.findPedido(id);
    }

    public List<Pedido> listaPedidos() {
        return pedidoJpa.findPedidoEntities();
    }



    // PRODUCTO ---------------------------------------------------------
    public void crearProducto(Producto producto) {
        productoJpa.create(producto);
    }

    public void eliminarProducto(long id) {
        try {
            productoJpa.destroy(id);
        } catch (NonexistentEntityException e) {
            throw new RuntimeException(e);
        }
    }

    public void editarProducto(Producto producto) {
        try {
            productoJpa.edit(producto);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Producto buscarProducto(long id) {
        return productoJpa.findProducto(id);
    }

    public List<Producto> listaProductos() {
        return productoJpa.findProductoEntities();
    }

}
