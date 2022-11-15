package bowling;

import bowling.domain.Frames;
import bowling.domain.NormalFrame;
import bowling.domain.Scores;
import bowling.domain.PlayerName;
import bowling.strategy.FallenPinCalculateStrategy;
import bowling.view.InputView;
import bowling.view.ResultView;

import java.util.Random;

public class BowlingApp {

    private final InputView inputView;
    private final ResultView resultView;
    private static final Random random = new Random();

    public BowlingApp(InputView inputView, ResultView resultView) {
        this.inputView = inputView;
        this.resultView = resultView;
    }

    public void start(){
        PlayerName playerName = new PlayerName(inputView.getPlayName());
        resultView.printDefaultPanels(playerName);
        NormalFrame normalFrame = new NormalFrame(new Scores(getPinCalculateStrategy()));
        Frames frames = new Frames(normalFrame);
        while (frames.isInProgress()) {
            resultView.printGameScores(playerName, frames);
            frames.next();
        }
    }

    public static FallenPinCalculateStrategy getPinCalculateStrategy(){
        return (maxNum) -> random.nextInt(maxNum+1);
    }
}
