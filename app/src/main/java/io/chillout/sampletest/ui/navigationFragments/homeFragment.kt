package io.chillout.sampletest.ui.navigationFragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation

import io.chillout.sampletest.R
import kotlinx.android.synthetic.main.fragment_home.view.*
import java.util.*

class homeFragment : Fragment() {

    lateinit var globalview:View
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view:View = inflater.inflate(R.layout.fragment_home, container, false)
        globalview = view
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.login_button.setOnClickListener {
            val username:String = view.username_edittext.text.toString()
            val password:String = view.password_edittext.text.toString()
            if (username.isEmpty()){
                view.username_edittext.requestFocus()
                view.username_edittext.error
            }
            else if (password.isEmpty()){
                view.password_edittext.requestFocus()
                view.password_edittext.error
            }
            else{
                // passing username through action [next_action]
                val random = Random()
                // if <FragmentName>Directons not available try checking
                // safe args and invalidate caches and rebuild
                val nextAction = homeFragmentDirections.actionNavigationHomeToAlbumFragment()
                nextAction.setUsname(username)
                nextAction.setNumberOfsongs(random.nextInt(100))
                // TODO : Add to databse using room

                // start navigation
                Navigation.findNavController(it).navigate(nextAction)
            }
        }
    }
}
