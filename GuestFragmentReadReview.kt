package com.example.mobileappdevcoursework

import android.annotation.SuppressLint
import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class GuestFragmentReadReview: Fragment() {



    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var restaurantName: TextView
    private lateinit var restaurantReview: TextView

    private lateinit var actualRatingBar: RatingBar


    // actualStarRating_ViewRestaurantReviews

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_read_review, container, false)!!;

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // code goes here
        restaurantName = getView()?.findViewById(R.id.text_RestaurantName_ViewTheRestaurant) as TextView
        restaurantReview = getView()?.findViewById(R.id.text_ActualReview_ViewTheRestaurant) as TextView
        actualRatingBar = getView()?.findViewById(R.id.actualStarRating_ViewRestaurantReviews) as RatingBar

        displayRestaurantName()
        displayRestaurantContent()
        displayRatingBar()

    }


    @SuppressLint("SetTextI18n")
    private fun displayRestaurantName() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val showingRestaurantName = documentSnapshot.getString("theReviewLocation")
                    if (showingRestaurantName != null) {
                        restaurantName.text = "$showingRestaurantName"
                    } else {
                        Log.d(ContentValues.TAG, "null in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Document does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document", e)
            }
    }



    @SuppressLint("SetTextI18n")
    private fun displayRestaurantContent() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference
            .get()
            .addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val showingRestaurantContent = documentSnapshot.getString("theReviewContent")
                    if (showingRestaurantContent != null) {
                        restaurantReview.text = "$showingRestaurantContent" // CHANGE THIS LINE
                    } else {
                        Log.d(ContentValues.TAG, "null in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Document does not exist for user with ID")
                }
            }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document", e)
            }
    }



    private fun displayRatingBar() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val showingRatingBar = documentSnapshot.getDouble("superRealFoodRatingBar")
                if (showingRatingBar != null) {
                    actualRatingBar.rating = showingRatingBar.toFloat()
                } else {
                    Log.d(ContentValues.TAG, "null in the document.")
                }
            } else {
                Log.d(ContentValues.TAG, "Document does not exist for user with ID")
            }
        }
            .addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document", e)
            }
    }



}