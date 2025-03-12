package com.nanda.laundry

import android.content.Intent
import android.net.ipsec.ike.TunnelModeChildSessionParams.ConfigRequestIpv4Address
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.nanda.laundry.cabang.DataCabang
import com.nanda.laundry.layanan.DataLayanan
import com.nanda.laundry.layanan.TambahLayanan
import com.nanda.laundry.pegawai.DataPegawai
import com.nanda.laundry.pelanggan.DataPelanggan

class MainActivity : AppCompatActivity() {
    // langkah 1 = deklarasi
    lateinit var ivPelanggan: ImageView
    lateinit var cvPegawai: CardView
    lateinit var cvLayanan: CardView
    lateinit var cvTambah: CardView
    lateinit var cvCabang: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        //inisialisasi
        ivPelanggan = findViewById(R.id.ivPelanggan)
        cvPegawai = findViewById(R.id.cvPegawai)
        cvLayanan = findViewById(R.id.cvLayanan)
        cvTambah = findViewById(R.id.cvTambahan)
        cvCabang = findViewById(R.id.cvCabang)

        ivPelanggan.setOnClickListener {
            val intent = Intent(this@MainActivity, DataPelanggan:: class.java)
            startActivity(intent)
        }
        cvPegawai.setOnClickListener {
            val intent = Intent(this@MainActivity, DataPegawai:: class.java)
            startActivity(intent)
        }
        cvLayanan.setOnClickListener{
            val intent = Intent(this, DataLayanan:: class.java)
            startActivity(intent)
        }
        cvTambah.setOnClickListener{
            val intent = Intent(this, TambahLayanan:: class.java)
            startActivity(intent)
        }
        cvCabang.setOnClickListener{
            val intent = Intent(this, DataCabang:: class.java)
            startActivity(intent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}