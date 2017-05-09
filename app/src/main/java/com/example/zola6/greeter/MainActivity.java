package com.example.zola6.greeter;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.zola6.greeter.m_MySQL.Downloader;
import com.example.zola6.greeter.m_MySQL.Sender;
import com.example.zola6.greeter.m_MySQL.SenderReceiver;

import static com.example.zola6.greeter.R.id.imageView;

public class MainActivity extends AppCompatActivity {

    String url="http://greeter.hostei.com/android.php";
    //String urlAddress="http://greeter.hostei.com/android_searcher.php";
    String urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
    String sms_label = "Birthday";
    String lang = "hu";
    SearchView sv;
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

        EditText nameTxt = (EditText)findViewById(R.id.editText);
        nameTxt.bringToFront();

        final ListView lv= (ListView) findViewById(R.id.lv);

        Spinner spin = (Spinner)findViewById(R.id.spin);
        spin.bringToFront();
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sms_label = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        final Downloader d = new Downloader(this, url, lv, lang);
        d.execute();

        final ImageButton imgBtn = (ImageButton)findViewById(R.id.imageButton);
        imgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageButton flashButtonOn = (ImageButton) findViewById(R.id.imageButton);
                flashButtonOn.setImageResource(R.drawable.hu_flag);

                ListView lv= (ListView) findViewById(R.id.lv);
                final ListView finalLv = lv;
                //urlAddress="http://greeter.hostei.com/android_sql_teszt.php";

                Button btn = (Button)findViewById(R.id.button);
                Button btn2 = (Button)findViewById(R.id.button2);
                Button btn3 = (Button)findViewById(R.id.button3);
                Button btn4 = (Button)findViewById(R.id.button4);
                Button btn5 = (Button)findViewById(R.id.button5);
                if (btn.getText() == "Születésnap") {
                    btn.setText("Birthday");
                    btn2.setText("Nameday");
                    btn3.setText("Christmas");
                    btn4.setText("NewYear");
                    btn5.setText("All");

                    String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en'";
                    SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
                    sr.execute();

                }else{
                    btn.setText("Születésnap");
                    btn2.setText("Névnap");
                    btn3.setText("Karácsony");
                    btn4.setText("Újév");
                    btn5.setText("Összes");
                    flashButtonOn.setImageResource(R.drawable.eng_flag);

                    String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu'";
                    SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
                    sr.execute();
                }
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            }
        });

        sv= (SearchView) findViewById(R.id.sv);
        noDataImg= (ImageView) findViewById(R.id.nodataImg);
        noNetworkImg= (ImageView) findViewById(R.id.noserver);

        //final ListView finalLv = lv;
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                urlAddress="http://greeter.hostei.com/android_searcher.php";
                final ListView finalLv = lv;
                SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
                sr.execute();
                sv.clearFocus();
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query) {
                return false;
            }
        });

    }

    public void buttonOnClick(View v){

        Button btn = (Button)findViewById(R.id.button);
        ListView lv= (ListView) findViewById(R.id.lv);
        final ListView finalLv = lv;
        urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
        if (btn.getText() == "Birthday"){
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en' AND sms_label='Birthday'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }else {
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu' AND sms_label='Birthday'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
            /*String url = "http://greeter.hostei.com/android_birthday.php";
            final ListView lv = (ListView) findViewById(R.id.lv);
            final Downloader d = new Downloader(this, url, lv, lang);
            d.execute();*/
        }
    }

    public void button2OnClick(View v){

        Button btn2 = (Button)findViewById(R.id.button2);
        ListView lv= (ListView) findViewById(R.id.lv);
        final ListView finalLv = lv;
        urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
        if (btn2.getText() == "Nameday"){
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en' AND sms_label='Nameday'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }else {
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu' AND sms_label='Nameday'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }
    }

    public void button3OnClick(View v){

        Button btn3 = (Button)findViewById(R.id.button3);
        ListView lv= (ListView) findViewById(R.id.lv);
        final ListView finalLv = lv;
        urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
        if (btn3.getText() == "Christmas"){
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en' AND sms_label='Christmas'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }else {
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu' AND sms_label='Christmas'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }
    }

    public void button4OnClick(View v){

        Button btn4 = (Button)findViewById(R.id.button4);
        ListView lv= (ListView) findViewById(R.id.lv);
        final ListView finalLv = lv;
        urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
        if (btn4.getText() == "NewYear"){
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en' AND sms_label='New_Year'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }else {
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu' AND sms_label='New_Year'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }
    }

    public void button5OnClick(View v){

        Button btn5 = (Button)findViewById(R.id.button5);
        ListView lv= (ListView) findViewById(R.id.lv);
        final ListView finalLv = lv;
        urlAddress="http://greeter.hostei.com/android_sql_teszt.php";
        if (btn5.getText() == "All"){
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='en'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }else {
            String query = "SELECT * FROM Message WHERE approved = 1 AND sms_language='hu'";
            SenderReceiver sr=new SenderReceiver(MainActivity.this,urlAddress,query, finalLv,noDataImg,noNetworkImg);
            sr.execute();
        }
    }

    public void button6OnClick(View v){
        EditText edt = (EditText)findViewById(R.id.editText);
        Button btn7 = (Button)findViewById(R.id.button7);
        Spinner spin = (Spinner)findViewById(R.id.spin);
        ImageView ImgV = (ImageView)findViewById(imageView);
        if(edt.getVisibility() == View.INVISIBLE){
            edt.setVisibility(View.VISIBLE);
            btn7.setVisibility(View.VISIBLE);
            spin.setVisibility(View.VISIBLE);
            ImgV.setVisibility(View.VISIBLE);
        }else{
            edt.setVisibility(View.INVISIBLE);
            edt.clearFocus();
            btn7.setVisibility(View.INVISIBLE);
            spin.setVisibility(View.INVISIBLE);
            ImgV.setVisibility(View.INVISIBLE);
        }
    }


    public void button7OnClick(View v){

        urlAddress="http://greeter.hostei.com/poster.php";
        EditText nameTxt = (EditText)findViewById(R.id.editText);
        //Spinner spin = (Spinner)findViewById(R.id.spin);

        if (sms_label.equals("Születésnap")){
            sms_label = "Birthday";
        }
        if (sms_label.equals("Névnap")){
            sms_label = "Nameday";
        }
        if (sms_label.equals("Karácsony")){
            sms_label = "Christmas";
        }
        if (sms_label.equals("Újév")){
            sms_label = "New_Year";
        }
        Sender s=new Sender(MainActivity.this,urlAddress,sms_label, nameTxt);
        s.execute();
    }

    int editclick = 0;
    public void editTextOnClick(View v){
        if (editclick == 0){
            EditText nameTxt = (EditText)findViewById(R.id.editText);
            nameTxt.setText("");
            editclick++;
        }
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