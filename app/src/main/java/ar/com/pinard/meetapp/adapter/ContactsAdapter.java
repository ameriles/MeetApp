package ar.com.pinard.meetapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import ar.com.pinard.meetapp.R;
import ar.com.pinard.meetapp.model.User;

/**
 * Created by agustin on 13/07/15.
 * Adapter for Contacts
 */
public class ContactsAdapter extends BaseModelAdapter<User> {

    public ContactsAdapter(Context context, List<User> modelList) {
        super(context, modelList, R.layout.item_contact);
    }

    @Override
    protected View buildView(View convertView, User item) {
        ImageView ivProfileImage = (ImageView) convertView.findViewById(R.id.contact_iv_profile_image);
        TextView tvName = (TextView) convertView.findViewById(R.id.contact_tv_name);
        TextView tvLastMeeting = (TextView) convertView.findViewById(R.id.contact_tv_last_meeting);

        //ivProfileImage;
        tvName.setText("An user contact");
        tvLastMeeting.setText("Last meeting at 17:12");

        return convertView;
    }
}
