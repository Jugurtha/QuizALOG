package test;

import java.rmi.RemoteException;

import wsclient.Answer;
import wsclient.Game;
import wsclient.GameQuestion;
import wsclient.Player;
import wsclient.Question;
import wsclient.QuizALOGBackendProxy;

public class TestWsClient {

	public static void main(String[] args) {
		QuizALOGBackendProxy quizWs = new QuizALOGBackendProxy();
		try {
			System.out.println(quizWs.testWebService("Hello World !!!"));
			for(Answer a : quizWs.loadQustionAnswers(2))
				System.out.println(a);
			System.out.println();
			for(Question q : quizWs.loadQustions(4))
				System.out.println(q);
			
System.out.println();

			Player p = new Player();
			p.setPseudo("NewPseudo");
			p.setName("NewName");
			p.setEmail("NewEmail@email.com");
			int id;
			if((id=quizWs.playerExist(p.getPseudo(), p.getEmail()))==0)
			{
				quizWs.addPlayer(p);
				if((id=quizWs.playerExist(p.getPseudo(), p.getEmail()))!=0)
				{
					p.setId(id);
					System.out.println("Player : "+ p + " Exists !!!");
				}
			}
			else
			{
				p.setId(id);
				System.out.println("Player : "+ p + " Exists !!!");
			}
			
System.out.println();
			
			Game g = new Game();
			g.setPlayer(p);
			g.setScore(10);
			//g.setId(8);
			
			GameQuestion[] gqs =  new GameQuestion[4];
			for(int i=0, j =2;i<4;i++)
			{
				GameQuestion gq = new GameQuestion();
				gq.setIdQuestion(i+1);
				gq.setGame(g);
				Answer a = new Answer();
				a.setId(j);
				a.setIdQuestion(i+1);
				gq.setAnswer(a);
				
				gqs[i] = gq;
				j+=5;
				
System.out.println("New GameQuestion : "+gq);
			}
System.out.println();
System.out.println("New Game : "+g);
			
			quizWs.saveGame(g, gqs);

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
