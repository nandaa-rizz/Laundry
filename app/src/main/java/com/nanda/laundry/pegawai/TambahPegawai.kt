package com.nanda.laundry.pegawai

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
import com.nanda.laundry.modeldata.ModelPegawai
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TambahPegawai : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")
    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etHP: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)
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
        etAlamat = findViewById(R.id.etAlamat)
        etHP = findViewById(R.id.etHP)
        etCabang = findViewById(R.id.etCabang)
        btSimpan = findViewById(R.id.btSimpan)
    }

    fun cekValidasi() {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val HP = etHP.text.toString()
        val cabang = etCabang.text.toString()
        // validasi data
        if (nama.isEmpty()) {
            etNama.error = this.getString(R.string.validasi_nama_pelanggan)
            Toast.makeText(
                this@TambahPegawai, getString(R.string.validasi_nama_pelanggan),
                Toast.LENGTH_SHORT
            ).show()
            etNama.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = this.getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(
                this@TambahPegawai, getString(R.string.validasi_alamat_pelanggan),
                Toast.LENGTH_SHORT
            ).show()
            etAlamat.requestFocus()
            return
        }
        if (HP.isEmpty()) {
            etHP.error = this.getString(R.string.validasi_nomor)
            Toast.makeText(
                this@TambahPegawai, getString(R.string.validasi_nomor),
                Toast.LENGTH_SHORT
            ).show()
            etHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabang.error = this.getString(R.string.validasi_cabang)
            Toast.makeText(
                this@TambahPegawai, getString(R.string.validasi_cabang),
                Toast.LENGTH_SHORT
            ).show()
            etCabang.requestFocus()
            return
        }
        simpan()
    }

    fun simpan() {
        val pegawaiBaru = myRef.push()
        val pegawaiId = pegawaiBaru.key

        val currentTime = SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault()).format(Date())
        val data = ModelPegawai(
            pegawaiId.toString(),
            etNama.text.toString(),
            etAlamat.text.toString(),
            etHP.text.toString(),
            etCabang.text.toString(),
            currentTime
        )
        pegawaiBaru.setValue(data).addOnSuccessListener {
            Toast.makeText(this@TambahPegawai, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            finish()
        }
            .addOnFailureListener {
                Toast.makeText(this@TambahPegawai, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            }
    }
}