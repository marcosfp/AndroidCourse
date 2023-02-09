package pmd.androidcourse

import android.Manifest
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.mapbox.common.location.compat.permissions.PermissionsManager
import pmd.androidcourse.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), MainActivityListener {
    private lateinit var fragmento: Fragment
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.buttonHome.setOnClickListener {

            cambiarFragmento("gridView")
        }

        setContentView(binding.root)
    }

    override fun cambiarFragmento(claseFragmento: String) {

        val transaction = supportFragmentManager.beginTransaction()


        if (claseFragmento == "gridView") fragmento = GridViewFragment()
        if (claseFragmento == "googleMapsFragment" && solcitarPermisosUbicacion())
            fragmento = MapsFragment()
        if (claseFragmento == "corrutinaFragment") fragmento = CorrutinaFragment()

        transaction.replace(R.id.fragmentContainerView, fragmento)
        transaction.addToBackStack(null)
        transaction.commit()
    }

    fun solcitarPermisosUbicacion(): Boolean {
        if (!PermissionsManager.areLocationPermissionsGranted(this)) {

            locationPermissionRequest.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.INTERNET
                )
            )
            if (PermissionsManager.areLocationPermissionsGranted(this))
                return true;
            else {
                false
            }
        }
        return true;
    }

    val locationPermissionRequest = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) { permissions ->
        when {
            permissions.getOrDefault(Manifest.permission.ACCESS_FINE_LOCATION, false) -> {
                // Precise location access granted.
            }
            permissions.getOrDefault(Manifest.permission.ACCESS_COARSE_LOCATION, false) -> {
                // Only approximate location access granted.
            }

            else -> {
                val toast = Toast.makeText(
                    this,
                    "Es necesario acceso a la ubicacion apra cargar MapBox",
                    Toast.LENGTH_SHORT
                )
                toast.show()
            }
        }
    }

}