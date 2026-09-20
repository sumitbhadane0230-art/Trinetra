package com.trinetra.personnel.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.trinetra.personnel.R;
import com.trinetra.personnel.models.ChatMessage;

import java.util.List;

public class ChatAdapter
        extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private final List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages) {
        this.messages = messages;
    }

    @Override
    public int getItemViewType(int position) {
        return messages.get(position).isFromUser()
                ? 1
                : 0;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent,
            int viewType
    ) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(
                        R.layout.item_chat_message,
                        parent,
                        false
                );

        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(
            @NonNull ChatViewHolder holder,
            int position
    ) {
        ChatMessage message = messages.get(position);

        holder.tvMessage.setText(
                message.getMessage()
        );

        if (message.isFromUser()) {
            holder.tvSender.setText("You");
        } else {
            holder.tvSender.setText("TRINETRA");
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    static class ChatViewHolder
            extends RecyclerView.ViewHolder {

        TextView tvSender;
        TextView tvMessage;

        ChatViewHolder(@NonNull View itemView) {
            super(itemView);

            tvSender =
                    itemView.findViewById(R.id.tvChatSender);

            tvMessage =
                    itemView.findViewById(R.id.tvChatMessage);
        }
    }
}