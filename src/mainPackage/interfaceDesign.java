package mainPackage;
import java.sql.*;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.JButton;
import java.awt.Dimension;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class interfaceDesign {
    private JFrame frame;
    private JTextField txtPname;
    private JTextField txtQuantity;
    private JTextField txtPrice;
    private JTextField txtsearchid;
    private JTable table_1;
    private JTextField txtSellid;
    private JTextField txtsellQuantity;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    interfaceDesign window = new interfaceDesign();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public interfaceDesign() {
        initialize();
        buildConnection();
        loadTable();
    }
    
    Connection con;
    PreparedStatement prestm;
    ResultSet rst;
    
    public void buildConnection() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/practise", "root", "abcd");
            System.out.println("Done with the stable connection");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public void loadTable() {
        try {
            prestm = con.prepareStatement("select * from inventorytable");
            rst = prestm.executeQuery();
            table_1.setModel(DbUtils.resultSetToTableModel(rst));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1098, 765);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("INVENTORY MANAGEMENT SYSTEM");
        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblNewLabel.setBounds(294, 10, 550, 37);
        frame.getContentPane().add(lblNewLabel);

        // Add Items panel
        JPanel panel = new JPanel();
        Font titleFont = new Font("Tahoma", Font.BOLD, 18);
        TitledBorder titledBorder = new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Add Items", TitledBorder.LEADING, TitledBorder.TOP, titleFont, new Color(0, 0, 0));
        panel.setBorder(titledBorder);
        panel.setBounds(45, 57, 409, 278);
        frame.getContentPane().add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_1 = new JLabel("Product Name");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1.setBounds(10, 36, 143, 37);
        panel.add(lblNewLabel_1);

        JLabel lblNewLabel_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1.setBounds(10, 98, 143, 37);
        panel.add(lblNewLabel_1_1);

        txtPname = new JTextField();
        txtPname.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtPname.setBounds(180, 36, 181, 37);
        panel.add(txtPname);
        txtPname.setColumns(10);

        txtQuantity = new JTextField();
        txtQuantity.setColumns(10);
        txtQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtQuantity.setBounds(180, 98, 181, 37);
        panel.add(txtQuantity);

        txtPrice = new JTextField();
        txtPrice.setColumns(10);
        txtPrice.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtPrice.setBounds(180, 169, 181, 37);
        panel.add(txtPrice);

        JButton btnAddItem = new JButton("Add item");
        btnAddItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name, quantity, price;
                
                name = txtPname.getText();
                quantity = txtQuantity.getText();
                price = txtPrice.getText();
                
                try {
                    prestm = con.prepareStatement("insert into inventorytable(ProductName, Quantity, PricePerItem)values(?,?,?)");
                    
                    prestm.setString(1, name);
                    prestm.setString(2, quantity);
                    prestm.setString(3, price);
                    
                    prestm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Item Added!!");
                    
                    loadTable();
                    
                    txtPname.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    
                    txtPname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        
        btnAddItem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnAddItem.setBounds(37, 234, 116, 29);
        panel.add(btnAddItem);

        JButton btnClear = new JButton("Clear");
        btnClear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                txtPname.setText("");
                txtQuantity.setText("");
                txtPrice.setText("");
                txtPname.requestFocus();
            }
        });
        btnClear.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnClear.setBounds(215, 234, 116, 29);
        panel.add(btnClear);
        
        JLabel lblNewLabel_1_1_2 = new JLabel("Price per Item");
        lblNewLabel_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_2.setBounds(10, 169, 143, 37);
        panel.add(lblNewLabel_1_1_2);

        // Search Item panel
        Font titleFontSearchItem = new Font("Tahoma", Font.BOLD, 18);
        TitledBorder titledBorderSearchItem = new TitledBorder(null, "Search item", TitledBorder.LEADING, TitledBorder.TOP, titleFontSearchItem, null);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(titledBorderSearchItem);
        panel_1.setBounds(45, 363, 409, 149);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);

        JLabel lblNewLabel_1_1_1_2 = new JLabel("Enter Product ID");
        lblNewLabel_1_1_1_2.setBounds(10, 34, 158, 25);
        lblNewLabel_1_1_1_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
        panel_1.add(lblNewLabel_1_1_1_2);

        txtsearchid = new JTextField();
        txtsearchid.setColumns(10);
        txtsearchid.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtsearchid.setBounds(188, 32, 181, 37);
        panel_1.add(txtsearchid);

        JButton btnSearch = new JButton(" Search In Inventory");
        btnSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchId = txtsearchid.getText();
                
                try {
                    prestm = con.prepareStatement("select * from inventorytable where ID=?");
                    prestm.setString(1, searchId);
                    rst = prestm.executeQuery();
                    
                    if(rst.next()) {
                        txtPname.setText(rst.getString(2));
                        txtQuantity.setText(rst.getString(3));
                        txtPrice.setText(rst.getString(4));
                    } else {
                        txtPname.setText("");
                        txtQuantity.setText("");
                        txtPrice.setText("");
                        txtPname.requestFocus();
                    }
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnSearch.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSearch.setBounds(89, 87, 205, 52);
        panel_1.add(btnSearch);
        
        Font modifySearchItem = new Font("Tahoma", Font.BOLD, 18);
        TitledBorder modifyBorderSearchItem = new TitledBorder(null, "Modify Records", TitledBorder.LEADING, TitledBorder.TOP, modifySearchItem, null);

        JPanel panel_2 = new JPanel();
        panel_2.setBorder(modifyBorderSearchItem);
        panel_2.setBounds(45, 536, 409, 160);
        frame.getContentPane().add(panel_2);
        panel_2.setLayout(null);
        
        JButton btnUpdate = new JButton("Update");
        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name, quantity, price, id;
                
                name = txtPname.getText();
                quantity = txtQuantity.getText();
                price = txtPrice.getText();
                id = txtsearchid.getText();
                
                try {
                    prestm = con.prepareStatement("update inventorytable set ProductName=?, Quantity=?, PricePerItem=? where ID=?");
                    
                    prestm.setString(1, name);
                    prestm.setString(2, quantity);
                    prestm.setString(3, price);
                    prestm.setString(4, id);
                    
                    prestm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Item Updated!!");
                    
                    loadTable();
                    
                    txtPname.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    txtPname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnUpdate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnUpdate.setBounds(45, 57, 127, 55);
        panel_2.add(btnUpdate);
        
        JButton btnDelete = new JButton("Delete");
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String id = txtsearchid.getText();
                
                try {
                    prestm = con.prepareStatement("delete from inventorytable where ID=?");
                    prestm.setString(1, id);
                    prestm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Item Deleted!!");
                    
                    loadTable();
                    
                    txtPname.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    txtPname.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnDelete.setBounds(249, 57, 127, 55);
        panel_2.add(btnDelete);
        
        Font f3 = new Font("Tahoma", Font.BOLD, 18);
        TitledBorder f33 = new TitledBorder(null, "Inventory", TitledBorder.LEADING, TitledBorder.TOP, f3, null);

        JPanel panel_3 = new JPanel();
        panel_3.setBorder(f33);
        panel_3.setBounds(502, 70, 550, 439);
        frame.getContentPane().add(panel_3);
        panel_3.setLayout(null);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(27, 30, 487, 375);
        panel_3.add(scrollPane);
        
        table_1 = new JTable();
        scrollPane.setViewportView(table_1);
        
        JButton btnExit = new JButton("Exit");
        btnExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnExit.setBounds(914, 26, 123, 51);
        frame.getContentPane().add(btnExit);
        
        Font f4 = new Font("Tahoma", Font.BOLD, 18);
        TitledBorder f44 = new TitledBorder(null, "Sell Items", TitledBorder.LEADING, TitledBorder.TOP, f4, null);

        JPanel panel_4 = new JPanel();
        panel_4.setBorder(f44);
        panel_4.setBounds(502, 547, 543, 149);
        frame.getContentPane().add(panel_4);
        panel_4.setLayout(null);
        
        JLabel lblNewLabel_1_1_1 = new JLabel("Enter Item Id");
        lblNewLabel_1_1_1.setBounds(23, 37, 143, 37);
        panel_4.add(lblNewLabel_1_1_1);
        lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        
        JLabel lblNewLabel_1_1_1_1 = new JLabel("Quantity");
        lblNewLabel_1_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel_1_1_1_1.setBounds(23, 91, 143, 37);
        panel_4.add(lblNewLabel_1_1_1_1);
        
        txtSellid = new JTextField();
        txtSellid.setColumns(10);
        txtSellid.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtSellid.setBounds(176, 41, 181, 37);
        panel_4.add(txtSellid);
        
        txtsellQuantity = new JTextField();
        txtsellQuantity.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String sellId = txtSellid.getText();
                String sellQuantity = txtsellQuantity.getText();
                
                if(sellId.isEmpty() || sellQuantity.isEmpty()) return;
                
                try {
                    prestm = con.prepareStatement("select * from inventorytable where ID=?");
                    prestm.setString(1, sellId);
                    rst = prestm.executeQuery();
                    
                    if(rst.next()) {
                        if(Integer.parseInt(sellQuantity) <= Integer.parseInt(rst.getString(3))) {
                            txtPname.setText(rst.getString(2));
                            txtQuantity.setText(rst.getString(3));
                            txtPrice.setText(rst.getString(4));
                        } else {
                            txtPname.setText("");
                            txtQuantity.setText("");
                            txtPrice.setText("");
                            JOptionPane.showMessageDialog(null, "Insufficient quantity!");
                        }
                    } else {
                        txtPname.setText("");
                        txtQuantity.setText("");
                        txtPrice.setText("");
                    }
                } catch (NumberFormatException | SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
        txtsellQuantity.setColumns(10);
        txtsellQuantity.setBorder(new LineBorder(new Color(0, 0, 0)));
        txtsellQuantity.setBounds(176, 91, 181, 37);
        panel_4.add(txtsellQuantity);
        
        JButton btnSellitem = new JButton("Sell");
        btnSellitem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name, quantity, price, id;
                
                name = txtPname.getText();
                quantity = txtQuantity.getText();
                price = txtPrice.getText();
                id = txtSellid.getText();
                String sellQuantity = txtsellQuantity.getText();
                
                if(id.isEmpty() || sellQuantity.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please enter ID and quantity!");
                    return;
                }
                
                Integer diffQuantity = Integer.parseInt(quantity) - Integer.parseInt(sellQuantity);
                
                try {
                    // FIXED: Changed InventoryTable to inventorytable (lowercase)
                    prestm = con.prepareStatement("update inventorytable set Quantity=? where ID=?");
                    
                    prestm.setString(1, diffQuantity.toString());
                    prestm.setString(2, id);
                    
                    prestm.executeUpdate();
                    
                    JOptionPane.showMessageDialog(null, "Items Sold!!");
                    
                    loadTable();
                    
                    txtPname.setText("");
                    txtQuantity.setText("");
                    txtPrice.setText("");
                    txtSellid.setText("");
                    txtsellQuantity.setText("");
                    
                    txtSellid.requestFocus();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error selling item: " + e1.getMessage());
                }
            }
        });
        btnSellitem.setFont(new Font("Tahoma", Font.PLAIN, 16));
        btnSellitem.setBounds(385, 49, 127, 55);
        panel_4.add(btnSellitem);
    }
}
