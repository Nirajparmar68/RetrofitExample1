package hrt.android.retrofitexample1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity
{
    ListView lvHeroes;
    Api api;
    Call<List<Hero>> call;
    List<Hero> heroList;
    String[] heroes;
    Retrofit rf;
    ArrayList<String> data;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvHeroes = findViewById(R.id.lvHeroes);

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
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }
                lvHeroes.setAdapter(new ArrayAdapter<String>(getApplicationContext(),
                        android.R.layout.simple_list_item_1,heroes));
            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });

        lvHeroes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),Main2Activity.class);
                intent.putExtra("name",heroList.get(position).getName());
                intent.putExtra("realname",heroList.get(position).getRealname());
                intent.putExtra("team",heroList.get(position).getTeam());
                intent.putExtra("firstappearance",heroList.get(position).getFirstappearance());
                intent.putExtra("createdby",heroList.get(position).getCreatedby());
                intent.putExtra("publisher",heroList.get(position).getPublisher());
                intent.putExtra("imageurl",heroList.get(position).getImageurl());
                intent.putExtra("bio",heroList.get(position).getBio());
                startActivity(intent);

            }
        });
    }

}
