package org.geeksforgeeks.demo

import android.graphics.Bitmap
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {

    // Variables for imageview, edittext,
    // button, bitmap and encoder.
    private lateinit var qrCodeIV: ImageView
    private lateinit var dataEdt: EditText
    private lateinit var generateQrBtn: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initializing all variables.
        qrCodeIV = findViewById(R.id.idIVQrcode)
        dataEdt = findViewById(R.id.idEdt)
        generateQrBtn = findViewById(R.id.idBtnGenerateQR)

        // Initializing onclick listener for button.
        generateQrBtn.setOnClickListener {
            if (TextUtils.isEmpty(
                    dataEdt.getText().toString()
                )
            ) {

                // If the edittext inputs are empty
                // then execute this method showing
                // a toast message.
                Toast.makeText(
                    this@MainActivity,
                    "Enter some text to generate QR Code",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else {
                generateQRCode(
                    dataEdt.getText().toString()
                )
            }
        }
    }

    private fun generateQRCode(text: String) {
        val barcodeEncoder = BarcodeEncoder()
        try {
            // This method returns a Bitmap image of the
            // encoded text with a height and width of 400
            // pixels.
            val bitmap: Bitmap = barcodeEncoder.encodeBitmap(text, BarcodeFormat.QR_CODE, 400, 400)
            qrCodeIV.setImageBitmap(bitmap) // Sets the Bitmap to ImageView
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}