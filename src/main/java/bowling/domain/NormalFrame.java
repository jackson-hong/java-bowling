package bowling.domain;

import bowling.type.PlayStatus;

import static bowling.BowlingApp.getPinCalculateStrategy;
import static bowling.domain.Frames.LAST_FRAME_ORDER;

public class NormalFrame extends DefaultFrame {

    public static final int MAX_SCORE_SIZE = 2;

    public NormalFrame(int order, Scores scores, PlayStatus playStatus) {
        super(scores, order);
        this.order = order;
        this.scores = scores;
        this.playStatus = playStatus;
    }

    public NormalFrame(Scores scores) {
        super(scores);
        if (scores.size() > MAX_SCORE_SIZE) {
            throw new IllegalArgumentException("score size cannot be bigger than 2");
        }
    }

    public NormalFrame(Scores scores, int order) {
        super(scores, order);
    }

    @Override
    public Frame nextFrame() {
        int nextOrder = order + 1;
        Frame result;
        result = new NormalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
        if (nextOrder == LAST_FRAME_ORDER) {
            result = new FinalFrame(new Scores(getPinCalculateStrategy()), nextOrder);
        }
        neighborFrames.add(result);
        return result;
    }


}
