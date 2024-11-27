package com.example.ui


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import com.squareup.picasso.Picasso

lateinit var cardView: CardView


class MyAdapters(private val context: Context, val mList: List<Product>) :

    RecyclerView.Adapter<MyAdapters.MyviewHolder>() {



    class MyviewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title : TextView
        var description : TextView
        var image : ShapeableImageView

        init {
            title = itemView.findViewById<TextView>(R.id.productTitle)
            description = itemView.findViewById<TextView>(R.id.Productdescription)
            image = itemView.findViewById(R.id.productImage)

        }

    }




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyviewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.eachitems,parent,false)
        return MyviewHolder(itemView)
        




    }



    override fun onBindViewHolder(holder: MyviewHolder, position: Int) {
        var cuurentItem = mList[position]
        holder.title.text = cuurentItem.title
        holder.description.text = cuurentItem.description
        // Load an image into an ImageView
        Picasso.get()
            .load(cuurentItem.thumbnail)
            .into(holder.image)




    }




    override fun getItemCount(): Int {
        return mList.size
    }

}


