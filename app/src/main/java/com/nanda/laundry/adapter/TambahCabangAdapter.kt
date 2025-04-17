package com.nanda.laundry.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.nanda.laundry.R
import com.nanda.laundry.modeldata.ModelCabang

class TambahCabangAdapter (
    private val listCabang: ArrayList<ModelCabang>) : RecyclerView.Adapter<TambahCabangAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cardcabang, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cabang = listCabang[position]
        holder.tvIDCabang.text = cabang.tvID ?: ""
        holder.tvCabang.text = cabang.tvCabang ?: ""
        holder.tvAlamat.text = cabang.tvAlamat ?: ""
        holder.tvNoHP.text = cabang.tvNoHP ?: ""
        holder.tvNamaLayanan.text = cabang.tvNamaLayanan ?: ""
        holder.btHubungi.setOnClickListener {

        }
        holder.btLihat.setOnClickListener{

        }
    }

    override fun getItemCount(): Int {
        return listCabang.size
    }

    class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
            val tvIDCabang: TextView = itemView.findViewById(R.id.tvIDCabang)
            val tvCabang: TextView = itemView.findViewById(R.id.tvCabang)
            val tvAlamat: TextView = itemView.findViewById(R.id.tvAlamat)
            val tvNoHP: TextView = itemView.findViewById(R.id.tvNoHP)
            val tvNamaLayanan: TextView = itemView.findViewById(R.id.tvNamaLayanan)
            val btHubungi: Button = itemView.findViewById(R.id.btHubungi)
            val btLihat: Button = itemView.findViewById(R.id.btLihat)
        }
    }