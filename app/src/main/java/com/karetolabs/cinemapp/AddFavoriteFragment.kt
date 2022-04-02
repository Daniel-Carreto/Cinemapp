package com.karetolabs.cinemapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.datepicker.MaterialDatePicker
import com.karetolabs.cinemapp.databinding.FragmentAddFavoriteBinding
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern


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

        ///Spinners
        val genres = listOf<String>("Selecciona el genero","Romantico", "Comedia", "Sci-Fi", "Terror")
        val adapterGenres = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            genres
        )
        fragmentAddFavoriteBinding.spinnerGenre.adapter = adapterGenres
        fragmentAddFavoriteBinding.spinnerGenre.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                if(position == 0){
                    return
                }
                showMessage(genres[position])
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                showMessage("Nada seleccionado")
            }

        }
        fragmentAddFavoriteBinding.tietGenre.setAdapter(adapterGenres)
        fragmentAddFavoriteBinding.tietGenre.onItemClickListener = object: AdapterView.OnItemClickListener{
            override fun onItemClick(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                showMessage(genres[position])
            }

        }
        //Validations
        fragmentAddFavoriteBinding.tietImage.doOnTextChanged { text, start, before, count ->
            Log.d("ADDFavoriteFragment","$text=$start=$before=$count")
            if(!isValidUrl(text.toString())){
                fragmentAddFavoriteBinding.tietImage.error = "La url no es valida"
            }
//            if(android.util.Patterns.WEB_URL.matcher(text.toString()).matches()){
//                fragmentAddFavoriteBinding.tilImage.error = ""
//            }else {
//                fragmentAddFavoriteBinding.tilImage.error = "Url no es valida"
//            }
        }
        fragmentAddFavoriteBinding.tietImage.addTextChangedListener {
            Log.d("ADDFavoriteFragment","${it.toString()}")
        }

        fragmentAddFavoriteBinding.tietImage.addTextChangedListener(changeTextListener)

        fragmentAddFavoriteBinding.btnSave.setOnClickListener {
            validateFields()
        }

    }

    private fun isValidUrl(url:String):Boolean{
        val patter = Pattern.compile("(https?:\\/\\/)?([\\w\\d]+\\.)?[\\w\\d]+\\.\\w+\\/?.+")
        val matcher = patter.matcher(url)
        return matcher.matches()
    }

    private fun validateFields() {
        if (fragmentAddFavoriteBinding.tietTitle.text.toString().isEmpty()) {
            fragmentAddFavoriteBinding.tietTitle.error = "El campo no debe ir vacio"
            fragmentAddFavoriteBinding.tietTitle.requestFocus()
            return
        }
        if (fragmentAddFavoriteBinding.tietSummary.text?.length ?: 0 < 30) {
            fragmentAddFavoriteBinding.tilSummary.error = "La descripcion debe ser mas amplia"
            fragmentAddFavoriteBinding.tilSummary.requestFocus()
            return
        }
        showMessage("Exitoso")
    }


    private val changeTextListener = object: TextWatcher{
        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            fragmentAddFavoriteBinding.tietImage.removeTextChangedListener(this)
            //Agregamos validaciones
        }

        override fun afterTextChanged(s: Editable?) {
            fragmentAddFavoriteBinding.tietImage.addTextChangedListener(this)
        }

    }

    private fun showMessage(message:String){
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
       // fragmentAddFavoriteBinding.tietImage.removeTextChangedListener(changeTextListener)
       // fragmentAddFavoriteBinding.tietImage.addTextChangedListener(null)

    }

}