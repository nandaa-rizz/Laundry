package com.nanda.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nanda.laundry.R
import com.nanda.laundry.modeldata.ModelPegawai

class DataPegawaiAdapter(
    private val listPegawai: ArrayList<ModelPegawai>) : RecyclerView.Adapter<DataPegawaiAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardpegawai, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pegawai = listPegawai[position]
        holder.tvAlamat.text = pegawai.alamatPelanggan ?: ""
        holder.tvID.text = pegawai.idPelanggan ?: ""
        holder.tvNama.text = pegawai.namaPelanggan ?: ""
        holder.tvHP.text = pegawai.noHPPelanggan ?: ""
        holder.tvTerdaftar.text = pegawai.terdaftar ?: ""
        holder.btHubungi.setOnClickListener{

        }
        holder.btLihat.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listPegawai.size
    }

    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
                val tvID: TextView = itemView.findViewById(R.id.tvID)
                val tvNama: TextView = itemView.findViewById(R.id.tvNama)
                val tvHP: TextView = itemView.findViewById(R.id.tvHP)
                val tvTerdaftar: TextView = itemView.findViewById(R.id.tvTerdaftar)
                val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
                val btLihat: Button = itemView.findViewById(R.id.btLihat)
            }
    }