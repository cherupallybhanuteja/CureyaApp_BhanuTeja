package com.example.cureya_chatbot.ui.quizpart;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cureya_chatbot.R;

import java.util.ArrayList;

public class Myadapter extends RecyclerView.Adapter <Myadapter.MyViewHolder>{
    ArrayList<Model> mList;
    Context context;
    public  Myadapter(Context context,ArrayList<Model> mList){
        this.mList=mList;
        this.context=context;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.items , parent ,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Model model = mList.get(position);
        holder.title.setText(model.getTitle());
        holder.optn1.setText(model.getOptn1());
        holder.optn2.setText(model.getOptn2());
        holder.optn3.setText(model.getOptn3());

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{
TextView title,optn1,optn2,optn3;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.title);
            optn1=itemView.findViewById(R.id.optn1);
            optn2=itemView.findViewById(R.id.optn2);
            optn3=itemView.findViewById(R.id.optn3);
        }
    }
}
