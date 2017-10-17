package kmitl.lab07.paniti58070080.mylazyinstagram;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import kmitl.lab07.paniti58070080.mylazyinstagram.adapter.PostAdapter;
import kmitl.lab07.paniti58070080.mylazyinstagram.api.LazyInstagramApi;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private PostAdapter postAdapter;
    private boolean layout = true;
    private String user = "nature";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getUserProfile(user);

        postAdapter = new PostAdapter(this);
    }

    private void getUserProfile(String user){
        OkHttpClient client = new OkHttpClient.Builder().build();
        Retrofit retrofit = new Retrofit.Builder()
                                .client(client)
                                .baseUrl(LazyInstagramApi.BASE_URL)
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();
        LazyInstagramApi lazyInstagramApi = retrofit.create(LazyInstagramApi.class);

        Call<UserProfile> call = lazyInstagramApi.getProfile(user);
        call.enqueue(new Callback<UserProfile>() {
            @Override
            public void onResponse(Call<UserProfile> call, Response<UserProfile> response) {
                if(response.isSuccessful()){
                    postAdapter.setPosts(response.body().getPosts());
                    UserProfile userProfile = response.body();
                    TextView textName = findViewById(R.id.textUser);
                    TextView textBio = findViewById(R.id.textBio);
                    TextView textFollowing = findViewById(R.id.textFollowing);
                    TextView textFollower = findViewById(R.id.textFollower);
                    TextView textPost = findViewById(R.id.textPost);
                    textName.setText("@"+userProfile.getUser());
                    textBio.setText(userProfile.getBio());
                    textPost.setText("Post\n"+userProfile.getPost());
                    textFollowing.setText("Following\n"+userProfile.getFollowing());
                    textFollower.setText("Follower\n"+userProfile.getFollower());
                    ImageView imageProfile = findViewById(R.id.imageProfile);
                    Glide.with(MainActivity.this).load(userProfile.getUrlProfile()).into(imageProfile);
                    RecyclerView recyclerView = findViewById(R.id.list);
                    if (layout){
                        recyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this, 3));
                    }
                    else{
                        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    }
                    recyclerView.setAdapter(postAdapter);
                }
            }

            @Override
            public void onFailure(Call<UserProfile> call, Throwable t) {

            }
        });

    }

    public void changeLayout(){
        layout = !layout;
        RecyclerView recyclerView = findViewById(R.id.list);
        if (layout){
            recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        }
        else{
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
        }
        recyclerView.setAdapter(postAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.ChangeLayout:
                changeLayout();
                return true;
            case R.id.user_android:
                getUserProfile("android");
                return true;
            case R.id.user_nature:
                getUserProfile("nature");
                return true;
            case R.id.user_cartoon:
                getUserProfile("cartoon");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}


