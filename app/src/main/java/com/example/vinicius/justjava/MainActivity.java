package com.example.vinicius.justjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantidadecafe = 0;
    int chantillyPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    /**
     * This method is called when the order button is clicked.
     */

    public void aumentar(View view) {
        quantidadecafe = quantidadecafe + 1;
        displayQuantity(quantidadecafe);
    }

    public void diminuir(View view) {
        quantidadecafe = quantidadecafe - 1;
        if (quantidadecafe <= 0)
            quantidadecafe = 0;
        displayQuantity(quantidadecafe);
    }

    public int calculatePrice(){
        return quantidadecafe * 5;
    }

    public String createOrderSummary(int price, boolean addChantily, boolean addChocolate){
        String priceMessage = "Nome: Vinicius Santana";
        priceMessage += "\nAdicionar Cobertura de chanttily?" + addChantily;
        priceMessage += "\nAdicionar Cobertura de chocolate?" + addChocolate;
        priceMessage += "\nQuantidade: " + quantidadecafe;
        priceMessage += "\nTotal: R$" + price;
        priceMessage += "\nMuito Obrigado!";
        return  priceMessage;
    }


    public void submitOrder(View view) {
        CheckBox checkBoxChantily = (CheckBox) findViewById(R.id.checkboxChantilly);
        boolean hasChantily = checkBoxChantily.isChecked();
        CheckBox checkBoxChocolate = (CheckBox) findViewById(R.id.checkboxChocolate);
        boolean hasChocolate = checkBoxChocolate.isChecked();
        displayPrice(createOrderSummary(calculatePrice(), hasChantily, hasChocolate));
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantidade_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayPrice(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(message);
    }

}

