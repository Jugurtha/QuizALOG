package presentation;
/*
 * T�che principal de la GUI : montrer les questions et r�cup�rer les r�ponses
 */
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import java.awt.Dimension;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.UIManager;

import data.Answer;
import data.Game;
import data.GameQuestion;
import data.Question;
import session.QuizALOGBackendRemote;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Vector;

public class JouerQuestion extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Game game;
	private HashSet<Integer> answeredQuestions;
	private ArrayList<GameQuestion> gameQuestions;
	private ArrayList<Question> questions;
	private Question currentQuestion;
    private JTextArea cntntQuest;
    JTextArea evalReponse;
    private JComboBox<String> repList;
    private JLabel nQuest;
    private JLabel scorQuest;
    private JLabel totalScroe;
    protected int flaqFinDepartie; // 0 si tout se termine bien 1 si le joueur � interompu le jeux avant la fin de questions
	private int indexCurrentQuestion=-1;
    
        
	public JouerQuestion(Game game) {
		
		loadQuestions();
		System.out.println(questions);
		answeredQuestions = new HashSet<Integer>(4);
		gameQuestions = new ArrayList<GameQuestion>(4);
		
		flaqFinDepartie=1;     //au d�part la partie n'est pas termin�e ... donc risque de fin anormale
		WindowAdapter manipQuestFen = new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				stopMatch();
			}
		};
		addWindowListener(manipQuestFen);
	    this.game = game;
	    this.setTitle("Joueur = "+game.getPlayer().getPseudo());
		setSize(new Dimension(298, 252));
		setLocationRelativeTo(null);
		setBounds(100, 100, 564, 347);
	
		JLabel score = new JLabel(" ");
		score.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().setLayout(null);
				
		cntntQuest = new JTextArea();
		cntntQuest.setWrapStyleWord(true);
		cntntQuest.setEditable(false);
		cntntQuest.setBounds(21, 61, 517, 114);
		getContentPane().add(cntntQuest);
		
		repList = new JComboBox<String>();
		repList.setForeground(UIManager.getColor("ComboBox.foreground"));
		repList.setModel(new DefaultComboBoxModel<String>(new String[] {"", "", ""}));
		repList.setBounds(21, 186, 517, 37);
		getContentPane().add(repList);
		
		JButton btnNewButton0 = new JButton("Evaluer Reponse");
		btnNewButton0.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				evaluerReponse();
				btnNewButton0.setEnabled(false);
			}
		});
		btnNewButton0.setBounds(220, 234, 150, 23);
		getContentPane().add(btnNewButton0);
		
		JButton btnNewButton = new JButton("Question Suivante");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chargerUneQuestion();
				btnNewButton0.setEnabled(true);
			}
		});
		btnNewButton.setBounds(21, 234, 190, 23);
		getContentPane().add(btnNewButton);	
		
		JButton btnNewButton_1 = new JButton("Arr\u00EAter");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				manipQuestFen.windowClosed(null);
			}
		});
		btnNewButton_1.setBounds(381, 234, 157, 23);
		getContentPane().add(btnNewButton_1);
		
		JLabel lblNewLabel = new JLabel("Question N\u00B0");
		lblNewLabel.setBounds(21, 36, 66, 14);
		getContentPane().add(lblNewLabel);
		
		nQuest = new JLabel(" ");
		nQuest.setBounds(97, 36, 46, 14);
		getContentPane().add(nQuest);
		
		scorQuest = new JLabel(" ");
		scorQuest.setBounds(394, 36, 46, 14);
		getContentPane().add(scorQuest);
		
		JLabel lblScore = new JLabel("BAREME");
		lblScore.setBounds(318, 36, 66, 14);
		getContentPane().add(lblScore);
		
		JLabel lblScore_2 = new JLabel("SCORE ");
		lblScore_2.setBounds(158, 11, 72, 14);
		getContentPane().add(lblScore_2);
		
		totalScroe = new JLabel(" ");
		totalScroe.setBounds(205, 11, 66, 14);
		getContentPane().add(totalScroe);
		
		evalReponse = new JTextArea();
		evalReponse.setBackground(Color.GRAY);
		evalReponse.setForeground(Color.BLACK);
		//evalReponse.setBounds(158, 275, 203, 22);
		getContentPane().add(evalReponse);
        // on charge une premi�re question
		chargerUneQuestion();
	}
	
	// Charger une question � jouer
	private void chargerUneQuestion() {
		currentQuestion = nextQuestion();        
		
		if (currentQuestion==null) {
			bnqEpuisee(); 
			return; 
			}  // soit la banque est epuis�e soit elle vide...
		
		cntntQuest.setText(currentQuestion.getText());   // la question (�nonc�)

		Vector<String> answers = new Vector<String>();
		for (Answer a : currentQuestion.getAnswers())
			answers.add(a.getText()); 
			
		repList.setModel(new DefaultComboBoxModel<String>(answers));           //mise en liste d�roulante
	    repList.setBackground(Color.WHITE);
      	repList.setForeground(Color.BLACK);
      	repList.setEnabled(true);
		evalReponse.setBackground(Color.GRAY);
      	evalReponse.setText("");

	    nQuest.setText(String.valueOf(answeredQuestions.size()));                    //num�ro de la question
	    scorQuest.setText(String.valueOf(currentQuestion.getScore()));                           // le bar�me
	    totalScroe.setText(String.valueOf(game.getScore()));                     // le score cummul� du joueur
	    this.setVisible(true);
	}
	
	 // �valuer la r�ponse du joueur: si vrai incr�menter le score sinon afficher un message d'erreur et passer � la suivante
		private void evaluerReponse() {
			repList.setEnabled(false);                             //ne pas permettre une autre tentative
			
			answeredQuestions.add(currentQuestion.getId());
			
			GameQuestion gq = new GameQuestion();
			gq.setIdQuestion(currentQuestion.getId());
			gq.setGame(game);
			gq.setAnswer(currentQuestion.getAnswers().get(repList.getSelectedIndex()));
			gameQuestions.add(gq);
			
			if (checkAnswer(currentQuestion, currentQuestion.getAnswers().get(repList.getSelectedIndex()))){   // cas de r�ponse juste
				game.setScore(game.getScore()+currentQuestion.getScore());
				totalScroe.setText(String.valueOf(game.getScore()));    // le score cummul� du joueur
				repList.setBackground(Color.GREEN);
	          	repList.setForeground(Color.WHITE);
				evalReponse.setBackground(Color.GREEN);
	          	evalReponse.setForeground(Color.WHITE);
	          	evalReponse.setText("BONNE REPONSE (:>)");
	          	evalReponse.setVisible(true);
			} else {
				repList.setBackground(Color.RED);
	          	repList.setForeground(Color.BLACK);			
				evalReponse.setBackground(Color.RED);
	          	evalReponse.setForeground(Color.BLACK);
	          	evalReponse.setText("MAUVAISE REPONSE (:o)");
	          	evalReponse.setVisible(true);
			  }
			
		}
	
	
	
    // Fin de partie banque epuis�e (totues les questions utilis�e) fin normale 0 d�gats
	private void bnqEpuisee() {
	  int reponse = JOptionPane.showConfirmDialog(null, "cette partir est termin� voulez vous enregistrer ses r�sultats ?", 
				"OUI", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION){
				if (answeredQuestions.size()==0) JOptionPane.showMessageDialog(null, " vous n'avez r�pondu � aucune question donc partie non enregistr�e ... Merci ... ") ;
				 else {
					 saveGame();
					 JOptionPane.showMessageDialog(JouerQuestion.this, " Partie enregistr�e ... Merci ... ") ;
				 }
			}
			flaqFinDepartie=0;
			this.dispose();		
	}
	
	// Arr�t demand�e par le joueur fin anormal 1
	private void stopMatch() {
		//v�rifier si il n'y a pas eu de fin normal
		if (flaqFinDepartie==0) return;
		int reponse = JOptionPane.showConfirmDialog(null, "�tes-vous s�r de vouloir quitter la partie en cours ?","OUI", JOptionPane.YES_NO_OPTION);
	    if(reponse == JOptionPane.YES_OPTION){
			if (answeredQuestions.size()>1) {					
				reponse = JOptionPane.showConfirmDialog(null, "voulez enregister la partie ?", "OUI", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){
					saveGame();
					JOptionPane.showMessageDialog(JouerQuestion.this, " Partie enregistr�e ... Merci ... ") ;
				}
			}
			flaqFinDepartie=1; // d�gats et interuption
		this.dispose();
		}
	}
	
	private void loadQuestions() {
		//*
		wsclient.QuizALOGBackendProxy quizWs = new wsclient.QuizALOGBackendProxy();
		try {
			questions = new ArrayList<Question>(4);
			for(wsclient.Question q : quizWs.loadQustions(4)) {
System.out.println(q);
				Question question = new Question();
				question.setId(q.getId());
				question.setIdCorrectAnswer(q.getIdCorrectAnswer());
				question.setScore(q.getScore());
				question.setText(q.getText());
				
				ArrayList<Answer> answers = new ArrayList<Answer>();
				for(wsclient.Answer wsa : q.getAnswers()) {
					Answer answer = new Answer();
					answer.setId(wsa.getId());
					answer.setIdQuestion(wsa.getIdQuestion());
					answer.setText(wsa.getText());
					answers.add(answer);
				}
				question.setAnswers(answers);
System.out.println(question);
				questions.add(question);
			}
				

		} catch (RemoteException e) {
			e.printStackTrace();
		}
		//*/
		/*
		Context ctx;
		try {
			ctx = new InitialContext();
			QuizALOGBackendRemote quizBackend = (QuizALOGBackendRemote)ctx.lookup("session.QuizALOGBackendRemote#session.QuizALOGBackendRemote");
			questions = quizBackend.loadQustions(4);
		} catch (NamingException e) {e.printStackTrace();}
		//*/
	}
	
	private Question nextQuestion()
	{
		indexCurrentQuestion++;
		if(indexCurrentQuestion>=questions.size())
			return null;
		return questions.get(indexCurrentQuestion);	
	}
	
	private void saveGame()
	{
		game.setScore(Integer.parseInt(totalScroe.getText()));
		Context ctx;
		try {
			ctx = new InitialContext();
			QuizALOGBackendRemote quizBackend = (QuizALOGBackendRemote)ctx.lookup("session.QuizALOGBackendRemote#session.QuizALOGBackendRemote");
			quizBackend.saveGame(game, gameQuestions);
		} catch (NamingException e) {e.printStackTrace();}
	}
	
	private boolean checkAnswer(Question q, Answer answer)
	{
		return q.getIdCorrectAnswer()==answer.getId();
	}
	
 
} // la fin de la classe JouerQuestion