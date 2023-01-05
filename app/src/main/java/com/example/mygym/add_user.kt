package com.example.mygym

import android.content.Intent
import android.content.SharedPreferences
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.util.Patterns
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_add_user.*
import kotlinx.android.synthetic.main.activity_gym_registration.*

//@Suppress("DEPRECATION")
class add_user : AppCompatActivity() {
    lateinit var DatabaseReference : DatabaseReference
    lateinit var auth: FirebaseAuth
    lateinit var img_adduser:ImageView
    lateinit var userimge:String
    lateinit var addnameuser:EditText
    lateinit var userphone:EditText
    lateinit var useradhar:EditText
    lateinit var usergender: EditText
    lateinit var userheight : EditText
    lateinit var userweight : EditText
    lateinit var userchest :EditText
    lateinit var userwaist : EditText
    lateinit var userthighs : EditText
    lateinit var userbicep : EditText
    lateinit var usergymtime :EditText
    lateinit var useremail : EditText
    lateinit var userpassword : EditText
    lateinit var gymail : String
    lateinit var userAddress : EditText
    lateinit var useragee : EditText
    lateinit var userid : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        val SharedPreferences : SharedPreferences = getSharedPreferences("MyGmailPreff", MODE_PRIVATE)
        gymail = SharedPreferences.getString("Gmail","").toString()


        img_adduser=findViewById(R.id.img_addUser)
        userimge="121222"

        addnameuser =findViewById(R.id.et_addUser_Name)
        userphone=findViewById(R.id.et_addUser_Phone)
        useradhar = findViewById(R.id.et_addUser_Addhar)
        usergender = findViewById(R.id.et_addUser_Gender)
        userheight = findViewById(R.id.et_addUser_Height)
        userweight = findViewById(R.id.et_addUser_Weight)
        userchest = findViewById(R.id.et_addUser_Chest)
        userwaist = findViewById(R.id.et_addUser_Waist)
        userthighs = findViewById(R.id.et_addUser_Thighs)
        userbicep = findViewById(R.id.et_addUser_Bicep)
        usergymtime = findViewById(R.id.et_addUser_gymtime)
        useremail = findViewById(R.id.et_addUser_Email)
        userpassword = findViewById(R.id.et_addUser_Cret_Password)
        userAddress = findViewById(R.id.et_addUser_userAdd)
        useragee = findViewById(R.id.et_addUser_age)

        DatabaseReference = FirebaseDatabase.getInstance().getReference("GymUsers")
        auth = FirebaseAuth.getInstance()
        add()

        btn_Adduser_back.setOnClickListener {
            var intent = Intent(this,admin_dashboard::class.java)
            startActivity(intent)
            finish()

        }



        img_adduser.setOnClickListener( View.OnClickListener {
            checkPermission()
        })









        // this is for storege permission
        //select imge from the phone
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
            when(requestCode){
                READIMAGE->{
                    if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                        loadImage()
                    }else{
                        Toast.makeText(applicationContext,"Cannot Access your images",Toast.LENGTH_LONG).show()
                    }
                }
            }
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }

    }

