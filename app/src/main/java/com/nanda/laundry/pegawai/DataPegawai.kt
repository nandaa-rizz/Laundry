package com.nanda.laundry.pegawai

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
import com.nanda.laundry.adapter.DataPegawaiAdapter
import com.nanda.laundry.modeldata.ModelPegawai
import com.nanda.laundry.modeldata.ModelPelanggan
import com.nanda.laundry.pegawai.TambahPegawai

class DataPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var rv_data_pegawai: RecyclerView
    lateinit var btTambah: FloatingActionButton
    lateinit var pegawaiList: ArrayList<ModelPegawai>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_pegawai)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_data_pegawai.layoutManager = layoutManager
        rv_data_pegawai.setHasFixedSize(true)
        pegawaiList = arrayListOf<ModelPegawai>()
        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        rv_data_pegawai = findViewById(R.id.rv_data_pegawai)
        btTambah = findViewById(R.id.btTambah)
        btTambah.setOnClickListener{
            val intent = Intent(this, TambahPegawai:: class.java)
            startActivity(intent)
        }
    }

    fun getData(){
        val query = myRef.orderByChild("idPegawai").limitToLast(100)
        query.addValueEventListener(object  : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    pegawaiList.clear()
                    for (dataSnapshot in snapshot.children){
                        val pegawai = dataSnapshot.getValue(ModelPegawai:: class.java)
                        pegawai?.let {pegawaiList.add(it)}
                    }
                    val adapter = DataPegawaiAdapter(pegawaiList)
                    rv_data_pegawai.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataPegawai, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }
}