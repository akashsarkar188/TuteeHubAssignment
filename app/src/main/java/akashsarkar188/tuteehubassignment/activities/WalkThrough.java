package akashsarkar188.tuteehubassignment.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import akashsarkar188.tuteehubassignment.R;
import akashsarkar188.tuteehubassignment.adapter.WelcomeSliderAdapter;

public class WalkThrough extends AppCompatActivity {

    ViewPager viewPager;

    int[] images = {R.drawable.smiley_placeholder,
            R.drawable.error_loading,
            R.drawable.profile_placeholder,
            R.drawable.like_heart_filled};

    String[] tagline = {"Tagline 1 !",
            "Tagline 2 !",
            "Tagline 3 !",
            "Tagline 4 !"};

    String[] subHeading = {"This is a subheading where you can write something !",
            "This is a subheading where you can write something !",
            "This is a subheading where you can write something !",
            "This is a subheading where you can write something !"};

    WelcomeSliderAdapter adapter;
    LinearLayout dotsLayout;
    ImageView[] Dots;
    int positionG = 0;
    int total = images.length - 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_walk_through);

        initView();
    }

    private void initView() {

        if (getWindow() != null) {
            getWindow().setNavigationBarColor(ContextCompat.getColor(WalkThrough.this, R.color.colorPrimary));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            }
            getWindow().setStatusBarColor(ContextCompat.getColor(WalkThrough.this, R.color.colorPrimary));
        }

        viewPager = findViewById(R.id.welcomeSliderViewPager);
        dotsLayout = findViewById(R.id.dotIndicatorLayout);

        adapter = new WelcomeSliderAdapter(WalkThrough.this, images, tagline, subHeading);

        viewPager.setAdapter(adapter);

        createDots(0);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                positionG = position;
                createDots(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void intentToLogin(View view) {
        startActivity(new Intent(WalkThrough.this, SignUp.class));
        finish();
    }

    private void createDots(int currant_position) {

        try {
            if (dotsLayout != null) {

                dotsLayout.removeAllViews();

            }
            Dots = new ImageView[images.length];

            for (int i = 0; i < images.length; i++) {

                Dots[i] = new ImageView(this);
                if (i == currant_position) {

                    Dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_filled_white));

                } else {
                    Dots[i].setImageDrawable(ContextCompat.getDrawable(this, R.drawable.dots_hollow_white));
                }

                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

                //params.setMargins(8, 0, 8, 0);
                dotsLayout.addView(Dots[i], params);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}