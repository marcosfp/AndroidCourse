package pmd.androidcourse.GridView

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pmd.androidcourse.R

class GVAdapter (
    var listaActivities: List<GVItem>,
    private val seleccionarActivity: (GVItem) -> Unit
) : RecyclerView.Adapter<GVViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GVViewHolder {
        val layoutInflater= LayoutInflater.from(parent.context)
        return GVViewHolder(itemView = layoutInflater.inflate(R.layout.gvitem_layout, parent, false))
    }

    override fun onBindViewHolder(holder: GVViewHolder, position: Int) {
        val item = listaActivities[position]
        holder.render(item,seleccionarActivity)
    }

    override fun getItemCount(): Int {
        return listaActivities.size
    }
}