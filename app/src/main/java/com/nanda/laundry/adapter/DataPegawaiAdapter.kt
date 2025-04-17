package com.nanda.laundry.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.nanda.laundry.R
import com.nanda.laundry.modeldata.ModelPegawai
import com.nanda.laundry.pegawai.TambahPegawai

class DataPegawaiAdapter(
    private val listPegawai: ArrayList<ModelPegawai>) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {
        lateinit var appContext: Context
        lateinit var databaseReference: DatabaseReference
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardpegawai, parent, false)
        appContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pegawai = listPegawai[position]
        databaseReference = FirebaseDatabase.getInstance().getReference("pegawai")
        holder.cardPegawai
        holder.tvAlamat.text = pegawai.alamatPegawai ?: ""
        holder.tvID.text = pegawai.idPegawai ?: ""
        holder.tvNama.text = pegawai.namaPegawai ?: ""
        holder.tvHP.text = pegawai.noHPPegawai ?: ""
        holder.tvTerdaftar.text = "terdaftar : ${pegawai.terdaftar}"
        holder.tvCabang.text = "cabang : ${pegawai.etCabang}"
        holder.btHubungi.setOnClickListener{
        }
        holder.btLihat.setOnClickListener{
        }

        holder.cardPegawai.setOnClickListener{
            val intent = Intent(appContext, TambahPegawai:: class.java)
            intent.putExtra("judul", "Edit pegawai")
            intent.putExtra("idPegawai", pegawai.idPegawai)
            intent.putExtra("namaPegawai", pegawai.namaPegawai)
            intent.putExtra("noHPPegawai", pegawai.noHPPegawai)
            intent.putExtra("alamatPegawai", pegawai.alamatPegawai)
            intent.putExtra("etCabang", pegawai.etCabang)
            appContext.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return listPegawai.size
    }

    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                val cardPegawai: CardView = itemView.findViewById(R.id.cardPegawai)
                val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
                val tvID: TextView = itemView.findViewById(R.id.tvID)
                val tvNama: TextView = itemView.findViewById(R.id.tvNama)
                val tvHP: TextView = itemView.findViewById(R.id.tvHP)
                val tvCabang: TextView = itemView.findViewById(R.id.tvCabang)
                val tvTerdaftar: TextView = itemView.findViewById(R.id.tvTerdaftar)
                val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
                val btLihat: Button = itemView.findViewById(R.id.btLihat)
            }
    }