package org.nirbhaya.heatmap;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 */

/**
 * @author romil
 *
 */
public class Problems {
	String problem;
	ArrayList<Stat> stats;
	
	
	public String getProblem() {
		return problem;
	}
	public void setProblem(String problem) {
		this.problem = problem;
	}
	public ArrayList<Stat> getStats() {
		return stats;
	}
	public void setStats(ArrayList<Stat> stats) {
		this.stats = stats;
	}
	public Problems(String problem, ArrayList<Stat> stats) {
		super();
		this.problem = problem;
		this.stats = stats;
	}
	public Problems() {
		super();
		// TODO Auto-generated constructor stub
	}
}
