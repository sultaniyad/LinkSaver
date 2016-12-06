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

        //PopMenu Interface
        void popMenuOpenlink(int position);

        void popMenuSharelink(int position);

        void popMenuDeletelink(int position);
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
        holder.img_imporant.setImageResource(link.isImportant() ? R.drawable.lightbulb_on : R.drawable.lightbulb_off);
        //change avatar
switch (link.getCategory()){
    case 1:        holder.img_avatar.setImageResource(R.drawable.ic_social_icon);
break;
    case 2:        holder.img_avatar.setImageResource(R.drawable.ic_video_icon);
break;
    default:        holder.img_avatar.setImageResource(R.drawable.ic_link_icon);

}

        holder.img_vert_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPopmenu(holder.img_vert_more, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    private void showPopmenu(View v, final int position) {
        PopupMenu popupMenu = new PopupMenu(v.getContext(), v);
        MenuInflater menuInflater = popupMenu.getMenuInflater();
        menuInflater.inflate(R.menu.pop_pop_menu, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.open_link:
                        mCallback.popMenuOpenlink(position);
                        break;
                    case R.id.share_link:
                        mCallback.popMenuSharelink(position);
                        break;
                    case R.id.delete_link:
                        mCallback.popMenuDeletelink(position);
                        break;
                }
                return false;
            }
        });
        popupMenu.show();
    }

    //ViewHolder
    public class myViewHolder extends RecyclerView.ViewHolder {
        private ImageView img_imporant;
        private ImageView img_delete;
        private ImageView img_vert_more;
        private ImageView img_avatar;

        private TextView txt_title;
        private TextView txt_date;

        public myViewHolder(View itemView) {
            super(itemView);

            txt_title = (TextView) itemView.findViewById(R.id.txt_title);
            txt_date = (TextView) itemView.findViewById(R.id.txt_date);
            img_delete = (ImageView) itemView.findViewById(R.id.img_delete);
            img_imporant = (ImageView) itemView.findViewById(R.id.img_important);
            img_vert_more = (ImageView) itemView.findViewById(R.id.vert_more);
            img_avatar = (ImageView) itemView.findViewById(R.id.imageView3);


         /*   //More vert clicked
            img_vert_more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onOpenLinkClicked(getAdapterPosition());

                }
            });
*/
            //Delete
            img_delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCallback.onDeleteLinkClicked(getAdapterPosition());
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
                    img_imporant.setImageResource(l.isImportant() ? R.drawable.lightbulb_on : R.drawable.lightbulb_off);
                    //change in realm
                    /*
                    * l.set*/
                }
            });
        }


    }


}
