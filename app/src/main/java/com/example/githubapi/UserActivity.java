package com.example.githubapi;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.githubapi.models.GithubUser;
import com.example.githubapi.rest.ApiClient;
import com.example.githubapi.rest.GitHubUserEndpoints;
import com.squareup.picasso.Picasso;

import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    ImageView profilePic;
    TextView usernameTV, followersTV,followingTV, nameTv,emailTV;
    Button reposButton;
    Bundle extras;
    String newString;
    Bitmap myImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        profilePic = findViewById(R.id.avatar);
        usernameTV= findViewById(R.id.username);
        followersTV = findViewById(R.id.followers);
        followingTV= findViewById(R.id.following);
        nameTv = findViewById(R.id.name_text_view);
        emailTV= findViewById(R.id.email);
        reposButton =findViewById(R.id.own_repos);
        extras = getIntent().getExtras();
        newString = extras.getString("USERNAME");
        loadData();

    }

    private void loadData() {
        final GitHubUserEndpoints apiService = ApiClient.getClient().create(GitHubUserEndpoints.class);
        Call<GithubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                ImageDownloader task = new ImageDownloader();
                try {
                    myImage = task.execute(response.body().getAvatar()).get();
                    profilePic.setImageBitmap(myImage);
//                    profilePic.getLayoutParams().height=220;
//                    profilePic.getLayoutParams().width=220;
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if (response.body().getName() == null){
                    nameTv.setText(String.format("Login: %s", response.body().getName()));
                }else {
                    nameTv.setText(String.format("Login: %s", response.body().getName()));
                }
                usernameTV.setText(String.format("Username: %s", response.body().getUsername()));
                followersTV.setText(String.format("Followers: %s", response.body().getFollowers()));
                followingTV.setText(String.format("Following: %s", response.body().getFollowing()));


                if (response.body().getEmail() == null) {
                    emailTV.setText(String.format("Email: %s", "No email provided"));
                }else {
                    emailTV.setText(String.format("Email: %s", response.body().getName()));
                }



            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void LoadOwnRepos(View view) {
        Intent intent = new Intent(this, RepositoriesActivity.class);
        intent.putExtra("username", newString);
        startActivity(intent);
    }
}
