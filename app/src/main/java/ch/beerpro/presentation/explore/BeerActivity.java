package ch.beerpro.presentation.explore;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.List;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import ch.beerpro.R;
import ch.beerpro.domain.models.Beer;
import ch.beerpro.presentation.BaseActivityWithTheme;
import ch.beerpro.presentation.details.DetailsActivity;
import lombok.val;

public class BeerActivity extends BaseActivityWithTheme implements OnBeerlistItemInteractionListener {

    @BindView(R.id.toolbar)
    androidx.appcompat.widget.Toolbar toolbar;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private BeerViewModel model;
    private BeerRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beer);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(getIntent().getExtras().getString(String.valueOf(R.string.title_activity_beer)));

        MutableLiveData<String> currentCategory = new MutableLiveData<>();
        currentCategory.setValue(getIntent().getExtras().getString(String.valueOf(R.string.title_activity_beer)));

        model = ViewModelProviders.of(this).get(BeerViewModel.class);

        if ((getIntent().getExtras().getString(String.valueOf(R.string.type_of_activity_beer)).equals(String.valueOf(R.string.value_category)))) {
            model.getBeersByCategorie(currentCategory).observe(this, this::updateBeerlist);
        } else if ((getIntent().getExtras().getString(String.valueOf(R.string.type_of_activity_beer)).equals(String.valueOf(R.string.value_manufacturer)))) {
            model.getBeersByManufacturer(currentCategory).observe(this, this::updateBeerlist);
        }

        val layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new BeerRecyclerViewAdapter(this);
        recyclerView.setAdapter(adapter);
    }

    private void updateBeerlist(List<Beer> entries) {
        adapter.submitList(entries);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onBeerClickedListener(ImageView photo, Beer item) {
        Intent intent = new Intent(this, DetailsActivity.class);
        intent.putExtra(DetailsActivity.ITEM_ID, item.getId());
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, photo, "image");
        startActivity(intent, options.toBundle());
    }
}
