package robinlb99.tienda.igu.gestionUsuarios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import robinlb99.tienda.igu.Window;
import robinlb99.tienda.logica.LogicController;
import robinlb99.tienda.logica.Usuario;

/**
 *
 * @author ROBINLB99
 */
public class ModificarUsuario extends javax.swing.JFrame {
    
    Window ventana = new Window();
    LogicController control = new LogicController();

    Usuario user = null;
    
    public ModificarUsuario(Usuario usuario) {
        this.user = usuario;
        
        initComponents();
        
        txtUserName.setText(user.getUsuario());
        txtPassword.setText(user.getContrasena());
        
        boolean tipoUsuario = user.isIsAdministrador();
        
        if (tipoUsuario) {
            cboxTipoUsuario.setSelectedIndex(2);
        } else {
            cboxTipoUsuario.setSelectedIndex(1);
        }
        
        checkNameUser.setSelected(true);
        checkPassword.setSelected(true);
        checkTipoUsuario.setSelected(true);
        
        txtUserName.setEnabled(false);
        txtPassword.setEnabled(false);
        txtConfirmPassword.setEnabled(false);
        cboxTipoUsuario.setEnabled(false);
        
        checkNameUser.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (checkNameUser.isSelected()) {    
                    txtUserName.setEnabled(false);
                }
                
                if (!checkNameUser.isSelected()) {
                    txtUserName.setEnabled(true);
                }
                
            }
        });
        
        checkPassword.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                
                if (checkPassword.isSelected()) {
                    txtPassword.setEnabled(false);
                    txtConfirmPassword.setEnabled(false);
                }

                if (!checkPassword.isSelected()) {
                    txtPassword.setEnabled(true);
                    txtConfirmPassword.setEnabled(true);
                }
                
            }
        });
        
        checkTipoUsuario.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {

                if (checkTipoUsuario.isSelected()) {
                    cboxTipoUsuario.setEnabled(false);
                }

                if (!checkTipoUsuario.isSelected()) {
                    cboxTipoUsuario.setEnabled(true);
                }
                
            }
        });
        
        
       btnModificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                if (!checkNameUser.isSelected() && txtUserName.getText().isBlank()) {
                    
                    ventana.mensaje("Campo vacio", "error", "El campo 'Nombre de usuario' esta vacio.");
                    txtUserName.grabFocus();
                    
                } else if (!checkPassword.isSelected() && String.valueOf(txtPassword.getPassword()).isBlank()) {
                    
                    ventana.mensaje("Campo vacio", "error", "El campo 'Contraseña' esta vacio");
                    txtPassword.grabFocus();
                    
                } else if (!checkPassword.isSelected() &&  String.valueOf(txtConfirmPassword.getPassword()).isBlank()) {
                    
                    ventana.mensaje("Campo vacio", "error", "El campo 'Confirmar contraseña' esta vacio.");
                    txtConfirmPassword.grabFocus();
                    
                } else if (!checkTipoUsuario.isSelected() && cboxTipoUsuario.getSelectedIndex() == 0) {
                    
                    ventana.mensaje("Tipo invalido", "error", "El tipo de usuario es invalido.");
                    cboxTipoUsuario.grabFocus();
                    
                } else if (!String.valueOf(txtPassword.getPassword())
                        .equals(String.valueOf(txtConfirmPassword.getPassword()))) {
                    
                    ventana.mensaje("Contraseñas no coincidentes", "error", "Las contraseñas no coinciden.");
                    txtConfirmPassword.grabFocus();
                    
                } else {

                    // Pide confirmacion mediante usuario y contraseña del actual administrador.
                    boolean confirmar = ventana.confirmarAccionUsuario(null, true).isConfirm();

                    if (confirmar) {

                        String nameUser = txtUserName.getText();
                        String password = String.valueOf(txtPassword.getPassword());

                        boolean esAdmin = false;

                        if (cboxTipoUsuario.getSelectedIndex() == 2) {
                            esAdmin = true;
                        }

                        user.setUsuario(nameUser);
                        user.setContrasena(password);
                        user.setIsAdministrador(esAdmin);

                        try {

                            control.editarUsuario(user);
                            ventana.mensaje("Usuario editado", "info", "El usuario ha sido editado con exito.");
                            dispose();
                            ventana.gestionarUsuarios();

                        } catch (Exception a) {
                            
                            ventana.mensaje("No modificado", "error", "No se ha podido modifcar el usuario");
                            dispose();
                            ventana.gestionarUsuarios();
                            
                        }

                    }
                    
                }
                
            }
       });
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtUserName = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        txtConfirmPassword = new javax.swing.JPasswordField();
        jLabel5 = new javax.swing.JLabel();
        cboxTipoUsuario = new javax.swing.JComboBox<>();
        btnModificar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        checkNameUser = new javax.swing.JCheckBox();
        checkPassword = new javax.swing.JCheckBox();
        checkTipoUsuario = new javax.swing.JCheckBox();
        jToggleButton1 = new javax.swing.JToggleButton();
        jToggleButton2 = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Modificar Usuario");

        jLabel2.setText("Nombre de usuario:");

        txtUserName.setToolTipText("");
        txtUserName.setPreferredSize(new java.awt.Dimension(77, 35));
        txtUserName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserNameActionPerformed(evt);
            }
        });

        jLabel3.setText("Nueva contraseña:");

        txtPassword.setPreferredSize(new java.awt.Dimension(94, 35));

        jLabel4.setText("Confirmar contraseña:");

        txtConfirmPassword.setPreferredSize(new java.awt.Dimension(94, 35));

        jLabel5.setText("Tipo de usuario:");

        cboxTipoUsuario.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Vendedor", "Administrador" }));
        cboxTipoUsuario.setPreferredSize(new java.awt.Dimension(76, 35));

        btnModificar.setText("Modificar");
        btnModificar.setPreferredSize(new java.awt.Dimension(79, 35));

        btnAtras.setText("Atras");
        btnAtras.setPreferredSize(new java.awt.Dimension(100, 35));
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        checkNameUser.setText("Modificar nombre de usuario");

        checkPassword.setText("Modificar contraseña");

        checkTipoUsuario.setText("Modificar tipo de usuario");

        jToggleButton1.setPreferredSize(new java.awt.Dimension(35, 35));

        jToggleButton2.setPreferredSize(new java.awt.Dimension(35, 35));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cboxTipoUsuario, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtUserName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkNameUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkPassword, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(checkTipoUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtUserName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkNameUser)
                .addGap(15, 15, 15)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtConfirmPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jToggleButton2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkPassword)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboxTipoUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(checkTipoUsuario)
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(50, 50, 50))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void txtUserNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUserNameActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
        ventana.gestionarUsuarios();
    }//GEN-LAST:event_btnAtrasActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnModificar;
    private javax.swing.JComboBox<String> cboxTipoUsuario;
    private javax.swing.JCheckBox checkNameUser;
    private javax.swing.JCheckBox checkPassword;
    private javax.swing.JCheckBox checkTipoUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JToggleButton jToggleButton1;
    private javax.swing.JToggleButton jToggleButton2;
    private javax.swing.JPasswordField txtConfirmPassword;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtUserName;
    // End of variables declaration//GEN-END:variables
}
