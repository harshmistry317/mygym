package com.example.mygym

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class DeleteUseradapater(var deletelist: List<Userdata>, var contex: Context):
    RecyclerView.Adapter<DeleteUseradapater.MydeleteViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MydeleteViewHolder {
        var layoutitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclelayout_deleteuser, parent, false)
        return MydeleteViewHolder(layoutitem)
    }

    override fun onBindViewHolder(holder:MydeleteViewHolder, position: Int) {
        holder.uuname.setText(deletelist[position].username)
        holder.uumail.setText(deletelist[position].userEmail)
        holder.imgdelete.setOnClickListener {

            var id = deletelist[position].userid

            FirebaseDatabase.getInstance().getReference().child("GymUsers").child(id)
                .removeValue().addOnSuccessListener {
                    var intent = Intent(holder.uuname.context, Delete_User::class.java)
                    holder.uuname.context.startActivity(intent)
                    (holder.uuname.context as Activity).finish()
                    Toast.makeText(contex, " Deleted", Toast.LENGTH_LONG).show()
                }
        }
    }

    override fun getItemCount() = deletelist.size

    class MydeleteViewHolder(dataitem: View) : RecyclerView.ViewHolder(dataitem) {

        var uuname: TextView = dataitem.findViewById(R.id.tv_deleteuser_name)
        var uumail: TextView = dataitem.findViewById(R.id.tv_deleteuser_email)
        var imgdelete: ImageView = dataitem.findViewById(R.id.img_deleteuser_delete)
    }
}