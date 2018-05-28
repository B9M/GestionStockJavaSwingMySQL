package dataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;

import java.awt.Font;

import javax.swing.JButton;

import Application.BDD;
import Application.Parameter;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;

import javax.swing.JPasswordField;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField txtName;
	private JButton btnFermer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
					//frame.dispose();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	///////////////////////////////////////////////////////////////////////

	ResultSet rs;
	BDD db;
	String username1, password1, hak;
	private JLabel lblerr1;
	private JPasswordField passwordField;
	
	public Login() {
		/////////////////////////////////////
		db = new BDD(new Parameter().HOST_DB, new Parameter().USERNAME_DB,new Parameter().PASSWORD_DB, new Parameter().IP_HOST,
				new Parameter().PORT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 346);
		//setSize(Toolkit.getDefaultToolkit().getScreenSize());// adapter al'ecran
		contentPane = new JPanel();
		contentPane.setForeground(new Color(51, 51, 51));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setFont(new Font("Dialog", Font.BOLD, 22));
		lblName.setBackground(Color.DARK_GRAY);
		lblName.setBounds(12, 74, 109, 36);
		contentPane.add(lblName);
		
		JLabel lblPassword = new JLabel("Password ");
		lblPassword.setFont(new Font("Dialog", Font.BOLD, 22));
		lblPassword.setBounds(12, 127, 131, 24);
		contentPane.add(lblPassword);
		
		txtName = new JTextField();
		txtName.setBounds(139, 83, 179, 25);
		contentPane.add(txtName);
		txtName.setColumns(10);
		
		JButton btnEntrer = new JButton("Entrer");
		btnEntrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		/////////////////////////////////////////////////////////////////////////////////
			//	rs = db.querySelectAll("utilisateur", "username = '"+txtName.getText()+"' and password= '"+txtPassword.getText()+"'");
				rs = db.querySelectAll("utilisateur", "username = '"+txtName.getText()+"' and password= '"+passwordField.getText()+"'");
				try{
					while (rs.next()){
						username1= rs.getString("username");
						password1=rs.getString("password");
						hak=rs.getString("type");
					}
				}
				catch(SQLException ex) {
					Logger.getLogger(Login.class.getName()).log(Level.SEVERE,null,ex);
				}
				
				if (username1 == null && password1 == null){
					JOptionPane.showMessageDialog(null,"User or Password Incorrect");
					lblerr1.setText("erreur De Saisie");
					lblerr1.setVisible(true);
				}
				else {
					if (hak.equals("directeur")){
						 Principale h = new Principale();
			                h.setVisible(true);
			           dispose();
					}
					else {
						 Cachier k = new Cachier();
			                k.setVisible(true);
			             dispose();
					}
				}
			}
		});
		btnEntrer.setBounds(26, 208, 117, 25);
		contentPane.add(btnEntrer);
		
		btnFermer = new JButton("Fermer");
		btnFermer.setFont(new Font("Dialog", Font.BOLD, 14));
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnFermer.setBounds(223, 208, 117, 25);
		contentPane.add(btnFermer);
		
		lblerr1 = new JLabel("");
		lblerr1.setFont(new Font("Dialog", Font.BOLD, 14));
		lblerr1.setForeground(Color.RED);
		lblerr1.setBounds(153, 58, 230, 15);
		contentPane.add(lblerr1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(139, 131, 179, 25);
		contentPane.add(passwordField);
		
		JLabel lblApplicationDeGestion = new JLabel("APPLICATION DE GESTION DE \nSTOCK");
		lblApplicationDeGestion.setForeground(Color.BLUE);
		lblApplicationDeGestion.setFont(new Font("Dialog", Font.BOLD, 18));
		lblApplicationDeGestion.setBounds(36, 12, 402, 46);
		contentPane.add(lblApplicationDeGestion);
		
		JLabel lblCopyrightBadrMoumoud = new JLabel("Copyright@ Badr MOUMOUD");
		lblCopyrightBadrMoumoud.setForeground(Color.CYAN);
		lblCopyrightBadrMoumoud.setBounds(208, 287, 230, 15);
		contentPane.add(lblCopyrightBadrMoumoud);
	}
}
