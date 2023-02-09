package pmd.androidcourse

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import pmd.androidcourse.GridView.GVAdapter
import pmd.androidcourse.GridView.GVItem
import pmd.androidcourse.databinding.FragmentGridViewBinding


class GridViewFragment : Fragment() {

    private lateinit var listener: MainActivityListener
    private var binding: FragmentGridViewBinding? = null
    private var listaActivity = mutableListOf<GVItem>()
    private lateinit var adaptador: GVAdapter

    //Es llamado cuando se crea el fragmento
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentGridViewBinding.inflate(inflater, container, false)
        binding = fragmentBinding

        iniciarRecyclerView()
        // Inflate the layout for this fragment
        return fragmentBinding.root
    }


    fun iniciarRecyclerView() {
        iniciliazarLista()
        adaptador = GVAdapter(
            listaActivities = listaActivity,
            seleccionarActivity = { GVItem -> seleccionarActivity(GVItem) })

        binding!!.GridViewRecyclerView.adapter = adaptador
        binding!!.GridViewRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
    }

    fun seleccionarActivity(gvItem: GVItem) {

        listener.cambiarFragmento(gvItem.clase)
        val toast = Toast.makeText(requireContext(), gvItem.clase, Toast.LENGTH_SHORT)
        toast.show()
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        listener = context as MainActivityListener
    }

    fun iniciliazarLista() {
        var mapsItem = GVItem("Mapbox Maps", "googleMapsFragment", "mapbox.png")
        var corrutinaItem = GVItem("Corutina", "corrutinaFragment", "corrutinas.png")

        if (listaActivity.isEmpty()) {
            listaActivity.add(mapsItem)
            listaActivity.add(corrutinaItem)

        }
    }

}