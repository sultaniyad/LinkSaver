package com.iyad.sultan.linksaver.Controller;

import android.app.Activity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
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
    private RealmResults<Link> results;
    CommunicationInterface mCallback;

    //Constructor
    public RecAdapter(RealmResults<Link> r, CommunicationInterface com) {
        results = r;

        try {
            mCallback = com;
        } catch (ClassCastException e) {
            throw new ClassCastException(e.toString()
                    + " must implement CommunicationInterface!!!");
        }
    }

    public void setNewResult(RealmResults<Link> r) {
        results = r;
    }

    //Interface
    public interface CommunicationInterface {
        void onImportantStatusChange(int position);

        void onDeleteLinkClicked(int position);

        void onOpenLinkClicked(int position);
        void onShareLinkClicked(int position);


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
    public void onBindViewHolder(final myViewHolder holder, final int position) {
        final Link link = results.get(position);
        holder.txt_title.setText(link.getTitle());
        holder.txt_date.setText(link.getDate());
        holder.img_imporant.setImageResource(link.isImportant() ? R.drawable.ic_bookmark_yes : R.drawable.ic_bookmark_no);
        //change avatar
switch (link.getCategory()){
    case 1:        holder.img_avatar.setImageResource(R.drawable.ic_social_icon);
break;
    case 2:        holder.img_avatar.setImageResource(R.drawable.ic_video);
break;
    default:        holder.img_avatar.setImageResource(R.drawable.ic_link_icon);

}



    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    //ViewHolder
    public class myViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView img_imporant;
        private ImageView img_delete;
        private ImageView img_open_link;
        private ImageView img_avatar;
        private ImageView img_share_link;


        private TextView txt_title;
        private TextView txt_date;

        public myViewHolder(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            img_imporant = (ImageView) itemView.findViewById(R.id.img_important);
            img_open_link = (ImageView) itemView.findViewById(R.id.img_open_link);
            img_share_link = (ImageView) itemView.findViewById(R.id.img_share_link);
            img_avatar = (ImageView) itemView.findViewById(R.id.imageView3);


            //Set Listener
           img_open_link.setOnClickListener(this);
            img_share_link.setOnClickListener(this);
            img_imporant.setOnClickListener(this);
            img_delete.setOnClickListener(this);



        }


        @Override
        public void onClick(View view) {

            switch (view.getId()){

                case R.id.img_open_link:mCallback.onOpenLinkClicked(getAdapterPosition());break;

                case R.id.img_share_link:mCallback.onShareLinkClicked(getAdapterPosition());break;
                case R.id.img_important:mCallback.onImportantStatusChange(getAdapterPosition());break;
                case R.id.img_delete:mCallback.onDeleteLinkClicked(getAdapterPosition());break;





            }
        }
    }


}
