package robinlb99.tienda.logica;

import java.io.Serializable;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Pedido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @ManyToOne
    private Cliente cliente;
    @ManyToMany(targetEntity = Producto.class)
    private List productos;
    private String isCancel;
    private double valorCompra;

    public Pedido() {
    }

    public Pedido(int id, Date fecha, Cliente cliente, List productos, String isCancel, double valorCompra) {
        this.id = id;
        this.fecha = fecha;
        this.cliente = cliente;
        this.productos = productos;
        this.isCancel = isCancel;
        this.valorCompra = valorCompra;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List getProductos() {
        return productos;
    }

    public void setProductos(List productos) {
        this.productos = productos;
    }

    public String getIsCancel() {
        return isCancel;
    }

    public void setIsCancel(String isCancel) {
        this.isCancel = isCancel;
    }

    public double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(double valorCompra) {
        this.valorCompra = valorCompra;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + 
                ", fecha=" + fecha + 
                ", cliente=" + cliente + 
                ", productos=" + productos + 
                ", isCancel=" + isCancel + 
                ", valorCompra=" + valorCompra + '}';
    }
    
    

}
