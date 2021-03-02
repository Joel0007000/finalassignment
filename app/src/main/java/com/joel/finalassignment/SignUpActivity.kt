package com.joel.finalassignment

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.WindowManager
import android.widget.*
import com.google.android.material.textfield.TextInputEditText
import com.joel.finalassignment.api_entity.UserTable
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.entity.User
import com.joel.finalassignment.repository.UserRepository
import kotlinx.coroutines.*
import kotlinx.coroutines.Dispatchers.Main
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")
class SignUpActivity : AppCompatActivity() {

    private lateinit var btn_Have_an_account: Button
    private lateinit var btn_SignUpGO: Button

    private lateinit var fullname: TextInputEditText
    private lateinit var username: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var img:ImageView
    private val gallery_code=0
    private val camera_code=1
    var image:String?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_sign_up)

        btn_Have_an_account = findViewById(R.id.btn_Have_an_account)
        btn_SignUpGO = findViewById(R.id.btn_SignUpGO)

        fullname = findViewById(R.id.fullname)
        username = findViewById(R.id.username)
        email = findViewById(R.id.email)
        phone = findViewById(R.id.phone)
        password = findViewById(R.id.password)
        img = findViewById(R.id.img)
        img.setOnClickListener(){
            val popup = PopupMenu(this,img)
            popup.menuInflater.inflate(R.menu.gallery_camera,popup.menu)
            popup.setOnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.menuCamera->
                        openCamera()
                    R.id.menuGallery->{
                        openGallery()
                    }


                }

                true
            }
            popup.show()
        }



        btn_SignUpGO.setOnClickListener {


            val fullname = fullname.text.toString()
            val username = username.text.toString()
            val email = email.text.toString()
            val phone = phone.text.toString()
            val password = password.text.toString()

            //code to insert in db
            val user = UserTable(fullname = fullname, email = email, username = username,
                    phone = phone, password = password)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val userRepository = UserRepository()
                    val response = userRepository.registerUser(user)
                    if (response.success == true) {
                        if(image!=null)
                        {
                            uploadImage(response.data!!._id.toString())
                        }

                        withContext(Main)
                        {
                            Toast.makeText(this@SignUpActivity, "register bhayo", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this@SignUpActivity, LoginActivity::class.java)
                            startActivity(intent)
                        }

                    } else {
                        withContext(Main)
                        {
                            Toast.makeText(this@SignUpActivity, "${response.success}", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (err: Exception) {
                    withContext(Main)
                    {
                        Toast.makeText(this@SignUpActivity, "${err}", Toast.LENGTH_SHORT).show()

                    }
                }


            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == gallery_code && data != null) {
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = contentResolver
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                image = cursor.getString(columnIndex)
                img.setImageBitmap(BitmapFactory.decodeFile(image))
                cursor.close()
            } else if (requestCode == camera_code && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                image = file!!.absolutePath
                img.setImageBitmap(BitmapFactory.decodeFile(image))
            }
        }

    }
    fun openGallery(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type="image/*"
        startActivityForResult(intent,gallery_code)

    }

    fun openCamera() {

        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent,camera_code)


    }

    fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file?.createNewFile()
            //Convert bitmap to byte array
            val bos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.PNG, 0, bos) // YOU can also save it in JPEG
            val bitMapData = bos.toByteArray()
            //write the bytes in file
            val fos = FileOutputStream(file)
            fos.write(bitMapData)
            fos.flush()
            fos.close()
            file
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
            file // it will return null
        }
    }

    fun uploadImage(id: String) {
        if(image!=null)
        {

            var file = File(image!!)
            var reqFile = RequestBody.create(MediaType.parse("multipart/form-data"),file)
            var body = MultipartBody.Part.createFormData("file",file.name,reqFile)
            CoroutineScope(Dispatchers.IO).launch {
                try{
                    val ur = UserRepository()
                    val response = ur.uploadImage(id,body)
                    if(response.success==true)
                    {


                        withContext(Dispatchers.Main){
                            Toast.makeText(this@SignUpActivity, "Add in Room databse", Toast.LENGTH_SHORT).show()
                        }

                    }

                }
                catch(ex:Exception)
                {

                }



            }

        }
    }



}