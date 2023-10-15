package com.ensaj;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.widget.SearchView;

import com.ensaj.adapter.StarAdapter;
import com.ensaj.beans.Star;
import com.ensaj.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private StarService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        stars = new ArrayList<>();
        service = StarService.getInstance();
        init();
        recyclerView = findViewById(R.id.recycle_view);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null) {
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.share) {
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder.from(this).setType(mimeType).setChooserTitle("Stars").setText(txt).startChooser();
        }
        return super.onOptionsItemSelected(item);
    }


    public void init() {
        service.create(new Star("kate bosworth", "https://upload.wikimedia.org/wikipedia/commons/thumb/e/e6/Kate_Bosworth_Deauville_2011.jpg/330px-Kate_Bosworth_Deauville_2011.jpg", 3.5f));
        service.create(new Star("george clooney", "https://upload.wikimedia.org/wikipedia/commons/thumb/9/92/George_Clooney-4_The_Men_Who_Stare_at_Goats_TIFF09_%28cropped%29.jpg/170px-George_Clooney-4_The_Men_Who_Stare_at_Goats_TIFF09_%28cropped%29.jpg", 3));
        service.create(new Star("michelle rodriguez", "https://ntvb.tmsimg.com/assets/assets/219616_v9_bb.jpg", 5));
        service.create(new Star("george clooney", "https://imgsrc.cineserie.com/2023/02/550725.jpg?ver=1", 1));
        service.create(new Star("louise bouroin", "https://fr.web.img4.acsta.net/pictures/15/10/06/10/23/291029.jpg", 5));
        service.create(new Star("louise bouroin", "https://fr.web.img4.acsta.net/pictures/15/10/06/10/23/291029.jpg", 1));
    }
}