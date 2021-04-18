package com.joel.finalassignment.ui.notifications

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.webkit.MimeTypeMap
import android.widget.Button
import android.widget.EditText
import android.widget.PopupMenu
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.joel.finalassignment.Logout
import com.joel.finalassignment.MainActivity2
import com.joel.finalassignment.MapsActivity
import com.joel.finalassignment.R
import com.joel.finalassignment.api.ServiceBuilder
import com.joel.finalassignment.db.StudentDB
import com.joel.finalassignment.repository.UserRepository
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.*

class NotificationsFragment : Fragment(),View.OnClickListener,PopupMenu.OnMenuItemClickListener {
    private lateinit var notificationsViewModel: NotificationsViewModel
    private lateinit var cvProfile: CircleImageView
    private lateinit var btnChange: Button
    private lateinit var tvName:TextView
    private lateinit var tvUsername:TextView
    private lateinit var tvEmail:TextView
    private lateinit var tvContact:TextView

    private lateinit var btnLogout: Button
    private lateinit var btnEdit: Button
    var CAMERA_REQUEST_CODE = 1
    var GALLERY_REQUEST_CODE = 0
    var image_url:String = ""
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        binding(root)
        initialize()
        btnEdit.setOnClickListener(this)
        btnChange.setOnClickListener(this)
        cvProfile.setOnClickListener(this)
        btnLogout.setOnClickListener {
            Logout(requireActivity(),requireContext()).logout()
        }
        return root
    }

    private fun binding(v:View)
    {
        cvProfile = v.findViewById(R.id.cvProfile)
        btnChange = v.findViewById(R.id.btnChange)
        tvName = v.findViewById(R.id.tvName)
        tvUsername = v.findViewById(R.id.tvUsername)
        tvEmail = v.findViewById(R.id.tvEmail)
        tvContact = v.findViewById(R.id.tvContact)


        btnEdit = v.findViewById(R.id.btnEdit)
        btnLogout=v.findViewById(R.id.btnLogout)
    }
    private fun initialize()
    {

        CoroutineScope(Dispatchers.IO).launch {
            var instance = StudentDB.getInstance(requireContext()).getUserDAO()
            var user = instance.authentication()
            withContext(Dispatchers.Main)
            {
                if( user.Image!=null && user.Image!="No-Image.jpg")
                {
                    var imgPath = ServiceBuilder.loadImgPath()+user.Image!!.replace("\\","/")
                    Glide.with(requireContext()).load(imgPath).into(cvProfile)
                }
                else
                {
                    cvProfile.setImageResource(R.drawable.logo)
                }
                tvName.text = user.fname
                tvUsername.text = user.username
                tvEmail.text = user.email
                tvContact.text = user.phoneNo

            }
        }
    }


    private fun uploadImage()
    {
        if(image_url != "")
        {
            val file = File(image_url)
            val extension = MimeTypeMap.getFileExtensionFromUrl(image_url)
            val mimetype = MimeTypeMap.getSingleton().getMimeTypeFromExtension(extension)
            val req_file = RequestBody.create(MediaType.parse(mimetype),file)
            val body = MultipartBody.Part.createFormData("profileImg",file.name,req_file)
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val repo = UserRepository()
                    val response = repo.editImage(body)
                    if(response.success == true)
                    {
                        var instance = StudentDB.getInstance(requireContext()).getUserDAO()
                        instance.delete()
                        instance.userSignUp(response.data!!)
                        withContext(Dispatchers.Main)
                        {
                            var intent = Intent(requireContext(),MainActivity2::class.java)
                            startActivity(intent)
                        }
                    }
                    else
                    {
                        withContext(Dispatchers.Main)
                        {
                            val snk = Snackbar.make(cvProfile,"${response.message}", Snackbar.LENGTH_INDEFINITE)
                            snk.setAction("OK",View.OnClickListener {
                                snk.dismiss()
                            })
                            snk.show()
                        }
                    }
                }
                catch (ex:Exception)
                {
                    withContext(Dispatchers.Main)
                    {
                        println(ex.printStackTrace())
                        val snk = Snackbar.make(cvProfile,"Sorry! We are having some problem:(",
                            Snackbar.LENGTH_INDEFINITE)
                        snk.setAction("OK",View.OnClickListener {
                            snk.dismiss()
                        })
                        snk.show()
                    }

                }
            }
        }
        else
        {
            val snk = Snackbar.make(tvContact,"Please Select image", Snackbar.LENGTH_LONG)
            snk.setAction("OK",View.OnClickListener {
                snk.dismiss()
            })
            snk.show()
        }
    }
    override fun onClick(v: View?) {
        when(v!!.id)
        {
            R.id.btnEdit->{

                var intent = Intent(requireContext(),MapsActivity::class.java)
                startActivity(intent)
                requireActivity().finish()
            }

            R.id.btnChange->{
                uploadImage()
            }

            R.id.cvProfile ->{
                showPopUp()

            }
        }
    }
    private fun showPopUp()
    {
        var popUp = PopupMenu(requireContext(),btnEdit)
        popUp.menuInflater.inflate(R.menu.camera_gallery,popUp.menu)
        popUp.setOnMenuItemClickListener(this)
        popUp.show()

    }

    override fun onMenuItemClick(item: MenuItem?): Boolean {
        when(item!!.itemId)
        {
            R.id.camera ->{
                val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                startActivityForResult(intent,CAMERA_REQUEST_CODE)
            }

            R.id.gallery ->{
                val intent = Intent(Intent.ACTION_PICK)
                intent.type = "image/*"
                startActivityForResult(intent,GALLERY_REQUEST_CODE)
            }
        }
        return true
    }
    private fun bitmapToFile(
        bitmap: Bitmap,
        fileNameToSave: String
    ): File? {
        var file: File? = null
        return try {
            file = File(
                requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES)
                    .toString() + File.separator + fileNameToSave
            )
            file.createNewFile()
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK)
        {
            if (requestCode == GALLERY_REQUEST_CODE && data != null) {
                //overall location of selected image
                val selectedImage = data.data
                val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
                val contentResolver = requireActivity().contentResolver
                //locator and identifier
                val cursor =
                    contentResolver.query(selectedImage!!, filePathColumn, null, null, null)
                cursor!!.moveToFirst()//moveTONext // movetolast
                val columnIndex = cursor.getColumnIndex(filePathColumn[0])
                image_url = cursor.getString(columnIndex)
                //image preview
                cvProfile.setImageBitmap(BitmapFactory.decodeFile(image_url))
                cursor.close()
            } else if (requestCode == CAMERA_REQUEST_CODE && data != null) {
                val imageBitmap = data.extras?.get("data") as Bitmap
                val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
                val file = bitmapToFile(imageBitmap, "$timeStamp.jpg")
                image_url = file!!.absolutePath
                cvProfile.setImageBitmap(BitmapFactory.decodeFile(image_url))
            }
        }
    }
}