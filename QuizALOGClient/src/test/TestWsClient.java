package test;


import java.rmi.RemoteException;

import wsclient.Answer;
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

		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
