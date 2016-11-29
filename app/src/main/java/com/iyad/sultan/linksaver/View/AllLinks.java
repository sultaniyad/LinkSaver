package com.iyad.sultan.linksaver.View;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
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
public class AllLinks extends Fragment implements RecAdapter.CommunicationInterface {

    private static boolean isFragmentVisible;
    private RModel rm;
    private RealmResults<Link> results;
    private RecAdapter adp;

    public AllLinks() {
        // Required empty public constructor
    }


    @BindView(R.id.rec)
    RecyclerView rec;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_all_links, container, false);

        ButterKnife.bind(this, v);
//Realm
        rm = new RModel();
        results = rm.getLinksAll();
        adp = new RecAdapter(results, this);
        rec.setAdapter(adp);

        LinearLayoutManager gridL = new LinearLayoutManager(getActivity());
        rec.setLayoutManager(gridL);


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

    }

    //Interface implements

    //important clicked
    @Override
    public void onImportantStatusChange(int position) {
        //change in realm
        rm.chnageStatus(position);
       //i used notifyItemChanged because it's  exit in realm  other hand ImportantLinks i used notifyItemRemoved because it's no exit in realm anymore
        adp.notifyItemChanged(position);

    }

    @Override
    public void onDeleteLinkClicked(int position) {

        adp.notifyItemRemoved(position);
        rm.deleteLink(position);

    }

    @Override
    public void onOpenLinkClicked(int position) {
        Toast.makeText(getActivity(), "open link" + position, Toast.LENGTH_SHORT).show();
    }


    //callback for realm listener
    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            //if fragment visible do not call notifyDataSetChanged
            if(!isFragmentVisible) {
                adp.notifyDataSetChanged();
                Toast.makeText(getActivity(), "all link in change", Toast.LENGTH_SHORT).show();
            }




        }
    };
//check visibility
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

    //Update view
    @Override
    public void onResume() {
        super.onResume();
        adp.notifyDataSetChanged();
    }
}
