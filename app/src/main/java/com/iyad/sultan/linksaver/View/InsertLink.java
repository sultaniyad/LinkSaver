package com.iyad.sultan.linksaver.View;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.iyad.sultan.linksaver.Model.Link;
import com.iyad.sultan.linksaver.Model.RModel;
import com.iyad.sultan.linksaver.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InsertLink extends AppCompatActivity {
    SimpleDateFormat format1;
    RModel rModel;
    Calendar c;
    @BindView(R.id.toolbar2)
    Toolbar toolbar;
    @BindView(R.id.link_title)
    TextView txt_title;
    @BindView(R.id.link_link)
    TextView txt_link;
    @BindView(R.id.spinner)
    Spinner spinner;

    private int category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_link);
        ButterKnife.bind(this);
        c = Calendar.getInstance();
        rModel = new RModel();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        format1 = new SimpleDateFormat("yyyy-MM-dd");

        //Set Category Listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                category = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                category = 0;
            }
        });


    }


    @OnClick(R.id.btn_add)
    public void submit(View view) {

        addNeeLink(txt_title.getText().toString(), txt_link.getText().toString(), category);

    }

    void addNeeLink(String title, String link, int category) {

        //Validation
        if(!link.contains("http"))
             link = "http://" + link;

        if(!Patterns.WEB_URL.matcher(link).matches()) {
            Toast.makeText(this, R.string.URL_INVALID, Toast.LENGTH_SHORT).show();
            return;
        }



        Link l = new Link();
        l.setTitle(title);
        l.setLink(link);
        l.setCategory(category);
        l.setDate(format1.format(c.getTime()));
        l.setImportant(false);
        if (rModel.insertLink(l))
            Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
        else Toast.makeText(this,  R.string.error, Toast.LENGTH_SHORT).show();
        finish();

    }
//

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
