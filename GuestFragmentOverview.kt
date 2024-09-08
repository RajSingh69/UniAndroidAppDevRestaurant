package com.example.mobileappdevcoursework

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar

/**
 * This class sets the associated XML file to the guest fragment overview page.
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class GuestFragmentOverview: Fragment() {

    private lateinit var shutDownButton: Button
    private lateinit var toLogOnButton: Button
    private lateinit var toHomeButton: Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_overview_guest, container, false)!!;
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shutDownButton = getView()?.findViewById(R.id.overview_ShutDownButton)
                as Button
        shutDownButton.setOnClickListener { shutDownClick() }


        toLogOnButton = getView()?.findViewById(R.id.overview_LogOnButton)
                as Button
        toLogOnButton.setOnClickListener { toLogOnClick() }

        toHomeButton = getView()?.findViewById(R.id.overview_HomePageButton)
                as Button
        toHomeButton.setOnClickListener { toHomeClick() }

    }


    private fun shutDownClick() {

        displayMessage(shutDownButton, getString(R.string.shuttingDownMessage))

        super.onStop()
        super.onDestroy()
        activity?.finishAffinity()

    }

    private fun toLogOnClick() {

        val newIntent = Intent(requireContext(), LoginPage::class.java)
        startActivity(newIntent)

    }

    private fun toHomeClick() {

        val newIntent = Intent(requireContext(), MainActivity::class.java)
        startActivity(newIntent)

    }

    /**
     * This function allows the code to display a toast under certain conditions
     *      with a certain message.
     */

    private fun displayMessage(view: View, msgText: String){
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }

}