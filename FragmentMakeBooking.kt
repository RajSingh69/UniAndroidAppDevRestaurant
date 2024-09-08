package com.example.mobileappdevcoursework

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * This class allows the user to make a booking.
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class FragmentMakeBooking: Fragment() {

    /**
     * These lines instantiate firebase and calls the necessary
     *      Button and EditTexts.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var inputRestaurantLocation: EditText
    private lateinit var inputRestaurantDate: EditText
    private lateinit var inputRestaurantTime: EditText
    private lateinit var makeBookingButton: Button

    /**
     * This onCreateView allows the code to inflate the defined XML layout
     *      inside the container so it can be seen.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_make_a_booking, container, false)!!;
    }

    /**
     * This sets the IDs to the button / EditText.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inputRestaurantLocation =
            getView()?.findViewById(R.id.UserInputRestaurantLocation_BookARestaurant) as EditText
        inputRestaurantDate =
            getView()?.findViewById(R.id.UserInputRestaurantDate_BookARestaurant) as EditText
        inputRestaurantTime =
            getView()?.findViewById(R.id.UserInputRestaurantTime_BookARestaurant) as EditText

        makeBookingButton =
            getView()?.findViewById(R.id.MakeABookingButton_BookARestaurant) as Button
        makeBookingButton.setOnClickListener { submitMakeReviewClick() }

        updateRestaurantLocation()
    }

    /**
     * This function calls the relevant methods when things need to be updated.
     */

    private fun submitMakeReviewClick() {
        updateRestaurantLocation()
        updateRestaurantDate()
        updateRestaurantTime()
    }

    /**
     * This function updates the restaurant location booking.
     */


    private fun updateRestaurantLocation() {
        val documentReference = db.collection("newlyCreatedUsers").document("RajTesterDocument")
        val newLocation = inputRestaurantLocation.text.toString()
        documentReference.update("BookingLocation", newLocation).addOnSuccessListener {
                Log.d(TAG, "DocumentSnapshot successfully updated!")
            displayMessage(makeBookingButton, getString(R.string.daLocationUpdated))
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error updating document", e)
            }
    }

    /**
     * This function updates the restaurant date booking.
     */


    private fun updateRestaurantDate() {
        val documentReference = db.collection("newlyCreatedUsers").document("RajTesterDocument")
        val newDate = inputRestaurantDate.text.toString()
        documentReference.update("BookingDate", newDate).addOnSuccessListener {
                Log.d(TAG, "Date successfully updated!")
                displayMessage(makeBookingButton, getString(R.string.daDateUpdated))
            }.addOnFailureListener{ e ->
                Log.w(TAG, "Error updating Date", e)
            }
    }

    /**
     * This function updates the restaurant time booking.
     */


    private fun updateRestaurantTime() {
        val documentReference = db.collection("newlyCreatedUsers").document("RajTesterDocument")
        val newTime = inputRestaurantTime.text.toString()
        documentReference.update("BookingTime", newTime).addOnSuccessListener {
                Log.d(TAG, "Time successfully updated!")
                displayMessage(makeBookingButton, getString(R.string.daTimeUpdated))
            }.addOnFailureListener { e ->
                Log.w(TAG, "Error updating Time", e)
            }
    }

    /**
     * This function allows the user to view a message / toast.
     */

    private fun displayMessage(view: View, msgText: String) {
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }

}




//    private fun updateRestaurantLocation() {
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser != null) {
//            val userId = currentUser.uid
//
//            // Specify the document reference based on the user ID
//            val documentReference = db.collection("newlyCreatedUsers").document(userId)
//
//            // Assuming inputRestaurantLocation is an EditText widget
//            val newLocation = inputRestaurantLocation.text.toString()
//
//            // Update the field in the specified document
//            documentReference
//                .update("BookingLocation", newLocation)
//                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully updated!") }
//                .addOnFailureListener { e -> Log.w(TAG, "Error updating document", e) }
//        }
//    }