//start add
    private fun add(){

        btn_Adduser_Add.setOnClickListener {

            var patterns = Patterns.EMAIL_ADDRESS
            if (TextUtils.isEmpty(et_addUser_Name.text.toString())){
                et_addUser_Name.setError("User Name is Empty")
                et_addUser_Name.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Phone.text.toString())){
                et_addUser_Phone.setError("User Phone Number is Empty")
                et_addUser_Phone.requestFocus()
                return@setOnClickListener
            }else if(et_addUser_Phone.length()!= 10){
                et_addUser_Phone.setError("Phone Number Mustbe in 10 digits")
                et_addUser_Phone.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Addhar.text.toString())){
                et_addUser_Addhar.setError("User Aaddhar Number is Empty")
                et_addUser_Addhar.requestFocus()
                return@setOnClickListener
            }else if(et_addUser_Addhar.length()!= 12){
                et_addUser_Addhar.setError("User Aaddhar Number Mustbe in 12 digits")
                et_addUser_Addhar.requestFocus()
                return@setOnClickListener
            } else if (TextUtils.isEmpty(et_addUser_userAdd.text.toString())){
                et_addUser_userAdd.setError("User Address is Empty")
                et_addUser_userAdd.requestFocus()
                return@setOnClickListener
            }
            else if (TextUtils.isEmpty(et_addUser_Gender.text.toString())){
                et_addUser_Gender.setError("User Gender is Empty")
                et_addUser_Gender.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_age.text.toString())){
                et_addUser_age.setError("User Age is Empty")
                et_addUser_age.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Height.text.toString())){
                et_addUser_Height.setError("User Height is Empty")
                et_addUser_Height.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Weight.text.toString())){
                et_addUser_Weight.setError("User Weight is Empty")
                et_addUser_Weight.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Chest.text.toString())){
                et_addUser_Chest.setError("User Chest is Empty")
                et_addUser_Chest.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Waist.text.toString())){
                et_addUser_Waist.setError("User Waist is Empty")
                et_addUser_Waist.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Thighs.text.toString())){
                et_addUser_Thighs.setError("User Thighs is Empty")
                et_addUser_Thighs.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Bicep.text.toString())){
                et_addUser_Bicep.setError("User Bicep is Empty")
                et_addUser_Bicep.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_gymtime.text.toString())){
                et_addUser_gymtime.setError("User Gym Time is Empty")
                et_addUser_gymtime.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_Email.text.toString())){
                et_addUser_Email.setError("User Email is Empty")
                et_addUser_Email.requestFocus()
                return@setOnClickListener
            }/*else if (et_addUser_Email.text.toString() != patterns.toString()){
                et_addUser_Email.setError("Not Valid Email")
                et_addUser_Email.requestFocus()
                return@setOnClickListener
            }*/else if (TextUtils.isEmpty(et_addUser_Cret_Password.text.toString())){
                et_addUser_Cret_Password.setError("User Password is Empty")
                et_addUser_Cret_Password.requestFocus()
                return@setOnClickListener
            }else if(et_addUser_Cret_Password.length() < 6){
                et_addUser_Cret_Password.setError("Password must have 6 or more char")
                et_addUser_Cret_Password.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_userAdd.text.toString())){
                et_addUser_userAdd.setError("User Address is Empty")
                et_addUser_userAdd.requestFocus()
                return@setOnClickListener
            }else if (TextUtils.isEmpty(et_addUser_age.text.toString())){
                et_addUser_age.setError("User Age is Empty")
                et_addUser_age.requestFocus()
                return@setOnClickListener
            }

                auth.createUserWithEmailAndPassword(useremail.text.toString(),userpassword.text.toString())
                        .addOnCompleteListener {
                            val currentuser = auth.currentUser
                            if (it.isSuccessful){
                            var id = DatabaseReference.push().key.toString()

                            //id=userphone.text.toString()
                            var addnameuserdata = addnameuser.text.toString()
                            var userphonedata = userphone.text.toString()
                            var useradhardata = useradhar.text.toString()
                            var userAddressdata = userAddress.text.toString()
                            var usergenderdata = usergender.text.toString()
                            var userheightdata =userheight.text.toString()
                            var userweightdata =userweight.text.toString()
                            var userchestdata = userchest.text.toString()
                            var userwaistdata =userwaist.text.toString()
                            var userthighsdata = userthighs.text.toString()
                            var userbicepdata = userbicep.text.toString()
                            var usergymtimedata = usergymtime.text.toString()
                            var useremaildata = useremail.text.toString()
                            var userpassworddata = userpassword.text.toString()
                            var useragedata = useragee.text.toString()
                            var usergyemaildata = gymail.toString()


                            var userdata = Userdata(id,userimge,addnameuserdata,userphonedata,useradhardata,userAddressdata,usergenderdata,
                                    useragedata,userheightdata , userweightdata , userchestdata ,userwaistdata,userthighsdata,userbicepdata,usergymtimedata
                                        ,useremaildata,userpassworddata,usergyemaildata,"","","","","","",
                                    "","","","","","","","")


                            DatabaseReference.child(id).setValue(userdata)


                            Toast.makeText(applicationContext,"Add user Suceesfully",Toast.LENGTH_LONG).show()
                                addnameuser.setText("")
                                userphone.setText("")
                                useradhar.setText("")
                                usergender.setText("")
                                userheight.setText("")
                                userweight.setText("")
                                userchest.setText("")
                                userwaist.setText("")
                                userthighs.setText("")
                                userbicep.setText("")
                                usergymtime.setText("")
                                useremail.setText("")
                                userpassword.setText("")
                                userAddress.setText("")
                                useragee.setText("")
                                


                            }else{
                                Toast.makeText(applicationContext,"Add user Fail",Toast.LENGTH_LONG).show()
                            }



                        }
        }


    }
    //finish Add

    // select imge form phone
    val READIMAGE:Int=253
     fun checkPermission() {
        if (Build.VERSION.SDK_INT>=21){
            if (ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE)!=
                    PackageManager.PERMISSION_GRANTED){

                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    requestPermissions(arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),READIMAGE)
                }
                return
            }
        }
        loadImage()
    }
    val PICK_IMG_CODE:Int=123
     fun loadImage() {
        var intent = Intent(Intent.ACTION_PICK,
        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(intent,PICK_IMG_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode==PICK_IMG_CODE && data!=null && resultCode== RESULT_OK){
            val selectedImage=data.data
            val filePathColum= arrayOf(MediaStore.Images.Media.DATA)
            val cursor= contentResolver.query(selectedImage!!,filePathColum,null,null,null)

            cursor!!.moveToFirst()
            val coulomindex= cursor!!.getColumnIndex(filePathColum[0])
            val picturePAth= cursor!!.getString(coulomindex!!)
            cursor.close()
            img_adduser.setImageBitmap(BitmapFactory.decodeFile(picturePAth))

        }
    }
    //till hear select imge from phone





}