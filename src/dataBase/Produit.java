package dataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import Application.BDD;
import Application.Parameter;
import Application.ResultSetTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Produit extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtcodeProduit;
	private JTextField txtreference;
	private JTextField txtdesignation;
	private JTextField txtfournisseur;
	private JTextField txtremise;
	private JTextField txtprix;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Produit frame = new Produit();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	ResultSet rs;
	BDD db ;
	private JTextField txtrangement;
	private JTextField txtstock;
	private JTextField txtrechercher;
	private JComboBox typerech;
	
	public void table() {
		String a []={"id","code_produit","deseignation","fournisseur","prix","reference","remise","rangement","stock"};
		rs= db.querySelect(a,"produit");
		table.setModel(new ResultSetTableModel(rs));
	}
	
	public void actualiser(){
		txtcodeProduit.setText("");
		txtdesignation.setText("");
		txtfournisseur.setText("");
		txtprix.setText("");
		txtreference.setText("");
		txtremise.setText("");
		txtrangement.setText("");
		txtstock.setText("");
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
	
	public Produit() {
		db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB, new Parameter().IP_HOST,
				new Parameter().PORT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 743, 464);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblProduit = new JLabel("Produit");
		lblProduit.setBounds(354, 12, 70, 15);
		contentPane.add(lblProduit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 24, 719, 137);
		contentPane.add(scrollPane);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setColumnHeaderView(tabbedPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtcodeProduit.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),1)));
				txtdesignation.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
				txtfournisseur.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
				txtprix.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),4)));
				txtreference.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),5)));
				txtremise.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),6)));
				txtrangement.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),7)));
				txtstock.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),8)));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
				{null, null, null, null, null, null, null, null, null},
			},
			new String[] {
				"id", "codeProduit", "reference", "designation", "rangement", "fournisseur", "remise", "prix", "stock"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, String.class, String.class, String.class, String.class, String.class, Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(30);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(82);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(94);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);
		table.getColumnModel().getColumn(8).setResizable(false);
		scrollPane.setViewportView(table);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcodeProduit.getText().equals("") || txtdesignation.getText().equals("") || txtfournisseur.getText().equals("")
					|| txtprix.getText().equals("") || txtreference.getText().equals("") || txtremise.getText().equals("")
					|| txtrangement.getText().equals("") || txtstock.getText().equals("")){
					JOptionPane.showMessageDialog(null,"Please Complete write ALL Informations ");
				}else {
					String [] colon ={"code_produit","deseignation","fournisseur","prix","reference","remise","rangement","stock"};
				String [] inf = {txtcodeProduit.getText(),txtdesignation.getText(),txtfournisseur.getText()
						,txtprix.getText(),txtreference.getText(),txtremise.getText()
					,txtrangement.getText(),txtstock.getText()};
					System.out.println(Arrays.toString(colon));
					System.out.println(Arrays.toString(inf));
					
					System.out.println(db.queryInsert("produit",colon,inf));
					table();
					actualiser();
				}
			}
		});
		btnAjouter.setBounds(22, 178, 117, 25);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtcodeProduit.getText().equals("") || txtdesignation.getText().equals("") || txtfournisseur.getText().equals("")
						|| txtprix.getText().equals("") || txtreference.getText().equals("") || txtremise.getText().equals("")
						|| txtrangement.getText().equals("") || txtstock.getText().equals("")){
						JOptionPane.showMessageDialog(null,"Please Complete write ALL Informations ");
					}else {
				
					String [] colon ={"code_produit","deseignation","fournisseur","prix","reference","remise","rangement","stock"};
					String [] inf = {txtcodeProduit.getText(),txtdesignation.getText(),txtfournisseur.getText()
							,txtprix.getText(),txtreference.getText(),txtremise.getText()
						,txtrangement.getText(),txtstock.getText()};
						System.out.println(Arrays.toString(colon));
						System.out.println(Arrays.toString(inf));
						
					String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));

					System.out.println(db.queryUpdate("produit", colon, inf,"id='"+id+"'"));
					table();
					actualiser();
				}
			}
		});
		btnModifier.setBounds(151, 178, 117, 25);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				if (JOptionPane.showConfirmDialog(null, "Do You Really Want to DELETE this !!!","ATTENTION !!!",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					db.queryDelete("produit", "id = "+id);
				}else {
					return; 
				}
				table();
			}
		});
		btnSupprimer.setBounds(283, 178, 117, 25);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
				table();
			}
		});
		btnActualiser.setBounds(420, 178, 117, 25);
		contentPane.add(btnActualiser);
		
		JLabel lblCodeproduit = new JLabel("codeProduitSTR");
		lblCodeproduit.setBounds(12, 226, 127, 17);
		contentPane.add(lblCodeproduit);
		
		JLabel lblreference = new JLabel("reference");
		lblreference.setBounds(30, 253, 88, 15);
		contentPane.add(lblreference);
		
		JLabel lbldesignation = new JLabel("designation");
		lbldesignation.setBounds(32, 283, 88, 15);
		contentPane.add(lbldesignation);
		
		txtcodeProduit = new JTextField();
		txtcodeProduit.setBounds(151, 226, 114, 19);
		contentPane.add(txtcodeProduit);
		txtcodeProduit.setColumns(10);
		
		txtreference = new JTextField();
		txtreference.setBounds(151, 253, 114, 19);
		contentPane.add(txtreference);
		txtreference.setColumns(10);
		
		txtdesignation = new JTextField();
		txtdesignation.setBounds(151, 281, 114, 19);
		contentPane.add(txtdesignation);
		txtdesignation.setColumns(10);
		
		JLabel lblFournisseur = new JLabel("fournisseur");
		lblFournisseur.setBounds(293, 226, 107, 15);
		contentPane.add(lblFournisseur);
		
		txtfournisseur = new JTextField();
		txtfournisseur.setBounds(400, 226, 114, 19);
		contentPane.add(txtfournisseur);
		txtfournisseur.setColumns(10);
		
		JLabel lblRemise = new JLabel("remiseINT");
		lblRemise.setBounds(290, 253, 70, 15);
		contentPane.add(lblRemise);
		
		txtremise = new JTextField();
		txtremise.setBounds(400, 249, 114, 19);
		contentPane.add(txtremise);
		txtremise.setColumns(10);
		
		JLabel lblPrix = new JLabel("prixINT");
		lblPrix.setBounds(293, 283, 70, 15);
		contentPane.add(lblPrix);
		
		txtprix = new JTextField();
		txtprix.setBounds(400, 283, 114, 19);
		contentPane.add(txtprix);
		txtprix.setColumns(10);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtrechercher.getText().equals(""))
					JOptionPane.showMessageDialog(null,"PLZ Write something to LOOKING FOR ");
				else {
					if (typerech.getSelectedItem().equals("id")){
						rs = db.querySelectAll("produit","id LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}else if (typerech.getSelectedItem().equals("code_produit")){
						rs = db.querySelectAll("produit","code_produit LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("reference")){
						rs = db.querySelectAll("produit","reference LIKE '%"+txtrechercher.getText()+"'");
						
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("deseignation")){
						rs = db.querySelectAll("produit","deseignation LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("rangement")){
						rs = db.querySelectAll("produit","rangement LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("fournisseur")){
						rs = db.querySelectAll("produit","fournisseur LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("remise")){
						rs = db.querySelectAll("produit","remise LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
						
					}else if (typerech.getSelectedItem().equals("prix")){
						rs = db.querySelectAll("produit","prix LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
					else if (typerech.getSelectedItem().equals("stock")){
						rs = db.querySelectAll("produit","stock LIKE '%"+txtrechercher.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
					}
			}
				});
		btnRechercher.setBounds(582, 278, 122, 25);
		contentPane.add(btnRechercher);
		
		JLabel lblRangement = new JLabel("rangement");
		lblRangement.setBounds(22, 310, 90, 15);
		contentPane.add(lblRangement);
		
		txtrangement = new JTextField();
		txtrangement.setBounds(150, 308, 114, 19);
		contentPane.add(txtrangement);
		txtrangement.setColumns(10);
		
		JLabel lblStock = new JLabel("stockINT");
		lblStock.setBounds(283, 310, 70, 15);
		contentPane.add(lblStock);
		
		txtstock = new JTextField();
		txtstock.setBounds(400, 308, 114, 19);
		contentPane.add(txtstock);
		txtstock.setColumns(10);
		
		txtrechercher = new JTextField();
		txtrechercher.setBounds(582, 249, 114, 19);
		contentPane.add(txtrechercher);
		txtrechercher.setColumns(10);
		
		typerech = new JComboBox();
		typerech.setModel(new DefaultComboBoxModel(new String[] {"id", "code_produit", "reference", "deseignation", "rangement", "fournisseur", "remise", "prix", "stock"}));
		typerech.setBounds(595, 213, 109, 24);
		contentPane.add(typerech);
		
		JLabel lblCopyrigthBadrMoumoud = new JLabel("Copyrigth@ Badr MOUMOUD");
		lblCopyrigthBadrMoumoud.setForeground(Color.CYAN);
		lblCopyrigthBadrMoumoud.setBounds(481, 390, 223, 15);
		contentPane.add(lblCopyrigthBadrMoumoud);
		
		JButton btnRetourAuMenu = new JButton("Retour au MENU");
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principale pr = new Principale();
				pr.setVisible(true);
				dispose();
			}
		});
		btnRetourAuMenu.setBounds(110, 385, 179, 25);
		contentPane.add(btnRetourAuMenu);
		
		table();
	}
}
