package io.chillout.sampletest.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.chillout.sampletest.R

class AlbumFragment : Fragment() {

    lateinit var globalview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_album, container, false)
        globalview = view
        return view
    }

}
