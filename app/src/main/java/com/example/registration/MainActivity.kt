package com.example.registration

import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.LinearLayout
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* hide keyboard */
        val mainLayout = findViewById<LinearLayout>(R.id.mainLinearLayout)
        mainLayout.setOnClickListener {
            hideKeyboard(view = it)
            return@setOnClickListener
        }

        /* user_name */
        val userName = findViewById<TextInputEditText>(R.id.textInputEditTextUserName)
        val lUserName = findViewById<TextInputLayout>(R.id.textInputLayoutUserName)

        /* document ID*/
        val documentID = findViewById<TextInputEditText>(R.id.textInputEditTextDocumentID)
        val lDocumentID = findViewById<TextInputLayout>(R.id.textInputLayoutDocumentID)

        /* gender */
        val genderSelect = resources.getStringArray(R.array.gender)
        val lGender = findViewById<TextInputLayout>(R.id.textInputLayoutGender)
        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, genderSelect)

        val gender = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextViewGender)
        gender.setAdapter(arrayAdapter)

        /* date */
        val dateOfBirth = findViewById<TextInputEditText>(R.id.textInputEditTextDateOfBirth)
        val lDateOfBirth = findViewById<TextInputLayout>(R.id.textInputLayoutDateOfBirth)
        dateOfBirth.setOnClickListener{
            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)
            val month = cal.get(Calendar.MONTH)
            val day = cal.get(Calendar.DAY_OF_MONTH)

            DatePickerDialog(this, { _, selectedYear, selectedMonth, selectedDayOfMonth ->
                val selectedDate = "$selectedDayOfMonth ${
                    when(selectedMonth+1){
                        2 -> "Feb"
                        3 -> "Mar"
                        4 -> "Apr"
                        5 -> "May"
                        6 -> "Jun"
                        7 -> "Jul"
                        8 -> "Aug"
                        9 -> "Sep"
                        10 -> "Oct"
                        11 -> "Nov"
                        12 -> "Dec"
                        else -> "Jan"
                    }
                } $selectedYear"
                dateOfBirth.setText(selectedDate)
            }
                , year, month, day).show()
        }

        /* Validation */
        userName.doOnTextChanged { text, _, _, _ ->
            if(text.isNullOrEmpty()) {
                lUserName.error = "Username is required"
            }
            else{
                lUserName.error = null
            }
        }

        gender.doOnTextChanged { text, _, _, _ ->
            if(text.isNullOrEmpty()) {
                lGender.error = "Gender is required"
            }
            else{
                lGender.error = null
            }
        }

        documentID.doOnTextChanged { text, _, _, _ ->
            if(text.isNullOrEmpty()) {
                lDocumentID.error = "Document ID is required"
            }
            else{
                lDocumentID.error = null
            }
        }

        dateOfBirth.doOnTextChanged { text, _, _, _ ->
            if(text.isNullOrEmpty()) {
                lDateOfBirth.error = "Date of Birth is required"
            }
            else{
                lDateOfBirth.error = null
            }
        }

        /* button_send */
        val buttonSendData = findViewById<Button>(R.id.buttonSend)
        buttonSendData.setOnClickListener {

            /* checking before send */
            when {
                userName.text.isNullOrEmpty() -> {
                    lUserName.error = "Username is required"
                }
                gender.text.isNullOrEmpty() -> {
                    lGender.error = "Gender is required"
                }
                documentID.text.isNullOrEmpty() -> {
                    lDocumentID.error = "Document ID is required"
                }
                dateOfBirth.text.isNullOrEmpty() -> {
                    lDateOfBirth.error = "Date of Birth is required"
                }
                else -> {
                    val secondActivity = Intent(this, SecondActivity::class.java).apply {
                        putExtra("userName", userName.text.toString())
                        putExtra("gender", gender.text.toString())
                        putExtra("documentID", documentID.text.toString())
                        putExtra("dateOfBirth", dateOfBirth.text.toString())
                    }
                    startActivity(secondActivity)
                }
            }
        }
    }

    private fun hideKeyboard(view: View){
        val vHide = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        vHide.hideSoftInputFromWindow(view.windowToken, 0)
    }
}