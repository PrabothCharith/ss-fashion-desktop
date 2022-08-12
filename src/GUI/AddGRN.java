/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import Model.MySql;
import java.io.InputStream;
import java.sql.Array;
import java.util.Vector;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author praboth
 */
public class AddGRN extends javax.swing.JDialog {

    /**
     * Creates new form AddProduct
     */
    public AddGRN(java.awt.Frame parent, boolean modal) {

        super(parent, modal);

        initComponents();
        loadCombo();
        jComboBox1.grabFocus();
        loadProduct();
        jTextArea2.setEditable(false);
        jButton5.setEnabled(false);
        jButton6.setEnabled(false);
        jButton11.setEnabled(false);
        jButton12.setEnabled(false);
        jButton13.setEnabled(false);

        loginValidate();

    }
    int grnId = 0;
    int adminId = Login.adminId;
    int empId = Login.empId;
    double totalPrice;

    public void loginValidate() {

        int x = Login.adminId;
        int y = Login.empId;

        if (x == 0) {

            if (y >= 0) {
                JOptionPane.showMessageDialog(this, "please login first", "Warning", JOptionPane.WARNING_MESSAGE);
                setEnabled(false);
                this.dispose();
                System.exit(0);
            } else {

            }

        } else {
            if (y > 0) {
                JOptionPane.showMessageDialog(this, "something want wrong please reopen application", "Warning", JOptionPane.WARNING_MESSAGE);
                setEnabled(false);
                this.dispose();
                System.exit(0);
            } else {
            }
        }

    }

