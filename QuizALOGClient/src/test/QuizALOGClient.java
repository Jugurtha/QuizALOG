package test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;

import data.Player;
import session.QuizALOGBackendRemote;

public class QuizALOGClient {

	public static void main(String[] args) {
		Context ctx;
		try {
			ctx = new InitialContext();
			NamingEnumeration<NameClassPair> list = ctx.list("");
			while(list.hasMore())
				System.out.println(list.next().getName());
			
			QuizALOGBackendRemote quizBackend = (QuizALOGBackendRemote)ctx.lookup("session.QuizALOGBackendRemote#session.QuizALOGBackendRemote");
			
			Player p = new Player();
			p.setPseudo("Jiji");
			p.setEmail("jiji@gmail.com");
			p.setName("Jugurtha");
			
			//quizBackend.addPlayer(p);
			
			System.out.println(quizBackend.playerExist(p.getPseudo(), p.getEmail()));
			
			System.out.println(quizBackend.loadQustionAnswers(2));

			
			System.out.println(quizBackend.loadQustions(4));

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
