package com.example.android.miwok;

/**
 * Created by Sachin on 2018-01-22.
 */

public class Color {

    private String englishWord;

    private String miwokWord;

    public String getEnglishWord() {
        return englishWord;
    }

    public Color(String englishColorName, String miwokColorName) {
        this.englishWord = englishColorName;
        this.miwokWord = miwokColorName;
    }

    public String getMiwokWord() {
        return miwokWord;
    }
}
