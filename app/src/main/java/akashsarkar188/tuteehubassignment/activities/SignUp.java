package akashsarkar188.tuteehubassignment.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import akashsarkar188.tuteehubassignment.R;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void intentToFeed(View view) {
        startActivity(new Intent(SignUp.this, Feed.class));
        finish();
    }
}