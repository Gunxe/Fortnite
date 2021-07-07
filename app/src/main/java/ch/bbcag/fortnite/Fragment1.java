package ch.bbcag.fortnite;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import ch.bbcag.fortnite.adapter.ShopAdapter;
import ch.bbcag.fortnite.model.Bundles;

public class Fragment1 extends Fragment {
    List<Bundles> bundles = new ArrayList<Bundles>();
    RecyclerView recyclerView;

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater, @Nullable @org.jetbrains.annotations.Nullable ViewGroup container, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.reclyclerView);


        ShopAdapter shopAdapter = new ShopAdapter(getContext(), bundles );
        return inflater.inflate(R.layout.fragment1_layout, container, false);
    }
}
