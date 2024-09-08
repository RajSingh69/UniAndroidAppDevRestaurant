package com.example.mobileappdevcoursework

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * This class is used to update the fragment overview.
 * It aims to display all of the user's information.
 * @author Rajan Singh Bhamra - 2034215
 * @version V16
 */

class FragmentOverview: Fragment() {

    /**
     * These lines instantiate firebase and set up all the necessary TextViews
     *      that will be written to.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var displayUserName: TextView
    private lateinit var displayUserEmail: TextView
    private lateinit var displayUserPhone: TextView
    private lateinit var displayUserAge: TextView
    private lateinit var displayUserPassword: TextView

    private lateinit var displayIndianFood: TextView
    private lateinit var displayItalianFood: TextView
    private lateinit var displayAfricanFood: TextView
    private lateinit var displayAsianFood: TextView
    private lateinit var displayFoodCreations: TextView
    private lateinit var displayFoodFails: TextView

    private lateinit var displayBookingLocation: TextView
    private lateinit var displayBookingDate: TextView
    private lateinit var displayBookingTime: TextView

    private lateinit var shutDownButton: Button
    private lateinit var toLogOnButton: Button
    private lateinit var toHomeButton: Button

    /**
     * This onCreateView allows the code to inflate the defined XML layout
     *      inside the container so it can be seen.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_overview, container, false)!!;

    }

    /**
     * This function passes through all IDs of every displayed TextView.
     * It also calls the function that will display each box.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        displayUserName = getView()?.findViewById(R.id.fragOverviewDisplayUserName) as TextView
        displayUserEmail = getView()?.findViewById(R.id.fragOverviewDisplayUserEmail) as TextView
        displayUserPhone = getView()?.findViewById(R.id.fragOverviewDisplayUserPhone) as TextView
        displayUserAge = getView()?.findViewById(R.id.fragOverviewDisplayUserAge) as TextView
        displayUserPassword = getView()?.findViewById(R.id.fragOverviewDisplayUserPassword) as TextView

        displayIndianFood = getView()?.findViewById(R.id.fragOverviewDisplayIndianFood) as TextView
        displayItalianFood = getView()?.findViewById(R.id.fragOverviewDisplayItalianFood) as TextView
        displayAfricanFood = getView()?.findViewById(R.id.fragOverviewDisplayAfricanFood) as TextView
        displayAsianFood = getView()?.findViewById(R.id.fragOverviewDisplayAsianFood) as TextView
        displayFoodCreations = getView()?.findViewById(R.id.fragOverviewDisplayLikeCreations) as TextView
        displayFoodFails = getView()?.findViewById(R.id.fragOverviewDisplayLikeFails) as TextView

        displayBookingLocation = getView()?.findViewById(R.id.fragOverviewDisplayBookingLocation) as TextView
        displayBookingDate = getView()?.findViewById(R.id.fragOverviewDisplayBookingDate) as TextView
        displayBookingTime = getView()?.findViewById(R.id.fragOverviewDisplayBookingTime) as TextView
        Log.d("Views Instantiated", "VIEWS ARE INSTANTIATED")


        shutDownButton = getView()?.findViewById(R.id.overview_ShutDownButton)
                as Button
        shutDownButton.setOnClickListener { shutDownClick() }


        toLogOnButton = getView()?.findViewById(R.id.overview_LogOnButton)
                as Button
        toLogOnButton.setOnClickListener { toLogOnClick() }


        toHomeButton = getView()?.findViewById(R.id.overview_HomePageButton)
                as Button
        toHomeButton.setOnClickListener { toHomeClick() }


        displayUserNameForCurrentUser()
        displayUserEmailForCurrentUser()
        displayPhoneForCurrentUser()
        displayAgeForCurrentUser()
        displayPasswordForCurrentUser()

        displayIndianFoodForCurrentUser()
        displayItalianFoodForCurrentUser()
        displayAfricanFoodForCurrentUser()
        displayAsianFoodForCurrentUser()
        displayFoodCreationsForCurrentUser()
        displayFoodFailsForCurrentUser()

        displayBookingLocationForCurrentUser()
        displayBookingDateForCurrentUser()
        displayBookingTimeForCurrentUser()
    }

    /**
     * This function allows the code to display a toast under certain conditions
     *      with a certain message.
     */

