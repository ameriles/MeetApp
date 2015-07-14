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

    private Date mLastMeeting;

    public String getId() {
        return mId;
    }

    public String getNickname() {
        return mNickname;
    }

    public String getImageUri() {
        return mImageUri;
    }

    public Date getLastMeeting() {
        return mLastMeeting;
    }
}
