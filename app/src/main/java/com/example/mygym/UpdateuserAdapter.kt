package com.example.mygym

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase

class UpdateuserAdapter(var updatelist: List<Userdata>, var contex: Context ):
    RecyclerView.Adapter<UpdateuserAdapter.MyupdateViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyupdateViewHolder {
        var layoutitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclelayout_updateuser, parent, false)
        return MyupdateViewHolder(layoutitem)
    }

    override fun onBindViewHolder(holder: MyupdateViewHolder, position: Int) {
        holder.uuname.setText(updatelist[position].username)
        holder.uumail.setText(updatelist[position].userEmail)
        holder.imgupdate.setOnClickListener {


            var myview = LayoutInflater.from(holder.uuname.context).inflate(R.layout.dilog_update_user, null)
            var dialoginterface = AlertDialog.Builder(holder.uuname.context).setView(myview)
            var editname = myview.findViewById<TextInputEditText>(R.id.et_updateuser_name)
            var editaadhar = myview.findViewById<TextInputEditText>(R.id.et_updateuser_aadharnumber)
            var ediadd = myview.findViewById<TextInputEditText>(R.id.et_updateuser_aad)
            var edigymtime = myview.findViewById<TextInputEditText>(R.id.et_updateuser_gymtime)
            var ediphone = myview.findViewById<TextInputEditText>(R.id.et_updateuser_phone)

            var buttonupdate = myview.findViewById<Button>(R.id.btn_updateuserdil_update)
            editname.setText(updatelist[position].username)
            editaadhar.setText(updatelist[position].userAdhar)
            ediadd.setText(updatelist[position].useradd)
            edigymtime.setText(updatelist[position].userGymtime)
            ediphone.setText(updatelist[position].userphone)
            var id = updatelist[position].userid

            var dialog = dialoginterface.create()
            dialog.show()

            buttonupdate.setOnClickListener {
                var map = HashMap<String, Any>()
                var name = editname.text.toString()
                var aadhar = editaadhar.text.toString()
                var add = ediadd.text.toString()
                var gymtime = edigymtime.text.toString()
                var phone = ediphone.text.toString()
                map.put("userid", id)
                map.put("username", name)
                map.put("userAdhar", aadhar)
                map.put("useradd", add)
                map.put("userGymtime", gymtime)
                map.put("userphone", phone)
                FirebaseDatabase.getInstance().getReference().child("GymUsers").child(id)
                    .updateChildren(map)
                    .addOnCompleteListener {
                        Toast.makeText(contex, " Updated", Toast.LENGTH_LONG).show()
                        var intent = Intent(holder.uuname.context, UpdateUser_admin::class.java)
                        holder.uuname.context.startActivity(intent)
                        (holder.uuname.context as Activity).finish()

                    }

            }

        }

    }

    override fun getItemCount() = updatelist.size

    class MyupdateViewHolder(dataitem: View) : RecyclerView.ViewHolder(dataitem) {

        var uuname: TextView = dataitem.findViewById(R.id.tv_updateuser_name)
        var uumail: TextView = dataitem.findViewById(R.id.tv_updateuser_email)
        var imgupdate: ImageView = dataitem.findViewById(R.id.img_updateuser_update)

    }
}