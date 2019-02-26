package hrt.android.retrofitexample1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DemoActivity extends AppCompatActivity {

    Button btnViewPager, btnListView,btnRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo);
        btnListView = findViewById(R.id.btnListView);
        btnViewPager = findViewById(R.id.btnViewPager);
        btnRecyclerView = findViewById(R.id.btnRecycler);
        btnRecyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),RecyclerViewDemoActivity.class);
                startActivity(intent);
            }
        });
        btnListView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnViewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ViewPagerActivity.class);
                startActivity(intent);
            }
        });
    }
}
