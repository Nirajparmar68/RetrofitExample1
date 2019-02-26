package hrt.android.retrofitexample1;

import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViewPagerActivity extends AppCompatActivity {

    ViewPager viewPager;
    Api api;
    Call<List<Hero>> call;
    List<Hero> heroList;
    String[] heroes;
    String[] images;
    Retrofit rf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);
        viewPager = findViewById(R.id.viewPager);
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
                /*heroes = new String[heroList.size()];
                images = new String[heroList.size()];
                for (int i = 0; i < heroList.size(); i++) {
                    heroes[i] = heroList.get(i).getName();
                    images[i] = heroList.get(i).getImageurl();
                }*/
                viewPager.setAdapter(new CustomAdapter(getSupportFragmentManager()));
            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.d("error:", t.getMessage());
            }
        });
    }

    private class CustomAdapter extends FragmentPagerAdapter {


        public CustomAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            return FragmentLoader.newInstance(heroList.get(position).getName() +
                            "\n" + heroList.get(position).getRealname() +
                            "\n" + heroList.get(position).getCreatedby() +
                            "\n" + heroList.get(position).getFirstappearance() +
                            "\n" + heroList.get(position).getPublisher() ,
                    heroList.get(position).getImageurl());

        }

        @Override
        public int getCount() {
            return heroList.size();
        }
    }
}
