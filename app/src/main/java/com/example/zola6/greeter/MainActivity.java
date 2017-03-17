package com.example.zola6.greeter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.zola6.greeter.m_MySQL.Downloader;
import com.example.zola6.greeter.m_MySQL.SenderReceiver;

import static com.example.zola6.greeter.R.id.editText;

public class MainActivity extends AppCompatActivity {

    String url="http://greeter.hostei.com/android.php";
    String urlAddress="http://greeter.hostei.com/android_searcher.php";
    SearchView sv;
    //ListView lv;
    ImageView noDataImg,noNetworkImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        //lv= (ListView) findViewById(R.id.lv);
        sv= (SearchView) findViewById(R.id.sv);
        noDataImg= (ImageView) findViewById(R.id.nodataImg);
        noNetworkImg= (ImageView) findViewById(R.id.noserver);

        final ListView finalLv = lv;
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
                sr.execute();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
                sr.execute();
                return false;
            }
        });

    }

    public void buttonOnClick(View v){
        String url="http://greeter.hostei.com/android_birthday.php";
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();
    }

    public void button2OnClick(View v){
        String url="http://greeter.hostei.com/android_nameday.php";
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();
    }

    public void button3OnClick(View v){
        String url="http://greeter.hostei.com/android_christmas.php";
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();
    }

    public void button4OnClick(View v){
        String url="http://greeter.hostei.com/android_newyear.php";
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();
    }

    public void button5OnClick(View v){
        String url="http://greeter.hostei.com/android.php";
        final ListView lv= (ListView) findViewById(R.id.lv);
        final Downloader d=new Downloader(this,url,lv);
        d.execute();
    }

    public void editTextOnClick(View v){
        EditText edt = (EditText)findViewById(R.id.editText);
        edt.setText("");
        edt.clearFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
