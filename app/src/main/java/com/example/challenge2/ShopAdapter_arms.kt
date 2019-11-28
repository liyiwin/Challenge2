package com.example.challenge2

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ShopAdapter_arms(val context: Context, private val layoutManager: GridLayoutManager? = null, var outerList: MutableList<Model> ):RecyclerView.Adapter<RecyclerView.ViewHolder>()  {


        var  innerList: MutableList<Model> = arrayListOf()

        init {
            innerList = outerList

        }

    //指定位置  two type

    companion object {
        open val SMALL = 1


        open val DETAILED  = 2

    }


         var thisBuy :buyItem_Two? = null


        interface buyItem_Two

        {   fun item (id: Int )

        }


        fun buy (myBuy:buyItem_Two){

            thisBuy =  myBuy




        }






    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
                when (viewType) {

                    DETAILED -> {

                            parent.let {

                                val view = LayoutInflater.from(this.context)

                                    .inflate(R.layout.item_1, parent, false)

                                return (DetailedViewHolder(view))

                            }

                        }

                        else -> {

                            parent.let {

                                val view = LayoutInflater.from(this.context)
                                    .inflate(R.layout.item_2, parent, false)


                                return (SimpleViewHolder(view))

                            }

                        }

                    }
    }

    override fun getItemCount(): Int =innerList.size

    override fun getItemViewType(position: Int): Int {
        return if (layoutManager?.spanCount == 1) DETAILED
        else SMALL

    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {

            is DetailedViewHolder ->{

                holder.money.text = innerList[position].price.toString()
                holder.name.text = innerList[position].name
                holder.number.text ="未輸入"
               // holder.image.setImageResource(innerList[position].pic)

                Glide.with(this.context)
                    .load(innerList[position].pic)
                    .into(holder.image)

                holder.image.setOnClickListener {

                    val mBuilder = AlertDialog.Builder(this.context)
                        .setTitle("確定購買")
                        .setNeutralButton("取消"){dialog, which -> /*要執行的程式*/ }
                        .setNegativeButton("拒絕"){dialog, which -> /*要執行的程式*/}
                        .setPositiveButton("確定") {dialog, which ->


                            thisBuy?.item(innerList[position].id)


                        }
                        .create()
                        .show()

                }


            }

            is SimpleViewHolder ->{

                holder.money.text = innerList[position].price.toString()
                holder.name.text = innerList[position].name
                holder.number.text ="未輸入"
               // holder.image.setImageResource(innerList[position].pic)

                Glide.with(this.context)
                    .load(innerList[position].pic)
                    .into(holder.image)

                holder.image.setOnClickListener {

                    val mBuilder = AlertDialog.Builder(this.context)
                        .setTitle("確定購買")
                        .setNeutralButton("取消"){dialog, which ->  }
                        .setNegativeButton("拒絕"){dialog, which -> }
                        .setPositiveButton("確定") {dialog, which ->

                            thisBuy?.item(innerList[position].id)
                        }
                        .create()
                        .show()

                }
            }


        }
    }




            inner class DetailedViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         val name=view.findViewById<TextView>(R.id.name)
        val money=view.findViewById<TextView>(R.id.money)
        val number=view.findViewById<TextView>(R.id.number)
        val image=view.findViewById<ImageView>(R.id.image)





            }


            inner class SimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

         val name=view.findViewById<TextView>(R.id.name1)
        val money=view.findViewById<TextView>(R.id.money1)
        val number=view.findViewById<TextView>(R.id.number1)
       val image=view.findViewById<ImageView>(R.id.image1)







            }




//       var  innerList: MutableList<Model> = arrayListOf()
//
//        init {
//            innerList = outerList
//
//        }
//
//    //指定位置  two type
//
//    companion object {
//        open val SMALL = 1
//
//
//        open val DETAILED  = 2
//
//    }
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
//
//                val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_1,parent,false)
//
//            return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int= innerList.size
//
//    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
//        holder.money.text = innerList[position].price.toString()
//        holder.name.text = innerList[position].name
//        holder.number.text ="未輸入"
//        holder.image.setImageResource(innerList[position].pic)
//    }
//
//       inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
//
//
//
//    val name=view.findViewById<TextView>(R.id.name)
//    val money=view.findViewById<TextView>(R.id.money)
//    val number=view.findViewById<TextView>(R.id.number)
//    val image=view.findViewById<ImageView>(R.id.image)
//       }

//class ShopAdapter_food(val context: Context, var outerList: MutableList<Model> ):RecyclerView.Adapter<ShopAdapter_food.ViewHolder>() {
//// ShopAdapter_special
//        var  innerList: MutableList<Model> = arrayListOf()
//
//    init {
//        innerList = outerList
//
//    }
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopAdapter_food.ViewHolder {
//                val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.item_1,parent,false)
//
//            return ViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = innerList.size
//
//    override fun onBindViewHolder(holder: ShopAdapter_food.ViewHolder, position: Int) {
//
//        holder.money.text = innerList[position].price.toString()
//        holder.name.text = innerList[position].name
//        holder.number.text ="未輸入"
//
//
//
//    }
//
//        inner class ViewHolder(view: View):RecyclerView.ViewHolder(view){
//
//         val name=view.findViewById<TextView>(R.id.name)
//        val money=view.findViewById<TextView>(R.id.money)
//        val number=view.findViewById<TextView>(R.id.number)
////        val image=view.findViewById<ImageView>(R.id.image2)
//
//
//    }
//
//
//}
}