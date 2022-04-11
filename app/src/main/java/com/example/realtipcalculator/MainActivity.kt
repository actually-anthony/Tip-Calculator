package com.example.realtipcalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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

//        binding.calculateButton.setOnClickListener{calculateSubtotal()}
        binding.zeroPercentButton.setOnClickListener{
            calculateSubtotal()
            calculateTip(0.0)}
        binding.tenPercentButton.setOnClickListener{
            calculateSubtotal()
            calculateTip(0.1)}
        binding.fifthteenPercentButton.setOnClickListener{
            calculateSubtotal()
            calculateTip(0.15)}
//        binding.customPercentButton.setOnClickListener{
//            calculateSubtotal()
//            calculateTip(0.0)}

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

    private fun calculateTip(tipPercent: Double){
        Log.i("Anth","I made it here")
        val totalBill = getTotal() ?: return
        val tip = NumberFormat.getCurrencyInstance().format(totalBill * tipPercent)

        binding.tipPercentDisplay.text = when (tipPercent){
            0.00 -> "No Tip"
            else -> "Tip Selected: ${NumberFormat.getPercentInstance(Locale.CANADA).format(tipPercent)}"
        }

    }

    private fun test(value: Float){
//        val tipSlider = findViewById<Slider>(R.id.tip_slider)
        val tipDisplay = findViewById<TextView>(R.id.tip_text)
//        tipDisplay.text = tipSlider.value.toString()

    }

    //TODO: tip percentage text view
    //TODO: Real tip and Round up
    //TODO: Close keyboard after pressing calculate




}