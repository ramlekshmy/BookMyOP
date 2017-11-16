package com.example.ashikvarma.patientportal;

/**
 * Created by Ashik Varma on 10/16/2017.
 */

public class Doctor {

    private String mText1;
    private String mText2;
    private String Mdpt;

    Doctor(String text1, String text2,String mdpt){
        mText1 = text1;
        mText2 = text2;
        Mdpt=mdpt;
    }

    public String getmText1() {
        return mText1;
    }



    public String getmText2() {
        return mText2;
    }
    public String getmDpt() {
        return Mdpt;
    }


}
