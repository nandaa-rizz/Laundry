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
import java.util.*

class TambahPegawai : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val myRef = database.getReference("pegawai")

    lateinit var tvJudul: TextView
    lateinit var etNama: EditText
    lateinit var etAlamat: EditText
    lateinit var etHP: EditText
    lateinit var etCabang: EditText
    lateinit var btSimpan: Button

    var idPegawai: String = ""
    var isEdit: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah_pegawai)

        init()
        getData()

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

    fun getData() {
        idPegawai = intent.getStringExtra("idPegawai").orEmpty()
        val judul = intent.getStringExtra("judul").orEmpty()
        val nama = intent.getStringExtra("namaPegawai")
        val alamat = intent.getStringExtra("alamatPegawai")
        val hp = intent.getStringExtra("noHPPegawai")
        val cabang = intent.getStringExtra("etCabang")

        tvJudul.text = judul
        etNama.setText(nama)
        etAlamat.setText(alamat)
        etHP.setText(hp)
        etCabang.setText(cabang)

        isEdit = idPegawai.isNotEmpty()

        if (isEdit) {
            mati()
            btSimpan.text = "Sunting"
        } else {
            hidup()
            btSimpan.text = "Simpan"
        }
    }

    fun mati() {
        etNama.isEnabled = false
        etAlamat.isEnabled = false
        etHP.isEnabled = false
        etCabang.isEnabled = false
    }

    fun hidup() {
        etNama.isEnabled = true
        etAlamat.isEnabled = true
        etHP.isEnabled = true
        etCabang.isEnabled = true
    }

    fun update() {
        val pegawaiRef = database.getReference("pegawai").child(idPegawai)
        val currentTime = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
        val data = ModelPegawai(
            idPegawai,
            etNama.text.toString(),
            etAlamat.text.toString(),
            etHP.text.toString(),
            etCabang.text.toString(),
            currentTime
        )
        val updateData = mapOf(
        "namaPegawai" to data.namaPegawai,
        "alamatPegawai" to data.alamatPegawai,
        "noHPPegawai" to data.noHPPegawai,
        "etCabang" to data.etCabang,
        )
        pegawaiRef.updateChildren(updateData).addOnSuccessListener {
            Toast.makeText(this, getString(R.string.Data_Pegawai_Berhasi_Diperbaharui), Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, getString(R.string.Data_Pegawai_Gagal_Diperbaharui), Toast.LENGTH_SHORT).show()
        }
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
            Toast.makeText(this, getString(R.string.sukses_simpan_pelanggan), Toast.LENGTH_SHORT).show()
            finish()
        }.addOnFailureListener {
            Toast.makeText(this, getString(R.string.gagal_simpan_pelanggan), Toast.LENGTH_SHORT).show()
        }
    }

    fun cekValidasi() {
        val nama = etNama.text.toString()
        val alamat = etAlamat.text.toString()
        val HP = etHP.text.toString()
        val cabang = etCabang.text.toString()

        if (nama.isEmpty()) {
            etNama.error = getString(R.string.validasi_nama_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_nama_pelanggan), Toast.LENGTH_SHORT).show()
            etNama.requestFocus()
            return
        }
        if (alamat.isEmpty()) {
            etAlamat.error = getString(R.string.validasi_alamat_pelanggan)
            Toast.makeText(this, getString(R.string.validasi_alamat_pelanggan), Toast.LENGTH_SHORT).show()
            etAlamat.requestFocus()
            return
        }
        if (HP.isEmpty()) {
            etHP.error = getString(R.string.validasi_nomor)
            Toast.makeText(this, getString(R.string.validasi_nomor), Toast.LENGTH_SHORT).show()
            etHP.requestFocus()
            return
        }
        if (cabang.isEmpty()) {
            etCabang.error = getString(R.string.validasi_cabang)
            Toast.makeText(this, getString(R.string.validasi_cabang), Toast.LENGTH_SHORT).show()
            etCabang.requestFocus()
            return
        }

        when (btSimpan.text.toString()) {
            "Simpan" -> simpan()
            "Sunting" -> {
                hidup()
                etNama.requestFocus()
                btSimpan.text = "Perbaharui"
            }
            "Perbaharui" -> update()
        }
    }
}
