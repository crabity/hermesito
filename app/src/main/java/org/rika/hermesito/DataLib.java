package org.rika.hermesito;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by Rika on 2/20/2015.
 */
public class DataLib {

    private static DataLib i_datalib;

    public DataLib(Context c){
        //TODO add constructor
    }

    public static DataLib getInstance(Context c){
        if(i_datalib == null){
            i_datalib = new DataLib(c.getApplicationContext());
        }
        return i_datalib;
    }

    /////////////////// Library /////////////////////

    public String[] getAcctList(){
        ArrayList<String> accts = new ArrayList<String>();
        accts.add("Checking");
        accts.add("Savings");
        return accts.toArray(new String[accts.size()]);
    }
}
