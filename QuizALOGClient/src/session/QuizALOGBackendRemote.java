package session;

import java.util.ArrayList;

import javax.ejb.Remote;

import data.Answer;
import data.Game;
import data.GameQuestion;
import data.Player;
import data.Question;

@Remote
public interface QuizALOGBackendRemote {
	int playerExist(String pseudo, String email);
	void addPlayer(Player p);
	void saveGame(Game g, ArrayList<GameQuestion> gameQuestions);
	ArrayList<Question> loadQustions(int nbr);
	ArrayList<Answer> loadQustionAnswers(int id);
}
