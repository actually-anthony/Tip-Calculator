package com.example.realtipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_calculate = findViewById<Button>(R.id.calculate_button)
        val totalBill = findViewById<TextView>(R.id.total_bill_edit_text)
        btn_calculate.setOnClickListener{
            calculate()
        }


    }

    fun calculate(){
        val stringInTextField = findViewById<TextView>(R.id.total_bill_edit_text).text.toString()
        // make double or null
        Log.i("Anth", stringInTextField)

        val totalBill = stringInTextField.toDouble()
        val subTotalBill = findViewById<TextView>(R.id.subtotal_bill_edit_text)

        val subTotal = totalBill/1.13
        val formattedSubTotal = NumberFormat.getCurrencyInstance().format(subTotal)
        println(subTotal)


        subTotalBill.text = formattedSubTotal
    }

}