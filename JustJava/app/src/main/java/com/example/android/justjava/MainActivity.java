package com.example.android.justjava; /**
 * IMPORTANT: Add your package below. Package name can be found in the project's AndroidManifest.xml file.
 * This is the package name our example uses:
 *
 * package com.example.android.justjava;
 *
 */


import android.content.Intent;
import android.icu.text.NumberFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.android.justjava.R.id.qtv;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    public int quantity = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void displayPrice(int number){
        TextView priceTextView = (TextView) findViewById(R.id.ptv);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }

    /**
     * This method is called when the order button is clicked.
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void submitOrder(View view) {

        CheckBox checkBox = (CheckBox) findViewById(R.id.notify_me_checkbox);
        CheckBox chockBox = (CheckBox) findViewById(R.id.chocBox);

        Boolean isChecked1, isChecked2;

        isChecked1 = checkBox.isChecked();
        isChecked2 = chockBox.isChecked();

        String priceMessage = createSummaryT(10, isChecked1,isChecked2);
        displayMessage(priceMessage);

        composeEmail("JustJava for bla bla", priceMessage);



    }

    public void composeEmail(String subject, String exText) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, exText);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public void increment(View view){
        //int quantity = 3;
        quantity = quantity + 1;
        displayQuanitity(quantity);
        displayPrice(quantity * 10);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void decrement(View view){
        //int quantity =  1;
        if (quantity > 0) {
            quantity = quantity - 1;
            displayQuanitity(quantity);
            displayPrice(quantity * 10);
        }

    }

    public String createSummaryT(int n, boolean bb, boolean bbb){
        EditText edit =  (EditText) findViewById(R.id.name_bar);
        String name = (String) edit.getText().toString();

        return "Name :  " + name + "\nAdd Cream : " + bb + "\nAdd chocolate : " +
                bbb + "\nQuantity : " + quantity
                + "\nTotal : " + calculatePrice() + "\nThank you";
    }


    private int calculatePrice( ){
        int p = quantity * 10;
        return  p;
    }

    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.ptv);
        priceTextView.setText(message);
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuanitity(int number) {
        TextView quantityTextView = (TextView) findViewById(qtv);
        quantityTextView.setText("" + number);
    }
}