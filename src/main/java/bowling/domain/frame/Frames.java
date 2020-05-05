package bowling.domain.frame;

import bowling.domain.FrameResults;
import bowling.exception.CannotBowlException;

public class Frames {

  private NormalFrameNode initialFrame;
  private FrameNode currentFrame;

  public Frames() {
    this.initialFrame = NormalFrameNode.initialize();
    this.currentFrame = this.initialFrame;
  }

  public NormalFrameNode getInitialFrame() {
    return initialFrame;
  }

  public FrameNode getCurrentFrame() {
    return currentFrame;
  }

  public FrameNode roll(int pinCount) throws CannotBowlException {
    currentFrame = currentFrame.roll(pinCount);
    return currentFrame;
  }

  public FrameResults produceResults() {
    FrameResults frameResults = new FrameResults();
    FrameNode frame = initialFrame;

    while(!frame.isFinalFrame()) {
      frame.addFrameResult(frameResults);
      frame = frame.getNextFrame();
    }

    frame.addFrameResult(frameResults);

    return frameResults;
  }

  public boolean isEnd() {
    return currentFrame.isFinalFrame() && currentFrame.isFinished();
  }
}
