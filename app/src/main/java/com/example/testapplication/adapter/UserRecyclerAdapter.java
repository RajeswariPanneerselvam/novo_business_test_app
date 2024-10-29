package com.example.testapplication.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.R;
import com.example.testapplication.model.UsersModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserRecyclerAdapter extends RecyclerView.Adapter<UserRecyclerAdapter.RecyclerViewHolder> {
    private ArrayList<UsersModel> usersDataList;
    private Context context;
    public UserRecyclerAdapter(ArrayList<UsersModel> usersList, Context mContext){
        this.usersDataList=usersList;
        this.context=mContext;
    }
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user,parent,false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        UsersModel usersModel = usersDataList.get(position);
        holder.textView.setText(usersModel.getLogin());
    Picasso.get().load(usersModel.getAvatar_url()).into(holder.imageView);
//        holder.imageView.setImageURI(Uri.parse(usersModel.getAvatar_url()));
    }

    @Override
    public int getItemCount() {

        return usersDataList.size();
    }
    public class RecyclerViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textView=itemView.findViewById(R.id.recyclerTxt);
            imageView=itemView.findViewById(R.id.recyclerImg);
        }
    }
}
