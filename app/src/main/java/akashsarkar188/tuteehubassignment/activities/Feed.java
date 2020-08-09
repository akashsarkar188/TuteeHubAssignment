package akashsarkar188.tuteehubassignment.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import akashsarkar188.tuteehubassignment.R;
import akashsarkar188.tuteehubassignment.adapter.FeedRecyclerAdapter;
import akashsarkar188.tuteehubassignment.models.feed.Msg;

public class Feed extends AppCompatActivity {

    private RecyclerView feedRecyclerView;
    private FeedRecyclerAdapter adapter;
    private List<Msg> list = new ArrayList<>();
    private String dataURL = "https://heartover.com/tictok/API/index.php?p=showAllVideos";
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);

        initView();
        getData();
    }

    private void initView() {

        feedRecyclerView = findViewById(R.id.feedRecyclerView);
        progressDialog = new ProgressDialog(Feed.this);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        feedRecyclerView.setLayoutManager(new LinearLayoutManager(Feed.this, RecyclerView.VERTICAL, false));
        SnapHelper mSnapHelper = new PagerSnapHelper();
        mSnapHelper.attachToRecyclerView(feedRecyclerView);

        adapter = new FeedRecyclerAdapter(Feed.this, list);
        feedRecyclerView.setAdapter(adapter);
    }

    private void getData() {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(Feed.this);

            JSONObject param = new JSONObject();
            param.put("fb_id", "0");
            param.put("token", "test");

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, dataURL, param, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    parseData(response);
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(Feed.this, "Some error occurred (Volley).", Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseData(JSONObject response) {
        try {

            if (response.getString("code").equals("200")) {
                // Success
                if (list.size() > 0)
                    list.clear();
                JSONArray msg = response.getJSONArray("msg");
                for (int i = 0; i < msg.length(); i++) {
                    Msg model = new Gson().fromJson(msg.get(i).toString(), Msg.class);
                    list.add(model);
                }
                adapter.notifyDataSetChanged();

                if (progressDialog.isShowing())
                    progressDialog.dismiss();

            } else {
                Toast.makeText(Feed.this, "Some error occurred (Parse).", Toast.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}