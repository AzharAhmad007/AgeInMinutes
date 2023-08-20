package azhar.ahmad19.ageinminutes

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker: Button=findViewById(R.id.btnDatePicker)
        tvSelectedDate=findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes=findViewById(R.id.tvAgeInMinutes)

        btnDatePicker.setOnClickListener {
            clickDataPicker()
        }
    }
    fun clickDataPicker()
    {
        val myCalender= Calendar.getInstance()
        val year= myCalender.get(Calendar.YEAR)
        val month= myCalender.get(Calendar.MONTH)
        val day= myCalender.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener
            { view, selectedYear, selectedMonth, selectedDayOfMonth ->
                Toast.makeText(this,"Year is $selectedYear, Month is ${selectedMonth+1}, Day is $selectedDayOfMonth" ,
                    Toast.LENGTH_SHORT).show()

                val selectedDate="$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                tvSelectedDate?.setText(selectedDate)

                val sdf= SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                val theDate= sdf.parse(selectedDate)

                val selectedDateInMinutes=theDate.time/60000

                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis()))

                val currentDateInMinutes=currentDate.time/60000

                val differenceInMinutes=currentDateInMinutes - selectedDateInMinutes

                tvAgeInMinutes?.text= differenceInMinutes.toString()
            },
            year,
            month,
            day).show()

    }
}