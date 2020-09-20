package com.example.tip_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.SeekBar
import kotlinx.android.synthetic.main.activity_main.*

private const val INITIAL_TIP_PERCENT=15
private const val TAG="MainActivity"


class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBartTip.progress= INITIAL_TIP_PERCENT
        tvtipPerce.text= "$INITIAL_TIP_PERCENT%"
        seekBartTip.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener{

            @SuppressLint("SetTextI18n")
            override fun onProgressChanged(p0: SeekBar?, progress: Int, p2: Boolean) {
                Log.i(TAG,"onProgressChanged $progress")
                tvtipPerce.text="$progress%"
                computeTipAmountTotal{}
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}

            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })

        etBase.addTextChangedListener(object:TextWatcher{
            override fun afterTextChanged(p0: Editable?) {
                Log.i(TAG,"AfterTextChanged $p0")
                computeTipAmountTotal{}
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        })

    }

    private fun computeTipAmountTotal(function: () -> Unit) {

            //Get the value of the base and tip amount

            val baseAmount=etBase.text.toString().toDouble()
            val tipPercent=seekBartTip.progress
            val tipAmount= baseAmount * tipPercent /100
            val totalAmount= tipAmount + baseAmount;
            tvTpiAmount.text=tipAmount.toString()
            tvTotalAmount.text=totalAmount.toString()

    }
}






