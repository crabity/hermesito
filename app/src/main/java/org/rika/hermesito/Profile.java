package org.rika.hermesito;

import android.content.Context;

/**
 * Created by Rika on 2/19/2015.
 */
public class Profile {

    private static Profile i_profile;

    // Profile data
    private String user_name;

    ///////////////// Handling for Singleton class ///////////////

    public Profile(Context c) {
        //TODO add constructor
    }

    public static Profile getInstance(Context c){
        if(i_profile==null){
            i_profile = new Profile(c.getApplicationContext());
        }
        return i_profile;
    }

    ///////////////// Setting Data //////////////////

    public void setUser_name(String name){
        user_name = name;
    }

    public String getUser_name(){
        return user_name;
    }



}
