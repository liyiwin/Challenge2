package com.example.challenge2

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class RecoderAdapter(var context: Context ,var outerList: MutableList<Model2>):RecyclerView.Adapter<RecoderAdapter.ViewHolder>() {

   var  innerList: MutableList<Model2> = arrayListOf()

    init {

      innerList = outerList

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecoderAdapter.ViewHolder {

        val view = LayoutInflater.from(this.context).inflate(R.layout.item3,parent,false)

        return ViewHolder(view)

    }

    override fun getItemCount(): Int = innerList.size

    override fun onBindViewHolder(holder: RecoderAdapter.ViewHolder, position: Int) {

        holder.name.text = innerList[position].item_name

        holder.stock.text =  innerList[position].stock

        holder.money.text = innerList[position].price

        //holder.image.setImageResource(innerList[position].image)

        Glide.with(this.context)
            .load(innerList[position].pic)
            .into(holder.image)

    }

    inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){

       val name = view.findViewById<TextView>(R.id.history_item_name)

       val money = view.findViewById<TextView>(R.id.history_item_money)

        val stock =view.findViewById<TextView>(R.id.history_item_stock)

        val image = view.findViewById<ImageView>(R.id.history_image)



    }

}