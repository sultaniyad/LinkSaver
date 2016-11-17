package com.iyad.sultan.linksaver.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Model.Link;
import com.iyad.sultan.linksaver.Model.RModel;
import com.iyad.sultan.linksaver.R;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertLink extends AppCompatActivity {

    RModel rModel ;
    Calendar c;
    @BindView(R.id.toolbar2)Toolbar toolbar;
    @BindView(R.id.link_title)TextView txt_title;
    @BindView(R.id.link_link)TextView txt_link;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_link);
        ButterKnife.bind(this);
        c=Calendar.getInstance();
        rModel =new RModel();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);





    }


    @OnClick(R.id.btn_add)
    public void submit(View view) {

        addNeeLink(txt_title.getText().toString(),txt_link.getText().toString(),1);
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();

    }

    void addNeeLink(String title,String link,int category){

        Link l=new Link();
        l.setTitle(title);
        l.setLink(link);
        l.setCategory(category);

        l.setDate(c.getTime());
        l.setImportant(false);
       if(  rModel.insertLink(l))
        Toast.makeText(this, "Done", Toast.LENGTH_SHORT).show();
        else Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        rModel.removeRealm();
    }
}
