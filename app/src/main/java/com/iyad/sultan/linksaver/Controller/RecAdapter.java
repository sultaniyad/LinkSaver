package com.iyad.sultan.linksaver.Controller;

import android.app.Activity;
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
    CommunicationInterface mCallback;
    //Constructor
    public RecAdapter(RealmResults<Link> r,CommunicationInterface com){
        results = r;

       try {
           mCallback  = com;
       }
       catch (ClassCastException e) {
           throw new ClassCastException(e.toString()
                   + " must implement CommunicationInterface!!!");
       }
    }
    public void setNewResult(RealmResults<Link> r){
        results =r;
    }

    //Interface
    public interface CommunicationInterface {
        void onImportantStatusChange(int position);
        void onDeleteLinkClicked(int position);
        void onOpenLinkClicked(int position);
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
        holder.txt_title.setText(link.getTitle());
        holder.txt_date.setText(link.getDate());
        holder.img_imporant.setImageResource(link.isImportant()?R.drawable.lightbulb_on:R.drawable.lightbulb_off);
       // holder.img_openlink.setImageResource(R.mipmap.ic_launcher);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    //ViewHolder
    public class myViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_imporant;
        private TextView txt_openlink;
        private ImageView img_delete;
        private ImageView img_vert_more;

        private TextView txt_title;
        private TextView txt_date;

        public myViewHolder(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_openlink = (TextView) itemView.findViewById(R.id.txt_open_link);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            img_imporant = (ImageView) itemView.findViewById(R.id.img_important);
            img_vert_more = (ImageView) itemView.findViewById(R.id.vert_more);

            //More vert clicked
            img_vert_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onOpenLinkClicked(getAdapterPosition());
                }
            });

            //Delete
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onDeleteLinkClicked(getAdapterPosition());
                }
            });
            //open Link
            txt_openlink.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onOpenLinkClicked(getAdapterPosition());
                }
            });
            //change important status
            img_imporant.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //**change first in realm then change interface
        int position = getAdapterPosition();
                    mCallback.onImportantStatusChange(position);
                    Link l = results.get(position);
                    img_imporant.setImageResource(l.isImportant()?R.drawable.lightbulb_on:R.drawable.lightbulb_off);
                    //change in realm
                    /*
                    * l.set*/
                }
            });
        }


    }


}
