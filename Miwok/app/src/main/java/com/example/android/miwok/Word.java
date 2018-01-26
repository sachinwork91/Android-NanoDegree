package com.example.android.miwok;

/**
 *
 * Created by Sachin on 2018-01-22.
 *
 **/

public class Word {

    private String miwokWord;
    private String englishWord;
    private int mImageResourceId = NO_IMAGE_PROVIDED;
    private int mVoiceResourceId = NO_VOICE_PROVIDED;

    private static final int NO_IMAGE_PROVIDED = -1;
    private static final int NO_VOICE_PROVIDED = -1;

    public String getMiwokWord() {
        return miwokWord;
    }


    public String getEnglishWord() {
        return englishWord;
    }


    public Word(String englishWord, String miwokWord) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
    }

    public Word(String englishWord, String miwokWord, int ImageResourceId) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.mImageResourceId=ImageResourceId;
    }

    public Word(String englishWord, String miwokWord, int ImageResourceId, int VoiceResourceId) {
        this.miwokWord = miwokWord;
        this.englishWord = englishWord;
        this.mImageResourceId=ImageResourceId;
        this.mVoiceResourceId= VoiceResourceId;
    }


    /**
     * Return the image resource ID of the word.
     * */
     public int getImageResourceId() {
                return mImageResourceId;
            }
    /**
          * Returns whether or not there is an image for this word.
          */
     public boolean hasImage() {
               return mImageResourceId != NO_IMAGE_PROVIDED;
     }
    /**
     * Return the image resource ID of the word.
     * */
    public int getVoiceResourceId() {
        return mVoiceResourceId;
    }



}
