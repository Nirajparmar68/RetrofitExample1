package hrt.android.retrofitexample1;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetroModal {
    Retrofit rf;
    Api api;
    Call<List<Hero>> call;
    List<Hero> heroList;
    String[] heroes;

    public RetroModal() {
        rf = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = rf.create(Api.class);
        call = api.getHeroes();
    }

    public String[] getData() {

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroList = response.body();
                heroes = new String[heroList.size()];
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                }
                Log.d("retro","response");
            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
        return heroes;
    }
    public List<Hero> getPositionalData() {

        call.enqueue(new Callback<List<Hero>>() {
            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {
                heroList = response.body();

            }
            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
        return heroList;
    }

}
