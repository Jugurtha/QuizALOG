package data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the ANSWER database table.
 * 
 */
@Entity
@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="ID_QUESTION")
	private int idQuestion;

	private String text;

	public Answer() {
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

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return "Answer [id=" + id + ", idQuestion=" + idQuestion + ", text=" + text + "]";
	}

}