/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import Model.MySql;
import java.sql.ResultSet;
import java.util.Vector;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author praboth
 */
public class AddNewCompany extends javax.swing.JDialog {

    /**
     * Creates new form AddNewCategory
     */
    public AddNewCompany(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadResult("");
        loadCombo();loginValidate();
    }

    AddNewBranch anb;

    public AddNewCompany(AddNewBranch parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadResult("");
        loadCombo();loginValidate();
        this.anb = parent;
    }

    AddNewSupplier ans;

    public AddNewCompany(AddNewSupplier parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadResult("");
        loadCombo();loginValidate();
        this.ans = parent;
    }

    AddGRN agrn;

    public AddNewCompany(AddGRN parent, boolean modal) {
        super(parent, modal);
        initComponents();
        loadResult("");
        loadCombo();loginValidate();
        this.agrn = parent;
    }

        public void loginValidate() {

        int x = Login.adminId;
        int y = Login.empId;

        if (x == 0) {

            if (y >= 0) {
                JOptionPane.showMessageDialog(this, "please login first", "Warning", JOptionPane.WARNING_MESSAGE);
                setEnabled(false);
                this.dispose();System.exit(0);
            } else {

            }

        } else {
            if (y > 0) {
                JOptionPane.showMessageDialog(this, "something want wrong please reopen application", "Warning", JOptionPane.WARNING_MESSAGE);
                setEnabled(false);
                this.dispose();System.exit(0);
            } else {
            }
        }

    }
    
    public void loadResult(String x) {
        try {

            String query;

            if (x.equals("")) {
                query = "";
            } else {
                query = x;
            }
            ResultSet rs = MySql.search("SELECT company.name, company.mobile, company.email, company.address, city.name FROM company INNER JOIN company_has_city ON company.id = company_has_city.company_id INNER JOIN city ON company_has_city.city_id = city.id " + query + " ORDER BY company.id ASC");
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);

            while (rs.next()) {
                Vector v = new Vector();
                v.add(rs.getString("company.name"));
                v.add(rs.getString("company.address"));
                v.add(rs.getString("company.mobile"));
                v.add(rs.getString("company.email"));
                v.add(rs.getString("city.name"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void loadCombo() {
        try {
            ResultSet rs = MySql.search("SELECT * FROM `city`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("city.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
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
        jTextField1 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jButton10 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Add new Supplier Company");

        jLabel1.setText("Add Company");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        jButton1.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Add");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Yellow"));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Clear");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel2.setText("Company Mobile");

        jLabel3.setText("Company Email");

        jLabel4.setText("City");

        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
        });

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel5.setText("Company Address");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(22, 22, 22)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(22, 22, 22)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(41, 41, 41)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox1, 0, 218, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jTextField4)))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel4)
                                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox1, jLabel1, jLabel2, jLabel3, jLabel4, jTextField1});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(153, 0, 153)), "Available Companies", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 BlkEx BT", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "company", "Address", "Mobile", "Email", "Branch"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 776, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String company = jTextField1.getText();
        String mobile = jTextField2.getText();
        String email = jTextField3.getText();
        String address = jTextField4.getText();
        String city = jComboBox1.getSelectedItem().toString();

//        check how many rows have to this company
        String query = "WHERE company.`name` = '" + company + "'";
        loadResult(query);

//        save row count
        int rowCount = jTable1.getRowCount();
        query = "";

