package com.iyad.sultan.linksaver.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImportantLinks extends Fragment implements RecAdapter.CommunicationInterface {

    private static boolean isFragmentVisible;
    private RModel rm;
    private RealmResults<Link> results;
    private RecAdapter adp;

    public ImportantLinks() {
        // Required empty public constructor
    }

    @BindView(R.id.rec_important)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_important_links, container, false);
        ButterKnife.bind(this, v);


        //Realm
        rm = new RModel();
        results = rm.getLinksByImportant();
        adp = new RecAdapter(results, this);
        recyclerView.setAdapter(adp);

        LinearLayoutManager gridL = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(gridL);

        results.addChangeListener(callback);

        return v;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //remove callback from attached result
        results.removeChangeListener(callback);
        //remove realm object
        rm.removeRealm();
        //nor visible to user
        isFragmentVisible = false;
    }

    @Override
    public void onImportantStatusChange(int position) {
        //change in realm  then hide change object from view(using notifyItemRemoved not notifyItemChanged) because it's no exit in realm anymore
        rm.chnageStatus(position);
        adp.notifyItemRemoved(position);

    }

    @Override
    public void onDeleteLinkClicked(int position) {
        adp.notifyItemRemoved(position);
        rm.deleteLink(position);
    }

    @Override
    public void onOpenLinkClicked(int position) {

    }

    //callback for realm listener
    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            //if fragment visible do not call notifyDataSetChanged
            if (!isFragmentVisible) {
                adp.notifyDataSetChanged();
                Toast.makeText(getActivity(), "important", Toast.LENGTH_SHORT).show();
            }
        }
    };


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            isFragmentVisible = true;
            Log.i("test", isFragmentVisible + " " + "" + isVisibleToUser);

        } else {
            isFragmentVisible = false;
            Log.i("test", isFragmentVisible + " " + "" + isVisibleToUser);
        }

    }
}
