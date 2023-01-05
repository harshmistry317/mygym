package com.example.mygym

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.FirebaseDatabase

class SetdeitAdpter(var updatelist: List<Userdata>, var contex: Context):
    RecyclerView.Adapter<SetdeitAdpter.MydeitViewHolder>() {

    class MydeitViewHolder(dataitem: View) : RecyclerView.ViewHolder(dataitem) {

        var uuname: TextView = dataitem.findViewById(R.id.tv_deit_user_name)
        var uumail: TextView = dataitem.findViewById(R.id.tv_deit_user_email)
        var imgsetdeit: ImageView = dataitem.findViewById(R.id.img_setdeit_set)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MydeitViewHolder {
        var layoutitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerlayout_set_deit, parent, false)
        return MydeitViewHolder(layoutitem)
    }

    override fun onBindViewHolder(holder: MydeitViewHolder, position: Int) {
        holder.uuname.setText(updatelist[position].username)
        holder.uumail.setText(updatelist[position].userEmail)
        holder.imgsetdeit.setOnClickListener {
            var myview = LayoutInflater.from(holder.uuname.context).inflate(R.layout.dialog_set_deit, null)
            var dialoginterface = AlertDialog.Builder(holder.uuname.context).setView(myview)
            var setdeitmon =  myview.findViewById<EditText>(R.id.et_mondaydeit)
            var setdeittues =  myview.findViewById<EditText>(R.id.et_tuesdeit)
            var setdeitwed =  myview.findViewById<EditText>(R.id.et_weddeit)
            var setdeitthus =  myview.findViewById<EditText>(R.id.et_thusdeit)
            var setdeifri =  myview.findViewById<EditText>(R.id.et_frideit)
            var setdeitsat =  myview.findViewById<EditText>(R.id.et_satdeit)
            var setdeitsun =  myview.findViewById<EditText>(R.id.et_sundeit)
            var setbutton = myview.findViewById<Button>(R.id.btn_setdeit_set)
            setdeitmon.setText(updatelist[position].deitmon)
            setdeittues.setText(updatelist[position].deittues)
            setdeitwed.setText(updatelist[position].deitwed)
            setdeitthus.setText(updatelist[position].deitthus)
            setdeifri.setText(updatelist[position].dietfri)
            setdeitsat.setText(updatelist[position].dietsat)
            setdeitsun.setText(updatelist[position].dietsun)
            var id = updatelist[position].userid

            var dialog = dialoginterface.create()
            dialog.show()

            setbutton.setOnClickListener {

                var map = HashMap<String, Any>()
                var mon = setdeitmon.text.toString()
                var tues = setdeittues.text.toString()
                var wed = setdeitwed.text.toString()
                var thus = setdeitthus.text.toString()
                var fri = setdeifri.text.toString()
                var sat = setdeitsat.text.toString()
                var sun = setdeitsun.text.toString()
                map.put("userid", id)
                map.put("deitmon",mon)
                map.put("deittues",tues)
                map.put("deitwed",wed)
                map.put("deitthus",thus)
                map.put("dietfri",fri)
                map.put("dietsat",sat)
                map.put("dietsun",sun)
                FirebaseDatabase.getInstance().getReference().child("GymUsers").child(id)
                    .updateChildren(map)
                    .addOnCompleteListener {
                        Toast.makeText(contex, "Diet Set", Toast.LENGTH_LONG).show()
                        var intent = Intent(holder.uuname.context, set_deit_admin::class.java)
                        holder.uuname.context.startActivity(intent)
                        (holder.uuname.context as Activity).finish()

                    }


            }

        }
    }

    override fun getItemCount()  = updatelist.size
}