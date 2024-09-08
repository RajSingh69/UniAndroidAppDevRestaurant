package com.example.mobileappdevcoursework

import android.content.ContentValues
import android.media.Rating
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

/**
 * This class is for the user to add and edit a review
 * @author Rajan Singh Bhamra - 2034215
 * @version V16
 */

class FragmentAddReview: Fragment() {

    /**
     * These calls are instantiating the necessary firebase, the EditTexts,
     *      the Buttons and the RatingBars features
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var reviewLocation: EditText
    private lateinit var reviewContent: EditText
    private lateinit var submitReviewButton: Button

    private lateinit var rateFoodRatingBar : RatingBar
    private lateinit var rateServiceRatingBar : RatingBar

    /**
     * This onCreateView allows the code to inflate the defined XML layout
     *      inside the container so it can be seen.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_add_review, container, false)!!;
    }

    /**
     * When the view is created, this code gets the IDs and passes it into the previously defined
     *      lateinit variables. It also calls the method when button is clicked.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        reviewLocation = getView()?.findViewById(R.id.UserInputReviewLocation_NewReviewPage)
                as EditText
        reviewContent = getView()?.findViewById(R.id.UserInputReviewContent_NewReviewPage)
                as EditText

        rateFoodRatingBar = getView()?.findViewById(R.id.RateTheFood_ratingBar)
                as RatingBar
        rateServiceRatingBar = getView()?.findViewById(R.id.Rate_The_Service_ratingBar)
                as RatingBar

        submitReviewButton = getView()?.findViewById(R.id.SubmitReviewButton_AddNewReviewPage)
                as Button
        submitReviewButton.setOnClickListener { submitReviewClick() }
        submitReviewClick()

    }

    /**
     * This function calls the methods which will update the location, content
     *      and rating bar. It can be used when the user wants to update a booking.
     */

    private fun submitReviewClick() {
        updateReviewLocation()
        updateReviewContent()
        updateFoodRatingBar()
    }

    /**
     * This code is used for updating the review LOCATION.
     * It passes in the prev. defined variable value and updates the database.
     * Whether successful or negative, there is a message.
     */

    private fun updateReviewLocation() {
        val documentReference = db.collection("newlyCreatedReviews")
            .document("reviewOneDocument")
        val newReviewLocation = reviewLocation.text.toString()
        documentReference
            .update("theReviewLocation", newReviewLocation)
            .addOnSuccessListener { Log.d(ContentValues.TAG,
                "Review Location successfully updated!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG,
                "Error updating Review Location.", e) }
    }

    /**
     * This code is used for updating the review CONTENT.
     * It passes in the prev. defined variable value and updates the database.
     * Whether successful or negative, there is a message.
     */

    private fun updateReviewContent() {
        val documentReference = db.collection("newlyCreatedReviews")
            .document("reviewOneDocument")
        val newReviewContent = reviewContent.text.toString()
        documentReference
            .update("theReviewContent", newReviewContent)
            .addOnSuccessListener { Log.d(ContentValues.TAG,
                "Review Content successfully updated!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG,
                "Error updating Review Content.", e) }
    }

    /**
     * This code is used for updating the RATING BAR.
     * It passes in the prev. defined variable value and updates the database.
     * Whether successful or negative, there is a message.
     */

    private fun updateFoodRatingBar() {
        val documentReference = db.collection("newlyCreatedReviews")
            .document("reviewOneDocument")
        val newFoodRatingBar = rateFoodRatingBar.rating
        documentReference
            .update("superRealFoodRatingBar", newFoodRatingBar)
            .addOnSuccessListener { Log.d(ContentValues.TAG,
                "RatingBar successfully updated!") }
            .addOnFailureListener { e -> Log.w(ContentValues.TAG,
                "Error updating RatingBar", e) }

    }

}