package kmitl.lab07.paniti58070080.mylazyinstagram.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;


import java.util.List;

import kmitl.lab07.paniti58070080.mylazyinstagram.Posts;
import kmitl.lab07.paniti58070080.mylazyinstagram.R;

class Holder extends RecyclerView.ViewHolder{

    public ImageView image;
    public TextView like;
    public TextView comment;

    public Holder(View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.image);
        like = itemView.findViewById(R.id.like);
        comment = itemView.findViewById(R.id.comment);
    }
}

public class PostAdapter extends RecyclerView.Adapter<Holder>{

    List<Posts> posts;


    public List<Posts> getPosts() {
        return posts;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
    }

    private Context context;

    public PostAdapter(Context context) {
        this.context = context;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.post_item, null, false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        itemView.setLayoutParams(layoutParams);
        Holder holder = new Holder(itemView);
        return holder;
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        ImageView image = holder.image;
        Glide.with(context).load(posts.get(position).getUrl()).into(image);

        holder.like.setText(" "+String.valueOf(posts.get(position).getLike()));
        holder.comment.setText(" "+String.valueOf(posts.get(position).getComment()));
    }

    @Override
    public int getItemCount() {
        if(posts != null){
            return posts.size();
        }
        return 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
