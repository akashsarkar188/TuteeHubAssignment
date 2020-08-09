package akashsarkar188.tuteehubassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import akashsarkar188.tuteehubassignment.R;
import akashsarkar188.tuteehubassignment.models.feed.Msg;
import pl.droidsonroids.gif.GifImageView;

public class FeedRecyclerAdapter extends RecyclerView.Adapter<FeedRecyclerAdapter.FeedViewHolder> {

    Context context;
    List<Msg> list;

    public FeedRecyclerAdapter(Context context, List<Msg> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public FeedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.feed_recycler_card, parent, false);
        return new FeedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeedViewHolder holder, int position) {
        Msg data = list.get(position);

        holder.userName.setText(data.getUserInfo().getUsername());
        holder.likeCount.setText(data.getCount().getLikeCount());
        holder.commentCount.setText(data.getCount().getVideoCommentCount());

        Glide.with(context)
                .load(data.getUserInfo().getProfilePic())
                .placeholder(R.drawable.profile_placeholder)
                .error(R.drawable.profile_placeholder)
                .into(holder.userProfilePic);

        Glide.with(context)
                .load(data.getGif())
                .placeholder(R.drawable.loading)
                .fitCenter()
                .error(R.drawable.error_loading)
                .into(holder.gifImageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FeedViewHolder extends RecyclerView.ViewHolder {
        GifImageView gifImageView;
        TextView likeCount, commentCount, userName;
        ImageView userProfilePic;

        public FeedViewHolder(@NonNull View itemView) {
            super(itemView);

            gifImageView = itemView.findViewById(R.id.gifImageView);
            likeCount = itemView.findViewById(R.id.likeCountTextView);
            commentCount = itemView.findViewById(R.id.commentCountTextView);
            userName = itemView.findViewById(R.id.userNameTextView);
            userProfilePic = itemView.findViewById(R.id.profilePictureImageView);
        }
    }
}
