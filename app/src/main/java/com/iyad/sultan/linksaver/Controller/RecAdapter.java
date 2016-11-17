package com.iyad.sultan.linksaver.Controller;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.iyad.sultan.linksaver.Model.Link;
import com.iyad.sultan.linksaver.R;

import java.util.List;

import butterknife.BindView;
import io.realm.RealmResults;

/**
 * Created by sultan on 11/17/16.
 */

public class RecAdapter extends RecyclerView.Adapter<RecAdapter.myViewHolder> {
    private  RealmResults<Link> results;
    public RecAdapter(RealmResults<Link> r){
        results = r;
    }
    public void setNewResult(RealmResults<Link> r){
        results =r;
    }

    @Override
    public myViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_item, parent, false);
        // set the view's size, margins, paddings and layout parameters

        myViewHolder vh = new myViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(myViewHolder holder, int position) {
        final Link link = results.get(position);
        holder.txt_title.setText(link.getTitle().toString());
        holder.txt_date.setText(link.getDate().toString());
       // holder.img_imporant.setImageResource(R.mipmap.ic_launcher);
       // holder.img_openlink.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    //ViewHolder
    public class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_imporant;
        private ImageView img_openlink;
        private TextView txt_title;
        private TextView txt_date;

        public myViewHolder(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);

        }
    }


}
