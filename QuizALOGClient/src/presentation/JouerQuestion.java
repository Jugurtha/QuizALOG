package presentation;
/*
 * Tâche principal de la GUI : montrer les questions et récupérer les réponses
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
    protected int flaqFinDepartie; // 0 si tout se termine bien 1 si le joueur à interompu le jeux avant la fin de questions
	private int indexCurrentQuestion=-1;
    
        
	public JouerQuestion(Game game) {
		
		loadQuestions();
		System.out.println(questions);
		answeredQuestions = new HashSet<Integer>(4);
		gameQuestions = new ArrayList<GameQuestion>(4);
		
		flaqFinDepartie=1;     //au départ la partie n'est pas terminée ... donc risque de fin anormale
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
        // on charge une première question
		chargerUneQuestion();
	}
	
	// Charger une question à jouer
	private void chargerUneQuestion() {
		currentQuestion = nextQuestion();        
		
		if (currentQuestion==null) {
			bnqEpuisee(); 
			return; 
			}  // soit la banque est epuisée soit elle vide...
		
		cntntQuest.setText(currentQuestion.getText());   // la question (énoncé)

		Vector<String> answers = new Vector<String>();
		for (Answer a : currentQuestion.getAnswers())
			answers.add(a.getText()); 
			
		repList.setModel(new DefaultComboBoxModel<String>(answers));           //mise en liste déroulante
	    repList.setBackground(Color.WHITE);
      	repList.setForeground(Color.BLACK);
      	repList.setEnabled(true);
		evalReponse.setBackground(Color.GRAY);
      	evalReponse.setText("");

	    nQuest.setText(String.valueOf(answeredQuestions.size()));                    //numéro de la question
	    scorQuest.setText(String.valueOf(currentQuestion.getScore()));                           // le barème
	    totalScroe.setText(String.valueOf(game.getScore()));                     // le score cummulé du joueur
	    this.setVisible(true);
	}
	
	 // évaluer la réponse du joueur: si vrai incrémenter le score sinon afficher un message d'erreur et passer à la suivante
		private void evaluerReponse() {
			repList.setEnabled(false);                             //ne pas permettre une autre tentative
			
			answeredQuestions.add(currentQuestion.getId());
			
			GameQuestion gq = new GameQuestion();
			gq.setIdQuestion(currentQuestion.getId());
			gq.setGame(game);
			gq.setAnswer(currentQuestion.getAnswers().get(repList.getSelectedIndex()));
			gameQuestions.add(gq);
			
			if (checkAnswer(currentQuestion, currentQuestion.getAnswers().get(repList.getSelectedIndex()))){   // cas de réponse juste
				game.setScore(game.getScore()+currentQuestion.getScore());
				totalScroe.setText(String.valueOf(game.getScore()));    // le score cummulé du joueur
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
	
	
	
    // Fin de partie banque epuisée (totues les questions utilisée) fin normale 0 dégats
	private void bnqEpuisee() {
	  int reponse = JOptionPane.showConfirmDialog(null, "cette partir est terminé voulez vous enregistrer ses résultats ?", 
				"OUI", JOptionPane.YES_NO_OPTION);
			if(reponse == JOptionPane.YES_OPTION){
				if (answeredQuestions.size()==0) JOptionPane.showMessageDialog(null, " vous n'avez répondu à aucune question donc partie non enregistrée ... Merci ... ") ;
				 else {
					 saveGame();
					 JOptionPane.showMessageDialog(JouerQuestion.this, " Partie enregistrée ... Merci ... ") ;
				 }
			}
			flaqFinDepartie=0;
			this.dispose();		
	}
	
	// Arrêt demandée par le joueur fin anormal 1
	private void stopMatch() {
		//vérifier si il n'y a pas eu de fin normal
		if (flaqFinDepartie==0) return;
		int reponse = JOptionPane.showConfirmDialog(null, "Êtes-vous sûr de vouloir quitter la partie en cours ?","OUI", JOptionPane.YES_NO_OPTION);
	    if(reponse == JOptionPane.YES_OPTION){
			if (answeredQuestions.size()>1) {					
				reponse = JOptionPane.showConfirmDialog(null, "voulez enregister la partie ?", "OUI", JOptionPane.YES_NO_OPTION);
				if(reponse == JOptionPane.YES_OPTION){
					saveGame();
					JOptionPane.showMessageDialog(JouerQuestion.this, " Partie enregistrée ... Merci ... ") ;
				}
			}
			flaqFinDepartie=1; // dégats et interuption
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