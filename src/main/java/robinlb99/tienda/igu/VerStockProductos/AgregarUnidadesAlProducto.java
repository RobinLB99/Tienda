package robinlb99.tienda.igu.VerStockProductos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Producto;

public class AgregarUnidadesAlProducto extends javax.swing.JFrame {
    
    LogicController control = new LogicController();
    Window ventana = new Window();

    /**
     * Creates new form AgregarUnidadesAlProducto
     */
    public AgregarUnidadesAlProducto(long id) {
        initComponents();
        
        txtUnidadesAgregar.setText("");
        
        Producto prod = control.buscarProducto(id);
        
        txtNombre.setText(prod.getNombre());
        txtPrecio.setText(String.valueOf(prod.getPrecio()));
        txtUnidades.setText(String.valueOf(prod.getStock()));
        
        btnAgregarUnidades.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (txtUnidadesAgregar.getText().isBlank()) {
                    
                    ventana.mensaje("Alerta", "warning", "El campo unidades a agragar esta vacio.");
                    txtUnidadesAgregar.grabFocus();
                    
                } else {
                    
                    try {
                        
                        int a = Integer.parseInt(txtUnidadesAgregar.getText());
                        
                        prod.setStock(prod.getStock() + a);
                        
                        boolean confirmar = ventana.confirmarAgregadoUnidades(null, true, txtNombre.getText(), txtPrecio.getText(), txtUnidades.getText(), txtUnidadesAgregar.getText()).isConfirm();
                        
                        if (confirmar) {
                            
                            control.editarProducto(prod);
                            ventana.mensaje("Unidades agregadas", "info", "Las unidades se agregaron con exito.");
                            dispose();
                            ventana.verStock();
                            
                        } else {
                            
                            ventana.mensaje("Agregado cancelado", "info", "El agregado de unidades fue cancelado");
                            
                        }
                        
                    } catch (Exception ex) {
                        
                        ventana.mensaje("Error", "error", "La cantidad a agregar debe ser un valor numerico entero.");
                        txtUnidadesAgregar.grabFocus();
                        
                    }
                    
                }
                
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
        btnAgregarUnidades = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JLabel();
        txtPrecio = new javax.swing.JLabel();
        txtUnidades = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtUnidadesAgregar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnAgregarUnidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/mas.png"))); // NOI18N
        btnAgregarUnidades.setText("Agregar");
        btnAgregarUnidades.setMaximumSize(new java.awt.Dimension(100, 35));
        btnAgregarUnidades.setMinimumSize(new java.awt.Dimension(100, 35));
        btnAgregarUnidades.setPreferredSize(new java.awt.Dimension(100, 35));

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/deshacer-16px.png"))); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.setMaximumSize(new java.awt.Dimension(100, 35));
        btnAtras.setMinimumSize(new java.awt.Dimension(100, 35));
        btnAtras.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setText("Agregar unidades del producto");
        jLabel1.setToolTipText("");

        jLabel2.setText("Producto:");

        jLabel3.setText("Precio:");

        jLabel4.setText("Unidades:");

        txtNombre.setText("Nombre del producto");

        txtPrecio.setText("Precio del producto");

        txtUnidades.setText("Unidades disponibles");

        jLabel8.setText("Unidades a agregar:");
        jLabel8.setMaximumSize(new java.awt.Dimension(100, 30));
        jLabel8.setMinimumSize(new java.awt.Dimension(100, 30));
        jLabel8.setPreferredSize(new java.awt.Dimension(100, 30));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtUnidades)
                                    .addComponent(txtUnidadesAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(106, 106, 106))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAgregarUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombre))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPrecio)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtUnidadesAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 42, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregarUnidades, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        ventana.verStock();
    }//GEN-LAST:event_btnAtrasActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarUnidades;
    private javax.swing.JButton btnAtras;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel txtNombre;
    private javax.swing.JLabel txtPrecio;
    private javax.swing.JLabel txtUnidades;
    private javax.swing.JTextField txtUnidadesAgregar;
    // End of variables declaration//GEN-END:variables
}
