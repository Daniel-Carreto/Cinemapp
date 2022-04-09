package com.karetolabs.cinemapp

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.ContextThemeWrapper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import com.google.android.material.datepicker.MaterialDatePicker
import com.karetolabs.cinemapp.databinding.FragmentAddFavoriteBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.text.SimpleDateFormat
import java.util.*
import java.util.regex.Pattern

const val GALLERY_SELECT = 0
const val CAMERA_SELECT = 1

class AddFavoriteFragment : Fragment() {

    private lateinit var fragmentAddFavoriteBinding: FragmentAddFavoriteBinding

    private lateinit var requestPermissionLauncher: ActivityResultLauncher<Array<String>>
    private var uriFile: Uri? = null
    private var uriPath: String? = null
    private var idGenero:Long = 0

    private val getGalleryContent =
        registerForActivityResult(ActivityResultContracts.GetContent()) { uri ->
            fragmentAddFavoriteBinding.ivPoster.setImageURI(uri)
            uriFile = uri
            val file = getFile(requireActivity(), uri)
            Log.e("GALLERYCONTENT-ABSOLUTE", file.absolutePath)
            Log.e("GALLERYCONTENT-URI", uri.toString())
        }

    private val takeImageResult =
        registerForActivityResult(ActivityResultContracts.TakePicture()) { isSuccess ->
            if (isSuccess) {
                fragmentAddFavoriteBinding.ivPoster.setImageURI(uriFile)
            }
        }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        requestPermissionLauncher =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {
                it.entries.forEach {
                    Log.d("Permisos", "Key=${it.key} Value = ${it.value}")
                    when (it.key) {
                        android.Manifest.permission.CAMERA -> {
                            if (!it.value) {
                                showMessage("El permiso de la camara es necesario para continuar")
                                return@registerForActivityResult
                            }
                        }
                        android.Manifest.permission.READ_EXTERNAL_STORAGE -> {
                            if (!it.value) {
                                showMessage("El permiso de lectura en almacenamiento es necesario para continuar")
                                return@registerForActivityResult
                            }
                        }
                        android.Manifest.permission.WRITE_EXTERNAL_STORAGE -> {
                            if (!it.value) {
                                showMessage("El permiso de escritura es necesario para continuar")
                                return@registerForActivityResult
                            }
                        }
                    }
                }
                showOptions()
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentAddFavoriteBinding =
            FragmentAddFavoriteBinding.inflate(layoutInflater, container, false)
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
        val genres =
            listOf<String>("Selecciona el genero", "Romantico", "Comedia", "Sci-Fi", "Terror")
        /*val adapterGenres = ArrayAdapter<String>(
            requireActivity(),
            android.R.layout.simple_spinner_dropdown_item,
            genres
        )*/
        val list = listOf<Genero>(
            Genero(0,"Romantico"),
            Genero(1,"Comedia"),
            Genero(2,"Sci-Fi"),
            Genero(3,"Terror")
        )
        val adapterGenres = SpinnerAdapter(requireActivity(),R.layout.item_genero, list)
        fragmentAddFavoriteBinding.spinnerGenre.adapter = adapterGenres
        fragmentAddFavoriteBinding.spinnerGenre.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position == 0) {
                        return
                    }
                    showMessage(genres[position])
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {
                    showMessage("Nada seleccionado")
                }

            }
        fragmentAddFavoriteBinding.tietGenre.setAdapter(adapterGenres)
        fragmentAddFavoriteBinding.tietGenre.onItemClickListener =
            object : AdapterView.OnItemClickListener {
                override fun onItemClick(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    fragmentAddFavoriteBinding.tietGenre.setText(list[position].title)
                    idGenero = list[position].id
                    showMessage(genres[position])
                }

            }
        //Validations
        fragmentAddFavoriteBinding.tietImage.doOnTextChanged { text, start, before, count ->
            Log.d("ADDFavoriteFragment", "$text=$start=$before=$count")
            if (!isValidUrl(text.toString())) {
                fragmentAddFavoriteBinding.tietImage.error = "La url no es valida"
            }
//            if(android.util.Patterns.WEB_URL.matcher(text.toString()).matches()){
//                fragmentAddFavoriteBinding.tilImage.error = ""
//            }else {
//                fragmentAddFavoriteBinding.tilImage.error = "Url no es valida"
//            }
        }
        fragmentAddFavoriteBinding.tietImage.addTextChangedListener {
            Log.d("ADDFavoriteFragment", "${it.toString()}")
        }

        fragmentAddFavoriteBinding.tietImage.addTextChangedListener(changeTextListener)

        fragmentAddFavoriteBinding.btnSave.setOnClickListener {
            validateFields()
        }

        fragmentAddFavoriteBinding.ivPoster.setOnClickListener {
            requestPermissionLauncher.launch(
                arrayOf(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    android.Manifest.permission.READ_EXTERNAL_STORAGE
                )
            )
        }
        fragmentAddFavoriteBinding.toolbar.apply {
            setTitle("Agregar Favorito")
            setTitleTextColor(
                ContextCompat.getColor(requireActivity(), R.color.white)
            )
            setNavigationIcon(
                ContextCompat.getDrawable(requireActivity(), R.drawable.ic_add)
            )
            setNavigationOnClickListener {
                activity?.onBackPressed()
            }
            //inflateMenu(R.menu.add_favorite_menu)
            setOnMenuItemClickListener {
                when(it.itemId){
                    R.id.action_save ->{
                        validateFields()
                    }
                }
                true
            }
        }

    }

    private fun showOptions() {
        // val items = listOf<CharSequence>("Galeria", "Tomar Foto").toTypedArray()
        val items = resources.getStringArray(R.array.option_image)
        val dialog = AlertDialog.Builder(requireActivity())
        //dialog.setCancelable(false)
        dialog.setTitle("Selecciona una imagen de")
        dialog.setItems(items) { dialog, which ->
            when (which) {
                GALLERY_SELECT -> {
                    showGallery()
                }
                CAMERA_SELECT -> {
                    showCamera()
                }
            }
            dialog.cancel()
        }
        dialog.create().show()
    }

    private fun showCamera() {
        getImageUri().let {
            uriFile = it
            takeImageResult.launch(it)
        }
    }

    private fun getImageUri(): Uri {
        val file = File.createTempFile("tmp_image_file", ".png", requireActivity().cacheDir).apply {
            createNewFile()
            deleteOnExit()
        }

        return FileProvider.getUriForFile(
            requireActivity(),
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )
    }


    @Throws(IOException::class)
    fun getFile(context: Context, uri: Uri): File {
        val path = context.filesDir.path + File.separatorChar + queryName(context, uri)
        val destinationFilename =
            File(path)
        try {
            context.contentResolver.openInputStream(uri).use { ins ->
                ins?.let {
                    createFileFromStream(
                        ins,
                        destinationFilename
                    )
                }
            }
        } catch (ex: Exception) {
            Log.e("Save File", ex.message.orEmpty())
            ex.printStackTrace()
        }
        return destinationFilename
    }

    private fun createFileFromStream(inputStream: InputStream, destination: File?) {
        try {
            FileOutputStream(destination).use { os ->
                val buffer = ByteArray(4096)
                var length: Int
                while (inputStream.read(buffer).also { length = it } > 0) {
                    os.write(buffer, 0, length)
                }
                os.flush()
            }
        } catch (ex: Exception) {
            Log.e("Failed to Save File", ex.message.orEmpty())
            ex.printStackTrace()
        }
    }

    private fun queryName(context: Context, uri: Uri): String {
        val returnCursor: Cursor = context.contentResolver.query(uri, null, null, null, null)!!
        val nameIndex: Int = returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        returnCursor.moveToFirst()
        val name: String = returnCursor.getString(nameIndex)
        returnCursor.close()
        return name
    }


    private fun showGallery() {
        getGalleryContent.launch("image/*")
    }

    private fun isValidUrl(url: String): Boolean {
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
        saveFavorite()
        requireActivity().onBackPressed()
    }

    private fun saveFavorite(){
        FavoriteProvider.favorites.add(
            Favorite(
                id = Calendar.getInstance().timeInMillis,
                title = fragmentAddFavoriteBinding.tietTitle.text.toString(),
                urlImage = fragmentAddFavoriteBinding.tietImage.text.toString(),
                summary = fragmentAddFavoriteBinding.tietSummary.text.toString(),
                year = fragmentAddFavoriteBinding.tietYear.text.toString(),
                genre = fragmentAddFavoriteBinding.tietGenre.text.toString(),
                duration = fragmentAddFavoriteBinding.tietDuration.text.toString(),
                uriImage = uriFile.toString()
            )
        )
    }


    private val changeTextListener = object : TextWatcher {
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

    private fun showMessage(message: String) {
        Toast.makeText(requireActivity(), message, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        // fragmentAddFavoriteBinding.tietImage.removeTextChangedListener(changeTextListener)
        // fragmentAddFavoriteBinding.tietImage.addTextChangedListener(null)

    }

}