package com.iyad.sultan.linksaver.Controller;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.iyad.sultan.linksaver.R;

/**
 * Created by salkhmis on 12/2/2016.
 */

public class LinkController {

    private final Context context;
    public LinkController(Context c){
        context =c;
    }
    public   void openLink(String link){
        Intent more = new Intent(Intent.ACTION_VIEW);

        more.setData(Uri.parse(link));
        try {
           context.startActivity(Intent.createChooser(more, context.getResources().getText(R.string.open_using)));
        } catch (Exception e) {
            Toast.makeText(context, "Error happened! Plz send Developer", Toast.LENGTH_SHORT).show();

        }
    }

    public void shareLink(String title,String link){
        Intent more = new Intent(Intent.ACTION_SEND);
        more.setType("text/plain");

        more.putExtra(Intent.EXTRA_TEXT, title + "\n" + link);
        try {
            context.startActivity(Intent.createChooser(more, context.getResources().getText(R.string.share_using)));
        } catch (Exception e) {
            Toast.makeText(context, "Error happened! Plz send Developer", Toast.LENGTH_SHORT).show();

        }
    }

}
