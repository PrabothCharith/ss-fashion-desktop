/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package GUI;

import Model.MySql;
import java.awt.Color;
import java.sql.ResultSet;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author praboth
 */
public class Management extends javax.swing.JFrame {

    /**
     * Creates new form Management
     */
    AddNewProduct ap;
    AdminPanel admin;
    Payment employee;

    public Management() {
        initComponents();
        loadCombo();
        loadTable();
    }

    public Management(AddNewProduct parent, boolean model) {
        initComponents();
        loadCombo();
        loadTable();
        this.ap = parent;
    }

    public Management(AdminPanel parent, boolean model) {
        initComponents();
        loadCombo();
        loadTable();
        this.admin = parent;
    }

    public Management(Payment parent, boolean model) {
        initComponents();
        loadCombo();
        loadTable();
        jPanel1.setEnabled(false);
        jPanel1.removeAll();
        jTable1.setEnabled(false);
        this.employee = parent;
    }

    public void clearAll() {
        jLabel12.setText("-");
        jTextField1.setText("");
        jTextArea1.setText("");
        jButton4.setText("State Change");
        jButton4.setBackground(Color.YELLOW);
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        jComboBox4.setSelectedIndex(0);
    }

    public void loadCombo() {
        try {
            ResultSet category = MySql.search("SELECT * FROM `category`");
            Vector v1 = new Vector();
            v1.add("Select");
            while (category.next()) {
                v1.add(category.getString("category.name"));
            }
            jComboBox1.setModel(new DefaultComboBoxModel(v1));
            jComboBox8.setModel(new DefaultComboBoxModel(v1));

            ResultSet brand = MySql.search("SELECT * FROM `brand`");
            Vector v2 = new Vector();
            v2.add("Select");
            while (brand.next()) {
                v2.add(brand.getString("brand.name"));
            }
            jComboBox2.setModel(new DefaultComboBoxModel(v2));
            jComboBox9.setModel(new DefaultComboBoxModel(v2));

            ResultSet gender = MySql.search("SELECT * FROM `gender`");
            Vector v3 = new Vector();
            v3.add("Select");
            while (gender.next()) {
                v3.add(gender.getString("gender.name"));
            }
            jComboBox3.setModel(new DefaultComboBoxModel(v3));
            jComboBox10.setModel(new DefaultComboBoxModel(v3));

            ResultSet age = MySql.search("SELECT * FROM `age`");
            Vector v4 = new Vector();
            v4.add("Select");
            while (age.next()) {
                v4.add(age.getString("age.name"));
            }
            jComboBox4.setModel(new DefaultComboBoxModel(v4));
            jComboBox11.setModel(new DefaultComboBoxModel(v4));

            ResultSet availability = MySql.search("SELECT * FROM `availability`");
            Vector v5 = new Vector();
            v5.add("Select");
            while (availability.next()) {
                v5.add(availability.getString("availability.name"));
            }
            jComboBox5.setModel(new DefaultComboBoxModel(v5));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadTable() {

        String pCategory = jComboBox8.getSelectedItem().toString();
        String pBrand = jComboBox9.getSelectedItem().toString();
        String pAvalability = jComboBox5.getSelectedItem().toString();
        String pGender = jComboBox10.getSelectedItem().toString();
        String pAge = jComboBox11.getSelectedItem().toString();
        String pSearch = jTextField2.getText();
        String query = "";

        if (pCategory.equals("Select")) {
            if (pBrand.equals("Select")) {
                if (pAvalability.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'  AND availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'  AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'  AND availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    }
                }
            } else {
                if (pAvalability.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'  AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'  AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                }
            }
        } else if (pBrand.equals("Select")) {
            if (pCategory.equals("Select")) {
                if (pAvalability.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND  availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND  availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND  availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    }
                }
            } else {
                if (pAvalability.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND  availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                }
            }
        } else if (pAvalability.equals("Select")) {
            if (pCategory.equals("Select")) {
                if (pBrand.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                }
            } else {
                if (pBrand.equals("Select")) {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                } else {
                    if (pGender.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                }
            }
        } else if (pGender.equals("Select")) {
            if (pCategory.equals("Select")) {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                }
            } else {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pAge.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                }
            }
            if (pBrand.equals("Select")) {
                if (pAvalability.equals("Select")) {
                    if (pAge.equals("Select")) {
                        if (pSearch.isEmpty()) {
                            query = "";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                        }
                    } else {
                        if (pSearch.isEmpty()) {
                            query = "WHERE age.`name` = '" + pAge + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "'";
                        }
                    }
                } else {
                    if (pAge.equals("Select")) {
                        if (pSearch.isEmpty()) {
                            query = "WHERE availability.`name` = '" + pAvalability + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "'";
                        }
                    } else {
                        if (pSearch.isEmpty()) {
                            query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "'";
                        }
                    }
                }
            } else {
                if (pAvalability.equals("Select")) {
                    if (pAge.equals("Select")) {
                        if (pSearch.isEmpty()) {
                            query = "WHERE brand.`name` = '" + pBrand + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                        }
                    } else {
                        if (pSearch.isEmpty()) {
                            query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                        }
                    }
                } else {
                    if (pAge.equals("Select")) {
                        if (pSearch.isEmpty()) {
                            query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                        }
                    } else {
                        if (pSearch.isEmpty()) {
                            query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                        } else {
                            query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                        }
                    }
                }
            }
        } else if (pAge.equals(
                "Select")) {
            if (pCategory.equals("Select")) {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                }
            } else {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pSearch.isEmpty()) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pSearch.isEmpty()) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE product.`name` LIKE '%" + pSearch + "%' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                }
            }
        } else if (pSearch.isEmpty()) {
            if (pCategory.equals("Select")) {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "'";
                            }
                        }
                    }
                }
            } else {
                if (pBrand.equals("Select")) {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                } else {
                    if (pAvalability.equals("Select")) {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    } else {
                        if (pGender.equals("Select")) {
                            if (pAge.equals("Select")) {
                                query = "WHERE availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        } else {
                            if (pAge.equals("Select")) {
                                query = "WHERE gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            } else {
                                query = "WHERE age.`name` = '" + pAge + "' AND gender.`name` = '" + pGender + "' AND availability.`name` = '" + pAvalability + "' AND brand.`name` = '" + pBrand + "' AND category.`name` = '" + pCategory + "'";
                            }
                        }
                    }
                }
            }
        } else {
            query = "WHERE category.`name` = '" + pCategory + "' AND brand.`name` = '" + pBrand + "' AND availability.`name` = '" + pAvalability + "' AND gender.`name` = '" + pGender + "' AND age.`name` = '" + pAge + "' AND product.`name` LIKE '%" + pSearch + "%'";
        }

        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        dtm.setRowCount(
                0);
        try {
            ResultSet rs = MySql.search("SELECT * FROM `product` INNER JOIN category ON category.id = product.category_id INNER JOIN brand ON brand.id = product.brand_id INNER JOIN gender ON gender.id = product.gender_id INNER JOIN age ON age.id = product.age_id INNER JOIN availability ON availability.id = product.availability " + query + " ORDER BY product.name ASC");

            while (rs.next()) {

                ResultSet rs1 = MySql.search("SELECT SUM(qty) FROM stock WHERE product_id = '" + rs.getString("product.id") + "'");
                rs1.next();
                Vector v = new Vector();
                v.add(rs.getString("product.name"));
                v.add(rs.getString("product.description"));
                v.add(rs.getString("category.name"));
                v.add(rs.getString("brand.name"));
                v.add(rs.getString("gender.name"));
                v.add(rs.getString("age.name"));
                v.add(rs.getString("availability.name"));
                v.add(rs1.getString("SUM(qty)"));
                dtm.addRow(v);
            }

        } catch (Exception e) {
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
        jTextField1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jComboBox1 = new javax.swing.JComboBox<>();
        jComboBox2 = new javax.swing.JComboBox<>();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox8 = new javax.swing.JComboBox<>();
        jComboBox9 = new javax.swing.JComboBox<>();
        jComboBox10 = new javax.swing.JComboBox<>();
        jComboBox11 = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Product Search and Management");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(153, 0, 255), 1, true), "Product Details", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 BlkEx BT", 0, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jLabel1.setText("Product name");

        jLabel2.setText("Product Description");

        jLabel3.setText("Category");

        jLabel4.setText("Brand");

        jLabel5.setText("Gender");

        jLabel6.setText("Age");

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        jButton1.setBackground(new java.awt.Color(0, 102, 102));
        jButton1.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Update");
        jButton1.setToolTipText("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jButton2.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Resource/images/plus_icon.png"))); // NOI18N
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jLabel11.setText("Product ID");

        jLabel12.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        jLabel12.setText("-");

        jButton4.setBackground(java.awt.Color.yellow);
        jButton4.setFont(new java.awt.Font("Helvetica Neue", 1, 13)); // NOI18N
        jButton4.setText("State Change");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55)
                        .addComponent(jButton2))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(18, 18, 18)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel6)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox4, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel5)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox3, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel4)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel3)
                                    .addGap(18, 18, 18)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jButton14)
                                .addComponent(jButton15)
                                .addComponent(jButton16)
                                .addComponent(jButton17)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel11))
                            .addGap(18, 18, 18)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(jPanel1Layout.createSequentialGroup()
                                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jScrollPane1, jTextField1});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton16))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton17))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, jButton2});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton14, jButton4, jComboBox1, jComboBox2, jComboBox3, jComboBox4, jLabel1, jLabel11, jLabel12, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(153, 0, 255)), "Search Product", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Swis721 BlkEx BT", 1, 14), new java.awt.Color(153, 0, 255))); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Product Name", "Description", "Category", "Brand", "Gender", "Age", "Status", "Qty"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(5);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(7).setPreferredWidth(2);
        }

        jLabel7.setText("Search by category");

        jLabel8.setText("Search by Brand");

        jLabel9.setText("Search by Gender");

        jLabel10.setText("Search by Age");

        jTextField2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField2KeyReleased(evt);
            }
        });

        jComboBox8.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox8ItemStateChanged(evt);
            }
        });

        jComboBox9.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox9ItemStateChanged(evt);
            }
        });

        jComboBox10.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox10ItemStateChanged(evt);
            }
        });

        jComboBox11.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox11ItemStateChanged(evt);
            }
        });

        jLabel13.setText("Avalability");

        jComboBox5.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox5ItemStateChanged(evt);
            }
        });

        jLabel16.setText("Search by Name");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 836, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(65, 65, 65)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox8, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox9, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jComboBox10, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jComboBox11, 0, 179, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jComboBox10, jComboBox11, jComboBox8, jComboBox9});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel13, jLabel7, jLabel8});

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel16, jLabel9});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(jComboBox8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jComboBox9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel13)
                            .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(jComboBox10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(jComboBox11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jComboBox10, jComboBox11, jComboBox5, jComboBox8, jComboBox9, jLabel10, jLabel13, jLabel16, jLabel7, jLabel8, jLabel9, jTextField2});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox5ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox5ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox5ItemStateChanged

    private void jComboBox11ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox11ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox11ItemStateChanged

    private void jComboBox10ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox10ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox10ItemStateChanged

    private void jComboBox9ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox9ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox9ItemStateChanged

    private void jComboBox8ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox8ItemStateChanged
        loadTable();
    }//GEN-LAST:event_jComboBox8ItemStateChanged

    private void jTextField2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField2KeyReleased
        loadTable();
    }//GEN-LAST:event_jTextField2KeyReleased

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();
            String pId = "";
            String pName = jTable1.getValueAt(selectedRow, 0).toString();
            String pDescription = jTable1.getValueAt(selectedRow, 1).toString();
            String pCategory = jTable1.getValueAt(selectedRow, 2).toString();
            String pBrand = jTable1.getValueAt(selectedRow, 3).toString();
            String pGender = jTable1.getValueAt(selectedRow, 4).toString();
            String pAge = jTable1.getValueAt(selectedRow, 5).toString();
            String pStatus = jTable1.getValueAt(selectedRow, 6).toString();

            try {
                ResultSet rs = MySql.search("SELECT * FROM `product` WHERE name = '" + pName + "'");
                rs.next();
                pId = rs.getString("product.id");
            } catch (Exception e) {
            }

            jLabel12.setText(pId);
            jTextField1.setText(pName);
            jTextArea1.setText(pDescription);
            jComboBox1.setSelectedItem(pCategory);
            jComboBox2.setSelectedItem(pBrand);
            jComboBox3.setSelectedItem(pGender);
            jComboBox4.setSelectedItem(pAge);

            if (pStatus.equals("Disabled")) {
                jButton4.setText("Click to Enable");
                jButton4.setBackground(Color.GREEN);
            } else {
                jButton4.setText("Click to Disable");
                jButton4.setBackground(Color.RED);
            }

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed

        if (jLabel12.getText().equals("-")) {
            JOptionPane.showMessageDialog(this, "please select product and try again !", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else {
            if (jButton4.getText().equals("Click to Enable")) {
                MySql.iud("UPDATE `product` SET `availability` = (SELECT id FROM availability WHERE availability.`name` = 'Enabled') WHERE product.id = '" + jLabel12.getText() + "'");
                jButton4.setText("Click to Disable");
                jButton4.setBackground(Color.RED);
            } else {
                MySql.iud("UPDATE `product` SET `availability` = (SELECT id FROM availability WHERE availability.`name` = 'Disabled') WHERE product.id = '" + jLabel12.getText() + "'");
                jButton4.setText("Click to Enable");
                jButton4.setBackground(Color.GREEN);
            }
            loadTable();
        }
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        new AddNewAge(this, true).setVisible(true);
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        new AddNewBrand(this, true).setVisible(true);
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        new AddNewGender(this, true).setVisible(true);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        new AddNewCategory(this, true).setVisible(true);
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        clearAll();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String pId = jLabel12.getText();
        String pName = jTextField1.getText();
        String pDescription = jTextArea1.getText();
        String pCategory = jComboBox1.getSelectedItem().toString();
        String pBrand = jComboBox2.getSelectedItem().toString();
        String pGender = jComboBox3.getSelectedItem().toString();
        String pAge = jComboBox4.getSelectedItem().toString();

        if (pId.equals("-")) {
            JOptionPane.showMessageDialog(this, "please select the first", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "please add name for product", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pDescription.isEmpty()) {
            JOptionPane.showMessageDialog(this, "please add description for product", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pCategory.equals("Select")) {
            JOptionPane.showMessageDialog(this, "please select product category", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pBrand.equals("Select")) {
            JOptionPane.showMessageDialog(this, "please select product brand", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pGender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "please select gender for product", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else if (pGender.equals("Select")) {
            JOptionPane.showMessageDialog(this, "please select age range for product", "Missing Information", JOptionPane.WARNING_MESSAGE);
        } else {
            MySql.iud("UPDATE `product` SET `name` = '" + pName + "' , `description` = '" + pDescription + "' , `category_id` = (SELECT id FROM category WHERE category.`name` = '" + pCategory + "') , `brand_id` = (SELECT id FROM brand WHERE brand.`name` = '" + pBrand + "') , `gender_id` = (SELECT id FROM gender WHERE gender.`name` = '" + pGender + "') , `age_id` = (SELECT id FROM age WHERE age.`name` = '" + pAge + "') WHERE product.id = '" + pId + "'");
            JOptionPane.showMessageDialog(this, "product updated successfully !", "Sucess", JOptionPane.INFORMATION_MESSAGE);
            loadTable();
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(Management.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Management.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Management.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Management.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Management().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox10;
    private javax.swing.JComboBox<String> jComboBox11;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JComboBox<String> jComboBox5;
    private javax.swing.JComboBox<String> jComboBox8;
    private javax.swing.JComboBox<String> jComboBox9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    // End of variables declaration//GEN-END:variables

}
