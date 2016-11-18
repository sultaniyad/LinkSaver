package com.iyad.sultan.linksaver.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Controller.RecAdapter;
import com.iyad.sultan.linksaver.Model.Link;
import com.iyad.sultan.linksaver.Model.RModel;
import com.iyad.sultan.linksaver.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class AllLinks extends Fragment implements RecAdapter.CommunicationInterface {


    public AllLinks() {
        // Required empty public constructor
    }


    @BindView(R.id.rec) RecyclerView rec;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_all_links, container, false);

        ButterKnife.bind(this, v);

        rec.setAdapter(new RecAdapter( new RModel().getLinksAll(),this));

        LinearLayoutManager gridL =new LinearLayoutManager(getActivity());
        rec.setHasFixedSize(true);
        rec.setLayoutManager(gridL);

        return v;
    }


    //Interface implements
    @Override
    public void onLinkSelected(int position) {
        Toast.makeText(getActivity(), "" + position, Toast.LENGTH_SHORT).show();
    }

}
