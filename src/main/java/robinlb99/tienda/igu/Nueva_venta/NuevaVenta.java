package robinlb99.tienda.igu.Nueva_venta;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import robinlb99.tienda.igu.Window;

import robinlb99.tienda.logica.Cliente;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Pedido;
import robinlb99.tienda.logica.Producto;

public class NuevaVenta extends javax.swing.JFrame {

    // Controladores
    Window window = null;
    LogicController control = new LogicController();
    
    // Variables
    private Cliente cliente = new Cliente();
    private Pedido pedido = new Pedido();
    private ArrayList<Producto> productos = new ArrayList();
    private double valorCompra = 0;
    private String totalPagar = "";
    private long idClienteTomado;
    private boolean consumidorFinal;
    private boolean canceladoInmediato;

    
    // Constructor
    public NuevaVenta(boolean cFinal, long idCTomado, ArrayList<Producto> listProductos) {
        this.consumidorFinal = cFinal;
        this.idClienteTomado = idCTomado;
        this.productos = listProductos;
        
        initComponents();
        
        window = new Window();
        
        // Valores predeterminado de inicio.
        btnSelectClient.setEnabled(false);
        pagoInmediato.setSelected(true);
        canceladoInmediato = true;
        
        
        // Establece valores prederminados en base al cliente obtenido
        if (idClienteTomado != 0) {
            
            cliente = control.buscarCliente(idClienteTomado);
            
            if (!cFinal) {
                
//                System.out.println("El cliente es: " + cliente.toString());

                checkConsumidorFinal.setEnabled(false);
                checkClientExist.setSelected(true);
                btnSelectClient.setEnabled(true);

                txtNombre.setEnabled(false);
                txtApellidos.setEnabled(false);
                txtCedula.setEnabled(false);

                txtNombre.setText(cliente.getNombres());
                txtApellidos.setText(cliente.getApellidos());
                txtCedula.setText(cliente.getCedula());
                
            } else {
                
                cliente = new Cliente();
                
            }
            
        }
        
        // Cuenta la cantidad de productos seleccionados
        if (!productos.isEmpty()) {
            contProductos.setText(productos.size() + " productos seleccionados");
        }
        
        // Calcula y escribe el valor total entre precio del producto por la cantidad de unidades en la compra.
        if (productos.size() > 0) {
            
            double total = 0;
            double pxc = 0;
            
            for (Producto prod : productos) {
                
                pxc = prod.getPrecio() * prod.getStock();
                total += pxc;
                
            }
            
            NumberFormat formato = NumberFormat.getInstance(Locale.ENGLISH);
            formato.setMaximumFractionDigits(2);
            formato.setMinimumFractionDigits(2);
            
            totalPagar = formato.format(total);
            
            valorPagar.setText("$" + totalPagar);
            
        }
        
        
        // Establece valores predeterminados en caso de consumidor final
        checkConsumidorFinal.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Este código se ejecutará cuando el estado del botón cambie
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    
                    txtNombre.setEnabled(false);
                    txtNombre.setText("");
                    txtApellidos.setEnabled(false);
                    txtApellidos.setText("");
                    txtCedula.setEnabled(false);
                    txtCedula.setText("");
                    btnSelectClient.setEnabled(false);
                    checkClientExist.setEnabled(false);
                    checkClientExist.setSelected(false);
                    pagoInmediato.setSelected(true);
                    consumidorFinal = true;
                    idClienteTomado = 0;

                } else {
                    
                    txtNombre.setEnabled(true);
                    txtApellidos.setEnabled(true);
                    txtCedula.setEnabled(true);
                    btnSelectClient.setEnabled(false);
                    checkClientExist.setEnabled(true);
                    consumidorFinal = false;
                    
                }
            }
        });
        
        // Establece valores predeterminados en caso de pago inmediato
        pagoInmediato.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Este código se ejecutará cuando el estado del botón cambie
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    pagoCredito.setSelected(false);
                    checkConsumidorFinal.setEnabled(true);
                    
                    if (checkClientExist.isSelected()) {
                        checkConsumidorFinal.setEnabled(false);
                    }
                    
                    canceladoInmediato = true;

                }
            }
        });
        
        // Establece valores predeterminados en caso de pago a credito
        pagoCredito.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Este código se ejecutará cuando el estado del botón cambie
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    pagoInmediato.setSelected(false);
                    checkConsumidorFinal.setSelected(false);
                    checkConsumidorFinal.setEnabled(false);
                    canceladoInmediato = false;

                }
            }
        });
        
        // Establece valores predeterminados en caso de Cliente existente
        checkClientExist.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                // Este código se ejecutará cuando el estado del botón cambie
                if (e.getStateChange() == ItemEvent.SELECTED) {

                    txtNombre.setEnabled(false);
                    txtApellidos.setEnabled(false);
                    txtCedula.setEnabled(false);
                    btnSelectClient.setEnabled(true);
                    checkConsumidorFinal.setEnabled(false);
                    checkConsumidorFinal.setSelected(false);
                    
                } else {

                    txtNombre.setEnabled(true);
                    txtApellidos.setEnabled(true);
                    txtCedula.setEnabled(true);
                    btnSelectClient.setEnabled(false);
                    
                    txtNombre.setText("");
                    txtApellidos.setText("");
                    txtCedula.setText("");
                    
                    idClienteTomado = 0;
                    cliente = new Cliente();
                    
                    if (pagoInmediato.isSelected()) {
                        checkConsumidorFinal.setEnabled(true);
                    }

                }
            }
        });
        
        // Accion lleva a seleccionar cliente existente
        btnSelectClient.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                window.traerClientes(consumidorFinal, idClienteTomado , productos);
            }
        });
        
        // Accion lleva a seleccionar productos
        btnSeleccionarProductos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                window.seleccionarProductos(consumidorFinal, idClienteTomado);
            }
        });
        
        // Accion limpiar datos
        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                window.nuevaVenta(false, 0, new ArrayList());
            }
        }); 
        
        // Por cuestion de que el funcionamiento sea el esperado, dejar esta condicional al final.
        if (cFinal) {
            checkConsumidorFinal.setSelected(true);

        } else {
            checkConsumidorFinal.setSelected(false);
        }
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        checkClientExist = new javax.swing.JCheckBox();
        btnSelectClient = new javax.swing.JButton();
        btnSeleccionarProductos = new javax.swing.JButton();
        contProductos = new javax.swing.JLabel();
        pagoInmediato = new javax.swing.JCheckBox();
        pagoCredito = new javax.swing.JCheckBox();
        jLabel6 = new javax.swing.JLabel();
        valorPagar = new javax.swing.JLabel();
        checkConsumidorFinal = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnLimpiar = new javax.swing.JButton();
        btnVender = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setText("      Nueva venta");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Apellido:");

        jLabel4.setText("C.I:");

        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        txtApellidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtApellidosActionPerformed(evt);
            }
        });

        txtCedula.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaActionPerformed(evt);
            }
        });

        checkClientExist.setText("Cliente ya existente");
        checkClientExist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkClientExistActionPerformed(evt);
            }
        });

        btnSelectClient.setText("Seleccionar cliente");
        btnSelectClient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSelectClientActionPerformed(evt);
            }
        });

        btnSeleccionarProductos.setText("Seleccionar productos");
        btnSeleccionarProductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSeleccionarProductosActionPerformed(evt);
            }
        });

        contProductos.setText("0 productos seleccionados.");

        pagoInmediato.setText("Inmediato");
        pagoInmediato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoInmediatoActionPerformed(evt);
            }
        });

        pagoCredito.setText("Credito");
        pagoCredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagoCreditoActionPerformed(evt);
            }
        });

        jLabel6.setText("Total a cobrar:");

        valorPagar.setFont(new java.awt.Font("Segoe UI", 0, 48)); // NOI18N
        valorPagar.setText("$0.00");

        checkConsumidorFinal.setText("Consumidor final");
        checkConsumidorFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkConsumidorFinalActionPerformed(evt);
            }
        });

        jLabel8.setText("Tipo de pago:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(pagoInmediato, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(pagoCredito, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(checkConsumidorFinal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, 210, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(checkClientExist)
                                .addComponent(btnSeleccionarProductos))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(btnSelectClient, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(contProductos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(valorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(checkConsumidorFinal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCedula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkClientExist)
                    .addComponent(btnSelectClient))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSeleccionarProductos)
                    .addComponent(contProductos))
                .addGap(14, 14, 14)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pagoInmediato)
                    .addComponent(pagoCredito))
                .addGap(18, 18, 18)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(valorPagar, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );

        btnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/barriendo-24px.png"))); // NOI18N
        btnLimpiar.setText("Limpiar");
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });

        btnVender.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/comercio-24px.png"))); // NOI18N
        btnVender.setText("Vender");
        btnVender.setMaximumSize(new java.awt.Dimension(102, 35));
        btnVender.setMinimumSize(new java.awt.Dimension(102, 35));
        btnVender.setPreferredSize(new java.awt.Dimension(102, 35));
        btnVender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVenderActionPerformed(evt);
            }
        });

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/deshacer-24px.png"))); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.setMaximumSize(new java.awt.Dimension(102, 35));
        btnAtras.setMinimumSize(new java.awt.Dimension(102, 35));
        btnAtras.setPreferredSize(new java.awt.Dimension(102, 35));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnLimpiar)
                .addGap(18, 18, 18)
                .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVender, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        
    }//GEN-LAST:event_txtNombreActionPerformed

    private void txtApellidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtApellidosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtApellidosActionPerformed

    private void txtCedulaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        window.menu();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnSelectClientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectClientActionPerformed

    }//GEN-LAST:event_btnSelectClientActionPerformed

    private void checkClientExistActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkClientExistActionPerformed
       
    }//GEN-LAST:event_checkClientExistActionPerformed

    private void checkConsumidorFinalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkConsumidorFinalActionPerformed
    
    }//GEN-LAST:event_checkConsumidorFinalActionPerformed

    private void btnSeleccionarProductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSeleccionarProductosActionPerformed
    }//GEN-LAST:event_btnSeleccionarProductosActionPerformed
    
    private void pagoCreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoCreditoActionPerformed
                
    }//GEN-LAST:event_pagoCreditoActionPerformed

    private void pagoInmediatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagoInmediatoActionPerformed
              
    }//GEN-LAST:event_pagoInmediatoActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        
    }//GEN-LAST:event_btnLimpiarActionPerformed
    
    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVenderActionPerformed
        
        if (idClienteTomado == 0 && txtNombre.getText().isBlank() && !checkConsumidorFinal.isSelected() && !checkClientExist.isSelected()) {
            
            window.mensaje("Campo vacio!", "error", "El campo nombre esta vacio");
            txtNombre.grabFocus();
            
        } else if (idClienteTomado == 0 && txtApellidos.getText().isBlank() && !checkConsumidorFinal.isSelected() && !checkClientExist.isSelected()) {
            
            window.mensaje("Campo vacio!", "error", "El campo apellido esta vacio");
            txtApellidos.grabFocus();
            
        } else if (idClienteTomado == 0 && txtCedula.getText().isBlank() && !checkConsumidorFinal.isSelected() && !checkClientExist.isSelected()) {
            
            window.mensaje("Campo vacio!", "error", "El campo cedula esta vacio");
            txtCedula.grabFocus();
            
        } else {
            
            if (checkClientExist.isSelected() && idClienteTomado == 0) {
                
                window.mensaje("Cliente no seleccionado", "error", "No se ha seleccionado ningun cliente.\nPor favor, seleccione un cliente.");
                btnSelectClient.grabFocus();
                
            } else if (productos.size() == 0) {
                
                window.mensaje("Productos no seleccionados!", "error", "No ha seleccionado ningun producto.\nPor favor, ingrese los productos a vender.");
                btnSeleccionarProductos.grabFocus();
                
            } else {
                
                ArrayList<Producto> productoAsignar = new ArrayList();

                for (Producto prod : productos) {

                    Producto prodOriginal = control.buscarProducto(prod.getId());
                    productoAsignar.add(prodOriginal);

                }

                cliente = new Cliente();

                if (consumidorFinal) {

                    cliente.setNombres("-");
                    cliente.setApellidos("-");
                    cliente.setCedula("-");
                    cliente.setConsumidorFinal("Si");

                } else if (idClienteTomado == 0) {

                    cliente.setNombres(txtNombre.getText());
                    cliente.setApellidos(txtApellidos.getText());
                    cliente.setCedula(txtCedula.getText());
                    cliente.setConsumidorFinal("No");

                }

                dispose();
                window.confirmarCompra(Double.parseDouble(totalPagar), cliente, productoAsignar, this.productos, this.canceladoInmediato, this.consumidorFinal, this.idClienteTomado);
                
            }
            
        }
        
        
       
    }//GEN-LAST:event_btnVenderActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSeleccionarProductos;
    private javax.swing.JButton btnSelectClient;
    private javax.swing.JButton btnVender;
    private javax.swing.JCheckBox checkClientExist;
    private javax.swing.JCheckBox checkConsumidorFinal;
    private javax.swing.JLabel contProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JCheckBox pagoCredito;
    private javax.swing.JCheckBox pagoInmediato;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JLabel valorPagar;
    // End of variables declaration//GEN-END:variables

}
