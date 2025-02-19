package com.nanda.laundry.layanan

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
import com.nanda.laundry.adapter.TambahLayananAdapter
import com.nanda.laundry.modeldata.ModelLayanan
import com.nanda.laundry.modeldata.ModelPelanggan

class DataLayanan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("layanan")
    lateinit var rv_data_layanan: RecyclerView
    lateinit var btTambah: FloatingActionButton
    lateinit var layananList: ArrayList<ModelLayanan>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_data_layanan)
        init()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_data_layanan.layoutManager = layoutManager
        rv_data_layanan.setHasFixedSize(true)
        layananList = arrayListOf<ModelLayanan>()
        getData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init() {
        rv_data_layanan = findViewById(R.id.rv_data_layanan)
        btTambah = findViewById(R.id.btTambah)
        btTambah.setOnClickListener{
            val intent = Intent(this, TambahLayanan::class.java)
            startActivity(intent)
        }
    }
    fun getData(){
        val query = myRef.orderByChild("idLayanan").limitToLast(100)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    layananList.clear()
                    for (dataSnapshot in snapshot.children) {
                        val layanan = dataSnapshot.getValue(ModelLayanan::class.java)
                        layanan?.let { layananList.add(it) }
                    }
                    val adapter = TambahLayananAdapter(layananList)
                    rv_data_layanan.adapter = adapter
                    adapter.notifyDataSetChanged()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@DataLayanan, error.message, Toast.LENGTH_LONG).show()
            }
        })
    }


}