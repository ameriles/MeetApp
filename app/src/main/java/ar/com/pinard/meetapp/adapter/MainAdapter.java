package ar.com.pinard.meetapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ar.com.pinard.meetapp.R;
import ar.com.pinard.meetapp.fragment.ContactsFragment;
import ar.com.pinard.meetapp.fragment.MeetingsFragment;

/**
 * Created by agustin on 12/07/15.
 * Adapter for main activity
 */
public class MainAdapter extends FragmentPagerAdapter {

    private static Fragment[] sFragments = new Fragment[]{new ContactsFragment(), new MeetingsFragment()};
    private final Context mContext;

    public MainAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        return sFragments[position];
    }

    @Override
    public int getCount() {
        return sFragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return mContext.getString(R.string.tab_contacts);
        } else {
            return mContext.getString(R.string.tab_meetings);
        }
    }
}
