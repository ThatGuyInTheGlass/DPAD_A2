package com.example.carzio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Cars> cars = new ArrayList<Cars>();
        cars.add(new Cars(R.drawable.audia3, "Audi", "A3", "2021", "$43,200"));
        cars.add(new Cars(R.drawable.fordfocus, "Ford", "Focus", "2020", "$19,000"));
        cars.add(new Cars(R.drawable.kiaceed, "Kia", "Ceed", "2020", "$22,150"));
        cars.add(new Cars(R.drawable.mazda3, "Mazda", "A3", "2019", "$22,750"));
        cars.add(new Cars(R.drawable.mercedesbenzhatchback, "Mercedez Benz", "Hatchback", "2021", "$27,500"));
        cars.add(new Cars(R.drawable.seatleon, "Seat", "Leon", "2018", "$21,000"));
        cars.add(new Cars(R.drawable.skodaoctavia, "Skoda", "Octavia", "2021", "$18,500"));
        cars.add(new Cars(R.drawable.volkswagengolf, "Volkswagen", "Golf", "2019", "$23,475"));

        RecyclerView recycler = findViewById(R.id.recycler);
        recycler.setHasFixedSize(false);

        RecyclerView.LayoutManager manager = new GridLayoutManager(getApplicationContext(), 1);
        recycler.setLayoutManager(manager);

        adapter = new CarAdapter(cars);
        recycler.setAdapter(adapter);

        ItemTouchHelper helper = new ItemTouchHelper(new SwipeToDeleteCallback(adapter));
        helper.attachToRecyclerView(recycler);


        FloatingActionButton btn = findViewById(R.id.fbtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(v.getContext(), AddCarActivity.class);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) v.getContext(),
                                btn,
                                "bg"
                        );
                startActivityForResult(i, 1, options.toBundle());
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @NonNull Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            Bundle b = data.getExtras();
            Cars user = (Cars) b.getSerializable("cars");
            adapter.AddCar(user);
        }

    }
}

