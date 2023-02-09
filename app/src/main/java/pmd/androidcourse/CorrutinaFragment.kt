package pmd.androidcourse

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import kotlinx.coroutines.*
import pmd.androidcourse.databinding.FragmentCorrutinaBinding
import java.net.URL

class CorrutinaFragment : Fragment() {

    private var binding: FragmentCorrutinaBinding? = null


    private val urlimagenes = mutableListOf(
        "https://media.tenor.com/hRiPtsp-m0IAAAAM/the-simpsons-homer-simpson.gif",
        "https://i.pinimg.com/originals/61/80/2c/61802cf3bffa8ff88fb5127446e318ad.gif",
        "https://media0.giphy.com/media/Zk9mW5OmXTz9e/giphy.gif",
        "https://thumbs.gfycat.com/ParchedBoringHarvestmen-size_restricted.gif",
        "https://st1.uvnimg.com/dims4/default/bfde192/2147483647/thumbnail/480x270/quality/75/format/jpg/?url=https%3A%2F%2Fuvn-brightspot.s3.amazonaws.com%2Fassets%2Fvixes%2Fbtg%2Fdonde-esta-mi-hamburguesa-15-gifs-de-homero-disfrutando-el-placer-de-comer-9.gif"
    );


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {

        val fragmentBinding = FragmentCorrutinaBinding.inflate(inflater, container, false)
        binding = fragmentBinding
        var tarea: Job? = null

        fragmentBinding.button.setOnClickListener {
            if (fragmentBinding.button.text == getString(R.string.btn_imageDownloader)) {
                // Se comprueba la existencia de ImageViews, si existen se eliminan.
                if (fragmentBinding.myLinearLayout.childCount > 3) {
                    fragmentBinding.myLinearLayout.removeViews(
                        3,
                        fragmentBinding.myLinearLayout.childCount - 3
                    )
                }
                tarea = descargarImagenes()
            } else {
                tarea?.let {
                    tarea?.cancel()
                    fragmentBinding.button.text = getString(R.string.btn_imageDownloader)
                    fragmentBinding.tvInfo.text = getString(R.string.txt_descargaCancelada)
                }
                fragmentBinding.tvInfo.text = getString(R.string.txt_descargaCancelada)
            }
        }
        return fragmentBinding.root
    }

    private fun descargarImagenes() = GlobalScope.launch(Dispatchers.Main) {
        binding!!.button.text = getString(android.R.string.cancel)
        binding!!.tvInfo.text = getString(R.string.descargando)
        binding!!.progressBar.progress = 0
        val imagenes = ArrayList<Bitmap>()

        urlimagenes.forEach {
            withContext(Dispatchers.IO) {
                try {
                    val inputStream = URL(it).openStream()
                    imagenes.add(BitmapFactory.decodeStream(inputStream))
                } catch (e: Exception) {
                    Log.e("DOWLOAD", e.message.toString())
                }
            }
            binding!!.progressBar.progress = (imagenes.size * 100) / urlimagenes.size
        }
        imagenes.forEach{
            addImagen(it)
        }
        binding!!.button.text = getString(R.string.btn_imageDownloader)
        binding!!.tvInfo.text = getString(R.string.txt_descargaCompleta)

    }


    fun addImagen(image: Bitmap) {
        val img = ImageView(requireContext())
        Glide.with(requireContext()).load(image).override(binding!!.myLinearLayout.width - 100)
            .into(img)

        img.setPadding(0, 0, 0, 10)
        binding!!.myLinearLayout.addView(img)
    }

}

