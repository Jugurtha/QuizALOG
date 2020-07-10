package data;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;


/**
 * The persistent class for the QUESTION database table.
 * 
 */
@Entity
@NamedQuery(name="Question.findAll", query="SELECT q FROM Question q")
public class Question implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ID_CORRECT_ANSWER")
	private int idCorrectAnswer;

	private int score;

	private String text;
	
	@javax.persistence.Transient
	private List<Answer> answers;


	public Question() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdCorrectAnswer() {
		return this.idCorrectAnswer;
	}

	public void setIdCorrectAnswer(int idCorrectAnswer) {
		this.idCorrectAnswer = idCorrectAnswer;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@javax.persistence.Transient
	public List<Answer> getAnswers() {
		return answers;
	}
	
	@javax.persistence.Transient
	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
	
	@javax.persistence.Transient
	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setIdQuestion(id);

		return answer;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", idCorrectAnswer=" + idCorrectAnswer + ", score=" + score + ", text=" + text
				+ ", answers=" + answers + "]";
	}

}