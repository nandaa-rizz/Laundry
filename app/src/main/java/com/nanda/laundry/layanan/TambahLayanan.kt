package com.nanda.laundry.layanan

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import com.nanda.laundry.R
import com.nanda.laundry.modeldata.ModelLayanan
import com.nanda.laundry.modeldata.ModelPegawai

class TambahLayanan : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("layanan")
    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etHarga: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_layanan)
        init()
        //getData()
        btSimpan.setOnClickListener {
            cekValidasi()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun init() {
        tvJudul = findViewById(R.id.tvJudul)
        etNama = findViewById(R.id.etNama)
        etHarga = findViewById(R.id.etHarga)
        etCabang = findViewById(R.id.etCabang)
        btSimpan = findViewById(R.id.btSimpan)
    }

    fun cekValidasi() {
        val nama = etNama.text.toString()
        val harga = etHarga.text.toString()
        val cabang = etCabang.text.toString()
        // validasi data
        if (nama.isEmpty()) {
            etNama.error = this.getString(R.string.validasi_nama_pelanggan)
            Toast.makeText(
                this@TambahLayanan, getString(R.string.validasi_nama_pelanggan),
                Toast.LENGTH_SHORT
            ).show()
            etNama.requestFocus()
            return
        }
        if (harga.isEmpty()) {
            etHarga.error = this.getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(
                this@TambahLayanan, getString(R.string.validasi_alamat_pelanggan),
                Toast.LENGTH_SHORT
            ).show()
            etHarga.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabang.error = this.getString(R.string.validasi_cabang)
            Toast.makeText(
                this@TambahLayanan, getString(R.string.validasi_cabang),
                Toast.LENGTH_SHORT
            ).show()
            etCabang.requestFocus()
            return
        }
        simpan()
    }

    fun simpan() {
        val layananBaru = myRef.push()
        val layananID = layananBaru.key
        val data = ModelLayanan(
            layananID.toString(),
            etNama.text.toString(),
            etHarga.text.toString(),
            etCabang.text.toString()
            //"timestamp"
        )
        layananBaru.setValue(data).addOnSuccessListener {
            Toast.makeText(this@TambahLayanan, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this@TambahLayanan, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}