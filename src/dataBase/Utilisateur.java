package dataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTable;

import java.awt.Component;
import java.awt.Dimension;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import Application.BDD;
import Application.Parameter;
import Application.ResultSetTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;

public class Utilisateur extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtIdusertxt;
	private JTextField txtUsernametxt;
	private JTextField txtPasswordtxt;
	private JTextField txtRecherchertxt;
	
	ResultSet rs;
	BDD db;
	private JComboBox typec;;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Utilisateur frame = new Utilisateur();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public void table() {
		String a []={"id","id_user","username","password","type"};
		rs= db.querySelect(a,"utilisateur");
		table.setModel(new ResultSetTableModel(rs));
	}
	
	public void actualiser(){
		txtIdusertxt.setText("");
		txtUsernametxt.setText("");
		txtPasswordtxt.setText("");
		typec.setSelectedItem("type");
		
	}
	public Utilisateur() {
		db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB, new Parameter().IP_HOST,
				new Parameter().PORT);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 658, 472);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUtilisateur = new JLabel("Utilisateur");
		lblUtilisateur.setBounds(288, 0, 90, 15);
		contentPane.add(lblUtilisateur);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 55, 607, 174);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtIdusertxt.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),1)));
				txtUsernametxt.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),2)));
				txtPasswordtxt.setText(String.valueOf(table.getValueAt(table.getSelectedRow(),3)));
				typec.setSelectedItem(String.valueOf(table.getValueAt(table.getSelectedRow(),4)));
			}
		});
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"id", "id_user", "Username", "MotDePasse", "Type"
			}
		) {
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class, String.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(27);
		table.getColumnModel().getColumn(1).setPreferredWidth(48);
		table.getColumnModel().getColumn(2).setPreferredWidth(155);
		table.getColumnModel().getColumn(3).setPreferredWidth(157);
		table.getColumnModel().getColumn(4).setPreferredWidth(125);
		scrollPane.setViewportView(table);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdusertxt.getText().equals("") || txtUsernametxt.getText().equals("") || txtPasswordtxt.getText().equals("")
						|| typec.getSelectedItem().equals("type")){
					JOptionPane.showMessageDialog(null,"Please Complete write ALL Informations ");
				}else {
					String [] colon ={"id_user","username","password","type"};
					String [] inf = {txtIdusertxt.getText(), txtUsernametxt.getText(),txtPasswordtxt.getText(),typec.getSelectedItem().toString()};
					System.out.println(Arrays.toString(colon));
					System.out.println(Arrays.toString(inf));
					
					System.out.println(db.queryInsert("utilisateur",colon,inf));
					table();
					actualiser();
				}
				
			}
		});
		btnAjouter.setBounds(25, 403, 117, 25);
		contentPane.add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtIdusertxt.getText().equals("") || txtUsernametxt.getText().equals("") || txtPasswordtxt.getText().equals("")
						|| typec.getSelectedItem().equals("type")){
					JOptionPane.showMessageDialog(null,"Please Choise after updating  ");
				}else {
				
					String [] colon ={"id_user","username","password","type"};
					String [] inf1 = {txtIdusertxt.getText(), txtUsernametxt.getText(),txtPasswordtxt.getText(),typec.getSelectedItem().toString()};
					String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
					System.out.println(txtIdusertxt.getClass().getName());
					System.out.println(Arrays.toString(colon));
					System.out.println(Arrays.toString(inf1)+id);
					System.out.println(db.queryUpdate("utilisateur", colon, inf1,"id='"+id+"'"));
					table();
					actualiser();
				}
			}
		});
		btnModifier.setBounds(154, 403, 117, 25);
		contentPane.add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
				if (JOptionPane.showConfirmDialog(null, "Do You Really Want to DELETE this !!!","ATTENTION !!!",
						JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION){
					db.queryDelete("utilisateur", "id = "+id);
				}else {
					return; 
				}
				table();
			}
		});
		btnSupprimer.setBounds(515, 241, 117, 25);
		contentPane.add(btnSupprimer);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actualiser();
				table();
			}
		});
		btnActualiser.setBounds(288, 241, 117, 25);
		contentPane.add(btnActualiser);
		
		JLabel lblIduser = new JLabel("Id_User");
		lblIduser.setBounds(39, 261, 70, 15);
		contentPane.add(lblIduser);
		
		JLabel lblUsername = new JLabel("Username ");
		lblUsername.setBounds(25, 292, 99, 15);
		contentPane.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(39, 330, 70, 15);
		contentPane.add(lblPassword);
		
		JLabel lblType = new JLabel("Type");
		lblType.setBounds(54, 357, 70, 15);
		contentPane.add(lblType);
		
		txtIdusertxt = new JTextField();
		txtIdusertxt.setBounds(142, 259, 114, 19);
		contentPane.add(txtIdusertxt);
		txtIdusertxt.setColumns(10);
		
		txtUsernametxt = new JTextField();
		txtUsernametxt.setBounds(142, 290, 114, 19);
		contentPane.add(txtUsernametxt);
		txtUsernametxt.setColumns(10);
		
		txtPasswordtxt = new JTextField();
		txtPasswordtxt.setBounds(142, 328, 114, 19);
		contentPane.add(txtPasswordtxt);
		txtPasswordtxt.setColumns(10);
		
		typec = new JComboBox();
		typec.setModel(new DefaultComboBoxModel(new String[] {"directeur", "cachier", "a"}));
		typec.setBounds(152, 352, 104, 24);
		contentPane.add(typec);
		
		txtRecherchertxt = new JTextField();
		txtRecherchertxt.setBounds(388, 24, 114, 19);
		contentPane.add(txtRecherchertxt);
		txtRecherchertxt.setColumns(10);
		
		JComboBox typecombox = new JComboBox();
		typecombox.setModel(new DefaultComboBoxModel(new String[] {"id_user", "username", "password", "type"}));
		typecombox.setBounds(274, 21, 104, 24);
		contentPane.add(typecombox);
		
		JLabel lblRechercherParCategorie = new JLabel("Rechercher par categorie");
		lblRechercherParCategorie.setBounds(54, 24, 207, 15);
		contentPane.add(lblRechercherParCategorie);
		
		JButton btnRechercher = new JButton("Rechercher");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (txtRecherchertxt.getText().equals(""))
					JOptionPane.showMessageDialog(null,"PLZ Write something to LOOKING FOR ");
				else {
					if (typecombox.getSelectedItem().equals("id_user")){
						rs = db.querySelectAll("utilisateur","id_user LIKE '%"+txtRecherchertxt.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
					else if (typecombox.getSelectedItem().equals("username")){
						rs = db.querySelectAll("utilisateur","username LIKE '%"+txtRecherchertxt.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
					else if (typecombox.getSelectedItem().equals("password")){
						rs = db.querySelectAll("utilisateur","password LIKE '%"+txtRecherchertxt.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
					else if (typecombox.getSelectedItem().equals("type")){
						rs = db.querySelectAll("utilisateur","type LIKE '%"+txtRecherchertxt.getText()+"'");
						table.setModel(new ResultSetTableModel(rs));
					}
				}
			}
		});
		btnRechercher.setBounds(515, 24, 117, 25);
		contentPane.add(btnRechercher);
		
		JLabel lblCopyrigthBadrMoumoud = new JLabel("Copyrigth@ Badr MOUMOUD");
		lblCopyrigthBadrMoumoud.setForeground(Color.CYAN);
		lblCopyrigthBadrMoumoud.setBounds(288, 413, 209, 15);
		contentPane.add(lblCopyrigthBadrMoumoud);
		
		JButton btnRetourAuMenu = new JButton("Retour au Menu");
		btnRetourAuMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Principale pr = new Principale();
				pr.setVisible(true);
				dispose();
			}
		});
		btnRetourAuMenu.setBounds(495, 352, 151, 25);
		contentPane.add(btnRetourAuMenu);
		
		table();
	}
}
