package io.chillout.sampletest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.chillout.sampletest.R
import kotlinx.android.synthetic.main.fragment_album.view.*

class AlbumFragment : Fragment() {

    lateinit var globalview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_album, container, false)
        globalview = view
        arguments?.let {
            val safeArgs = AlbumFragmentArgs.fromBundle(it)
            view.user_cred_info.text = "UserName is ${safeArgs.usname} \n Number of songs are ${safeArgs.numberOfsongs}"
        }
        return view
    }

}
