package com.example.quizapphanan.adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapphanan.R;
import com.example.quizapphanan.models.quiz;
import com.example.quizapphanan.questionactivity;
import com.example.quizapphanan.utils.IconPicker;
import com.example.quizapphanan.utils.colorpicker;

import java.util.List;

public class quizadapter extends RecyclerView.Adapter<quizadapter.QuizViewHolder>  {
    Context context;
    List<quiz> quizzes;
  //hashmap
    public quizadapter(Context context, List<quiz> quizzes) {
        this.context = context;
        this.quizzes = quizzes;
    }

    @Override
    public QuizViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.subj_item, parent,false);
        return new QuizViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuizViewHolder holder, int position) {
        holder.textViewTitle.setText(quizzes.get(position).getTitle());
        holder.cardContainer.setCardBackgroundColor(Color.parseColor(colorpicker.getInstance().getcolor()));
        holder.iconView.setImageResource(IconPicker.getInstance().getIcon());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,quizzes.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(context, questionactivity.class);
                intent.putExtra("DATE",quizzes.get(position).getTitle());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return quizzes.size();
    }


    public class QuizViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        ImageView iconView;
        CardView cardContainer;

        public QuizViewHolder(View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.quizTitle);
            iconView = itemView.findViewById(R.id.quizIcon);
            cardContainer = itemView.findViewById(R.id.cardContainer);
        }

    }

}