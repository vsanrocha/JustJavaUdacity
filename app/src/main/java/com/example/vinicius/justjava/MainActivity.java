package com.example.vinicius.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantidadecafe = 0;
    int chantilyPrice = 0;
    int chocolatePrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void aumentar(View view) {
        if (quantidadecafe == 100) {
            Toast.makeText(MainActivity.this, getString(R.string.max_quant_toast), Toast.LENGTH_SHORT).show();
            return;
        }
        quantidadecafe = quantidadecafe + 1;
        displayQuantity(quantidadecafe);
    }

    public void diminuir(View view) {
        if (quantidadecafe == 0) {
            return;
        }
        quantidadecafe = quantidadecafe - 1;
        displayQuantity(quantidadecafe);
    }

    public int calculatePrice(){
        return quantidadecafe * (5 + chantilyPrice + chocolatePrice);
    }

    public String createOrderSummary(String name, int price, boolean addChantily, boolean addChocolate) {
        String priceMessage = getResources().getString(R.string.order_sumary_name, name);
        priceMessage += "\n" + getString(R.string.order_sumary_whipped_cream, addChantily);
        priceMessage += "\n" + getString(R.string.order_sumary_chocolate, addChocolate);
        priceMessage += "\n" + getString(R.string.order_sumary_quantity, quantidadecafe);
        priceMessage += "\n" + getString(R.string.order_sumary_total, price);
        priceMessage += "\n" + getString(R.string.thank_you);
        return priceMessage;
    }

    public void submitOrder(View view) {
        CheckBox checkBoxChantily = (CheckBox) findViewById(R.id.checkboxChantilly);
        boolean hasChantily = checkBoxChantily.isChecked();
        if (hasChantily) {
            chantilyPrice = 1;
        }
        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.checkboxChocolate);
        boolean hasChocolate = checkBoxChocolate.isChecked();
        if (hasChocolate) {
            chocolatePrice = 2;
        }
        EditText editTextName = (EditText) findViewById(R.id.name_field);
        String hasName = editTextName.getText().toString();

        String priceMessage = createOrderSummary(hasName, calculatePrice(), hasChantily, hasChocolate);

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_SUBJECT, getResources().getString(R.string.email_subject, hasName));
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantidade_text_view);
        quantityTextView.setText("" + number);
    }
}

