package dataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable.PrintMode;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import Application.BDD;
import Application.Parameter;
import Application.ResultSetTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.*;
import java.awt.print.*;

public class Cachier extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtrechercher;
	private JTextField txtcodeproduit;
	private JTextField txtreference;
	private JTextField txtrangement;
	private JTextField txtfournisseur;
	private JTextField txtremise;
	private JTextField txtnvprix;
	private JTextField txtstock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cachier frame = new Cachier();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

			ResultSet rs;
			BDD db;
			int old,dec, now;
			private JTextField txtnumfacture;
			private JTable tablevente;
			private JTextField txtcach;
			private JTextField txtprepaye;
			private JLabel lbl1;
			private JLabel lbl2;
			private JLabel lblTotal1;
			private JTextField txtprix;
			private JLabel lbltot2;
			private JLabel lblTotal;

			public void table() {
				String a []={"id","code_produit","deseignation","fournisseur","prix","reference","remise","rangement","stock"};
				rs= db.querySelect(a,"produit");
				table.setModel(new ResultSetTableModel(rs));
			}
			
			public void actualiser(){
				txtcodeproduit.setText("");
				//txtdeseignation.setText("");
				txtfournisseur.setText("");
				txtnvprix.setText("");
				txtreference.setText("");
				txtremise.setText("");
				txtrangement.setText("");
				txtstock.setText("");
				txtprix.setText("");
				/*
				txtcodeProduit.getText()
				txtdesignation.getText()
				txtfournisseur.getText()
				txtprix.getText()
				txtreference.getText()
				txtremise.getText()
				txtrangement.getText()
				txtstock.getText()
				*/
			}
			
			public void importer(){
				String colon[]={"id","num_facture","code_produit","reference","prix_vente","stock_sortie","subtotal"};
				rs = db.fsSelectCommand(colon, "vente","num_facture= '"+txtnumfacture.getText()+"'");
				tablevente.setModel(new ResultSetTableModel(rs));
			}
			
			public void jam(){
				Date s = new Date();
				SimpleDateFormat tgl = new SimpleDateFormat("EEEE-DD-MM-YYYY");
				SimpleDateFormat jam = new SimpleDateFormat("HH:mm");
				lbl1.setText(jam.format(s));
				lbl2.setText(tgl.format(s));
			}
			
			public void subtotal(){
				double a = Double.parseDouble(txtnvprix.getText());
				double b = Double.parseDouble(txtstock.getText());
				double c= a*b;
					lblTotal1.setText(String.valueOf(c));; 
			}
			
			public boolean test_stock() throws SQLException{
				boolean teststock;
				rs = db.querySelectAll("produit", "code_produit = '"+txtcodeproduit.getText()+"'");
				while (rs.next()){
					old = rs.getInt("stock");
				}
				System.out.println(old);
				dec=Integer.parseInt(txtstock.getText());
				if (old < dec){
					teststock=false;
				}
				else {
					teststock = true;
				}
				return teststock;
			}
			
			public void def()  throws SQLException{
				rs = db.querySelectAll("produit", "code_produit = '"+txtcodeproduit.getText()+"'");
				while (rs.next()){
					old = rs.getInt("stock");
				}
				dec=Integer.parseInt(txtstock.getText());
				now = old - dec;
				
				String nvstock = Integer.toString(now);
				String a = String.valueOf(nvstock);
				String [] colon = {"stock"};
				String [] inf = {a};
				System.out.println("Updating PRODUCTS");
				System.out.println(db.queryUpdate("produit", colon,inf,"code_produit = '"+txtcodeproduit.getText()+"'"));
			}
			
			public void total(){
				rs = db.executionQuery("SELECT SUM(subtotal)  as subtotal FROM vente WHERE num_facture = '"+txtnumfacture.getText()+"'");
				try{
					rs.next();
					lbltot2.setText(rs.getString("subtotal"));
					
				}
				catch (SQLException ex){
					Logger.getLogger(Cachier.class.getName()).log(Level.SEVERE,null,ex);
				//	System.out.println("\n"+ex);
					}
			}
			
			public void prepayee(){
			int a = Integer.parseInt(lbltot2.getText());
			int b = Integer.parseInt(txtcach.getText());
			int c = b-a;
			txtprepaye.setText(Integer.toString(c));
			
			}
			
			public void cout(){
				double a = Double.parseDouble(txtprix.getText());
				double b = Double.parseDouble(txtremise.getText());
				double c = a - a * ( b / 100 ) ;
				txtnvprix.setText(String.valueOf(c));
			}
			
	public Cachier() {
		db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB, new Parameter().IP_HOST,
				new Parameter().PORT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 810, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCachier = new JLabel("Cachier");
		lblCachier.setBounds(324, 0, 70, 15);
		contentPane.add(lblCachier);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(22, 134, 306, 294);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblRechercherParCategorie = new JLabel("Rechercher par categorie");
		lblRechercherParCategorie.setBounds(12, 0, 201, 15);
		panel.add(lblRechercherParCategorie);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"id", "code_produit", "reference", "deseignation", "rangement", "fournisseur", "remise", "prix", "stock"}));
		comboBox.setBounds(2, 18, 77, 24);
		panel.add(comboBox);
		
		txtrechercher = new JTextField();
		txtrechercher.setBounds(80, 20, 163, 19);
		panel.add(txtrechercher);
		txtrechercher.setColumns(10);
		
		JButton OK = new JButton("OK");
		OK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtrechercher.getText().equals(""))
					JOptionPane.showMessageDialog(null,"PLZ Write something to LOOKING FOR ");
				else {
				if (comboBox.getSelectedItem().equals("id")){
					rs = db.querySelectAll("produit","id LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
				}else if (comboBox.getSelectedItem().equals("code_produit")){
					rs = db.querySelectAll("produit","code_produit LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("reference")){
					rs = db.querySelectAll("produit","reference LIKE '%"+txtrechercher.getText()+"'");
					
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("deseignation")){
					rs = db.querySelectAll("produit","deseignation LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("rangement")){
					rs = db.querySelectAll("produit","rangement LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("fournisseur")){
					rs = db.querySelectAll("produit","fournisseur LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("remise")){
					rs = db.querySelectAll("produit","remise LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
					
				}else if (comboBox.getSelectedItem().equals("prix")){
					rs = db.querySelectAll("produit","prix LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
				}
				else if (comboBox.getSelectedItem().equals("stock")){
					rs = db.querySelectAll("produit","stock LIKE '%"+txtrechercher.getText()+"'");
					table.setModel(new ResultSetTableModel(rs));
				}
				}
			}
		});
		OK.setBounds(246, 18, 54, 24);
		panel.add(OK);
		
		JLabel lblCodeproduit = new JLabel("codeProdINT");
		lblCodeproduit.setBounds(9, 46, 97, 15);
		panel.add(lblCodeproduit);
		
		txtcodeproduit = new JTextField();
		txtcodeproduit.setBounds(127, 46, 114, 19);
		panel.add(txtcodeproduit);
		txtcodeproduit.setColumns(10);
		
		JLabel lblReference = new JLabel("reference");
		lblReference.setBounds(9, 68, 100, 15);
		panel.add(lblReference);
		
		txtreference = new JTextField();
		txtreference.setBounds(127, 68, 114, 19);
		panel.add(txtreference);
		txtreference.setColumns(10);
		
		txtrangement = new JTextField();
		txtrangement.setBounds(127, 90, 114, 19);
		panel.add(txtrangement);
		txtrangement.setColumns(10);
		
		txtfournisseur = new JTextField();
		txtfournisseur.setBounds(127, 112, 114, 19);
		panel.add(txtfournisseur);
		txtfournisseur.setColumns(10);
		
		txtremise = new JTextField();
		txtremise.setBounds(127, 156, 114, 19);
		panel.add(txtremise);
		txtremise.setColumns(10);
		
		txtnvprix = new JTextField();
		txtnvprix.setBounds(127, 178, 114, 19);
		panel.add(txtnvprix);
		txtnvprix.setColumns(10);
		
		txtstock = new JTextField();
		txtstock.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				subtotal();
			}
			
		});
		txtstock.setBounds(127, 200, 114, 19);
		panel.add(txtstock);
		txtstock.setColumns(10);
		
		JLabel lblRangement = new JLabel("rangement");
		lblRangement.setBounds(9, 90, 97, 15);
		panel.add(lblRangement);
		
		JLabel lblFournisseur = new JLabel("fournisseur");
		lblFournisseur.setBounds(9, 112, 97, 15);
		panel.add(lblFournisseur);
		
		JLabel lblRemise = new JLabel("remise");
		lblRemise.setBounds(9, 156, 70, 15);
		panel.add(lblRemise);
		
		JLabel lblPrix = new JLabel("NV prix");
		lblPrix.setBounds(9, 178, 70, 15);
		panel.add(lblPrix);
		
		JLabel lblStock = new JLabel("stock");
		lblStock.setBounds(9, 200, 70, 15);
		panel.add(lblStock);
		
		JLabel lblRp = new JLabel("RP");
		lblRp.setBounds(12, 222, 70, 15);
		panel.add(lblRp);
		
		JButton btnAjouterAuProduit = new JButton("Ajouter au Produit");
		btnAjouterAuProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (txtcodeproduit.getText().equals("") ||  txtfournisseur.getText().equals("")
						|| txtprix.getText().equals("") || txtreference.getText().equals("") || txtremise.getText().equals("")
						|| txtrangement.getText().equals("")||txtnvprix.getText().equals("") || txtstock.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please Complete write ALL Informations ");
					}else if (txtnumfacture.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please ENTREZ le Numero du FACTURE");
					}
				else {
					String [] colon ={"num_facture","code_produit","reference","prix_vente","stock_sortie","subtotal"};
					String [] inf = {txtnumfacture.getText(), txtcodeproduit.getText(),txtreference.getText()
							,txtnvprix.getText(),txtstock.getText(),lblTotal1.getText()};	
					System.out.println("**********************************************************");
				//	System.out.println(db.queryInsert("vente",colon,inf));
					System.out.println("**********************************************************");
						try{
							if (!test_stock())
							{
								JOptionPane.showMessageDialog(null,"STCOK LIMITEE");
								System.out.println("STOCK LIMITE");
							}
							else {
								System.out.println(db.queryInsert("vente",colon,inf));
								def();
								table();
							}
						}
						catch (SQLException ex){
							Logger.getLogger(Cachier.class.getName()).log(Level.SEVERE,null,ex);
							System.out.println("\n"+ex);
						}
						subtotal();
						importer();
						total();
					}
			}
		});
		btnAjouterAuProduit.setBounds(82, 249, 187, 25);
		panel.add(btnAjouterAuProduit);
		
		JLabel lblNouveauPrix = new JLabel("prix");
		lblNouveauPrix.setBounds(9, 134, 97, 15);
		panel.add(lblNouveauPrix);
		
		txtprix = new JTextField();
		txtprix.setColumns(10);
		txtprix.setBounds(127, 134, 114, 19);
		panel.add(txtprix);
		
		lblTotal1 = new JLabel("TOTAL : ");
		lblTotal1.setBounds(172, 222, 70, 15);
		panel.add(lblTotal1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.DARK_GRAY);
		panel_1.setBounds(12, 12, 719, 118);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 12, 695, 94);
		panel_1.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtcodeproduit.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),1)));
				txtfournisseur.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
				txtprix.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),4)));
				txtreference.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),5)));
				txtremise.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),6)));
				txtrangement.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),7)));
				cout();
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null},
			},
			new String[] {
				"codeProduit", "reference", "deseignation", "rangement", "fournisseur", "remise", "prix", "stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(90);
		table.getColumnModel().getColumn(2).setPreferredWidth(87);
		scrollPane.setViewportView(table);
		
		JLabel lblNmfct = new JLabel("NmFct");
		lblNmfct.setBounds(343, 134, 70, 15);
		contentPane.add(lblNmfct);
		
		txtnumfacture = new JTextField();
		txtnumfacture.setBounds(401, 134, 114, 19);
		contentPane.add(txtnumfacture);
		txtnumfacture.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				importer();
				total();
			}
		});
		btnRechercher.setBounds(515, 129, 115, 25);
		contentPane.add(btnRechercher);
		
		JButton btnDel = new JButton("DELETE");
		btnDel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 String id = String.valueOf(tablevente.getValueAt(tablevente.getSelectedRow(), 0));
			        if (JOptionPane.showConfirmDialog(null, "est ce que tu es sure que tu veux supprimer ", "Attention", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
			            db.queryDelete("vente", "id=" + id);
			        } else {
			            return;
			        }
			        importer();
			        total();
			}
		});
		btnDel.setBounds(634, 129, 95, 25);
		contentPane.add(btnDel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(330, 161, 453, 108);
		contentPane.add(scrollPane_1);
		
		tablevente = new JTable();
		tablevente.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null},
			},
			new String[] {
				"id", "num_facture", "code_produit", "reference", "prix_vente", "stock_sortie", "subtotal"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, Integer.class, String.class, Integer.class, Integer.class, Integer.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tablevente.getColumnModel().getColumn(1).setPreferredWidth(94);
		tablevente.getColumnModel().getColumn(2).setPreferredWidth(93);
		tablevente.getColumnModel().getColumn(5).setPreferredWidth(87);
		scrollPane_1.setViewportView(tablevente);
		
		lbl1 = new JLabel("");
		lbl1.setBounds(515, 393, 70, 15);
		contentPane.add(lbl1);
		
		lbl2 = new JLabel("");
		lbl2.setBounds(569, 393, 144, 15);
		contentPane.add(lbl2);
		
		JLabel lblCach = new JLabel("Cach");
		lblCach.setBounds(343, 308, 70, 15);
		contentPane.add(lblCach);
		
		txtcach = new JTextField();
		txtcach.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				prepayee();
			}
		});
		txtcach.setBounds(401, 308, 114, 19);
		contentPane.add(txtcach);
		txtcach.setColumns(10);
		
		JLabel lblPrepayee = new JLabel("Prepayee");
		lblPrepayee.setBounds(552, 308, 70, 15);
		contentPane.add(lblPrepayee);
		
		txtprepaye = new JTextField();
		txtprepaye.setBounds(633, 308, 114, 19);
		contentPane.add(txtprepaye);
		txtprepaye.setColumns(10);
		
		JButton btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MessageFormat header = new MessageFormat("Facture");
				MessageFormat footer =  new MessageFormat("page");
				
				try {
					tablevente.print(PrintMode.NORMAL,footer,header);
						//	print(JTable.PrintMode.FIT_WIDTH,footer, header);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null,"Please ENTREZ le Numero du FACTURE");
				}
			}
		});
		btnImprimer.setBounds(513, 339, 117, 25);
		contentPane.add(btnImprimer);
		
		JButton btnAnnuler = new JButton("Annuler");
		btnAnnuler.setBounds(643, 339, 117, 25);
		contentPane.add(btnAnnuler);
		
		lbltot2 = new JLabel("0");
		lbltot2.setBounds(643, 270, 70, 15);
		contentPane.add(lbltot2);
		
		lblTotal = new JLabel("0");
		lblTotal.setBounds(363, 366, 173, 15);
		contentPane.add(lblTotal);
		
		JLabel lblNewLabel = new JLabel("Copyrigth@ Badr MOUMOUD");
		lblNewLabel.setForeground(Color.CYAN);
		lblNewLabel.setBounds(346, 413, 212, 15);
		contentPane.add(lblNewLabel);
		
		JButton btnRetourAuMenu = new JButton("Retour au MENU");
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principale pr = new Principale();
				pr.setVisible(true);
				dispose();
			}
		});
		btnRetourAuMenu.setBounds(579, 413, 168, 15);
		contentPane.add(btnRetourAuMenu);
		table();
		jam();
	}
}

