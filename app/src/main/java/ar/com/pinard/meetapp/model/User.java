package ar.com.pinard.meetapp.model;

import java.util.Date;

/**
 * Created by agustin on 12/07/15.
 * User model class
 */
public class User {

    private String mId;

    private String mNickname;

    private String mImageUri;

    private Date mLastConnection;

    public String getId() {
        return mId;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getmImageUri() {
        return mImageUri;
    }
    public Date getLastConnection() {
        return mLastConnection;
    }
}
