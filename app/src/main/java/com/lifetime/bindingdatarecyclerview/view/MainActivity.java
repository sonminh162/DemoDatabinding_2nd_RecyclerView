package com.lifetime.bindingdatarecyclerview.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Toast;

import com.lifetime.bindingdatarecyclerview.R;
import com.lifetime.bindingdatarecyclerview.databinding.ActivityMainBinding;
import com.lifetime.bindingdatarecyclerview.model.Post;
import com.lifetime.bindingdatarecyclerview.model.User;
import com.lifetime.bindingdatarecyclerview.utils.GridSpacingItemDecoration;

import java.util.ArrayList;

import static com.lifetime.bindingdatarecyclerview.BR.user;

public class MainActivity extends AppCompatActivity implements PostsAdapter.PostsAdapterListener {
    private MyClickHandlers handlers;
    private PostsAdapter mAdapter;
    private RecyclerView recyclerView;
    private ActivityMainBinding binding;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        handlers = new MyClickHandlers(this);

        renderProfile();

        initRecyclerView();
    }

    private void initRecyclerView(){
        recyclerView = binding.content.recyclerView;
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(3,dpToPx(4),true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setNestedScrollingEnabled(false);
        mAdapter = new PostsAdapter(getPosts(),this);
        recyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Post> getPosts() {
        ArrayList<Post> posts = new ArrayList<>();
        for(int i = 1; i < 10; i++){
            Post post = new Post();
            post.setImageUrl("https://api.androidhive.info/images/nature/" + i + ".jpg");

            posts.add(post);
        }
        return posts;
    }

    private void renderProfile(){
        user = new User();
        user.setName("David Attenborough");
        user.setEmail("david@natgeo.com");
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg");
        user.setAbout("Naturallist");

        user.numberOfPosts.set(3400L);
        user.numberOfFollowing.set(150L);
        user.numberOfFollowers.set(3050890L);

        binding.setUser(user);

        binding.content.setHandlers(handlers);
    }

    @Override
    public void onPostClicked(Post post) {
        Toast.makeText(getApplicationContext(), "Post clicked!", Toast.LENGTH_SHORT).show();
    }

    public class MyClickHandlers {

        Context context;

        public MyClickHandlers(Context context){
            this.context = context;
        }

        public void onProfileFabClicked(View view){
            user.setName("Sir David Attenborough");
            user.setProfileImage("https://api.androidhive.info/images/nature/david1.jpg");

            user.numberOfPosts.set(5500L);
            user.numberOfFollowers.set(5050890L);
            user.numberOfFollowing.set(180L);
        }

        public boolean onProfileImageLongPressed(View view){
            Toast.makeText(getApplicationContext(), "Profile image long pressed", Toast.LENGTH_SHORT).show();
            return false;
        }

        public void onFollowersClicked(View view){
            Toast.makeText(context, "Followers is clicked", Toast.LENGTH_SHORT).show();
        }

        public void onFollowingClicked(View view){
            Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show();
        }

        public void onPostsClicked(View view){
            Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show();
        }
    }

    private int dpToPx(int dp){
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dp,r.getDisplayMetrics()));
    }
}
