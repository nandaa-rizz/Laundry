package com.nanda.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nanda.laundry.R
import com.nanda.laundry.modeldata.ModelLayanan

class TambahLayananAdapter(
    private val listLayanan: ArrayList<ModelLayanan>) : RecyclerView.Adapter<TambahLayananAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardlayanan, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val layanan = listLayanan[position]
        holder.tvIDLayanan.text = layanan.tvID ?: ""
        holder.tvNamaLayanan.text = layanan.tvNama ?: ""
        holder.tvHarga.text = layanan.tvHarga ?: ""
        holder.tvCabang.text = layanan.etCabang ?: ""
        holder.btHubungi.setOnClickListener{

        }
        holder.btLihat.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listLayanan.size
    }

    class ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
                val tvIDLayanan: TextView = itemView.findViewById(R.id.tvIDLayanan)
                val tvNamaLayanan: TextView = itemView.findViewById(R.id.tvNamaLayanan)
                val tvHarga: TextView = itemView.findViewById(R.id.tvHarga)
                val tvCabang: TextView = itemView.findViewById(R.id.tvCabang)
                val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
                val btLihat: Button = itemView.findViewById(R.id.btLihat)
            }
    }