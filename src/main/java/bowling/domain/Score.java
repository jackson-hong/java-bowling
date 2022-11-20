package bowling.domain;

import javax.naming.OperationNotSupportedException;

public class Score {

    private int score;
    private int left;

    public Score(int score, int left) {
        this.score = score;
        this.left = left;
    }

    public Score bowl(int countOfPins){
        return new Score(score += countOfPins, left - 1);
    }

    public int getScore() throws OperationNotSupportedException {
        if (!canCalculateScore()) {
            throw new OperationNotSupportedException("no calculation available");
        }
        return this.score;
    }

    public boolean canCalculateScore() {
        return left == 0;
    }
}
