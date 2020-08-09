package akashsarkar188.tuteehubassignment.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import akashsarkar188.tuteehubassignment.R;


public class WelcomeSliderAdapter extends PagerAdapter {

    private final LayoutInflater Inflater;
    private Context context;
    private int[] images;
    private String[] taglines;
    private String[] subHeading;

    public WelcomeSliderAdapter(Context context, int[] images, String[] taglines, String[] subHeading) {
        this.context = context;
        this.images = images;
        this.taglines = taglines;
        this.subHeading = subHeading;
        Inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View itemview = Inflater.inflate(R.layout.welcome_slider_layout, container, false);

        ImageView sliderImageView = itemview.findViewById(R.id.sliderImageView_welcomeSlider);
        TextView tagLineTextView = itemview.findViewById(R.id.mainTagline_welcomeSlider);
        TextView subHeadingTextView = itemview.findViewById(R.id.subCaption_welcomeSlider);

        sliderImageView.setImageResource(images[position]);

        tagLineTextView.setText(taglines[position]);
        subHeadingTextView.setText(subHeading[position]);

        container.addView(itemview);

        return itemview;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((RelativeLayout) object);
    }
}
