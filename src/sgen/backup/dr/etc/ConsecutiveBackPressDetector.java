package sgen.backup.dr.etc;

import android.os.Handler;
import android.os.Message;

import java.util.Calendar;

/**
 * 백 버튼 한번 누르고 일정 시간 내에 또 눌러야 액티비티 종료할 수 있도록 연속된 백 버튼 누름을 감지하는
 * 클래스이다.
 *
 * 사용법
 * ------
 *   1. setOnFirstBackPressListener(), setOnSecondBackPressListener()로 리스너를 등록해
 *      첫번째/두번째 백버튼 클릭시 액션을 지정한다.
 *   2. 액티비티의 onBackPressed() 이벤트 핸들러에서 ConsecutiveBackPressDetector 객체의
 *      onBackPressed() 를 호출해 준다.
 *
 *
 * 상태 초기화
 * --------
 * 만약 액티비티의 onBackPressed() 에서 다른 종류의 액션을 수행했다면 ConsecutiveBackPressDetector가
 * 트랙킹하는 상태를 초기화해 줄 필요가 있는데 이럴 때는 ConsecutiveBackPressDetector#clear()를
 * 호출해 준다.
 *
 *
 * 연속 클릭 간격 조정
 * ---------------
 * 연속 클릭으로 간주하는 기본 값은 2초이다. 이 값을 변경하기 위해서는 상수 ConsecutiveBackPressDetector#BACKKEY_TIMEOUT 의
 * 값을 변경한다. 값의 단위는 millisecond 이다.
 * 동적으로 이 값을 변경할 일이 없어 상수로 정의해 놓는다.
 */
public class ConsecutiveBackPressDetector {
    private static final int MSG_TIMER_EXPIRED	= 1;
    private static final int BACKKEY_TIMEOUT = 2 * 1000;  // 2초이내 다시 눌렸을 경우 액티비티 종료

    public interface OnFirstBackPressListener {
        void onPressed();
    }

    public interface OnSecondBackPressListener {
        void onPressed();
    }

    OnFirstBackPressListener onFirstBackPressListener;
    OnSecondBackPressListener onSecondBackPressListener;

    boolean isBackKeyPressed = false;
    long currTimeInMillis = 0;
    Handler timerHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == MSG_TIMER_EXPIRED) {
                clear();
            }
        }
    };

    public void clear() {
        isBackKeyPressed = false;
        currTimeInMillis = 0;
    }

    public void setOnFirstBackPressListener(OnFirstBackPressListener onFirstBackPressListener) {
        this.onFirstBackPressListener = onFirstBackPressListener;
    }

    public void setOnSecondBackPressListener(OnSecondBackPressListener onSecondBackPressListener) {
        this.onSecondBackPressListener = onSecondBackPressListener;
    }

    public void onBackPressed() {
        if (!isBackKeyPressed) {
            // 첫번째로 클릭된 경우
            isBackKeyPressed = true;
            currTimeInMillis = Calendar.getInstance().getTimeInMillis();
            startTimer();

            if (onFirstBackPressListener != null) {
                onFirstBackPressListener.onPressed();
            }

        } else {
            isBackKeyPressed = false;
            if (Calendar.getInstance().getTimeInMillis() <= (currTimeInMillis + BACKKEY_TIMEOUT)) {
                // 연속 클릭으로 간주된 경우
                if (onSecondBackPressListener != null) {
                    onSecondBackPressListener.onPressed();
                }
            }
        }
    }

    void startTimer() {
        timerHandler.sendEmptyMessageDelayed(MSG_TIMER_EXPIRED, BACKKEY_TIMEOUT);
    }
}
