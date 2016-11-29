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
public class SocialLink extends Fragment implements RecAdapter.CommunicationInterface {

@BindView(R.id.rec_social)RecyclerView rec;
    public SocialLink() {
        // Required empty public constructor
    }
    private static boolean isFragmentVisible;
    private static final int social = 1;
    private RModel rModel;
    private RecAdapter recAdapter;
    private RealmResults<Link> results;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View  v= inflater.inflate(R.layout.fragment_social_link, container, false);
        ButterKnife.bind(this,v);
        // Inflate the layout for this fragment
        rModel = new RModel();
        results = rModel.getLinksByCategory(social);
        recAdapter = new RecAdapter(results,this);

        rec.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec.setAdapter(recAdapter);
        results.addChangeListener(callback);
        return  v;
    }

    //callback for realm listener
    private RealmChangeListener callback = new RealmChangeListener() {
        @Override
        public void onChange(Object element) {
            //if fragment visible do not call notifyDataSetChanged
            if(!isFragmentVisible) {
                recAdapter.notifyDataSetChanged();
                Toast.makeText(getActivity(), "Social called", Toast.LENGTH_SHORT).show();

            }

        }
    };
    //check visibility
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
          isFragmentVisible = isVisibleToUser;

    }

    @Override
    public void onImportantStatusChange(int position) {

    }

    @Override
    public void onDeleteLinkClicked(int position) {

    }

    @Override
    public void onOpenLinkClicked(int position) {

    }

    @Override
    public void onResume() {
        super.onResume();
        recAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        results.removeChangeListener(callback);
        rModel.removeRealm();
    }
}
