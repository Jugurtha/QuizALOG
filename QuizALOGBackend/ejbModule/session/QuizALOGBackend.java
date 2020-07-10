package session;

import java.util.ArrayList;
import java.util.Collections;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import data.Answer;
import data.Game;
import data.GameQuestion;
import data.Player;
import data.Question;

/**
 * Session Bean implementation class QuizALOGBackend
 */
@Stateless
@LocalBean
public class QuizALOGBackend implements QuizALOGBackendRemote {

    /**
     * Default constructor. 
     */
    public QuizALOGBackend() {
    }
    
    @PersistenceContext(unitName = "QuizALOGBackend")
    private EntityManager entityManager;

	@Override
	public int playerExist(String pseudo, String email) {
		
		ArrayList<Player> l = new ArrayList<Player>();
		for(Object o : entityManager.createQuery("SELECT p FROM Player p WHERE p.pseudo = :pseudo AND p.email = :email")
				 .setParameter("pseudo", pseudo)
				 .setParameter("email", email)
				 .setMaxResults(1)
				 .getResultList())
			l.add((Player)o);
		if(l.isEmpty())
			return 0;
		return l.get(0).getId();
	}

	@Override
	public void addPlayer(Player p) {
		entityManager.persist(p);
	}

	@Override
	public void saveGame(Game g, ArrayList<GameQuestion> gameQuestions) {
		entityManager.persist(g);
		for(GameQuestion gq : gameQuestions)
			entityManager.persist(gq);
	}

	@Override
	public ArrayList<Question> loadQustions(int nbr) {
		
		ArrayList<Question> questions = new ArrayList<Question>();
		for(Object o : entityManager.createQuery("SELECT q FROM Question q").getResultList())
		{
			Question q = (Question) o;
			ArrayList<Answer> answers =  new ArrayList<Answer>();
			for(Object a : entityManager.createQuery("SELECT a FROM Answer a WHERE a.question = :question")
					.setParameter("question", q)
					.getResultList())
			{
				Answer an = (Answer)a;
				an.setQuestion(q);
				answers.add(an);
			}
			
			q.setAnswers(answers);
			questions.add(q);
		}

		Collections.shuffle(questions);
		return new ArrayList<Question>(questions.subList(0, nbr));
	}

	@Override
	public ArrayList<Answer> loadQustionAnswers(int id) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Question q = new Question();
		q.setId(id);
		for(Object a : entityManager.createQuery("SELECT a FROM Answer a WHERE a.question = :param")
				.setParameter("param", q)
				.getResultList())
			answers.add((Answer)a);
		return answers;
	}

}
