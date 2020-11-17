package model;

import java.awt.Color;
import java.util.ArrayList;
/**
 * @author Chernyj Dmitry
 *
 */
public class Contestant {
	private String nick;
	private int score;
	private ArrayList<Integer> numbersChosenList;
	private Color color;
	
	public Contestant(String userName, ArrayList<Integer> numbers, Color color) {
		this.nick = userName;
		this.numbersChosenList = numbers;
		this.color = color;
		
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public ArrayList<Integer> getNumbersChosenList() {
		return numbersChosenList;
	}
	public void setNumbersChosenList(ArrayList<Integer> numbersChosenList) {
		this.numbersChosenList = numbersChosenList;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	
	
}