    public void print() {

        try {
            InputStream stream = AddGRN.class.getResourceAsStream("/reports/GRN.jrxml");
            JasperReport jr = JasperCompileManager.compileReport(stream);

            String grnIdS = grnId + "";
            String supplier = jComboBox7.getSelectedItem().toString() + " " + jComboBox11.getSelectedItem().toString();

            HashMap parameters = new HashMap();
            parameters.put("grnId", grnIdS);
            parameters.put("total", jLabel23.getText());
            parameters.put("supplier", supplier);

            TableModel tm = jTable1.getModel();
            JRTableModelDataSource dataSource = new JRTableModelDataSource(tm);

            JasperPrint jp = JasperFillManager.fillReport(jr, parameters, dataSource);

            JasperViewer.viewReport(jp, false);
            JasperPrintManager.printReport(jp, true);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void totalPrice() {
        totalPrice = 0.00;
        for (int i = 0; i < jTable1.getRowCount(); i++) {
            double qty = Double.parseDouble(jTable1.getValueAt(i, 4).toString());
            double pBP = Double.parseDouble(jTable1.getValueAt(i, 7).toString());
            totalPrice = totalPrice + (qty * pBP);

            jLabel23.setText(totalPrice + "");
        }

        try {
            ResultSet rs = MySql.search("SELECT * FROM com_payments WHERE com_payments.com_id = (SELECT id FROM company WHERE company.`name` = '" + jComboBox1.getSelectedItem().toString() + "')");
            if (rs.next()) {
                double balance = Double.parseDouble(rs.getString("com_payments.amount"));
                jLabel24.setText(balance + "");
                jLabel25.setText((balance + totalPrice) + "");
            } else {
                jLabel25.setText(totalPrice + "");
            }
        } catch (Exception e) {
        }
    }

    public void loadCombo() {
        try {
            ResultSet rs = MySql.search("SELECT * FROM `company`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("company.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `category`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("category.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox3.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `colour`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("colour.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox4.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `size`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("size.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox5.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `material`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("material.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox6.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `gender`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("gender.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox9.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `age`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("age.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox10.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ResultSet rs = MySql.search("SELECT * FROM `payment_type`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("payment_type.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox8.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadProduct() {
        String category = jComboBox3.getSelectedItem().toString();
        String colour = jComboBox4.getSelectedItem().toString();
        String size = jComboBox5.getSelectedItem().toString();
        String material = jComboBox6.getSelectedItem().toString();
        String gender = jComboBox9.getSelectedItem().toString();
        String age = jComboBox10.getSelectedItem().toString();

        if (category.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select category");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else if (colour.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select colour");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else if (size.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select size");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else if (material.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select material");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else if (gender.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select gender");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else if (age.equals("Select")) {
            Vector v = new Vector();
            v.add("Please select age");
            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox12.setModel(dcm);
        } else {
            try {

                ResultSet rs = MySql.search("SELECT * FROM product INNER JOIN `product_colour` ON `product_colour`.product_id = `product`.id INNER JOIN `product_size` ON `product_size`.product_id = `product`.id INNER JOIN `product_material` ON `product`.id = `product_material`.product_id WHERE `product`.category_id = (SELECT `category`.id FROM category WHERE `category`.`name` = '" + category + "') AND `product_colour`.colour_id = (SELECT `colour`.id FROM colour WHERE `colour`.`name` = '" + colour + "') AND `product_size`.size_id = (SELECT `size`.id FROM size WHERE `size`.`name` = '" + size + "') AND `product_material`.material_id = (SELECT `material`.id FROM material WHERE `material`.`name` = '" + material + "') AND `product`.gender_id = (SELECT `gender`.id FROM gender WHERE `gender`.`name` = '" + gender + "') AND `product`.age_id = (SELECT `age`.id FROM age WHERE `age`.`name` = '" + age + "') ORDER BY `name` ASC");

                if (rs.next()) {

                    ResultSet rs1 = MySql.search("SELECT * FROM product INNER JOIN `product_colour` ON `product_colour`.product_id = `product`.id INNER JOIN `product_size` ON `product_size`.product_id = `product`.id INNER JOIN `product_material` ON `product`.id = `product_material`.product_id WHERE `product`.category_id = (SELECT `category`.id FROM category WHERE `category`.`name` = '" + category + "') AND `product_colour`.colour_id = (SELECT `colour`.id FROM colour WHERE `colour`.`name` = '" + colour + "') AND `product_size`.size_id = (SELECT `size`.id FROM size WHERE `size`.`name` = '" + size + "') AND `product_material`.material_id = (SELECT `material`.id FROM material WHERE `material`.`name` = '" + material + "') AND `product`.gender_id = (SELECT `gender`.id FROM gender WHERE `gender`.`name` = '" + gender + "') AND `product`.age_id = (SELECT `age`.id FROM age WHERE `age`.`name` = '" + age + "') ORDER BY `name` ASC");
                    Vector v = new Vector();

                    v.add("Select");
                    while (rs1.next()) {
                        v.add(rs1.getString("product.name"));
                    }
                    DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
                    jComboBox12.setModel(dcm);

                } else {
                    int x = JOptionPane.showConfirmDialog(this, "Product doesn't available like this. Do you want to ad new one?", "Not product found!", JOptionPane.YES_NO_OPTION);

                    if (x == 0) {
                        new AddNewProduct(this, true).setVisible(true);
                        ResultSet rs1 = MySql.search("SELECT * FROM product INNER JOIN `product_colour` ON `product_colour`.product_id = `product`.id INNER JOIN `product_size` ON `product_size`.product_id = `product`.id INNER JOIN `product_material` ON `product`.id = `product_material`.product_id WHERE `product`.category_id = (SELECT `category`.id FROM category WHERE `category`.`name` = '" + category + "') AND `product_colour`.colour_id = (SELECT `colour`.id FROM colour WHERE `colour`.`name` = '" + colour + "') AND `product_size`.size_id = (SELECT `size`.id FROM size WHERE `size`.`name` = '" + size + "') AND `product_material`.material_id = (SELECT `material`.id FROM material WHERE `material`.`name` = '" + material + "') AND `product`.gender_id = (SELECT `gender`.id FROM gender WHERE `gender`.`name` = '" + gender + "') AND `product`.age_id = (SELECT `age`.id FROM age WHERE `age`.`name` = '" + age + "') ORDER BY `name` ASC");
                        Vector v = new Vector();

                        v.add("Select");
                        while (rs1.next()) {
                            v.add(rs1.getString("product.name"));
                        }
                        DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
                        jComboBox12.setModel(dcm);
                    } else {
                        Vector v = new Vector();
                        v.add("Select");
                        DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
                        jComboBox12.setModel(dcm);
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void checkRowCount() {
        int rowCount = jTable1.getRowCount();

        if (rowCount < 0) {
            //   
        } else {
            jButton13.setEnabled(true);
            jButton12.setEnabled(true);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jComboBox5 = new javax.swing.JComboBox<>();
        jComboBox6 = new javax.swing.JComboBox<>();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        jLabel9 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jComboBox9 = new javax.swing.JComboBox<>();
        jLabel27 = new javax.swing.JLabel();
        jComboBox10 = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jComboBox12 = new javax.swing.JComboBox<>();
        jButton18 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox7 = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jComboBox11 = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("New GEN Report");
        setAutoRequestFocus(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 255), 1, true), "Product Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 Ex BT", 1, 14), new java.awt.Color(153, 51, 255))); // NOI18N

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(102, 0, 102));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("Name of Product");

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 0, 102));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel2.setText("Category");

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 0, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel3.setText("Colour");

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 0, 102));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel4.setText("Size");

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 0, 102));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel5.setText("Material");

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(102, 0, 102));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Buying price");

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(102, 0, 102));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel7.setText("Selling price");

        jComboBox3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox3.setFocusable(false);
        jComboBox3.setName(""); // NOI18N
        jComboBox3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox3ItemStateChanged(evt);
            }
        });
        jComboBox3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox3KeyReleased(evt);
            }
        });

        jComboBox4.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox4ItemStateChanged(evt);
            }
        });
        jComboBox4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox4KeyReleased(evt);
            }
        });

        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });
        jComboBox5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox5KeyReleased(evt);
            }
        });

        jComboBox6.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox6ItemStateChanged(evt);
            }
        });
        jComboBox6.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox6KeyReleased(evt);
            }
        });

        jTextField2.setText("0.");
        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField2KeyTyped(evt);
            }
        });

        jTextField3.setText("0.");
        jTextField3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField3KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField3KeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(102, 0, 102));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel8.setText("Description");

        jTextArea2.setColumns(20);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText("-");
        jScrollPane2.setViewportView(jTextArea2);

        jLabel9.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(102, 0, 102));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Quentity");

        jTextField4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField4KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField4KeyTyped(evt);
            }
        });

        jButton5.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton5.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Add product");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jButton6.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 255, 255));
        jButton6.setText("Clear");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });
        jComboBox9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox9KeyReleased(evt);
            }
        });

        jLabel27.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(102, 0, 102));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel27.setText("Gender");

        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });
        jComboBox10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox10KeyReleased(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(102, 0, 102));
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Age");

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/reset_icon.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/reset_icon.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jComboBox12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jComboBox12.setFocusable(false);
        jComboBox12.setName(""); // NOI18N
        jComboBox12.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox12ItemStateChanged(evt);
            }
        });
        jComboBox12.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox12KeyReleased(evt);
            }
        });

        jButton18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton13.setBackground(javax.swing.UIManager.getDefaults().getColor("nb.errorForeground"));
        jButton13.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 255, 255));
        jButton13.setText("Delete");
        jButton13.setOpaque(true);
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
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
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField3))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel6)
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox6, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox12, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton18))
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel2)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 5, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel28)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel27)
                                        .addGap(18, 18, 18)
                                        .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel27, jLabel28, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane2, jTextField4});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox5, jComboBox6, jComboBox9});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jComboBox12)
                            .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGap(5, 5, 5)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jComboBox6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel27)
                    .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox12, jComboBox3, jComboBox4, jComboBox5, jComboBox6, jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6, jLabel7, jLabel8, jLabel9, jTextField2, jTextField3});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox9, jLabel27});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox10, jLabel28});

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 255)), "Supplier Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 Ex BT", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jLabel10.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(102, 0, 102));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel10.setText("Company Name");

        jLabel11.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(102, 0, 102));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel11.setText("Company Branch");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(102, 0, 102));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel12.setText("Supplier Name");

        jLabel13.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(102, 0, 102));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel13.setText("Company Hotline");

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox1KeyReleased(evt);
            }
        });

        jComboBox2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox2ItemStateChanged(evt);
            }
        });
        jComboBox2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox2KeyReleased(evt);
            }
        });

        jComboBox7.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox7ItemStateChanged(evt);
            }
        });
        jComboBox7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox7KeyReleased(evt);
            }
        });

        jLabel15.setText("-");

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton7.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton8.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton9.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(188, 244, 214));
        jButton15.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton15.setText("Create GRN");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jComboBox11.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jComboBox11KeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(18, 18, 18)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 243, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(126, 126, 126))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel15)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel12, jLabel13});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox1, jComboBox2, jLabel15});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox11, jComboBox7});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton7, jButton8, jButton9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel10)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(jComboBox7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton7, jButton8, jButton9, jComboBox1, jComboBox11, jComboBox2, jComboBox7, jLabel10, jLabel11, jLabel12, jLabel13, jLabel15});

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 51, 255), 1, true), "GRN Payments", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 Ex BT", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jLabel17.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(102, 0, 102));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel17.setText("Total price");

        jLabel18.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(102, 0, 102));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel18.setText("Payable amount");

        jLabel19.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(102, 0, 102));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel19.setText("Total amount");

        jLabel20.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(102, 0, 102));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("Paying amount");

        jLabel21.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(102, 0, 102));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel21.setText("Payment method");

        jLabel22.setFont(new java.awt.Font("Helvetica Neue", 1, 12)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(102, 0, 102));
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel22.setText("Remaining paybel");

        jLabel23.setText("0.00");

        jLabel24.setText("0.00");

        jLabel25.setText("0.00");

        jTextField5.setText("0.");
        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextField5KeyTyped(evt);
            }
        });

        jLabel26.setText("0.00");

        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton10.setPreferredSize(new java.awt.Dimension(26, 26));
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jButton11.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 255, 255));
        jButton11.setText("Cancel & reset fields");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Green"));
        jButton12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 255, 255));
        jButton12.setText("Complete & Print GRN");
        jButton12.setBorderPainted(false);
        jButton12.setOpaque(true);
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/reset_icon.png"))); // NOI18N
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel19)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addComponent(jTextField5))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel22)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton11, jButton12});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton10, jButton19});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(jLabel23))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(jLabel24))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel25))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel21)
                                .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel26))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11)))
                    .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton10, jButton19, jComboBox8, jLabel17, jLabel18, jLabel19, jLabel20, jLabel21, jLabel22, jLabel23, jLabel24, jLabel25, jLabel26, jTextField5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton11, jButton12});

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 255), 1, true), "GRN items", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 Ex BT", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Name", "Category", "Size", "Qty", "Age", "Gender", "Buying (P.P.1)", "Selling (P.P.1)"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setColumnSelectionAllowed(true);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jSeparator3)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jSeparator4)))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 11, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 3, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        String pName = jComboBox12.getSelectedItem().toString();

        String pCategory = jComboBox3.getSelectedItem().toString();
        String pColour = jComboBox4.getSelectedItem().toString();
        String pSize = jComboBox5.getSelectedItem().toString();
        String pMaterial = jComboBox6.getSelectedItem().toString();
        String pGender = jComboBox9.getSelectedItem().toString();
        String pAge = jComboBox10.getSelectedItem().toString();
        String bPrice = jTextField2.getText();
        String sPrice = jTextField3.getText();
        String pQty = jTextField4.getText();

