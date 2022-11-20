package bowling.domain;

import bowling.type.PlayStatus;

public class FinalFrame extends DefaultFrame {

    public FinalFrame(Scores scores) {
        super(scores);
        this.scores = scores;
        this.playStatus = PlayStatus.IN_PROGRESS;
    }

    @Override
    public Frame nextFrame(int currentOrder) {
        throw new UnsupportedOperationException("no next frame available");
    }

    @Override
    public void nextTry() {
        if (isThirdTry()) {
            scores.nextTry(10);
            playStatus = PlayStatus.END;
            return;
        }
        scores.nextTry();
        if (!hasAnotherChance()) {
            playStatus = PlayStatus.END;
        }
    }

    private boolean isThirdTry() {
        return scores.size() == 2;
    }

    private boolean hasAnotherChance() {
        return scores.sum() >= 10;
    }

    @Override
    public int getTotalScore() {
        return 0;
    }
}
