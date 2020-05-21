package com.myapplication.mvvmsample.Demo;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.myapplication.mvvmsample.R;

import java.util.ArrayList;

public class WorkFragment extends Fragment {

    private ArrayList<Integer> arrayList;
    private RecyclerView recyclerView;

    private RecyceleAdapter adapter;

    public WorkFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.work_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setUpRecyclerView(view);
        loadDrawables();
        showRecycleView();

    }

    private void setUpRecyclerView(View view)
    {
        recyclerView = view.findViewById(R.id.frag_recycler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setHasFixedSize(true);


    }

    private void loadDrawables()
    {
        arrayList = new ArrayList<>();

        arrayList.add(R.drawable.nature1);
        arrayList.add(R.drawable.nature3);
        arrayList.add(R.drawable.nature2);
        arrayList.add(R.drawable.photoshot1);
        arrayList.add(R.drawable.sea2);
        arrayList.add(R.drawable.photoshot2);
        arrayList.add(R.drawable.sea1);

    }

    private void showRecycleView()
    {
        adapter = new RecyceleAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }

}
