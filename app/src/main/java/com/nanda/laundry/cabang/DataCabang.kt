package com.nanda.laundry.cabang

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.nanda.laundry.R
import com.nanda.laundry.adapter.TambahCabangAdapter
import com.nanda.laundry.modeldata.ModelCabang

class DataCabang : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("cabang")
    lateinit var rv_data_cabang: RecyclerView
    lateinit var btTambah: FloatingActionButton
    lateinit var cabangList: ArrayList<ModelCabang>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_cabang)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_data_cabang.layoutManager = layoutManager
        rv_data_cabang.setHasFixedSize(true)
        cabangList = arrayListOf<ModelCabang>()
        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
    }
}
    fun init() {
        rv_data_cabang = findViewById(R.id.rv_data_cabang)
        btTambah = findViewById(R.id.btTambah)
        btTambah.setOnClickListener{
            val intent = Intent(this@DataCabang, TambahCabang::class.java)
            startActivity(intent)
        }
    }
    fun getData(){
        val query = myRef.orderByChild("idCabang").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    cabangList.clear()
                    for (dataSnapshot in snapshot.children) {
                        val cabang = dataSnapshot.getValue(ModelCabang::class.java)
                        cabang?.let {cabangList.add(it)}
                    }
                    val adapter = TambahCabangAdapter(cabangList)
                    rv_data_cabang.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataCabang, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }

}