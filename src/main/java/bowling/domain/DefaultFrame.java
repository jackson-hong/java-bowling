package bowling.domain;

import bowling.type.BowlingScore;
import bowling.type.PlayStatus;

import java.util.LinkedList;
import java.util.Objects;

public abstract class DefaultFrame implements Frame {

    protected Scores scores;
    protected PlayStatus playStatus;
    protected LinkedList<Frame> otherFrames;

    public DefaultFrame(Scores scores) {
        this.scores = scores;
        this.playStatus = decideEnd(scores);
    }

    private PlayStatus decideEnd(Scores scores) {
        if (scores.isStrike()) {
            return PlayStatus.END;
        }
        return PlayStatus.IN_PROGRESS;
    }

    public void nextTry() {
        scores.nextTry();
        playStatus = PlayStatus.END;
    }

    @Override
    public int getLatestScore() {
        return scores.getLatest();
    }

    public BowlingScore getBowlingScore() {
        return BowlingScore.from(scores);
    }

    public boolean isInProgress() {
        return PlayStatus.IN_PROGRESS == playStatus;
    }

    public int getFirstScore() {
        return scores.getFirst();
    }

    public int getSecondScore() {
        return scores.getSecond();
    }

    public int getThirdScore() {
        return scores.getThird();
    }

    @Override
    public int getScoreSize() {
        return scores.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DefaultFrame that = (DefaultFrame) o;
        return Objects.equals(scores, that.scores) && playStatus == that.playStatus && Objects.equals(otherFrames, that.otherFrames);
    }

    @Override
    public int hashCode() {
        return Objects.hash(scores, playStatus, otherFrames);
    }
}
