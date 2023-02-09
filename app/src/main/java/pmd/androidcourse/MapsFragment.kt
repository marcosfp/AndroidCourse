package pmd.androidcourse

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import pmd.androidcourse.databinding.FragmentMapsBinding


class MapsFragment : Fragment() {

    private var binding: FragmentMapsBinding? = null
    var mapView: MapView? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val fragmentBinding = FragmentMapsBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)
        return fragmentBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }


    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }
}