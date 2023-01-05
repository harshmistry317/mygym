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

class SetWorkoutAdpter(var updatelist: List<Userdata>, var contex: Context):
    RecyclerView.Adapter<SetWorkoutAdpter.MyWorkoutViewHolder>() {

    class MyWorkoutViewHolder(dataitem: View) : RecyclerView.ViewHolder(dataitem) {

        var uuname: TextView = dataitem.findViewById(R.id.tv_deit_user_name)
        var uumail: TextView = dataitem.findViewById(R.id.tv_deit_user_email)
        var imgsetdeit: ImageView = dataitem.findViewById(R.id.img_setdeit_set)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyWorkoutViewHolder {

        var layoutitem = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerlayout_set_deit, parent, false)
        return MyWorkoutViewHolder(layoutitem)
    }

    override fun onBindViewHolder(holder: MyWorkoutViewHolder, position: Int) {
        holder.uuname.setText(updatelist[position].username)
        holder.uumail.setText(updatelist[position].userEmail)
        holder.imgsetdeit.setOnClickListener {

            var myview = LayoutInflater.from(holder.uuname.context)
                .inflate(R.layout.dailog_set_workout, null)
            var dialoginterface = AlertDialog.Builder(holder.uuname.context).setView(myview)
            var setworkmon = myview.findViewById<TextInputEditText>(R.id.et_workoutmon)
            var setworktues = myview.findViewById<TextInputEditText>(R.id.et_workouttues)
            var setworkwed = myview.findViewById<TextInputEditText>(R.id.et_workoutwed)
            var setworkthus = myview.findViewById<TextInputEditText>(R.id.et_workouthus)
            var setworkfri = myview.findViewById<TextInputEditText>(R.id.et_workoufri)
            var setworksat = myview.findViewById<TextInputEditText>(R.id.et_workousat)
            var setworksun = myview.findViewById<TextInputEditText>(R.id.et_workousun)
            var setbutton = myview.findViewById<Button>(R.id.btn_setworkout_set)
            setworkmon.setText(updatelist[position].workmon)
            setworktues.setText(updatelist[position].worktues)
            setworkwed.setText(updatelist[position].workwed)
            setworkthus.setText(updatelist[position].workthus)
            setworkfri.setText(updatelist[position].workfri)
            setworksat.setText(updatelist[position].worksat)
            setworksun.setText(updatelist[position].worksun)
            var id = updatelist[position].userid
            var dialog = dialoginterface.create()
            dialog.show()
            setbutton.setOnClickListener {

                var map = HashMap<String, Any>()
                var mon = setworkmon.text.toString()
                var tues = setworktues.text.toString()
                var wed = setworkwed.text.toString()
                var thus = setworkthus.text.toString()
                var fri = setworkfri.text.toString()
                var sat = setworksat.text.toString()
                var sun = setworksun.text.toString()

                map.put("userid", id)
                map.put("workmon", mon)
                map.put("worktues", tues)
                map.put("workwed", wed)
                map.put("workthus", thus)
                map.put("workfri", fri)
                map.put("worksat", sat)
                map.put("worksun", sun)

                FirebaseDatabase.getInstance().getReference().child("GymUsers").child(id)
                    .updateChildren(map)
                    .addOnCompleteListener {
                        Toast.makeText(contex, "Workout Set", Toast.LENGTH_LONG).show()
                        var intent = Intent(holder.uuname.context, Set_workout_admin::class.java)
                        holder.uuname.context.startActivity(intent)
                        (holder.uuname.context as Activity).finish()

                    }


            }
        }


    }

    override fun getItemCount() = updatelist.size

}