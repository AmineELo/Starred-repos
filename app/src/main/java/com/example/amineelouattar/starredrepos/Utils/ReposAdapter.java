package com.example.amineelouattar.starredrepos.Utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.amineelouattar.starredrepos.R;
import com.example.amineelouattar.starredrepos.models.Repos;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by amineelouattar on 12/30/17.
 */

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {

    private List<Repos> repos;
    private Context context;

    public ReposAdapter(List<Repos> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View item = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_content, parent, false);

        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Repos repo = repos.get(position);

        holder.title.setText(repo.getTitle());
        holder.description.setText(repo.getDescription());
        holder.username.setText(repo.getUsername());
        holder.rating.setText(repo.getRating());

        Picasso.with(context).load(repo.getAvatar_url()).into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return repos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView  title, description, username, rating;
        public ImageView avatar;

        public ViewHolder(View view){
            super(view);

            title = (TextView) view.findViewById(R.id.title);
            description = (TextView) view.findViewById(R.id.description);
            username = (TextView) view.findViewById(R.id.username);
            rating = (TextView) view.findViewById(R.id.rating);

            avatar = (ImageView) view.findViewById(R.id.avatar);

        }
    }

}
