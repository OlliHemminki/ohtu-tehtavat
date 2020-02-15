
package ohtu;

public class Player implements Comparable<Player>{
    private String name;
    private String team;
    private String nationality;
    private String birthdate;
    private int goals;
    private int assists;
    private int penalties;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public String getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(int goals) {
		this.goals = goals;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	public int getPoints() {
		return this.goals + this.assists;
	}
	
	@Override
    public String toString() {
		if(name.length()<=15) {
			return name + "\t\t" + team + "\t" + goals + "\t+\t" + assists + "\t=\t" + (goals + assists);	
		} else {
			return name + "\t" + team + "\t" + goals + "\t+\t" + assists + "\t=\t" + (goals + assists);
		}
        
    }
	  
	@Override
	public int compareTo(Player otherPlayer) {
		if(this.getPoints() == otherPlayer.getPoints()) {
			return otherPlayer.getGoals() - this.goals;
		}
		else if(this.getPoints() < otherPlayer.getPoints()) {
			return 1;
		} else {
			return -1;
		}
	}
}