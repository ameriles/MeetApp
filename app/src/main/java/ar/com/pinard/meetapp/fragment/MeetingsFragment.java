package ar.com.pinard.meetapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ar.com.pinard.meetapp.R;

/**
 * Created by agustin on 12/07/15.
 * Fragment that displays the meetings
 */
public class MeetingsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meetings, container, false);
    }
}
