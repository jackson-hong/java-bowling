package bowling.status;

import bowling.frame.ShootScore;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;

class MissTest {

    private static final int STRIKE = 10;
    private static final int FIVE = 5;
    private static final int FOUR = 4;

    @Test
    @DisplayName("두 개의 투구를 진행했을 때 10개 미만의 점수를 얻었을 경우 발생하는 상태")
    void create() {
        Miss miss = Miss.of(ShootScore.from(FIVE), ShootScore.from(FOUR));

        assertThat(miss).isEqualTo(Miss.of(ShootScore.from(FIVE), ShootScore.from(FOUR)));
        assertThat(miss.isEnd()).isTrue();
    }

    @Test
    @DisplayName("두 개의 투구는 스트라이크 이거나 스페어의 조건을 충족할 수 없습니다")
    void invalidMissCondition() {
        assertAll(
                () -> {
                    assertThatThrownBy(() -> Miss.of(ShootScore.from(STRIKE), ShootScore.from(STRIKE)))
                            .isInstanceOf(IllegalArgumentException.class);
                    assertThatThrownBy(() -> Miss.of(ShootScore.from(FIVE), ShootScore.from(FIVE)))
                            .isInstanceOf(IllegalArgumentException.class);
                }
        );
    }

}