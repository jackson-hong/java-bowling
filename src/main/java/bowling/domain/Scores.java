package bowling.domain;

import bowling.strategy.FallenPinCalculateStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Scores {
    private static final int MAX_PIN_COUNT = 10;

    private final List<Integer> fallenPins;
    private FallenPinCalculateStrategy strategy;

    public Scores(FallenPinCalculateStrategy strategy) {
        this.strategy = strategy;
        this.fallenPins = new ArrayList<>();
        int pinNum = strategy.calculate(MAX_PIN_COUNT);
        fallenPins.add(pinNum);
    }

    public Scores(List<Integer> fallenPins) {
        this.fallenPins = fallenPins;
    }

    public boolean isStrike() {
        return fallenPins.get(0) == 10;
    }

    public void nextTry() {
        int maxNum = MAX_PIN_COUNT - getFirst();
        int newScore = strategy.calculate(maxNum);
        fallenPins.add(newScore);
    }

    public void nextTry(int maxNum) {
        fallenPins.add(strategy.calculate(maxNum));
    }

    public int getFirst() {
        return fallenPins.get(0);
    }

    public int getSecond() {
        return fallenPins.get(1);
    }

    public int getThird() {
        return fallenPins.get(2);
    }

    public int getLatest() {
        return fallenPins.get(fallenPins.size() - 1);
    }

    public int sum() {
        return fallenPins.stream()
                .mapToInt(i -> i)
                .sum();
    }

    public int size() {
        return fallenPins.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Scores that = (Scores) o;
        return Objects.equals(fallenPins, that.fallenPins);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fallenPins);
    }
}
