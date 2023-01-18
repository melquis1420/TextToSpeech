package com.example.libtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.util.Log
import android.util.Printer
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private var tts: TextToSpeech ? = null
    private var btnSpeak : Button? = null
    private var txtSpeak: TextView? = null
    private var txtLeitura: TextInputEditText? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //findViews
        btnSpeak = findViewById(R.id.btnLer)
        txtSpeak = findViewById(R.id.txtTeste)
        txtLeitura = findViewById(R.id.inpFala)

        btnSpeak!!.isEnabled = false



        tts = TextToSpeech(this,this)

        btnSpeak!!.setOnClickListener{ speakOut()
        }

    }

    //TextToSpeech configuration
    override fun onInit(status: Int) {
        if(status == TextToSpeech.SUCCESS){
            val result = tts!!.setLanguage(Locale.ENGLISH)

            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED){
                Log.e("MYTEST", "LANGUAGE NOT SUPPORTED")
            }else{
                btnSpeak!!.isEnabled = true
            }
        }
    }

    //will be read
    private fun speakOut(){

        val text = txtLeitura!!.text.toString()
        tts!!.speak(text, TextToSpeech.QUEUE_FLUSH, null, "")
        txtSpeak!!.setText(text)

    }

    public override fun onDestroy() {
        if (tts!= null){
            tts!!.stop()
            tts!!.shutdown()
        }
                super.onDestroy()
    }


}