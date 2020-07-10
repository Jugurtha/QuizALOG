package client;

import java.rmi.RemoteException;

import session.Answer;
import session.Question;
import session.QuizALOGBackendProxy;

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

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
