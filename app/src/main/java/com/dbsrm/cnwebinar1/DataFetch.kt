package com.dbsrm.cnwebinar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_data_fetch.*

class DataFetch : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_fetch)

        fetchData()
    }


    private fun fetchData(){
        val ref = FirebaseDatabase.getInstance().getReference("/")
        ref.addValueEventListener(object: ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                var arrayList: ArrayList<String> = ArrayList()
                snapshot.children.forEach {
                    val data = it.getValue(UserData::class.java)
                    arrayList.add(data!!.name + data!!.age)
                    //fetchedData.text = data!!.name + data!!.age
                }
                fetchedData.text = arrayList.toString()
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}