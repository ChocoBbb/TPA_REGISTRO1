package com.example.tpa_registro

import android.content.Context
import android.content.Intent
import java.text.SimpleDateFormat
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar

class RegistrerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.resactivity_main)

        val nameET: EditText = findViewById(R.id.etName)
        val correoET: EditText = findViewById(R.id.etCorreo)
        val cumpleET: EditText = findViewById(R.id.etCumple)
        val passET: EditText = findViewById(R.id.etContra)
        val pass2ET: EditText = findViewById(R.id.etContra2)
        val rButton: Button = findViewById(R.id.regButton)
        val regLogin: TextView = findViewById(R.id.regLogin)


        cumpleET.setOnClickListener {
            val calendar = Calendar.getInstance()
            val año = calendar.get(Calendar.YEAR)
            val mes = calendar.get(Calendar.MONTH)
            val dia = calendar.get(Calendar.DAY_OF_MONTH)

            val datePickerDialog = DatePickerDialog(
                this,
                { _, selectedYear, selectedMonth, selectedDay ->
                    val formattedDate = String.format(
                        "%02d/%02d/%04d",
                        selectedDay, selectedMonth + 1, selectedYear
                    )
                    cumpleET.setText(formattedDate)
                },
                año,
                mes,
                dia
            )
            datePickerDialog.show()
        }


        rButton.setOnClickListener {
            val name = nameET.text.toString().trim()
            val mail = correoET.text.toString().trim()
            val cumple = cumpleET.text.toString().trim()
            val pass = passET.text.toString().trim()
            val pass2 = pass2ET.text.toString().trim()

            if (name.isBlank() || mail.isBlank() || cumple.isBlank() || pass.isBlank() || pass2.isBlank()) {
                Toast.makeText(this, "Todos los campos deben ser ingresados", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            if (pass != pass2) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val sPreferences = getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
            val editor = sPreferences.edit()
            editor.putString("NAME", name)
            editor.putString("EMAIL", mail)
            editor.putString("CUMPLEAÑOS", cumple)
            editor.putString("CONTRASEÑA", pass)
            editor.apply()

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}
