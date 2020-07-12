package presentation.ejb;
//cette classe s'occupe de la GUI de l'inscritpion du jeur
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import data.Game;
import data.Player;
import session.QuizALOGBackendRemote;

@SuppressWarnings("serial")
public class InscrirJoueur extends JFrame {
	private JTextField pseudo;
	private JTextField nom;
	private JTextField mail;
	private Player player;
	private JouerQuestion jouer;
	private Game game;
	
	public InscrirJoueur(File questions) {
		WindowAdapter manipFenetre = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
			  interupMatch();
			} };
		addWindowListener(manipFenetre);
		setBackground(new Color(169, 169, 169));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 564, 347);
		setLocationRelativeTo(null);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setResizable(false);
		setSize(new Dimension(298, 252));
		setTitle("JOUEUR");
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Jouer");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				inscrire();
			}
		});
		btnNewButton.setBounds(10, 192, 82, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sortir");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		      manipFenetre.windowClosed(null);
			}
		});
		btnNewButton_1.setBounds(188, 192, 89, 23);
		getContentPane().add(btnNewButton_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBounds(10, 25, 267, 145);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblPseudo = new JLabel("PSEUDO");
		lblPseudo.setBounds(10, 38, 46, 14);
		panel.add(lblPseudo);
		
		pseudo = new JTextField();
		pseudo.setBounds(107, 35, 150, 20);
		panel.add(pseudo);
		pseudo.setText("MonPseudo");
		pseudo.setColumns(10);
		
		JLabel lblInscriptionAuJeux = new JLabel("INSCRIPTION AU JEUX");
		lblInscriptionAuJeux.setBounds(67, 11, 120, 14);
		panel.add(lblInscriptionAuJeux);
		
		nom = new JTextField();
		nom.setBounds(107, 63, 150, 20);
		panel.add(nom);
		nom.setText("NOM");
		nom.setColumns(10);
		
		JLabel lblNom = new JLabel("NOM");
		lblNom.setBounds(10, 63, 46, 14);
		panel.add(lblNom);
		
		mail = new JTextField();
		mail.setBounds(107, 97, 150, 20);
		panel.add(mail);
		mail.setText("exemplel@exemple.xxx");
		mail.setColumns(10);
		
		JLabel lblMail = new JLabel("MAIL");
		lblMail.setBounds(10, 100, 46, 14);
		panel.add(lblMail);

	}
	
	
	private void inscrire() {
		 player=new Player();
		 player.setPseudo(pseudo.getText());
		 player.setName(nom.getText());
		 player.setEmail(mail.getText());
		 
		 Context ctx;
		 try {
			 ctx = new InitialContext();
			 QuizALOGBackendRemote quizBackend = (QuizALOGBackendRemote)ctx.lookup("session.QuizALOGBackendRemote#session.QuizALOGBackendRemote");
			 
			 if(quizBackend.playerExist(player.getPseudo(), player.getEmail())==0) {
				 quizBackend.addPlayer(player);
				 System.out.println("Player "+player.getPseudo()+" Added.");
			 }

	

			player.setId(quizBackend.playerExist(player.getPseudo(), player.getEmail()));
	
			} catch (NamingException e) {e.printStackTrace();}
		 
		 this.setVisible(false);
		 jouerMatch();
	}


	public Player getInfoJoueur() {
		return player;
	}
	//  Lancer le match
	private void jouerMatch() {
		game = new Game();//questions, this.getInfoJoueur()
		game.setPlayer(player);
		jouer = new JouerQuestion(game);
		jouer.setVisible(true);
	}
	
	private void interupMatch() {
		int reponse = JOptionPane.showConfirmDialog(null, "cette action annulera l'inscription et la partie : continuez?", 
				"OUI", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION){
				JOptionPane.showMessageDialog(null, " Partie interompue Merci ") ;
				this.dispose();
			}	
	}
	
}// Fin de la classe InscrirJoueur
