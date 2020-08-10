
/**
* IMPORTANT: Make sure you are using the correct package name.
* This example uses the package name:
* package com.example.android.justjava
* If you get an error when copying this code into Android studio, update it to match teh package name found
* in the project's AndroidManifest.xml file.
**/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
* This app displays an order form to order coffee.
*/
public class MainActivity extends AppCompatActivity {

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
}

 /**
 * This method is called when the order button is clicked.
 */
int quantity = 0 ;
public void submitOrder(View view) {

    CheckBox WhippedCreamCheckbox = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
    boolean hasWhippedCream = WhippedCreamCheckbox.isChecked();
    CheckBox choclateCheckbox = (CheckBox) findViewById(R.id.choclate_checkbox);
    boolean hasChoclate = choclateCheckbox.isChecked();
    EditText enterName = (EditText) findViewById(R.id.name);
    String name = enterName.getText().toString();
    int price = calculatePrice(hasWhippedCream,hasChoclate);
    String message= createOrderSummary(name,price,hasWhippedCream,hasChoclate);
    Intent intent = new Intent(Intent.ACTION_SENDTO);
    intent.setData(Uri.parse("mailto:"));
    intent.putExtra(Intent.EXTRA_SUBJECT,"just java for " +name);
    intent.putExtra(Intent.EXTRA_TEXT,message);
    if(intent.resolveActivity(getPackageManager()) !=null){
        startActivity(intent);
    }

}
public void Increment(View view){
    if(quantity < 100) {
        quantity = quantity + 1;
    }else {
        quantity = 100;
        Toast.makeText(this,"You Cannot Have More Than 100 Coffee ",Toast.LENGTH_SHORT).show();
    }

    displayQuantity(quantity);
}
public void Decrement(View view){
    if(quantity>0) {
        quantity = quantity - 1;
    }
    else{
        quantity=0;
        Toast.makeText(this,"You Cannot Have Less Than 0 Coffee ",Toast.LENGTH_SHORT).show();
    }
    displayQuantity(quantity);
}

/**
 * This method displays the given quantity value on the screen.
 */
private void displayQuantity(int cofee) {
    TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
    quantityTextView.setText("" + cofee);
}
/**
 * Calculates the price of the order.
 *
 *
 */
private int calculatePrice(boolean whippedCream ,boolean choclate) {
    int baseprice=5;
    if(whippedCream) {
         baseprice +=1 ;
    }  if(choclate){
        baseprice +=2;
    }
    return baseprice*quantity;
}


/**
 *This method is for order summary
 */
public String createOrderSummary(String customername ,int price , boolean addWhippedCream,boolean addchoclate){
    String toDisplay = "NAME : " + customername;
    toDisplay += "\nADD WHIPPED CREAM ? " + addWhippedCream ;
    toDisplay +="\nADD CHOCLATE ? " + addchoclate ;
    toDisplay += "\nQuantity : " + quantity;
    toDisplay += "\nTotal : ₹" +price;
    toDisplay +=  "\nTHANK YOU!";
    return toDisplay;


}
}