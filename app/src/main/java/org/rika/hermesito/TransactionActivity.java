package org.rika.hermesito;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import org.rika.hermesito.util.DatePickerFragment;
import org.rika.hermesito.util.TimePickerFragment;

import java.util.ArrayList;
import java.util.Calendar;


public class TransactionActivity extends FragmentActivity {

    Transaction transaction;
    DataLib datalib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);

        datalib = DataLib.getInstance(this);

        setupDateTime();
        setupAcctList();
        addCostListener();
        addSubtotalListener();
        addTaxListener();
        addTipListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_transaction, menu);
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

    private void setupDateTime(){
        final Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int min = cal.get(Calendar.MINUTE);

        EditText date_field = (EditText) findViewById(R.id.transaction_date);
        date_field.setText(year + "-" + month + "-" + day);
        EditText time_field = (EditText) findViewById(R.id.transaction_time);
        time_field.setText(hour + ":" + min);
    }

    private void setupAcctList(){
        String[] accts = datalib.getAcctList();
        SpinnerAdapter acct_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, accts);
        Spinner spin_accts = (Spinner) findViewById(R.id.acct_list_spinner);
        spin_accts.setAdapter(acct_adapter);
    }

    private void addCostListener(){
        //TODO: on change, recalculate the subtotal, tax, total
    }

    private void addSubtotalListener(){

    }

    private void addTaxListener(){

    }

    private void addTipListener(){
        //TODO: on change, update total
    }

    private void addTotalListener(){
        //TODO: on change, recalculate subtotal or tip
    }


    public void getDatePickerDialog(View v){
        EditText date_field = (EditText) findViewById(R.id.transaction_date);

        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.setEditText(date_field);

        DialogFragment frag = datePickerFragment;
        frag.show(getSupportFragmentManager(), "datepicker");
    }

    public void getTimePickerDialog(View v){
        EditText time_field = (EditText) findViewById(R.id.transaction_time);

        TimePickerFragment timePickerFragment = new TimePickerFragment();
        timePickerFragment.setEditText(time_field);

        DialogFragment frag = timePickerFragment;
        frag.show(getSupportFragmentManager(), "timepicker");
    }

    public void addItem(View v){
        //TODO: implement adding more than one item
    }

    public void addPayment(View v){
        //TODO: implement adding more than one payment
    }

    public void getLocation(View v){
        //TODO: get history of locations
    }

    public void submit(View v){
        if(transaction==null) transaction = new Transaction();
        else transaction.update();
        Intent intent = new Intent(this, TransactionActivity.class);
        startActivity(intent);
    }
}
