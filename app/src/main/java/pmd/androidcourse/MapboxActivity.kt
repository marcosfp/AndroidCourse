package pmd.androidcourse

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mapbox.common.location.compat.permissions.PermissionsManager
import com.mapbox.maps.MapView
import com.mapbox.maps.Style


class MapboxActivity : AppCompatActivity() {
    var mapView: MapView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_mapbox)
        mapView = findViewById(R.id.mapView)
        mapView?.getMapboxMap()?.loadStyleUri(Style.MAPBOX_STREETS)

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