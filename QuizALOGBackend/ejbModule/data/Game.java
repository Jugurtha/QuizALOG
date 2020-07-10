package data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the GAME database table.
 * 
 */
@Entity
@NamedQuery(name="Game.findAll", query="SELECT g FROM Game g")
public class Game implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private int score;

	//bi-directional many-to-one association to Player
	@ManyToOne
	@JoinColumn(name="ID_PLAYER")
	private Player player;

	public Game() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Player getPlayer() {
		return this.player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

}