//        load all result again
        loadResult(query);

        if (company.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add company name", "missing part", JOptionPane.WARNING_MESSAGE);
            jTextField1.grabFocus();
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add address for company", "missing part", JOptionPane.WARNING_MESSAGE);
            jTextField4.grabFocus();
        } else if (mobile.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add mobile number for company", "missing part", JOptionPane.WARNING_MESSAGE);
            jTextField2.grabFocus();
        } else if (mobile.length() > 10) {
            JOptionPane.showMessageDialog(this, "Please add correct value", "missing informations", JOptionPane.OK_OPTION);
            jTextField3.grabFocus();
        } else if (!Pattern.compile("(07[01245678]|011)[0-9]{7}").matcher(mobile).find()) {
            JOptionPane.showMessageDialog(this, "Please add correct value", "missing informations", JOptionPane.OK_OPTION);
            jTextField3.grabFocus();
        } else if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add email for company", "missing part", JOptionPane.WARNING_MESSAGE);
            jTextField3.grabFocus();
        } else if (city.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select city for company", "missing part", JOptionPane.WARNING_MESSAGE);
            jComboBox1.grabFocus();
        } else if (rowCount > 0) {

            int yesNo = JOptionPane.showConfirmDialog(this, "This company already exsist do you want to update these details?", "Already exsist", JOptionPane.YES_NO_OPTION);

//            confirmDialog 0=yes 1=no
            if (yesNo == 0) {
                int yesNo2 = JOptionPane.showConfirmDialog(this, "Do you want to add new branch ?", "Already exsist", JOptionPane.YES_NO_OPTION);
                if (yesNo2 == 0) {

//                    search already have this branch
                    String tableName = "company_has_city";
                    try {
                        ResultSet rs = MySql.search("SELECT * FROM `" + tableName + "` INNER JOIN `city` ON `city_id` = `city`.id INNER JOIN `company` ON `company_id` = `company`.id WHERE `company`.`name` = '" + company + "' AND `city`.`name`='" + city + "'");
                        if (rs.next()) {
                            int jOptionResult = JOptionPane.showConfirmDialog(this, "This company already have this city. Do you want to Duplicate it?", "Already exist", JOptionPane.YES_NO_OPTION);

                            if (jOptionResult == 0) {
                                MySql.iud("INSERT INTO " + tableName + " (`city_id`,`company_id`) VALUES ((SELECT id FROM city WHERE `name`='" + city + "'),(SELECT id FROM company WHERE `name`='" + company + "'))");
                                JOptionPane.showMessageDialog(this, "New value duplicated succesfully", "success", JOptionPane.INFORMATION_MESSAGE);
                                MySql.iud("INSERT INTO `ss-fashions`.`log` (`type`, `who`, `description`) VALUES ('INSERT','e-1','Added " + company + " to new branch as " + city + "')");
                                loadResult("");
                            } else {
                                loadResult("");
                                jComboBox1.grabFocus();
                            }

                        } else {
                            MySql.iud("INSERT INTO " + tableName + " (`city_id`,`company_id`) VALUES ((SELECT id FROM city WHERE `name`='" + city + "'),(SELECT id FROM company WHERE `name`='" + company + "'))");
                            JOptionPane.showMessageDialog(this, "new branch added to " + company, "branch added sucessful", JOptionPane.INFORMATION_MESSAGE);
                            MySql.iud("INSERT INTO `ss-fashions`.`log` (`type`, `who`, `description`) VALUES ('INSERT','e-1','Added " + company + " to new branch as " + city + "')");
                            loadResult("");
                        }
                    } catch (Exception e) {
                    }

                } else {
                    int yesNo3 = JOptionPane.showConfirmDialog(this, "Do you want to update these typed details ?", "Already exsist", JOptionPane.YES_NO_OPTION);
                    if (yesNo3 == 0) {

                        String why = JOptionPane.showInputDialog(this, "test", "test", JOptionPane.WARNING_MESSAGE);

                        if (why.equals("")) {
                            JOptionPane.showMessageDialog(this, "Try again", "something went wrong", JOptionPane.OK_OPTION);
                        } else {
                            MySql.iud("INSERT INTO `ss-fashions`.`log` (`type`, `who`, `description`) VALUES ('UPDATE','e-1','Updated " + company + " company details because : " + why + "')");
                            MySql.iud("UPDATE `company` SET `address`='" + address + "',`mobile`='" + mobile + "',`email`='" + email + "' WHERE `name` = '" + company + "'");
                        }

                    } else {
                        jComboBox1.grabFocus();
                    }
                }

            }
        } else if (rowCount == 0) {
            MySql.iud("INSERT INTO company (`name`,`address`,`mobile`,`email`) VALUES ('" + company + "','" + address + "','" + mobile + "','" + email + "')");
            MySql.iud("INSERT INTO company_has_city (`city_id`,`company_id`) VALUES ((SELECT id FROM city WHERE `name`='" + city + "'),(SELECT id FROM company WHERE `name`='" + company + "'))");
            JOptionPane.showMessageDialog(this, "New value added succesfully", "success", JOptionPane.INFORMATION_MESSAGE);
            MySql.iud("INSERT INTO `ss-fashions`.`log` (`type`, `who`, `description`) VALUES ('INSERT','e-1','Registerd " + company + " as new company')");
        }

        loadResult(query);


    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jComboBox1.setSelectedIndex(0);
        jTextField1.grabFocus();

    }//GEN-LAST:event_jButton2ActionPerformed

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox1.grabFocus();
        }
    }//GEN-LAST:event_jTextField1KeyReleased

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
        if (evt.getKeyCode() == 10) {
            jTextField2.grabFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == 10) {
            jTextField3.grabFocus();
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        if (evt.getKeyCode() == 10) {
            jTextField4.grabFocus();
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if (evt.getKeyCode() == 10) {
            jButton1.grabFocus();
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        new AddNewCity(this, true).setVisible(true);
        loadCombo();
    }//GEN-LAST:event_jButton10ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddNewCompany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddNewCompany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddNewCompany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddNewCompany.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddNewCompany dialog = new AddNewCompany(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    // End of variables declaration//GEN-END:variables
}