//        search from databse
        String pId;

        if (grnId == 0) {
            JOptionPane.showMessageDialog(this, "Please create grn first", "Missing information", JOptionPane.OK_OPTION);
            jComboBox1.grabFocus();
        } else {
            if (!bPrice.isEmpty()) {
                int xx = 0;
                for (int i = 0; i < bPrice.length(); i++) {
                    String ff = bPrice.charAt(i) + "";
                    if (ff.equals(".")) {
                        xx++;
                    }
                }
                if (xx > 1) {
                    jTextField2.grabFocus();
                    JOptionPane.showMessageDialog(this, "You can't add pullstops more than one ", "Invalid operation", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (!sPrice.isEmpty()) {
                int xx = 0;
                for (int i = 0; i < sPrice.length(); i++) {
                    String ff = sPrice.charAt(i) + "";
                    if (ff.equals(".")) {
                        xx++;
                    }
                }
                if (xx > 1) {
                    jTextField3.grabFocus();
                    JOptionPane.showMessageDialog(this, "You can't add pullstops more than one ", "Invalid operation", JOptionPane.WARNING_MESSAGE);
                }

            }
            if (pName.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select product name", "Missing information", JOptionPane.OK_OPTION);
                jComboBox12.grabFocus();
            } else if (pCategory.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select category", "Missing information", JOptionPane.OK_OPTION);
                jComboBox3.grabFocus();
            } else if (pColour.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select colour", "Missing information", JOptionPane.OK_OPTION);
                jComboBox4.grabFocus();
            } else if (pSize.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select product price", "Missing information", JOptionPane.OK_OPTION);
                jComboBox5.grabFocus();
            } else if (pMaterial.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select product material", "Missing information", JOptionPane.OK_OPTION);
                jComboBox6.grabFocus();
            } else if (pGender.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select gender for product", "Missing information", JOptionPane.OK_OPTION);
                jComboBox9.grabFocus();
            } else if (pAge.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select age range for product", "Missing information", JOptionPane.OK_OPTION);
                jComboBox10.grabFocus();
            } else if (bPrice.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add buying price for product", "Missing information", JOptionPane.OK_OPTION);
                jTextField2.grabFocus();
            } else if (sPrice.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add selling price for product", "Missing information", JOptionPane.OK_OPTION);
                jTextField3.grabFocus();
            } else if (!Pattern.compile("([1-9]+[.0-9]+)|((0.)[0-9]+)|([0-9]+)").matcher(bPrice).matches()) {
                JOptionPane.showMessageDialog(this, "Please add valid buying price for product", "Invalid information", JOptionPane.OK_OPTION);
                jTextField2.grabFocus();
            } else if (!Pattern.compile("([1-9]+[.0-9]+)|((0.)[0-9]+)|([0-9]+)").matcher(sPrice).matches()) {
                JOptionPane.showMessageDialog(this, "Please add valid selling price for product", "Missing information", JOptionPane.OK_OPTION);
                jTextField3.grabFocus();
            } else if (pQty.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please add quentity for product", "Missing information", JOptionPane.OK_OPTION);
                jTextField4.grabFocus();
            } else if (!Pattern.compile("[0-9]+").matcher(pQty).matches()) {
                JOptionPane.showMessageDialog(this, "Please add valid quentity for product", "Missing information", JOptionPane.OK_OPTION);
                jTextField4.grabFocus();
            } else {
                try {
                    ResultSet rs1 = MySql.search("SELECT * FROM product WHERE `name` = '" + pName + "'");
                    if (rs1.next()) {
                        pId = rs1.getString("product.id");

                        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
                        Vector v = new Vector();
                        v.add(pId);
                        v.add(pName);
                        v.add(pCategory);
                        v.add(pSize);
                        v.add(pQty);
                        v.add(pAge);
                        v.add(pGender);
                        v.add(bPrice);
                        v.add(sPrice);
                        dtm.addRow(v);

                        totalPrice();

                        checkRowCount();

                    }
                } catch (Exception e) {
                }

            }

        }


    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        jComboBox12.setSelectedIndex(0);
        jTextField2.setText("0.");
        jTextField3.setText("0.");
        jTextField4.setText("");
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
        jComboBox5.setSelectedIndex(0);
        jComboBox6.setSelectedIndex(0);
        jComboBox9.setSelectedIndex(0);
        jComboBox10.setSelectedIndex(0);
        jTextArea2.setText("-");
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        String sCompany = jComboBox1.getSelectedItem().toString();

        for (int i = 0; i < jTable1.getRowCount(); i++) {
            String pId = jTable1.getValueAt(i, 0).toString();
            String pQty = jTable1.getValueAt(i, 4).toString();
            String pBP = jTable1.getValueAt(i, 7).toString();
            String pSP = jTable1.getValueAt(i, 8).toString();

            try {
                ResultSet rs1 = MySql.search("SELECT * FROM stock WHERE product_id = '" + pId + "' AND sprice = '" + pSP + "'");

                if (rs1.next()) {
                    MySql.iud("INSERT INTO grn_item (grn_id,bprice,stock_id,qty) VALUES ('" + grnId + "','" + pBP + "','" + rs1.getString("stock.id") + "','" + pQty + "')");
                    pQty = (Integer.parseInt(rs1.getString("stock.qty")) + Integer.parseInt(pQty)) + "";
                    MySql.iud("UPDATE stock SET qty = '" + pQty + "' WHERE product_id = '" + pId + "' AND sprice = '" + pSP + "'");
                } else {
                    MySql.iud("INSERT INTO stock (product_id,sprice,qty) VALUES ('" + pId + "','" + pSP + "','" + pQty + "')");
                    ResultSet rs2 = MySql.search("SELECT * FROM stock WHERE product_id = '" + pId + "' AND sprice = '" + pSP + "'");
                    rs2.next();
                    MySql.iud("INSERT INTO grn_item (grn_id,bprice,stock_id,qty) VALUES ('" + grnId + "','" + pBP + "','" + rs2.getString("stock.id") + "','" + pQty + "')");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        MySql.iud("UPDATE grn SET total_amount = '" + jLabel23.getText() + "' WHERE id = '" + grnId + "'");

        try {
            ResultSet rs3 = MySql.search("SELECT * FROM com_payments WHERE com_payments.com_id = (SELECT id FROM company WHERE company.`name` = '" + sCompany + "')");

            if (rs3.next()) {
                MySql.iud("UPDATE com_payments SET com_payments.amount = '" + jLabel26.getText() + "' WHERE com_payments.com_id = (SELECT id FROM company WHERE company.`name` = '" + sCompany + "')");
                //create reports
                JOptionPane.showMessageDialog(this, "GRN created sucess", "sucess", JOptionPane.INFORMATION_MESSAGE);
                print();
            } else {
                MySql.iud("INSERT INTO com_payments (`com_id`,`amount`) VALUES((SELECT id FROM company WHERE company.`name` = '" + sCompany + "'),'" + jLabel26.getText() + "')");
                JOptionPane.showMessageDialog(this, "GRN created sucess", "sucess", JOptionPane.INFORMATION_MESSAGE);
                print();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        jButton12.setEnabled(false);
        jButton11.setEnabled(true);

    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed

        String sId = null;

        if (jComboBox1.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select company name", "Missing information", JOptionPane.OK_OPTION);
            jComboBox1.grabFocus();
        } else if (jComboBox2.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select company branch", "Missing information", JOptionPane.OK_OPTION);
            jComboBox2.grabFocus();
        } else if (jComboBox7.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select supplier first name", "Missing information", JOptionPane.OK_OPTION);
            jComboBox7.grabFocus();
        } else if (jComboBox11.getSelectedItem().equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select supplier last name", "Missing information", JOptionPane.OK_OPTION);
            jComboBox11.grabFocus();
        } else {
            try {
                ResultSet rs = MySql.search("SELECT * FROM suppliers WHERE fname = '" + jComboBox7.getSelectedItem().toString() + "' AND lname = '" + jComboBox11.getSelectedItem().toString() + "' AND branch_id IN (SELECT id FROM city WHERE `name` = '" + jComboBox2.getSelectedItem().toString() + "') AND company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "' )");
                rs.next();
                sId = rs.getString("suppliers.id");

                ResultSet rs2 = MySql.search("SELECT * FROM grn ORDER BY id DESC");

                if (rs2.next()) {
                    grnId = rs2.getInt("grn.id");
                    grnId++;
                } else {
                    grnId = 1;
                }

                MySql.iud("INSERT INTO grn (id,suppliers_id,admin_id) VALUES('" + grnId + "','" + sId + "','" + adminId + "')");

                JOptionPane.showMessageDialog(this, "GRN created sucessful !, now you can add products", "Sucess", JOptionPane.PLAIN_MESSAGE);

                boolean desable = true;
                jButton5.setEnabled(true);
                jButton6.setEnabled(true);
                jPanel1.setEnabled(true);
                jButton15.setEnabled(false);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_jButton15ActionPerformed

    private void jComboBox1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox2.grabFocus();
        }
    }//GEN-LAST:event_jComboBox1KeyReleased

    private void jComboBox2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox2KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox7.grabFocus();
        }
    }//GEN-LAST:event_jComboBox2KeyReleased

    private void jComboBox7KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox7KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox11.grabFocus();
        }
    }//GEN-LAST:event_jComboBox7KeyReleased

    private void jComboBox11KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox11KeyReleased
        if (evt.getKeyCode() == 10) {
            jButton15.grabFocus();
        }
    }//GEN-LAST:event_jComboBox11KeyReleased

    private void jComboBox3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox3KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox4.grabFocus();
        }
    }//GEN-LAST:event_jComboBox3KeyReleased

    private void jComboBox4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox4KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox5.grabFocus();
        }
    }//GEN-LAST:event_jComboBox4KeyReleased

    private void jComboBox5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox5KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox6.grabFocus();
        }
    }//GEN-LAST:event_jComboBox5KeyReleased

    private void jComboBox6KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox6KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox9.grabFocus();
        }
    }//GEN-LAST:event_jComboBox6KeyReleased

    private void jComboBox9KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox9KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox10.grabFocus();
        }
    }//GEN-LAST:event_jComboBox9KeyReleased

    private void jComboBox10KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox10KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox12.grabFocus();
        }
    }//GEN-LAST:event_jComboBox10KeyReleased

    private void jTextField3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyReleased
        if (evt.getKeyCode() == 10) {
            jTextField4.grabFocus();
        }
    }//GEN-LAST:event_jTextField3KeyReleased

    private void jTextField4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyReleased
        if (evt.getKeyCode() == 10) {
            jButton5.grabFocus();
        }
    }//GEN-LAST:event_jTextField4KeyReleased

    private void jTextField2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyTyped
        String x = jTextField2.getText();
        if (!Pattern.compile("([1-9]+[.0-9]+)|((0.)[0-9]+)|([0-9]+)").matcher(x + evt.getKeyChar()).matches()) {
            evt.consume();
        } else {
            int xx = 0;

            for (int i = 0; i < x.length(); i++) {
                String ff = x.charAt(i) + "";
                if (ff.equals(".")) {
                    xx++;
                }
            }

            if (xx > 1) {
                evt.consume();
                JOptionPane.showMessageDialog(this, "You can't add pullstops more than one ", "Invalid operation", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_jTextField2KeyTyped

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        if (evt.getKeyCode() == 10) {
            jTextField3.grabFocus();
        }
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTextField3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField3KeyTyped
        String x = jTextField3.getText();

        if (!Pattern.compile("([1-9]+[.0-9]+)|((0.)[0-9]+)|([0-9]+)").matcher(x + evt.getKeyChar()).matches()) {
            evt.consume();
        } else {
            int xx = 0;

            for (int i = 0; i < x.length(); i++) {
                String ff = x.charAt(i) + "";
                if (ff.equals(".")) {
                    xx++;
                }
            }

            if (xx > 1) {
                evt.consume();
                JOptionPane.showMessageDialog(this, "You can't add pullstops more than one ", "Invalid operation", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_jTextField3KeyTyped

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        jTextField2.setText("0.");
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        jTextField3.setText("0.");
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTextField4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField4KeyTyped
        String x = jTextField4.getText();

        if (!Pattern.compile("[0-9]+").matcher(x + evt.getKeyChar()).matches()) {
            evt.consume();
        }
    }//GEN-LAST:event_jTextField4KeyTyped

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        new AddNewProduct(this, true).setVisible(true);
        loadProduct();
        loadCombo();
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        new AddNewCompany(this, true).setVisible(true);
        try {
            ResultSet rs = MySql.search("SELECT * FROM `company`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("company.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox1.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        new AddNewBranch(this, true).setVisible(true);
        try {
            ResultSet rs = MySql.search("SELECT * FROM city WHERE id IN (SELECT city_id FROM company_has_city WHERE company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "'))");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("city.name"));
            }

            rs = MySql.search("SELECT * FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "'");
            rs.next();
            jLabel15.setText(rs.getString("company.mobile"));

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        new AddNewSupplier(this, true).setVisible(true);
        try {
            ResultSet rs = MySql.search("SELECT * FROM suppliers WHERE branch_id = (SELECT id FROM city WHERE `name` = '" + jComboBox2.getSelectedItem().toString() + "' ) AND company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "' )");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("suppliers.fname"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox7.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        new AddNewPaymentMethod(this, true).setVisible(true);
        try {
            ResultSet rs = MySql.search("SELECT * FROM `payment_type`");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("payment_type.name"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox8.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        int x = jTable1.getSelectedRow();
        if (x < 0) {
        } else {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.removeRow(x);
            totalPrice();
        }
        checkRowCount();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jComboBox3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox3ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox3ItemStateChanged

    private void jComboBox4ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox4ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox4ItemStateChanged

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox6ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox6ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox6ItemStateChanged

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        loadProduct();
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox12ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox12ItemStateChanged
        String x = jComboBox12.getSelectedItem().toString();

        if (x.equals("Select")) {
            jTextArea2.setText("-");
        } else {
            try {
                ResultSet rs = MySql.search("SELECT `product`.description , `stock`.sprice , `grn_item`.bprice FROM product INNER JOIN stock ON product.id = stock.product_id INNER JOIN grn_item ON stock.id = grn_item.stock_id INNER JOIN grn ON grn.id = grn_item.grn_id WHERE product.`name` = '" + x + "' ORDER BY grn.date DESC");
                rs.next();
                jTextArea2.setText(rs.getString("product.description"));
                jTextField2.setText(rs.getString("grn_item.bprice"));
                jTextField3.setText(rs.getString("stock.sprice"));
            } catch (Exception e) {
            }
        }
    }//GEN-LAST:event_jComboBox12ItemStateChanged

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        int x = JOptionPane.showConfirmDialog(this, "Do you want to cancel this GRN ?", "Conform action", JOptionPane.YES_NO_OPTION);
        if (x == 0) {
            DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();
            dtm.setRowCount(0);
            jComboBox12.setSelectedIndex(0);
            jTextField2.setText("0.");
            jTextField3.setText("0.");
            jTextField4.setText("");
            jComboBox1.setSelectedIndex(0);
            jComboBox2.setSelectedIndex(0);
            jComboBox3.setSelectedIndex(0);
            jComboBox4.setSelectedIndex(0);
            jComboBox5.setSelectedIndex(0);
            jComboBox6.setSelectedIndex(0);
            jComboBox7.setSelectedIndex(0);
            jComboBox9.setSelectedIndex(0);
            jComboBox10.setSelectedIndex(0);
            jComboBox11.setSelectedIndex(0);
            jLabel15.setText("-");
            jTextArea2.setText("-");
            jLabel23.setText("0.00");
            jLabel24.setText("0.00");
            jLabel25.setText("0.00");
            jLabel26.setText("0.00");
            jTextField5.setText("0.");

            MySql.iud("UPDATE grn SET deleted = '2' WHERE id = '" + grnId + "'");
            jButton15.setEnabled(true);

        } else {
        }

    }//GEN-LAST:event_jButton11ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased

        if (!jTextField5.getText().isEmpty()) {
            double paying = Double.parseDouble(jTextField5.getText());
            double total = Double.parseDouble(jLabel25.getText());

            double balance = total - paying;

            jLabel26.setText(balance + "");
        }

    }//GEN-LAST:event_jTextField5KeyReleased

    private void jTextField5KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyTyped
        String x = jTextField5.getText();

        if (!Pattern.compile("([1-9]+[.0-9]+)|((0.)[0-9]+)|([0-9]+)").matcher(x + evt.getKeyChar()).matches()) {
            evt.consume();
        } else {
            int xx = 0;

            for (int i = 0; i < x.length(); i++) {
                String ff = x.charAt(i) + "";
                if (ff.equals(".")) {
                    xx++;
                }
            }

            if (xx > 1) {
                evt.consume();
                JOptionPane.showMessageDialog(this, "You can't add pullstops more than one ", "Invalid operation", JOptionPane.WARNING_MESSAGE);
            }

        }
    }//GEN-LAST:event_jTextField5KeyTyped

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        jTextField5.setText("0.");
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        try {
            ResultSet rs = MySql.search("SELECT * FROM city WHERE id IN (SELECT city_id FROM company_has_city WHERE company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "'))");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("city.name"));
            }

            rs = MySql.search("SELECT * FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "'");
            rs.next();
            jLabel15.setText(rs.getString("company.mobile"));

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox2.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jComboBox2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox2ItemStateChanged
        try {
            ResultSet rs = MySql.search("SELECT * FROM suppliers WHERE branch_id = (SELECT id FROM city WHERE `name` = '" + jComboBox2.getSelectedItem().toString() + "' ) AND company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "' )");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("suppliers.fname"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox7.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }//GEN-LAST:event_jComboBox2ItemStateChanged

    private void jComboBox7ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox7ItemStateChanged
        try {
            ResultSet rs = MySql.search("SELECT * FROM suppliers WHERE branch_id = (SELECT id FROM city WHERE `name` = '" + jComboBox2.getSelectedItem().toString() + "' ) AND company_id = (SELECT id FROM company WHERE `name` = '" + jComboBox1.getSelectedItem().toString() + "' ) AND fname = '" + jComboBox7.getSelectedItem().toString() + "' ");
            Vector v = new Vector();
            v.add("Select");
            while (rs.next()) {
                v.add(rs.getString("suppliers.lname"));
            }

            DefaultComboBoxModel dcm = new DefaultComboBoxModel(v);
            jComboBox11.setModel(dcm);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jComboBox7ItemStateChanged

    private void jComboBox12KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox12KeyReleased
        if (evt.getKeyCode() == 10) {
            jComboBox3.grabFocus();
        }
    }//GEN-LAST:event_jComboBox12KeyReleased

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
            java.util.logging.Logger.getLogger(AddGRN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddGRN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddGRN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddGRN.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                AddGRN dialog = new AddGRN(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox12;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox6;
    private javax.swing.JComboBox<String> jComboBox7;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
