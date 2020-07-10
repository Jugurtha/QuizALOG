package data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GAME_QUESTIONS database table.
 * 
 */
@Entity
@Table(name="GAME_QUESTIONS")
@NamedQuery(name="GameQuestion.findAll", query="SELECT g FROM GameQuestion g")
public class GameQuestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ID_QUESTION")
	private int idQuestion;

	//uni-directional many-to-one association to Answer
	@ManyToOne
	@JoinColumn(name="ID_ANSWER")
	private Answer answer;

	//uni-directional many-to-one association to Game
	@ManyToOne
	@JoinColumn(name="ID_GAME")
	private Game game;

	public GameQuestion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdQuestion() {
		return this.idQuestion;
	}

	public void setIdQuestion(int idQuestion) {
		this.idQuestion = idQuestion;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Game getGame() {
		return this.game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

}