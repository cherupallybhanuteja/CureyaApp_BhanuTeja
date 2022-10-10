package com.example.cureya_chatbot;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cureya_chatbot.loadingAnim.Ball;
import com.example.cureya_chatbot.loadingAnim.BallView;

import com.eyalbira.loadingdots.LoadingDots;


import java.util.ArrayList;


public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {

  private ArrayList<com.example.cureya_chatbot.Message> messageList;
  //private Activity activity;

  public ChatAdapter(ArrayList<com.example.cureya_chatbot.Message> messageList) {
    this.messageList = messageList;

  }

  public ChatAdapter() {
  }

  @NonNull @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_message_one, parent, false);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    String message = messageList.get(position).getMessage();
    boolean isReceived = messageList.get(position).getIsReceived();


     if(isReceived){

     //  holder.root.removeAllViews();
       holder.ballView.setVisibility(View.GONE);
       holder.messageReceive.setVisibility(View.VISIBLE);
       holder.messageSend.setVisibility(View.GONE);
       holder.imgSend.setVisibility(View.GONE);
       holder.imgReceive.setVisibility(View.VISIBLE);
       holder.messageReceive.setText(message);

     }else {
//       holder.ballView.setVisibility(View.VISIBLE);

       holder.messageSend.setVisibility(View.VISIBLE);
       holder.messageReceive.setVisibility(View.GONE);
       holder.imgReceive.setVisibility(View.GONE);
       holder.imgSend.setVisibility(View.VISIBLE);
       holder.messageSend.setText(message);
      // addProgrammatically(holder.root);


     }
  }

  @Override public int getItemCount() {
    return messageList.size();
  }

  static class MyViewHolder extends RecyclerView.ViewHolder{
     ViewGroup root;
    TextView messageSend;
    TextView messageReceive;
    ImageView imgSend;
    BallView ballView;
    ImageView imgReceive;
    MyViewHolder(@NonNull View itemView) {
      super(itemView);
      messageSend = itemView.findViewById(R.id.message_send);
      messageReceive = itemView.findViewById(R.id.message_receive);
      imgSend=itemView.findViewById(R.id.imgUserAvatar);
      imgReceive=itemView.findViewById(R.id.imgbotavatar);
      ballView =itemView.findViewById(R.id.ballview);



  //    root = (ViewGroup) itemView.findViewById(R.id.root1);
/*    }
  }
  private void addProgrammatically(ViewGroup root) {
    LoadingDots loadingDots = new LoadingDots(root.getContext());
    loadingDots.setDotsCount(3);
    loadingDots.setDotsSizeRes(R.dimen.LoadingDots_dots_size_default);
    loadingDots.setDotsColor(Color.BLUE);
    root.addView(loadingDots, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));


  }

  private void stopAll(ViewGroup root) {
    int count = root.getChildCount();
    for (int index = 0; index < count; index++) {
      View view = root.getChildAt(index);
      if (view instanceof LoadingDots) {
        ((LoadingDots)view).stopAnimation();
      } else if (view instanceof ViewGroup) {
        stopAll((ViewGroup) view);
      }

 */
    }
  }
}
