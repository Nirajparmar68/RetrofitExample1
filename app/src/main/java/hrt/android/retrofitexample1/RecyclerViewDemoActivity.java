package hrt.android.retrofitexample1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecyclerViewDemoActivity extends AppCompatActivity {

    Api api;
    Call<List<Hero>> call;
    List<Hero> heroList;
    String[] heroes;
    String[] images;
    Retrofit rf;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_demo);
        recyclerView = findViewById(R.id.recyclerView);
        rf = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = rf.create(Api.class);
        call = api.getHeroes();
        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroList = response.body();

                heroes = new String[heroList.size()];
                images = new String[heroList.size()];
                for (int i=0;i<heroList.size();i++)
                {
                    heroes[i] = heroList.get(i).getName();
                    images[i] = heroList.get(i).getImageurl();
                }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new RecyclerAdapter(getApplicationContext(),heroes,images));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });

    }
}
