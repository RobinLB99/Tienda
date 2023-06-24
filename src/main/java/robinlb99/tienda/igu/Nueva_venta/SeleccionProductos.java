package robinlb99.tienda.igu.Nueva_venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Producto;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.logica.ProductoUnidad;

public class SeleccionProductos extends javax.swing.JFrame {
    
    LogicController control = new LogicController();
    Window ventana = new Window();
    
    ArrayList<Producto> listaProductos = new ArrayList();
    ArrayList<ProductoUnidad> productosSeleccionados = new ArrayList<ProductoUnidad>();
    ArrayList<Producto> copiaTablaActual = new ArrayList();
    ArrayList<Producto> listaProductosAEnviar = new ArrayList<Producto>();
    Producto prodFiltrado = null;
    
    private boolean cFinal;
    private long idCliente;
    private String cantidadProducto = "";

    /**
     * Creates new form SeleccionProductos
     */
    public SeleccionProductos(boolean consumidorFinal, long idTomado) {
        this.cFinal = consumidorFinal;
        this.idCliente = idTomado;
        
        listaProductos = control.listaProductos();
        
        initComponents();
        
        btnBorrarFiltro.setEnabled(false);
        
        cargarTabla();
        cargarTablaProductosSeleccionados();
        
        
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (!txtFiltroNombre.getText().isEmpty()) {
                    
                    try {
                        
                        int a = Integer.parseInt(txtFiltroNombre.getText());
                        ventana.mensaje("Error", "error", "Se ingreso caracteres numericos.");
                        txtFiltroNombre.grabFocus();
                        
                    } catch (Exception ex) {
                        
                        copiarTablaActualProductos();
                        cargarTablaFiltrada(txtFiltroNombre.getText());
                                                
                    }
                    
                } else {
                    
                    ventana.mensaje("Error", "error", "No ingreso un nombre para filtrar.");
                    txtFiltroNombre.grabFocus();
                    
                } 
                
            }
        });
        
        
        
        btnBorrarFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                long id = Long.parseLong(listProductTable.getValueAt(0, 0).toString());
                String nombre = listProductTable.getValueAt(0, 1).toString();
                double precio = Double.parseDouble(listProductTable.getValueAt(0, 2).toString());
                int stock = Integer.parseInt(listProductTable.getValueAt(0, 3).toString());
        
                // Crea un nuevo objeto con los datos de la fila
                prodFiltrado = new Producto(id, nombre, precio, stock);
                
                cargarTablaActual();
                actualizarStockConFiltrado(prodFiltrado);
                
                txtFiltroNombre.setEnabled(true);
                txtFiltroNombre.setText("");
                btnFiltrar.setEnabled(true);
                btnBorrarFiltro.setEnabled(false);
                
            }
        });
        
        
        
        // Cantidad de unidades por producto a agregar al carrito
        cantProductoSelec.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                // Acciones a realizar cuando se inserta un dígito
                cantidadProducto = cantProductoSelec.getText();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                // Acciones a realizar cuando se elimina un dígito
                cantidadProducto = cantProductoSelec.getText();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                // No se utiliza para campos de texto sin formato
            }
        });
        
        // Agregar producto al carrito       
        btnAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listProductTable.getSelectedRow() != -1) {
                    
                    try {
                        int cantidad = Integer.parseInt(cantidadProducto);
                        
                        try {
                            
                            long id = Long.parseLong(String.valueOf(listProductTable.getValueAt(listProductTable.getSelectedRow(), 0)));
                                                        
                            boolean isSelected = false;
                            
                            for (int i = 0; i <= productosSeleccionados.size() - 1; i++) {
                                if (productosSeleccionados.get(i).getId() == id) {
                                    isSelected = true;
                                    break;
                                }
                            }
                            
                            
                            if (isSelected) {
                                
                                ventana.mensaje("Producto en el carrito", "error", "El producto ya se encuentra en el carrito\nSi desea añadir mas unidades de algun producto, hagalo por medio de los botones centrales inferiores.");
                                
                            } else {
                                
                                Producto product = control.buscarProducto(id);
                                
                                int value = (Integer) listProductTable.getValueAt(listProductTable.getSelectedRow(), 3);

                                if (cantidad > value) {
                                    ventana.mensaje("Cantidad no disponible", "error", "El numero de unidades para el producto es mayor al del inventario");
                                    cantProductoSelec.grabFocus();
                                } else {

//                                    for (int i = 0; listaProductosAEnviar.size() < cantidad; i++) {
//                                        listaProductosAEnviar.add(product);
//                                    }

                                    ProductoUnidad prodXUnid = UnidadesProducto(product, cantidad);
                                    productosSeleccionados.add(prodXUnid);

                                    cargarTablaProductosSeleccionados();

                                    value -= cantidad;

                                    listProductTable.setValueAt(value, listProductTable.getSelectedRow(), 3);

                                    cantProductoSelec.setText("");

                                }
                            }
                                                     
                            
                            
                        } catch (Exception exc) {
                            
                            System.out.println("Error: " + exc.getMessage());
                            exc.printStackTrace();
                            
                        }
                        
                    } catch (Exception ex) {
                        if (cantidadProducto.isEmpty()) {
                            
                            ventana.mensaje("Error!", "error", "No se ingreso una cantidad a tomar del producto");
                            cantProductoSelec.grabFocus();
                            
                        } else {
                            ventana.mensaje("Error!", "error", "La cantidad debe ser un valor numerico.");
                            cantProductoSelec.grabFocus();
                        }
                    }
                    
                } else {
                    ventana.mensaje("Alert!", "warning", "No se selecciono un producto.");
                }
            }
            
        });
        
        // Quitar producto del carrito.
        btnQuitar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
             
                if (listSelectProductTable.getSelectedRow() != -1) {
                    
                    try {
                        
                        int index = listSelectProductTable.getSelectedRow();
                        Object idProd = listSelectProductTable.getValueAt(index, 0);
                        long idEliminar = (Long) idProd;
                        
                        int cantMas = (Integer) listSelectProductTable.getValueAt(index, 3);
//                        
                        int indexArray = 0;
                        
                        for (int i = 0; i <= productosSeleccionados.size() - 1; i++) {
                            if (productosSeleccionados.get(i).getId() == idEliminar) {
                                indexArray = i;
                                break;
                            }
                        }

                        productosSeleccionados.remove(indexArray);                       
                        
                        cargarTablaProductosSeleccionados();
                       
                        int indexListStock = buscarFilaPorValorColumna(listProductTable, 0, idProd);
                        
                        int stock = (Integer) listProductTable.getValueAt(indexListStock, 3);
                        
                        stock += cantMas;
                        
                        listProductTable.setValueAt((Object) stock, indexListStock, 3);
                        
                        
                    } catch (Exception ex) {
                        
                        System.out.println(ex);
                        
                    }
                    
                } else {
                    
                    ventana.mensaje("Alerta", "warning", "No ha seleccionado un elemento para quitar.");
                    
                }
                
            }

        });
        
        
        // Agregar cantidad de unidades del producto seleccionado del carrito.
        sumarCantidad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listSelectProductTable.getSelectedRow() != -1) {
                    try {
                        
                        int stock = Integer.parseInt(listProductTable.getValueAt(listProductTable.getSelectedRow(), 3).toString());
                        
                        if (stock == 0) {
                            
                            ventana.mensaje("Unidades no disponibles", "error", "Unidades no disponibles");
                       
                        } else {
                            
                            int index = listSelectProductTable.getSelectedRow();
                            Object idProd = listSelectProductTable.getValueAt(index, 0);

                            int cantMas = (Integer) listSelectProductTable.getValueAt(index, 3);
                            cantMas += 1;
                            listSelectProductTable.setValueAt((Object) cantMas, index, 3);

                            int indexListStock = buscarFilaPorValorColumna(listProductTable, 0, idProd);

                            int stockMenos = (Integer) listProductTable.getValueAt(indexListStock, 3);
                            stockMenos -= 1;
                            listProductTable.setValueAt((Object) stockMenos, indexListStock, 3);

                            actualizarSeleccionadosConTabla();
                            
                        }
                                                
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    
                    ventana.mensaje("Alerta", "warning", "Debe seleccionar un producto de la lista del carrito");
                }
            }
        });
        
        // Quitar cantidad de unidades del producto seleccionado del carrito.
        restarCantidad1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (listSelectProductTable.getSelectedRow() != -1) {
                    try {
                        int index = listSelectProductTable.getSelectedRow();
                        Object idProd = listSelectProductTable.getValueAt(index, 0);
                        

                        int cantMenos = (Integer) listSelectProductTable.getValueAt(index, 3);
                        
                        if (cantMenos > 1) {
                            cantMenos -= 1;
                            listSelectProductTable.setValueAt((Object) cantMenos, index, 3);

                            int indexListStock = buscarFilaPorValorColumna(listProductTable, 0, idProd);
                            
                            int stockMas = (Integer) listProductTable.getValueAt(indexListStock, 3);
                            stockMas += 1;
                            listProductTable.setValueAt((Object) stockMas, indexListStock, 3);
                            
                            actualizarSeleccionadosConTabla();
                            
                        } else {
                            
                            ventana.mensaje("Alerta", "warning", "Advertencia! Debe haber por lo menos una unidad del producto en el carrito.\nPara eliminar el producto del carrito, seleccione el producto del carrito y luego presione 'Quitar'.");
                        }
                        
                        
                    } catch (Exception ex) {
                        System.out.println(ex);
                    }
                } else {
                    
                    ventana.mensaje("Alerta", "warning", "Debe seleccionar un producto de la lista del carrito");
                }
            }
        });
        
        
        // Volver a la ventana de nueva venta.
        btnAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                ventana.nuevaVenta(cFinal, idCliente, new ArrayList());
                
            }
        });
        
        // Confirmar seleccion de producto.
        btnConfirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Codigo
                System.out.println("Lista de productos actual es: " + listaProductos.toString());
                System.out.println("La lista de productos seleccionados es: " + productosSeleccionados.toString());
                
                System.out.println(productosSeleccionados.size());
                
                for (ProductoUnidad prod : productosSeleccionados) {
                    
                    Producto prodSelect = new Producto(prod.getId(), prod.getNombre(), prod.getPrecio(), prod.getCantidad());
                    listaProductosAEnviar.add(prodSelect);
                    
                }
                
                dispose();
                ventana.nuevaVenta(cFinal, idCliente, listaProductosAEnviar);
                
            }
        });
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnBorrarFiltro = new javax.swing.JButton();
        btnConfirm = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        listSelectProductTable = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        listProductTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtFiltroNombre = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        cantProductoSelec = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        btnQuitar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        sumarCantidad1 = new javax.swing.JButton();
        restarCantidad1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        cantidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText(" Seleccione los productos");

        btnBorrarFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/borrar-24px.png"))); // NOI18N
        btnBorrarFiltro.setText("Borrar filtro");

        btnConfirm.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cheque-16px.png"))); // NOI18N
        btnConfirm.setText("Confirmar");
        btnConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmActionPerformed(evt);
            }
        });

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/deshacer-16px.png"))); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.setIconTextGap(10);
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        listSelectProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(listSelectProductTable);

        listProductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(listProductTable);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar-24px.png"))); // NOI18N
        jLabel2.setText("Filtrar por nombre: ");
        jLabel2.setIconTextGap(10);

        btnFiltrar.setText("Filtrar");

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Cantidad:");

        cantProductoSelec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantProductoSelecActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar ->");

        btnQuitar.setText("<- Quitar");

        jLabel4.setText("Listado de productos:");

        jLabel5.setText("Productos seleccionados:");

        sumarCantidad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/flecha-hacia-arriba.png"))); // NOI18N

        restarCantidad1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/flecha-hacia-abajo.png"))); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("Agregar/Quitar");
        jLabel6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cantidad.setText("cantidad.");
        cantidad.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 378, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnConfirm, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 99, Short.MAX_VALUE)
                                    .addComponent(btnQuitar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cantProductoSelec)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(sumarCantidad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(restarCantidad1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(cantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 430, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtFiltroNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantProductoSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnQuitar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(sumarCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidad)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(restarCantidad1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(83, 83, 83)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    private ProductoUnidad UnidadesProducto(Producto product, int cantidad) {
        
        ProductoUnidad prodUnid = new ProductoUnidad(product.getId(), product.getNombre(), product.getPrecio(), cantidad);
        return prodUnid;
        
    }
    
    
    private void copiarTablaActualProductos() {
        copiaTablaActual = new ArrayList();
        // Recorre las filas de la tabla
        for (int i = 0; i < listProductTable.getRowCount(); i++) {
            // Obtiene los datos de las columnas para cada fila
            long id = Long.parseLong(listProductTable.getValueAt(i, 0).toString());
            String nombre = listProductTable.getValueAt(i, 1).toString();
            double precio = Double.parseDouble(listProductTable.getValueAt(i, 2).toString());
            int stock = Integer.parseInt(listProductTable.getValueAt(i, 3).toString());
            // ...

            // Crea un nuevo objeto con los datos de la fila
            Producto p = new Producto(id, nombre, precio, stock);
            // ...

            // Agrega el objeto al ArrayList
            copiaTablaActual.add(p);
        }
    }
    
    
    private void actualizarSeleccionadosConTabla() {
        productosSeleccionados = new ArrayList();
        // Recorre las filas de la tabla
        for (int i = 0; i < listSelectProductTable.getRowCount(); i++) {
            // Obtiene los datos de las columnas para cada fila
            long id = Long.parseLong(listSelectProductTable.getValueAt(i, 0).toString());
            String nombre = listSelectProductTable.getValueAt(i, 1).toString();
            double precio = Double.parseDouble(listSelectProductTable.getValueAt(i, 2).toString());
            int cantidad = Integer.parseInt(listSelectProductTable.getValueAt(i, 3).toString());
            // ...

            // Crea un nuevo objeto con los datos de la fila
            ProductoUnidad p = new ProductoUnidad(id, nombre, precio, cantidad);
            // ...

            // Agrega el objeto al ArrayList
            productosSeleccionados.add(p);
        }
    }
    
    
    public int buscarFilaPorValorColumna(JTable tabla, int columna, Object valorBuscado) {
        for (int fila = 0; fila < tabla.getRowCount(); fila++) {
            Object valorCelda = tabla.getValueAt(fila, columna);
            if (valorCelda != null && valorCelda.equals(valorBuscado)) {
                return fila;
            }
        }
        return -1; // Valor no encontrado
    }

    
    
    private void cargarTabla() {
        // Definir modelo de tabla
        DefaultTableModel tablaModel = new DefaultTableModel() {
            // Filas y columnas no editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Establecer los nombres de las columnas
        String titulos[] = {"ID", "Nombre", "Precio", "Stock"};
        tablaModel.setColumnIdentifiers(titulos);

        // Recorrer datos y mostrarlos
        if (listaProductos != null) {

            for (Producto producto : listaProductos) {

                Object[] objeto = {producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock()};
                tablaModel.addRow(objeto);

            }

        }

        listProductTable.setModel(tablaModel);

    }
    
    
    private void cargarTablaActual() {
        // Definir modelo de tabla
        DefaultTableModel tablaModel = new DefaultTableModel() {
            // Filas y columnas no editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Establecer los nombres de las columnas
        String titulos[] = {"ID", "Nombre", "Precio", "Stock"};
        tablaModel.setColumnIdentifiers(titulos);

        // Recorrer datos y mostrarlos
        if (copiaTablaActual != null) {
            
            for (Producto producto : copiaTablaActual) {
                
                Object[] objeto = {producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock()};
                tablaModel.addRow(objeto);
                
            }
        
        }

        listProductTable.setModel(tablaModel);
        
    }
    
    private void actualizarStockConFiltrado(Producto prodFiltrado) {
        
        if (prodFiltrado != null) {
            
            int indexListStock = buscarFilaPorValorColumna(listProductTable, 0, prodFiltrado.getId());

            listProductTable.setValueAt(prodFiltrado.getStock(), indexListStock, 3);

            prodFiltrado = null;

        }
        
    }
    
    private void cargarTablaFiltrada(String filtro) {
        // Definir modelo de tabla
        DefaultTableModel tablaModel = new DefaultTableModel() {
            // Filas y columnas no editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Establecer los nombres de las columnas
        String titulos[] = {"ID", "Nombre", "Precio", "Stock"};
        tablaModel.setColumnIdentifiers(titulos);

        // Recorrer datos y mostrarlos
        if (copiaTablaActual != null) {
            
            ArrayList<Producto> filtrado = new ArrayList();

            for (Producto producto : copiaTablaActual) {
                
                if (producto.getNombre().toLowerCase().equals(filtro.toLowerCase())) {
                    filtrado.add(producto);
                    Object[] objeto = {producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getStock()};
                    tablaModel.addRow(objeto);
                }
                
            }
            
            if (filtrado.size() > 0) {
            
                listProductTable.setModel(tablaModel);
                
                btnBorrarFiltro.setEnabled(true);
                txtFiltroNombre.setEnabled(false);
                btnFiltrar.setEnabled(false);
                
            } else {
                
                ventana.mensaje("Error", "error", "No se encontro productos con ese nombre.");
                txtFiltroNombre.grabFocus();
                
            }

        }

    }
    
    private void cargarTablaProductosSeleccionados() {
        // Definir modelo de tabla
        DefaultTableModel tablaModel = new DefaultTableModel() {
            // Filas y columnas no editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        // Establecer los nombres de las columnas
        String titulos[] = {"ID", "Nombre", "Precio", "Cantidad"};
        tablaModel.setColumnIdentifiers(titulos);

        // Recorrer datos y mostrarlos
        if (productosSeleccionados != null) {

            for (ProductoUnidad producto : productosSeleccionados) {
                Object[] objeto = {producto.getId(), producto.getNombre(), producto.getPrecio(), producto.getCantidad()};
                tablaModel.addRow(objeto);
            }

        }
        
        listSelectProductTable.setModel(tablaModel);

    }
    
    
    private void btnConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmActionPerformed

    }//GEN-LAST:event_btnConfirmActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed

    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cantProductoSelecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantProductoSelecActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantProductoSelecActionPerformed
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrarFiltro;
    private javax.swing.JButton btnConfirm;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JTextField cantProductoSelec;
    private javax.swing.JLabel cantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable listProductTable;
    private javax.swing.JTable listSelectProductTable;
    private javax.swing.JButton restarCantidad1;
    private javax.swing.JButton sumarCantidad1;
    private javax.swing.JTextField txtFiltroNombre;
    // End of variables declaration//GEN-END:variables
}