    private fun displayMessage(view: View, msgText: String){
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }

    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayUserNameForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val userName = documentSnapshot.getString("UserName")
                    if (userName != null) {
                        displayUserName.text = "$userName"
                    } else {
                        Log.d(ContentValues.TAG, " 'User Name' is null in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Document 'User Name' does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting 'User Name' ", e)
            }
    }

    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayUserEmailForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val email = documentSnapshot.getString("Email")
                    if (email != null) {
                        displayUserEmail.text = "$email"
                    } else {
                        Log.d(ContentValues.TAG, " 'Email' is null in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Document 'Email' does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Email' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayPhoneForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val phone = documentSnapshot.getString("Number")
                if (phone != null) {
                    //val phoneInt = phone.toInt()
                    displayUserPhone.text = "$phone"
                } else {
                    Log.d(ContentValues.TAG, " 'Phone' is null in the document.")
                }
            } else {
                Log.d(ContentValues.TAG, "Document 'Phone' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Phone' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayAgeForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val age = documentSnapshot.getString("Age")
                if (age != null) {
                    displayUserAge.text = "$age"
                } else {
                    Log.d(ContentValues.TAG, " 'Age' is null in the document.")
                }
            } else {
                Log.d(ContentValues.TAG, "Document 'Age' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Age' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayPasswordForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val password = documentSnapshot.getString("Password")
                if (password != null) {
                    displayUserPassword.text = "$password"
                } else {
                    Log.d(ContentValues.TAG, " 'Password' is null in the document.")
                }
            } else {
                Log.d(ContentValues.TAG, "Document 'Password' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Password' ", e)
            }
    }

    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayIndianFoodForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val indianFood = documentSnapshot.getBoolean("IndianFood")

                    if (indianFood != null) {
                        displayIndianFood.text = "$indianFood"
                    } else {
                        Log.d(ContentValues.TAG, " 'Indian Food' is null in the document.")
                    }

                } else {
                    Log.d(ContentValues.TAG, "Document 'Indian Food' does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Indian Food' ", e)
            }
    }

    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayItalianFoodForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val italianFood = documentSnapshot.getBoolean("ItalianFood")

                if (italianFood != null) {
                    displayItalianFood.text = "$italianFood"
                } else {
                    Log.d(ContentValues.TAG, " 'Italian Food' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Italian Food' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Italian Food' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayAfricanFoodForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val africanFood = documentSnapshot.getBoolean("AfricanFood")

                if (africanFood != null) {
                    displayAfricanFood.text = "$africanFood"
                } else {
                    Log.d(ContentValues.TAG, " 'African Food' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'African Food' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'African Food' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayAsianFoodForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val asianFood = documentSnapshot.getBoolean("AsianFood")

                if (asianFood != null) {
                    displayAsianFood.text = "$asianFood"
                } else {
                    Log.d(ContentValues.TAG, " 'Asian Food' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Asian Food' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Asian Food' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayFoodCreationsForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val foodCreations = documentSnapshot.getBoolean("NewCreations")

                if (foodCreations != null) {
                    displayFoodCreations.text = "$foodCreations"
                } else {
                    Log.d(ContentValues.TAG, " 'Food Creations' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Food Creations' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Food Creations' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayFoodFailsForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val foodFails = documentSnapshot.getBoolean("FoodFails")

                if (foodFails != null) {
                    displayFoodFails.text = "$foodFails"
                } else {
                    Log.d(ContentValues.TAG, " 'Food Fails' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Food Fails' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document 'Food Fails' ", e)
            }
    }



    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayBookingLocationForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val bookingLocation = documentSnapshot.getString("BookingLocation")

                    if (bookingLocation != null) {
                        displayBookingLocation.text = "$bookingLocation"
                    } else {
                        Log.d(ContentValues.TAG, " 'Booking Location' is null in the document.")
                    }

                } else {
                    Log.d(ContentValues.TAG, "Document 'Booking Location' does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting 'Booking Location' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayBookingDateForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val bookingDate = documentSnapshot.getString("BookingDate")

                if (bookingDate != null) {
                    displayBookingDate.text = "$bookingDate"
                } else {
                    Log.d(ContentValues.TAG, " 'Booking Date' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Booking Date' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting 'Booking Date' ", e)
            }
    }


    /**
     *
     */

    @SuppressLint("SetTextI18n")
    private fun displayBookingTimeForCurrentUser() {
        val documentReference = db.collection("newlyCreatedUsers")
            .document("RajTesterDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val bookingTime = documentSnapshot.getString("BookingTime")

                if (bookingTime != null) {
                    displayBookingTime.text = "$bookingTime"
                } else {
                    Log.d(ContentValues.TAG, " 'Booking Time' is null in the document.")
                }

            } else {
                Log.d(ContentValues.TAG, "Document 'Booking Time' does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting 'Booking Time' ", e)
            }
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


} // end of class




