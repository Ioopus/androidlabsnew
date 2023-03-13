package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.databinding.ActivityChatRoomBinding;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ChatRoom extends AppCompatActivity {
    ActivityChatRoomBinding binding;
    ChatMessage chatmessage = new ChatMessage();
    private RecyclerView.Adapter myAdapter;
    ChatRoomViewModel chatModel;
    ArrayList<ChatMessage> messages = new ArrayList<>();
    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd-MMM-yyyy hh-mm-ss a");
    String currentDateandTime = sdf.format(new Date());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chatModel = new ViewModelProvider(this).get(ChatRoomViewModel.class);
        binding = ActivityChatRoomBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        if(messages == null)
        {
            chatModel.messages.postValue( messages = new ArrayList<ChatMessage>());
        }

        binding.sendButton.setOnClickListener(click -> {
            chatmessage = new ChatMessage();
            chatmessage.ChatRoom(binding.textInput.getText().toString(), currentDateandTime, true);
            messages.add(chatmessage);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");
        });
        binding.recieveButton.setOnClickListener(click -> {
            chatmessage = new ChatMessage();
            chatmessage.ChatRoom(binding.textInput.getText().toString(), currentDateandTime, false);
            messages.add(chatmessage);
            myAdapter.notifyItemInserted(messages.size() - 1);
            binding.textInput.setText("");
        });

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));
        binding.recycleView.setAdapter(myAdapter = new RecyclerView.Adapter<MyRowHolder>() {

            @NonNull
            @Override
            public MyRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View source = null;
                if (viewType == 0) {
                    source = getLayoutInflater().inflate(R.layout.sent_message, parent, false);
                } else if (viewType == 1) {
                    source = getLayoutInflater().inflate(R.layout.recieve_message, parent, false);
                }
                return new MyRowHolder(source);
            }

            @Override
            public void onBindViewHolder(@NonNull MyRowHolder holder, int position){
                holder.messageText.setText(messages.get(position).getMessage());
                holder.timeText.setText(messages.get(position).getTime());


            }

            @Override
            public int getItemViewType(int pos){
                if (messages.get(pos).isSentButton()) {
                    return 0;
                }
                else{
                    return 1;
                }
            }

            @Override
            public int getItemCount() {
                return messages.size();
            }
        });
    }

    class MyRowHolder extends RecyclerView.ViewHolder {
        TextView messageText;
        TextView timeText;
        Button sendButton;
        Button recieveButton;

        public MyRowHolder(@NonNull View itemView) {
            super(itemView);

            messageText = itemView.findViewById(R.id.message);
            timeText = itemView.findViewById(R.id.time);
            sendButton = itemView.findViewById(R.id.sendButton);
            recieveButton = itemView.findViewById(R.id.recieveButton);

        }
    }
    }