package robinlb99.tienda.igu.gestionUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Usuario;

/**
 *
 * @author ROBINLB99
 */
public class GestionUsuarios extends javax.swing.JFrame {
    
    LogicController control = new LogicController();
    Window ventana = new Window();
    ArrayList<Usuario> listaUsuarios = null;

    public GestionUsuarios() {
        
        initComponents();
        
        listaUsuarios = control.listaUsuarios();
        
        cargarTabla(false, 0, false);
        
        
        /* Botones de acción -------------------------------*/
        
        // Filtra la tabla de acuerdo al tipo de filtro.
        btnFiltrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (cboxTipoFiltro.getSelectedIndex() == 0) {
                    
                    ventana.mensaje("Filtro invalido", "error", "No se selecciono un tipo de filtro valido");
                    cboxTipoFiltro.grabFocus();
                    
                } else if (txtFiltro.getText().isBlank()) {
                    
                    ventana.mensaje("Filtro vacio", "error", "El campo 'Filtro' esta vacio.");
                } else {
                    
                    boolean continuar = true;
                    
                    if (cboxTipoFiltro.getSelectedIndex() == 1) {
                        
                        try {
                        
                        int a = Integer.parseInt(txtFiltro.getText());
                        
                        ventana.mensaje("Parametro invalido", "error", "El nombre de ususario a filtrar no puede ser un número.");
                        txtFiltro.grabFocus();
                        
                        } catch (Exception a) {
                        
                            for (Usuario user : listaUsuarios) {
                                if (user.getUsuario().equals(txtFiltro.getText())) {
                                    continuar = false;
                                    cargarTabla(true, user.getId(), true);
                                    btnBorrarFiltro.setEnabled(true);
                                    break;
                                }
                            }
                        
                        }
                        
                    } else if (cboxTipoFiltro.getSelectedIndex() == 2) {
                        
                        try {
                            
                            long cedula = Long.parseLong(txtFiltro.getText());
                            
                            for (Usuario user : listaUsuarios) {
                                if (user.getCedula().equals(txtFiltro.getText())) {
                                    continuar = false;
                                    cargarTabla(true, user.getId(), true);
                                    btnBorrarFiltro.setEnabled(true);
                                    break;
                                }
                            }
                            
                        } catch (Exception u) {
                            
                            ventana.mensaje("Parametro invalido", "error", "Verifique el numero de cedula digitado.");
                            txtFiltro.grabFocus();
                            
                        }
                        
                    }
                    
                    if (continuar) {
                        ventana.mensaje("Usuario no encontrado", "error", "El usuario a filtrar no ha sido encontrado");
                        txtFiltro.grabFocus();
                    }
                }
            }
        });
        
        // Borra el filtro en la tabla sin actualizar la tabla desde la base de datos.
        btnBorrarFiltro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTabla(false, 0, false);
                btnBorrarFiltro.setEnabled(false);
                txtFiltro.setText("");
            }
        });
        
        // Actualiza la tabla con los datos mas recientes de la base de datos.
        btnActualizarTabla.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarTabla(false, 0, true);
                btnBorrarFiltro.setEnabled(false);
            }
        });
        
        // Invoca la funcion agregar nuevo usuario.
        btnAgregarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                dispose();
                ventana.agregarUsuario();
                
            }
            
        });
        
        // Invoca la eliminacion del usuario seleccionado.
        btnEliminarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (dataTable.getSelectedRow() != -1) {
                    
                    int idUsuario = Integer.parseInt(dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString());
                    
                    // Pide confirmacion mediante usuario y contraseña del actual administrador.
                    boolean continuar = ventana.confirmarAccionUsuario(null, true).isConfirm();
                    
                    if (continuar) {
                        
                        control.eliminarUsuario(idUsuario);
                        cargarTabla(false, 0, true);
                        
                        ventana.mensaje("Usuario eliminado", "info", "El usuario ha sido eliminado con exito.");
                        
                    }
                    
                } else {
                    
                    ventana.mensaje("Usuario no seleccionado", "error", "Debe seleccionar un usuario de la tabla.");
                    
                }
                
            }
        });
        
        
        btnModificarUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (dataTable.getSelectedRow() != -1) {
                    
                    int idUser = Integer.parseInt(dataTable.getValueAt(dataTable.getSelectedRow(), 0).toString());
                    
                    Usuario user = control.buscarUsuario(idUser);
                    
                    dispose();
                    ventana.modificarUsuario(user);
                    
                } else {
                    
                    ventana.mensaje("Usuario no seleccionado", "error", "Debe seleccionar un usuario de la tabla");
                    
                }
                
            }
        });
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEliminarUsuario = new javax.swing.JButton();
        btnModificarUsuario = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        btnAgregarUsuario = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtFiltro = new javax.swing.JTextField();
        btnFiltrar = new javax.swing.JButton();
        btnBorrarFiltro = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        dataTable = new javax.swing.JTable();
        btnActualizarTabla = new javax.swing.JButton();
        cboxTipoFiltro = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("Gestionar usuarios");

        btnEliminarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/eliminar-amigo.png"))); // NOI18N
        btnEliminarUsuario.setText("Eliminar");

        btnModificarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/editar-user-16px.png"))); // NOI18N
        btnModificarUsuario.setText("Modificar");

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/deshacer-16px.png"))); // NOI18N
        btnAtras.setText("Atras");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        btnAgregarUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/agregar-usuario.png"))); // NOI18N
        btnAgregarUsuario.setText("Agregar");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/buscar-24px.png"))); // NOI18N
        jLabel2.setText("Filtrar por:");

        btnFiltrar.setText("Filtrar");

        btnBorrarFiltro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/borrar-24px.png"))); // NOI18N
        btnBorrarFiltro.setText("Borrar filtro");
        btnBorrarFiltro.setEnabled(false);

        dataTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(dataTable);

        btnActualizarTabla.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Img/recargar-24px.png"))); // NOI18N
        btnActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarTablaActionPerformed(evt);
            }
        });

        cboxTipoFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Usuario", "Cedula" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 818, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificarUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboxTipoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnActualizarTabla, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnBorrarFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cboxTipoFiltro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 431, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnModificarUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
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

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        ventana.menu();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarTablaActionPerformed
        
    }//GEN-LAST:event_btnActualizarTablaActionPerformed
     
    // Setea los datos en la tabla o los actualiza.
    private void cargarTabla(boolean filtrarTabla, int id, boolean recargarListaUsuarios) {
        // Definir modelo de tabla
        DefaultTableModel tablaModel = new DefaultTableModel() {
            // Filas y columnas no editables.
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        if (recargarListaUsuarios) {
            listaUsuarios = control.listaUsuarios();
        } 
        
        // Establecer los nombres de las columnas
        String titulos[] = {"ID", "Cedula", "Nombres", "Apellidos", "Nombre de Usuario", "Contraseña"};
        tablaModel.setColumnIdentifiers(titulos);

        if (filtrarTabla) {

            if (id == 0) {

                ventana.mensaje("Registro no encontrado", "error", "El registro que usted esta buscando no se encontro\nVerifique que haya ingresado correctamente el dato.");
                txtFiltro.grabFocus();

            } else {

                Usuario user = control.buscarUsuario(id);
                
                String[] pw = user.getContrasena().split("");
                ArrayList<String> password = new ArrayList<String>();

                for (int i = 0; i <= pw.length; i++) {
                    if (i <= 2) {
                        password.add(pw[i]);
                    } else {
                        password.add("*");
                    }
                }
                
                Object[] objeto = {user.getId(), user.getCedula(), user.getNombres(), user.getApellidos(), user.getUsuario(), String.join("", password)};
                tablaModel.addRow(objeto);

            }

        } else {

            // Recorrer datos y mostrarlos
            if (listaUsuarios != null) {

                for (Usuario user : listaUsuarios) {
                    
                    String[] pw = user.getContrasena().split("");
                    ArrayList<String> password = new ArrayList<String>();
                    
                    for (int i = 0; i <= pw.length; i++) {
                        if (i <= 2) {
                            password.add(pw[i]);
                        } else {
                            password.add("*");
                        }
                    }

                    Object[] objeto = {user.getId(), user.getCedula(), user.getNombres(), user.getApellidos(), user.getUsuario(), String.join("", password)};
                    tablaModel.addRow(objeto);

                }

            }

        }

        dataTable.setModel(tablaModel);
        
    }
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarTabla;
    private javax.swing.JButton btnAgregarUsuario;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnBorrarFiltro;
    private javax.swing.JButton btnEliminarUsuario;
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnModificarUsuario;
    private javax.swing.JComboBox<String> cboxTipoFiltro;
    private javax.swing.JTable dataTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
}
