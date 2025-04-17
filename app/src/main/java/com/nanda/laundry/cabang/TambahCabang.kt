package com.nanda.laundry.cabang

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
import com.nanda.laundry.modeldata.ModelCabang
import com.nanda.laundry.modeldata.ModelLayanan

class TambahCabang : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("cabang")
    lateinit var tvJudul: TextView
    lateinit var etTambahCabang: EditText
    lateinit var etAlamat: EditText
    lateinit var etNomor: EditText
    lateinit var etLayanan: EditText
    lateinit var btSimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_cabang)
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
        etTambahCabang = findViewById(R.id.etTambahCabang)
        etAlamat = findViewById(R.id.etAlamat)
        etNomor = findViewById(R.id.etNomor)
        etLayanan = findViewById(R.id.etLayanan)
        btSimpan = findViewById(R.id.btSimpan)
    }

    fun cekValidasi() {
        val tambah = etTambahCabang.text.toString()
        val alamat = etAlamat.text.toString()
        val nomor = etNomor.text.toString()
        val layanan = etLayanan.text.toString()
        // validasi data
        if (tambah.isEmpty()) {
            etTambahCabang.error = this.getString(R.string.validasi_cabang)
            Toast.makeText(
                this@TambahCabang, getString(R.string.validasi_cabang),
                Toast.LENGTH_SHORT
            ).show()
            etTambahCabang.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = this.getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(
                this@TambahCabang, getString(R.string.validasi_alamat_pelanggan),
                Toast.LENGTH_SHORT
            ).show()
            etAlamat.requestFocus()
            return
        }
        if (nomor.isEmpty()) {
            etNomor.error = this.getString(R.string.validasi_nomor)
            Toast.makeText(
                this@TambahCabang, getString(R.string.validasi_nomor),
                Toast.LENGTH_SHORT
            ).show()
            etNomor.requestFocus()
            return
        }
        if (layanan.isEmpty()) {
            etLayanan.error = this.getString(R.string.validasi_layanan)
            Toast.makeText(
                this@TambahCabang, getString(R.string.validasi_layanan),
                Toast.LENGTH_SHORT
            ).show()
            etLayanan.requestFocus()
            return
        }
        simpan()
    }

    fun simpan() {
        val cabangBaru = myRef.push()
        val cabangID = cabangBaru.key
        val data = ModelCabang(
            cabangID.toString(),
            etTambahCabang.text.toString(),
            etAlamat.text.toString(),
            etNomor.text.toString(),
            etLayanan.text.toString()
            //"timestamp"
        )
        cabangBaru.setValue(data).addOnSuccessListener {
            Toast.makeText(this@TambahCabang, getString(R.string.sukses_simpan_cabang), Toast.LENGTH_SHORT).show()
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this@TambahCabang, getString(R.string.gagal_simpan_cabang), Toast.LENGTH_SHORT).show()
            }
    }
}