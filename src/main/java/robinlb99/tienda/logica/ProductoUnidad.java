package robinlb99.tienda.logica;

public class ProductoUnidad {
    
    private long id;
    private String nombre;
    private double precio;
    private int cantidad;

    public ProductoUnidad() {
    }

    public ProductoUnidad(long id, String nombre, double precio, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "ProductoUnidad{" + "id=" + id + 
                ", nombre=" + nombre + 
                ", precio=" + precio + 
                ", cantidad=" + cantidad + '}';
    }    
    
}
