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

        val calculateButton = findViewById<Button>(R.id.calculate_button)
        val totalBill = findViewById<TextView>(R.id.total_bill_edit_text)
        calculateButton.setOnClickListener{
            calculateSubtotal()
        }

    }

    private fun calculateSubtotal(){
        val subTotalBill = findViewById<TextView>(R.id.subtotal_bill_edit_text)
        // return if input is empty
        val totalBill = getTotal() ?: return
        val subTotal = totalBill/1.13

        subTotalBill.text = NumberFormat.getCurrencyInstance().format(subTotal)
    }

    // returns a double or null
    private fun getTotal(): Double?{
        val stringInTextField = findViewById<TextView>(R.id.total_bill_edit_text).text.toString()
        return stringInTextField.toDoubleOrNull()
    }

    private fun calculateTip(){

    }

    private fun displayTip(tip: Double){

    }



}