package com.example.mygym

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ShowalluserAdapter(var list: List<Userdata>): RecyclerView.Adapter<ShowalluserAdapter.Myviewholder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Myviewholder {
        var layoutitem = LayoutInflater.from(parent.context).inflate(R.layout.recyclelayoutshowalluser,parent,false)
        return Myviewholder(layoutitem)
    }

    override fun onBindViewHolder(holder: Myviewholder, position: Int) {

        // holder.imgview.setImageResource(list[position].userimg)
       // holder.uuname.text = list[position].username
        //holder.uumail.text = list[position].userEmail
        holder.uuname.setText(list[position].username)
        holder.uumail.setText(list[position].userEmail)
        holder.uuphone.setText(list[position].userphone)
        holder.uuheight.setText(list[position].userHeight)
        holder.uuweight.setText(list[position].userWeight)
        holder.uuchest.setText(list[position].userChest)
        holder.uubiceps.setText(list[position].userBicep)
        holder.uuthigs.setText(list[position].userThighs)
        holder.uuwaist.setText(list[position].userWaist)
        holder.uuaadhar.setText(list[position].userAdhar)
        holder.uugander.setText(list[position].usergender)
        holder.uugymtime.setText(list[position].userGymtime)
        holder.uuaddress.setText(list[position].useradd)




    }

    class Myviewholder(dataitem:View) : RecyclerView.ViewHolder(dataitem) {
        var imgview :ImageView = dataitem.findViewById(R.id.img_showalluser)
        var uuname : TextView = dataitem.findViewById(R.id.tv_showalluser_name)
        var uumail : TextView = dataitem.findViewById(R.id.tv_showalluser_email)
        var uuphone : TextView = dataitem.findViewById(R.id.tv_showalluser_phone)
        var uuheight: TextView = dataitem.findViewById(R.id.tv_showalluser_height)
        var uuweight: TextView = dataitem.findViewById(R.id.tv_showalluser_Weight)
        var uuchest: TextView = dataitem.findViewById(R.id.tv_showalluser_chest)
        var uubiceps: TextView = dataitem.findViewById(R.id.tv_showalluser_biceps)
        var uuthigs: TextView = dataitem.findViewById(R.id.tv_showalluser_Thighs)
        var uuwaist: TextView = dataitem.findViewById(R.id.tv_showalluser_waist)
        var uuaadhar: TextView = dataitem.findViewById(R.id.tv_showalluser_AadharNumber)
        var uugander : TextView = dataitem.findViewById(R.id.tv_showalluser_gender)
        var uugymtime : TextView = dataitem.findViewById(R.id.tv_showalluser_gymtime)
        var uuaddress : TextView = dataitem.findViewById(R.id.tv_showalluser_address)



    }

    override fun getItemCount() = list.size


}