package com.example.realtipcalculator

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.TextView
import com.example.realtipcalculator.databinding.ActivityMainBinding
import com.google.android.material.slider.Slider
import org.w3c.dom.Text
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //close keyboard after entering
        binding.totalBillEditText.setOnKeyListener{view, keyCode, _ -> handleKeyEvent(view, keyCode)}

        //TODO: Close keyboard after pressing button
        binding.zeroPercentButton.setOnClickListener {
            calculateSubtotal()
            calculateTip(0.0)
            calculateFinalBill(0.0)
        }
        binding.tenPercentButton.setOnClickListener {
            calculateSubtotal()
            calculateTip(0.1)
            calculateFinalBill(0.1)
        }
        binding.fifthteenPercentButton.setOnClickListener {
            calculateSubtotal()
            calculateTip(0.15)
            calculateFinalBill(0.15)
        }
        binding.twentyPercentButton.setOnClickListener {
            calculateSubtotal()
            calculateTip(0.2)
            calculateFinalBill(0.2)
        }

    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
    //Using binding for subtotalbill results in .text being editable
    // Hard to change string to editable
    private fun calculateSubtotal() {
        val subTotalBill = findViewById<TextView>(R.id.subtotal_bill_edit_text)
        // return if input is empty
        val totalBill = getTotal() ?: return
        val subTotal = totalBill / 1.13
        subTotalBill.text = NumberFormat.getCurrencyInstance().format(subTotal)
    }

    private fun getSubtotal(): Double? {
        val subtotal = findViewById<TextView>(R.id.subtotal_bill_edit_text).text.toString()
        // editable cannot use toDouble, so it requires toString() first
        return subtotal.toDoubleOrNull()
    }

    // returns a double or null
    private fun getTotal(): Double? {
        val stringInTextField = findViewById<TextView>(R.id.total_bill_edit_text).text.toString()
        return stringInTextField.toDoubleOrNull()
    }


    private fun calculateTip(tipPercent: Double) {
        val totalBill = getTotal() ?: return

        binding.tipPercentDisplay.text = when (tipPercent) {
            0.00 -> "Tip Selected: 0%"
            else -> "Tip Selected: ${
                NumberFormat.getPercentInstance(Locale.CANADA).format(tipPercent)
            }"
        }

    }

    private fun calculateFinalBill(tipPercent: Double) {

        val totalBill = getTotal() ?: return
        val subtotalBill = totalBill/1.13

        // tip before tax
        var tip: Double = if (binding.realTipSwitch.isChecked) {
            subtotalBill * tipPercent
        } else {
            totalBill * tipPercent
        }

        if (binding.roundUpSwitch.isChecked) {
            tip = kotlin.math.ceil(tip)
        }

        val tipText = "Tip amount: ${NumberFormat.getCurrencyInstance().format(tip)}"
        val totalText = "Total bill with tip: ${NumberFormat.getCurrencyInstance().format(totalBill + tip)}"

        binding.tipText.text = tipText
        binding.finalTotalText.text = totalText


    }




}