package session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
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
@WebService
public class QuizALOGBackend implements QuizALOGBackendRemote {

    /**
     * Default constructor. 
     */
    public QuizALOGBackend() {
    }
    
    @PersistenceContext(unitName = "QuizALOGBackend")
    private EntityManager entityManager;

    @WebMethod
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
    
    @WebMethod
	@Override
	public void addPlayer(Player p) {
		entityManager.persist(p);
	}
    
    @WebMethod
	@Override
	public void saveGame(Game g, ArrayList<GameQuestion> gameQuestions) {
		entityManager.persist(g);
		for(GameQuestion gq : gameQuestions)
		{
			gq.setGame(g);
			entityManager.persist(gq);
		}
			
	}

    @WebMethod
	@Override
	public ArrayList<Question> loadQustions(@WebParam(name = "nbr")int nbr) {
		
		ArrayList<Question> questions = new ArrayList<Question>();
		for(Object o : entityManager.createNamedQuery("Question.findAll").getResultList())
		{
			Question q = (Question) o;
			
			ArrayList<Answer> answers =  new ArrayList<Answer>();
			for(Object a : entityManager.createQuery("SELECT a FROM Answer a WHERE a.idQuestion = :param")
					.setParameter("param", q.getId())
					.getResultList())
			{
				
				Answer an = (Answer)a;
				answers.add(an);
			}
			
			q.setAnswers(answers);
			
			questions.add(q);
		}

		Collections.shuffle(questions);
		return new ArrayList<Question>(questions.subList(0, nbr));
	}

    @WebMethod
	@Override
	public ArrayList<Answer> loadQustionAnswers(@WebParam(name = "id")int id) {
		ArrayList<Answer> answers = new ArrayList<Answer>();
		Question q = new Question();
		q.setId(id);
		for(Object a : entityManager.createQuery("SELECT a FROM Answer a WHERE a.idQuestion = :param")
				.setParameter("param", q.getId())
				.getResultList())
			answers.add((Answer)a);
		return answers;
	}
	
    @WebMethod
	public String testWebService(String text)
	{
		return text;
	}

    @WebMethod//(exclude = true)
	@Override
	public Object[][] getScores() {
		Object[][] result = new Object[10][3];
		List<?> list = entityManager.createQuery("select g from Game g join g.player p order by g.score desc")
		.setMaxResults(10)
		.getResultList();
		for(int i = 0;i<list.size();i++)
		{
			result[i][0] = new String(((Game) list.get(i)).getPlayer().getPseudo());
			result[i][1] = new String(((Game) list.get(i)).getPlayer().getEmail());
			result[i][2] = new Integer(((Game) list.get(i)).getScore());
		}
		return result;
	}

}
