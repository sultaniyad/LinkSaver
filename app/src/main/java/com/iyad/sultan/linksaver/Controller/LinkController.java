package com.iyad.sultan.linksaver.Controller;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by salkhmis on 12/2/2016.
 */

public class LinkController {

    private Context context;
    public LinkController(Context c){
        context =c;
    }
    public   void openLink(String link){
        Intent more = new Intent(Intent.ACTION_SEND);
        more.setType("text/plain");

        more.putExtra(Intent.EXTRA_TEXT, link);
        try {
           context.startActivity(Intent.createChooser(more, "Open using"));
        } catch (Exception e) {
            Toast.makeText(context, "Error happened! Plz send Developer", Toast.LENGTH_SHORT).show();

        }
    }
    public   void sentWhatsUp(String title,String link){
        Intent shareWattsApp = new Intent(Intent.ACTION_SEND);
        shareWattsApp.setType("text/plain");
        shareWattsApp.setPackage("com.whatsapp");
        shareWattsApp.putExtra(Intent.EXTRA_TEXT, title + "\n" + link);
        try {
            context.startActivity(shareWattsApp);
        } catch (Exception e) {
            Toast.makeText(context, "تأكد من تنصيب واتساب", Toast.LENGTH_SHORT).show();

        }
    }
    public   void sentTwitter(String title,String link){
        Intent shareTwitter = new Intent(Intent.ACTION_SEND);
        shareTwitter.setType("text/plain");
        shareTwitter.setPackage("com.twitter.android");
        shareTwitter.putExtra(Intent.EXTRA_TEXT, title + "\n" + link);
        try {
            context.startActivity(shareTwitter);
        } catch (Exception e) {
            Toast.makeText(context, "تأكد من تنصب تويتر", Toast.LENGTH_SHORT).show();

        }
    }

    public void shareLink(String title,String link){
        Intent more = new Intent(Intent.ACTION_SEND);
        more.setType("text/plain");

        more.putExtra(Intent.EXTRA_TEXT, title + "\n" + link);
        try {
            context.startActivity(Intent.createChooser(more, "Share using"));
        } catch (Exception e) {
            Toast.makeText(context, "Error happened! Plz send Developer", Toast.LENGTH_SHORT).show();

        }
    }

}
