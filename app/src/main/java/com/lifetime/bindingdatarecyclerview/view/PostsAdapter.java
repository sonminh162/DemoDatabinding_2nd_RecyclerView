package com.lifetime.bindingdatarecyclerview.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.lifetime.bindingdatarecyclerview.R;
import com.lifetime.bindingdatarecyclerview.databinding.PostRowItemBinding;
import com.lifetime.bindingdatarecyclerview.model.Post;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    private List<Post> postList;
    private LayoutInflater layoutInflater;
    private PostsAdapterListener listener;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final PostRowItemBinding binding;

        public MyViewHolder(PostRowItemBinding itemBinding) {
            super(itemBinding.getRoot());
            this.binding = itemBinding;
        }

        public void bindView(final Post post){
            binding.setPost(post);
            binding.thumbnail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        listener.onPostClicked(post);
                    }
                }
            });
        }
    }

    public PostsAdapter(List<Post> postList, PostsAdapterListener listener){
        this.postList = postList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PostsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null){
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        PostRowItemBinding binding =
                DataBindingUtil.inflate(layoutInflater, R.layout.post_row_item,parent,false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.MyViewHolder holder, int position) {
        holder.bindView(postList.get(position));
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public interface PostsAdapterListener {


        void onPostClicked(Post post);

    }


}
