package bowling.domain;

import bowling.type.PlayStatus;

import static bowling.BowlingApp.getPinCalculateStrategy;
import static bowling.domain.Frames.LAST_FRAME_ORDER;

public class NormalFrame extends DefaultFrame {

    public static final int MAX_SCORE_SIZE = 2;

    public NormalFrame(Scores scores, PlayStatus playStatus) {
        super(scores);
        this.scores = scores;
        this.playStatus = playStatus;
    }

    public NormalFrame(Scores scores) {
        super(scores);
        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException("score size cannot be bigger than 2");
        }
    }

    @Override
    public Frame nextFrame(int nextOrder) {
        Frame result;
        result = new NormalFrame(new Scores(getPinCalculateStrategy()));
        if (nextOrder == LAST_FRAME_ORDER) {
            result = new FinalFrame(new Scores(getPinCalculateStrategy()));
        }
        otherFrames.add(result);
        return result;
    }

    @Override
    public int getTotalScore() {
        return 0;
    }
}
