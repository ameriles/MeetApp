package ar.com.pinard.meetapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ar.com.pinard.meetapp.R;
import ar.com.pinard.meetapp.adapter.ContactsAdapter;
import ar.com.pinard.meetapp.model.User;

/**
 * Created by agustin on 12/07/15.
 * Fragment that displays Contacts
 */
public class ContactsFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contacts, container, false);
        ListView lvContacts = (ListView)rootView.findViewById(R.id.contacts_lv_contacts);

        List<User> mock = new ArrayList<>();
        mock.add(new User());
        mock.add(new User());
        mock.add(new User());

        ContactsAdapter adapter = new ContactsAdapter(getActivity(), mock);
        lvContacts.setAdapter(adapter);
        return rootView;
    }
}
