package com.karetolabs.cinemapp

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import com.google.android.material.datepicker.MaterialDatePicker
import com.karetolabs.cinemapp.databinding.FragmentAddFavoriteBinding
import java.text.SimpleDateFormat
import java.util.*


class AddFavoriteFragment : Fragment() {

    private lateinit var fragmentAddFavoriteBinding: FragmentAddFavoriteBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddFavoriteBinding = FragmentAddFavoriteBinding.inflate(layoutInflater, container, false)
        return fragmentAddFavoriteBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentAddFavoriteBinding.tietYear.setOnClickListener {
//            val showDatePickerDialog = DatePickerDialog(
//                requireActivity(),
//                object : DatePickerDialog.OnDateSetListener {
//                    override fun onDateSet(view: DatePicker?, year: Int, month: Int, day: Int) {
//                        fragmentAddFavoriteBinding.tietYear.setText(year.toString())
//                    }
//                },
//                Calendar.getInstance().get(Calendar.YEAR),
//                Calendar.getInstance().get(Calendar.MONTH),
//                Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
//            )
//            showDatePickerDialog.datePicker.maxDate = Calendar.getInstance().timeInMillis
//            showDatePickerDialog.create()
//            showDatePickerDialog.show()
            val materialDatePicker = MaterialDatePicker.Builder.datePicker()
            materialDatePicker.setTitleText("Selecciona el anio de la pelicula")
            materialDatePicker.setSelection(MaterialDatePicker.todayInUtcMilliseconds())
           // materialDatePicker.setTheme(
           //     com.google.android.material.R.style.ThemeOverlay_MaterialComponents_MaterialCalendar_Fullscreen
           // )

            val picker = materialDatePicker.build()
            picker.addOnPositiveButtonClickListener { timeInMillis ->
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timeInMillis
                val format = SimpleDateFormat("yyyy")
                fragmentAddFavoriteBinding.tietYear.setText(format.format(calendar.time))
            }
            picker.show(childFragmentManager, materialDatePicker.toString())

        }
    }

}