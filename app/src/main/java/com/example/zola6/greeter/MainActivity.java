package com.example.zola6.greeter;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zola6.greeter.m_MySQL.Downloader;
import com.example.zola6.greeter.m_MySQL.SenderReceiver;

import static com.example.zola6.greeter.R.id.button;
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

        Button btn = (Button)findViewById(R.id.button);
        btn.setText("Születésnap");
        Button btn2 = (Button)findViewById(R.id.button2);
        btn2.setText("Névnap");
        Button btn3 = (Button)findViewById(R.id.button3);
        btn3.setText("Karácsony");
        Button btn4 = (Button)findViewById(R.id.button4);
        btn4.setText("Újév");
        Button btn5 = (Button)findViewById(R.id.button5);
        btn5.setText("Összes");

        final ImageButton imgBtn = (ImageButton)findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton flashButtonOn = (ImageButton) findViewById(R.id.imageButton);
                flashButtonOn.setImageResource(R.drawable.hu_flag);

                Button btn = (Button)findViewById(R.id.button);
                if (btn.getText() == "Születésnap") {
                    btn.setText("Birthday");
                }else{
                    btn.setText("Születésnap");
                    flashButtonOn.setImageResource(R.drawable.eng_flag);
                }

                Button btn2 = (Button)findViewById(R.id.button2);
                if (btn2.getText()=="Névnap") {
                    btn2.setText("Nameday");
                }else{
                    btn2.setText("Névnap");
                }

                Button btn3 = (Button)findViewById(R.id.button3);
                if (btn3.getText()=="Karácsony") {
                    btn3.setText("Christmas");
                }else{
                    btn3.setText("Karácsony");
                }

                Button btn4 = (Button)findViewById(R.id.button4);
                if (btn4.getText()=="Újév") {
                    btn4.setText("NewYear");
                }else{
                    btn4.setText("Újév");
                }

                Button btn5 = (Button)findViewById(R.id.button5);
                if (btn5.getText()=="Összes") {
                    btn5.setText("All");
                }else{
                    btn5.setText("Összes");
                }
            }
        });

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
                //sr.execute();
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
