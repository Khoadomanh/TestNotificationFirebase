package com.example.nagat.testnotificationfirebase.activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nagat.testnotificationfirebase.R;
import com.example.nagat.testnotificationfirebase.adapters.ConversationAdapter;
import com.example.nagat.testnotificationfirebase.models.Message;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChatActivity extends AppCompatActivity {
    private String uID;
    @BindView(R.id.send)
    FloatingActionButton fabSend;
    @BindView(R.id.message)
    EditText edtMessage;
    @BindView(R.id.conversation)
    ListView lvConversation;
    FirebaseDatabase database;
    private ConversationAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        uID = getIntent().getStringExtra("uID");
        database = FirebaseDatabase.getInstance();
        adapter = new ConversationAdapter(this,Message.class,
                R.layout.item_in_message,database.getReference().child("message"),uID);
        lvConversation.setAdapter(adapter);
    }

    @OnClick(R.id.send)
    public void sendMessage() {
        String messageContent = edtMessage.getText().toString();
        if (messageContent.equals("")) {
            edtMessage.setError("Input message");
        } else {
            Message message = new Message(uID, messageContent);
            database.getReference().child("message").push().setValue(message);
            adapter.notifyDataSetChanged();
            edtMessage.setText("");
        }
    }
}
