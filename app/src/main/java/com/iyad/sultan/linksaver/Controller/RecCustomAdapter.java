package com.iyad.sultan.linksaver.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.iyad.sultan.linksaver.Model.Link;
import com.iyad.sultan.linksaver.R;

import io.realm.RealmResults;

/**
 * Created by salkhmis on 12/2/2016.
 */

public class RecCustomAdapter extends RecyclerView.Adapter<RecCustomAdapter.CustomeViewHodler> {


    private RealmResults<Link> results;

    public  RecCustomAdapter(RealmResults<Link> Presults){
        this.results = Presults;
    }
    @Override
    public CustomeViewHodler onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item_2, parent, false);
        // set the view's size, margins, paddings and layout parameters
        return new CustomeViewHodler(v);
    }

    @Override
    public void onBindViewHolder(CustomeViewHodler holder, int position) {
        final Link link = results.get(position);
        holder.txt_linkTitle.setText( link.getTitle());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class CustomeViewHodler extends RecyclerView.ViewHolder
    {

        private TextView txt_linkTitle;
        private ImageButton img_open_link_2;
        private ImageButton img_delete_link_2;
        public CustomeViewHodler(View itemView) {
            super(itemView);
            txt_linkTitle = (TextView) itemView.findViewById(R.id.txt_link_title_2);
            img_open_link_2 = (ImageButton) itemView.findViewById(R.id.img_open_2);
            img_delete_link_2 = (ImageButton) itemView.findViewById(R.id.img_delete_2);
        }
    }
}
