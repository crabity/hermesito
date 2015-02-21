package org.rika.hermesito;

import android.text.format.Time;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by Rika on 2/20/2015.
 */
public class Transaction {

    Calendar calendar;

    public Transaction(){
        super();
        System.out.println("New Entry");
    }

    public void update(){
        System.out.println("Update Entry");
    }



}
