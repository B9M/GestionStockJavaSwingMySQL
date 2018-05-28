package dataBase;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class Principale extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Principale frame = new Principale();
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
	public Principale() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblPrincipale = new JLabel("Principale");
		lblPrincipale.setFont(new Font("Lucida Sans", Font.BOLD | Font.ITALIC, 18));
		lblPrincipale.setForeground(Color.BLUE);
		lblPrincipale.setBounds(147, 12, 150, 15);
		contentPane.add(lblPrincipale);
		
		JButton btnUtilisateur = new JButton("Utilisateur");
		btnUtilisateur.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Utilisateur u = new Utilisateur();
				u.setVisible(true);
				dispose();
			}
		});
		btnUtilisateur.setBounds(135, 50, 117, 25);
		contentPane.add(btnUtilisateur);
		
		JButton btnProduit = new JButton("Produit");
		btnProduit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produit p = new Produit();
				p.setVisible(true);
				dispose();
			}
		});
		btnProduit.setBounds(135, 87, 117, 25);
		contentPane.add(btnProduit);
		
		JButton btnCachier = new JButton("Cachier");
		btnCachier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cachier c = new Cachier();
				c.setVisible(true);
				dispose();
			}
		});
		btnCachier.setBounds(135, 123, 117, 25);
		contentPane.add(btnCachier);
		
		JButton btnRetour = new JButton("Retour");
		btnRetour.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login l = new Login();
				l.setVisible(true);
				dispose();
			}
		});
		btnRetour.setBounds(290, 200, 117, 25);
		contentPane.add(btnRetour);
		
		JLabel lblCopyrigthBadrMoumoud = new JLabel("Copyrigth@ Badr MOUMOUD");
		lblCopyrigthBadrMoumoud.setForeground(Color.CYAN);
		lblCopyrigthBadrMoumoud.setBounds(38, 226, 214, 15);
		contentPane.add(lblCopyrigthBadrMoumoud);
	}
}
