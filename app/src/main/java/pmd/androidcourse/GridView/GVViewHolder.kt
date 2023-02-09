package pmd.androidcourse.GridView

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import pmd.androidcourse.CorrutinaFragment
import pmd.androidcourse.GridViewFragment
import pmd.androidcourse.MapsFragment
import pmd.androidcourse.R

class GVViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


    val imagenView = itemView.findViewById<ImageView>(R.id.ImageGVItem)
    var textViewTitulo = itemView.findViewById<TextView>(R.id.TituloGVItem)


    fun render(gvItem: GVItem, anadirProducto: (GVItem) -> Unit) {

        textViewTitulo.text = gvItem.titulo



        if (gvItem.clase == "googleMapsFragment")
            Picasso.with(imagenView.context).load(R.drawable.mapbox)
                .fit().centerCrop().into(imagenView)
        if (gvItem.clase == "corrutinaFragment")
            Picasso.with(imagenView.context).load(R.drawable.corrutinas)
                .fit().centerCrop().into(imagenView)

        imagenView.setOnClickListener {
            anadirProducto(gvItem)
        }
        imagenView.isClickable = true
    }
}