package com.example.nagat.testnotificationfirebase.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nagat.testnotificationfirebase.R;
import com.example.nagat.testnotificationfirebase.activities.ChatActivity;
import com.example.nagat.testnotificationfirebase.models.Message;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.Query;


/**
 * Created by ducng on 03/13/2018.
 */

public class ConversationAdapter extends FirebaseListAdapter<Message> {
    private ChatActivity chatActivity;
    private String uID;
    public ConversationAdapter(ChatActivity chatActivity, Class<Message> modelClass, int modelLayout, Query ref,String uID) {
        super(chatActivity, modelClass, modelLayout, ref);
        this.chatActivity = chatActivity;
        this.uID = uID;
    }

    @Override
    protected void populateView(View v, Message model, int position) {
        TextView content = v.findViewById(R.id.message_text);
        content.setText(model.getContent());
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        Message message = getItem(position);
        if (message.getSenderID().equals(uID)) {
            view = chatActivity.getLayoutInflater().inflate(R.layout.item_out_message, viewGroup, false);
        } else {
            view = chatActivity.getLayoutInflater().inflate(R.layout.item_in_message, viewGroup, false);
        }
        populateView(view, message, position);
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2;
    }
}
