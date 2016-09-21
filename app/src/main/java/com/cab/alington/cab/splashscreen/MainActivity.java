package com.cab.alington.cab.splashscreen;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.cab.alington.cab.R;
import com.cab.alington.cab.splashscreen.datetime.CustomDateTimePicker;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, View.OnTouchListener {
    private static final int REQUEST_CODE = 100;
    FloatingActionButton fab;
    private ImageButton contact_pick;
    EditText name, mob, email, datetime;
    private TextInputLayout tiemail, tiphone;
    String num = "";
    String friend_contact_name, friend_contact_email;
    String hasNumber;
    String mob_no;
    int check_id = 0;
    private String contactId;
    private ImageView map1;
    private CustomDateTimePicker custom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        contact_pick = (ImageButton) findViewById(R.id.contact_picker);
        map1 = (ImageView) findViewById(R.id.map);
        map1.setOnClickListener(this);
        // referbtn = (Button) findViewById(R.id.refer);
        // btnSendFeedBack1.setOnClickListener(this);
        contact_pick.setOnClickListener(this);
        name = (EditText) findViewById(R.id.userform);
        email = (EditText) findViewById(R.id.usermail);
        mob = (EditText) findViewById(R.id.phone);
        datetime = (EditText) findViewById(R.id.timeform);
        tiemail = (TextInputLayout) findViewById(R.id.mailwrapper);
        tiemail.setErrorEnabled(false);
        datetime.setOnTouchListener(this);
        tiphone = (TextInputLayout) findViewById(R.id.phwrapper);
        tiphone.setErrorEnabled(false);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                YoYo.with(Techniques.RotateOutDownLeft).duration(500).playOn(findViewById(R.id.fab));
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        date();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void displayemail(Intent data) {
        Uri contactData = data.getData();
        Cursor c = getContentResolver().query(contactData, null, null, null, null);
        // cur.moveToFirst();
        if (c.getCount() > 0) {
            if (c.moveToFirst()) {
                String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));

                Log.e("id", id + c);
                Cursor emailCur = getContentResolver().query(ContactsContract.CommonDataKinds.Email.CONTENT_URI, null, ContactsContract.CommonDataKinds.Email.CONTACT_ID + " = ?", new String[]{id}, null);
                if (emailCur.getCount() > 0) {
                    while (emailCur.moveToNext()) {

                        int emdta = emailCur.getColumnIndex(ContactsContract.CommonDataKinds.Email.DATA);
                        // Log.e("ecursor", emailCur + "" + emdta);
                        friend_contact_email = emailCur.getString(emdta);
                        if (friend_contact_email != null) {
                            email.setText(friend_contact_email);        //print data
                        }
                    }
                    emailCur.close();
                }

            } else {
                email.setText("");
            }
        }
    }

    @Override
    public void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);
        switch (reqCode) {
            case (REQUEST_CODE):
                try {
                    if (resultCode == Activity.RESULT_OK) {
                        Uri contactData = data.getData();
                        Cursor c = getContentResolver().query(contactData, null, null, null, null);
                        if (c.moveToFirst()) {
                            check_id = c.getColumnIndex(ContactsContract.Contacts._ID);
                            // Log.e("check_id", String.valueOf(check_id));

                            contactId = c.getString(check_id);

                            int check_no = c.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER);

                            hasNumber = c.getString(check_no);
                            //Log.e("email", String.valueOf(check_no));
                            displayemail(data);
                            if (Integer.valueOf(hasNumber) == 1) {
                                Cursor numbers = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + contactId, null, null);
                                if (numbers.moveToFirst()) {
                                    num = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)).replaceAll("[\\s\\-()+]", "");

                                    friend_contact_name = numbers.getString(numbers.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                                    //Toast.makeText(this, "Number=" + num, Toast.LENGTH_LONG).show();
                                    // Log.e("value", num);

                                    if (friend_contact_name != null) {
                                        name.setText(friend_contact_name);
                                    }
                                    if (num != null) {
                                        Log.e("num", num);
                                        if (num.startsWith("977")) {
                                            Log.e("refer form", num);
                                            num = num.replaceFirst("977", "");
                                            mob.setText(num);
                                            Log.e("pick", num);
                                        } else if (num.startsWith("0")) {
                                            num = num.replaceFirst("0", "");
                                            Log.e("pick", num);
                                            mob.setText(num);
                                        } else {
                                            mob.setText(num);
                                        }


                                    }
                                    numbers.close();
                                }

                            }
                        }


                    }

                } catch (IllegalArgumentException e) {
                } catch (Exception e) {
                    e.printStackTrace();
                    //Log.e("Error :: ", e.toString());
                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.contact_picker:
                boolean result = Permission.Utility.checkPermission(this, Manifest.permission.READ_CONTACTS, 123, "READ CONTACT permission is necessary");
                if (result) {
                    getContact();
                }
                break;
            case R.id.map:

                Intent refermap = new Intent(this, MapsActivity.class);

                startActivityForResult(refermap, 1);

                overridePendingTransition(R.anim.abc_slide_in_bottom, R.anim.abc_slide_out_top);

                break;
        }
    }

    public void getContact() {
        mob.setText("");
        Intent intent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (v.getId() == R.id.timeform) {
            custom.showDialog();

        }
        return false;
    }

    public void date() {

        custom = new CustomDateTimePicker(this,
                new CustomDateTimePicker.ICustomDateTimeListener() {

                    @Override
                    public void onSet(Dialog dialog, Calendar calendarSelected,
                                      Date dateSelected, int year, String monthFullName,
                                      String monthShortName, int monthNumber, int date,
                                      String weekDayFullName, String weekDayShortName,
                                      int hour24, int hour12, int min, int sec,
                                      String AM_PM) {
                        //                        ((TextInputEditText) findViewById(R.id.edtEventDateTime))
                        datetime.setText("");
                        datetime.setText(monthShortName+" "
                                + calendarSelected.get(Calendar.DAY_OF_MONTH)
                                + "," +year+ "    "+ hour12 + ":" + min
                                + " " + AM_PM);
                    }

                    @Override
                    public void onCancel() {

                    }
                });
        /**
         * Pass Directly current time format it will return AM and PM if you set
         * false
         */
        custom.set24HourFormat(false);
    }

}
