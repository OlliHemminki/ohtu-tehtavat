package ohtu;

public class TennisGame {
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;
    private String[] pointsMapping;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
        this.pointsMapping = new String[4];
        this.populatePointsMapping();
    }

    public void wonPoint(String playerName) {
        if (playerName == player1Name){
            player1Score++;
        } else {
            player2Score++;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return equalScore(player1Score);
        }
        
        if (player1Score >= 4 || player2Score >= 4) {
            return advancedGame(player1Score, player2Score);
        }
        
        return earlyGame(player1Score, player2Score);
    }
    
    private String equalScore(int score){
        if(score <= 3){
            return pointsMapping[score] + "-All";
        }
        return "Deuce";
    }
 
    private String advancedGame(int score1, int score2){
        
        int scoreDifference = score1 - score2;
        
        if (scoreDifference == 1) {
            return "Advantage player1";
        }
        if (scoreDifference == -1) {
            return "Advantage player2";
        }
        if (scoreDifference >= 2) {
            return "Win for player1";
        }
        return "Win for player2";
    }
    
    private String earlyGame(int score1, int score2){
        return pointsMapping[score1] + "-" + pointsMapping[score2];
    }
    
    private void populatePointsMapping(){
        this.pointsMapping[0] = "Love";
        this.pointsMapping[1] = "Fifteen";
        this.pointsMapping[2] = "Thirty";
        this.pointsMapping[3] = "Forty";
    }
}
