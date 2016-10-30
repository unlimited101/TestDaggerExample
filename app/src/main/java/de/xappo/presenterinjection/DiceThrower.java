package de.xappo.presenterinjection;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by knoppik on 29.10.16.
 */

public class DiceThrower {
    private static final String TAG = "DiceThrower";
    private List<Integer> dices = new ArrayList<>();

    public DiceThrower() {

    }

    public void throwDices(final int numberOfThrows, final int numberOfPossibleNumbers) {
        for (int i = 0; i < numberOfThrows; i++) {
            int randDiceNumber = ((int) Math.floor(Math.random() * numberOfPossibleNumbers)) + 1;
            dices.add(randDiceNumber);
        }
    }

    public List<Integer> getDices() {
        return dices;
    }

    public int getResult() {
        int res = 0;
        Log.i(TAG, "getResult() no of dices: " + dices.size());
        for (int tmpRes : dices) {
            res += tmpRes;
            Log.i(TAG, "getResult() tmpRes: " + tmpRes);
        }
        return res;
    }
}
