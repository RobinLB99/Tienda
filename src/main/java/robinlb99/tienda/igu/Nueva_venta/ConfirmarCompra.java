package robinlb99.tienda.igu.Nueva_venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.logica.Cliente;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Pedido;
import robinlb99.tienda.logica.Producto;


public class ConfirmarCompra extends javax.swing.JFrame {
    
    Window window = new Window();
    LogicController control = new LogicController();
    
    private Pedido pedido = null;
    private double valorPagar = 0;
    private double cambio = 0;
    private Cliente clt = null;
    private boolean cancelado;
    
    
    public ConfirmarCompra(double value, Cliente cliente, ArrayList<Producto> productos, ArrayList<Producto> unidadesARestar, boolean canceladoInmediato, boolean cFinal, long idTomado) {
        this.cancelado = canceladoInmediato;
        
        initComponents();
        
        System.out.println(this.cancelado);
        
        if (!this.cancelado) {
            txtRecibe.setText("0");
            txtRecibe.setEnabled(false);
            btnRecibir.setEnabled(false);
            txtCredito.setText("Credito");
            btnConfirmarCompra.setEnabled(true);
        } else {
            btnConfirmarCompra.setEnabled(false);
            txtRecibe.setText("");
            txtRecibe.setEnabled(true);
            btnRecibir.setEnabled(true);
        }

        
        valorCompra.setText("$" + value);
        valorPagar = value;
        
//        System.out.println("Confirmar compra---------------------------------");
//        System.out.println("Valor: " + value +
//                "\n Cliente: " + cliente.toString() +
//                "\n Produtos: " + productos.toString() +
//                "\n Unidades Seleccionadas: " + unidadesARestar.toString() +
//                "\n Cancelado inmediato: " + canceladoInmediato +
//                "\n Consumidor Final: " + cFinal +
//                "\n Id tomado: " + idTomado + 
//                "\n Pago imnediato: " + pagoInmediato);
        
        
        btnConfirmarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Tomo un cliente existente o creo uno nuevo.
                    
                    if (idTomado == 0) {
                        
                        if (cliente.getId() == 0) {
                            clt = cliente;
                            control.crearCliente(clt);
                        }
                        
                    } else if (idTomado != 0) {
                        clt = control.buscarCliente(idTomado);
                    }
//                    System.out.println("\n" + clt.toString());


                    // Cancelacion inmediata o credito.
                    String isCancel = "";
                    
                    if (cancelado) {
                        isCancel = "Cancelado";
                    } else {
                        isCancel = "Pendiente";
                    }
//                    System.out.println(isCancel);

                    // Crear pedido
                    pedido = new Pedido();
                    pedido.setCliente(clt);
                    pedido.setFecha(new Date());
                    pedido.setIsCancel(isCancel);
                    pedido.setProductos(productos);
                    pedido.setValorCompra(valorPagar);
                    
//                    System.out.println(pedido.toString());
                    
                    control.crearPedido(pedido);
                    
                    
                    // Restar la cantidad vendida al stock
                    
                    for (int i = 0; i <= productos.size() - 1; i++) {
                        Producto prodMod = productos.get(i);
                        Producto prodCantRestar = unidadesARestar.get(i);
                        prodMod.setStock(prodMod.getStock() - prodCantRestar.getStock());
                        control.editarProducto(prodMod);
                    }
                    
                    dispose();
                    window.mensaje("Pedido creado", "info", "La venta realizada con exito.");
                    window.menu();

                } catch (Exception ex) {
                    
//                    System.out.println(ex.getMessage());
                    
                    window.mensaje("Error en la venta!", "error", "Ups! Hubo un error al realizar la venta.");
                    dispose();
                    window.nuevaVenta(cFinal, idTomado, unidadesARestar);
                    
                }
                
            }
        });
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                window.nuevaVenta(cFinal, idTomado, unidadesARestar);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtRecibe = new javax.swing.JTextField();
        btnRecibir = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txtCambio = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        valorCompra = new javax.swing.JLabel();
        txtCredito = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        btnConfirmarCompra = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("Confirmacion de compra");

        jLabel2.setText("Cliente paga con:");

        btnRecibir.setText("Recibir");
        btnRecibir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecibirActionPerformed(evt);
            }
        });

        jLabel3.setText("Cambio: ");

        txtCambio.setFont(new java.awt.Font("Segoe UI", 1, 32)); // NOI18N
        txtCambio.setText("$0");

        jLabel5.setText("Valor de compra:");

        valorCompra.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N

        txtCredito.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtRecibe)
                                .addGap(18, 18, 18)
                                .addComponent(btnRecibir))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtCambio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(valorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 148, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtRecibe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRecibir))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCambio))
                        .addGap(0, 8, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(txtCredito, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(21, 21, 21))))
        );

        btnConfirmarCompra.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/cheque-16px.png"))); // NOI18N
        btnConfirmarCompra.setText("Confirmar");
        btnConfirmarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarCompraActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnConfirmarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmarCompra, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnRecibirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecibirActionPerformed
        
        try {
            double pago = Double.parseDouble(txtRecibe.getText());
            cambio = pago - valorPagar;
            
            NumberFormat formato = NumberFormat.getInstance(Locale.ENGLISH);
            formato.setMaximumFractionDigits(2);
            formato.setMinimumFractionDigits(2);
            
            String vuelto = formato.format(cambio);
            
            txtCambio.setText("$" + vuelto);
            
            btnConfirmarCompra.setEnabled(true);
            
        } catch (Exception e) {
//            System.out.println(e.getMessage());
            window.mensaje("Error", "error", "El valor a ingresar debe ser numerico");
        }
        
    }//GEN-LAST:event_btnRecibirActionPerformed

    private void btnConfirmarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarCompraActionPerformed
        
    }//GEN-LAST:event_btnConfirmarCompraActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        
    }//GEN-LAST:event_btnCancelarActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmarCompra;
    private javax.swing.JButton btnRecibir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel txtCambio;
    private javax.swing.JLabel txtCredito;
    private javax.swing.JTextField txtRecibe;
    private javax.swing.JLabel valorCompra;
    // End of variables declaration//GEN-END:variables
}
