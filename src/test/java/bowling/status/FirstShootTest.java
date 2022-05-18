package bowling.status;

import bowling.frame.ShootScore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class FirstShootTest {

    private static final int STRIKE = 10;
    private static final int FIVE = 5;
    private static final int ZERO = 0;

    private FirstShoot firstFiveShoot;

    @BeforeEach
    void setUp() {
        firstFiveShoot = FirstShoot.from(ShootScore.from(FIVE));
    }

    @Test
    @DisplayName("첫 투구 이후 두 번째 투구를 앞두고 있는 상태를 생성")
    void create() {
        assertThat(firstFiveShoot).isEqualTo(FirstShoot.from(ShootScore.from(FIVE)));
        assertThat(firstFiveShoot.isEnd()).isFalse();
    }

    @Test
    @DisplayName("첫 투구의 값이 Strike 인 10이 들어온다면 생성할 수 없습니다")
    void invalidFirstShootCondition() {
        assertThatThrownBy(() -> FirstShoot.from(ShootScore.from(STRIKE)))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("첫 번째, 두 번째 투구의 합이 10인 경우 Spare 상태로 전환되어야 함")
    void firstShootToSpareStatus() {
        Status spareStatus = firstFiveShoot.shoot(ShootScore.from(FIVE));
        assertThat(spareStatus.getClass()).hasSameClassAs(Spare.class);
        assertThat(spareStatus.isEnd()).isTrue();
    }

    @Test
    @DisplayName("첫 번째, 두 번째 투구의 합이 10이 아닌 경우 Miss 상태로 전환되어야 함")
    void firstShootToMissStatus() {
        Status missStatus = firstFiveShoot.shoot(ShootScore.from(ZERO));
        assertThat(missStatus.getClass()).hasSameClassAs(Miss.class);
        assertThat(missStatus.isEnd()).isTrue();
    }
}