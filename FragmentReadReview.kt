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

/**
 * This class is created so the user can read reviews.
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class FragmentReadReview: Fragment() {

    /**
     * These lines instantiate firebase and call in the necessary
     *      TextViews and RatingBar.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var restaurantName: TextView
    private lateinit var restaurantReview: TextView
    private lateinit var actualRatingBar: RatingBar


    /**
     * This onCreateView allows the code to inflate the defined XML layout
     *      inside the container so it can be seen.
     */

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_read_review, container, false)!!;

    }

    /**
     * This method is called when the view is created. It sets the necessary IDs for the
     *      TextViews and RatingBar. It also calls the methods when it is the right time
     *      to be displayed.
     */

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        restaurantName = getView()?.findViewById(R.id.text_RestaurantName_ViewTheRestaurant) as TextView
        restaurantReview = getView()?.findViewById(R.id.text_ActualReview_ViewTheRestaurant) as TextView
        actualRatingBar = getView()?.findViewById(R.id.actualStarRating_ViewRestaurantReviews) as RatingBar

        displayRestaurantName()
        displayRestaurantContent()
        displayRatingBar()

    }

    /**
     * This code gets the restaurant name from the database
     */

    @SuppressLint("SetTextI18n")
    private fun displayRestaurantName() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val showingRestaurantName = documentSnapshot.getString("theReviewLocation")
                    if (showingRestaurantName != null) {
                        restaurantName.text = "$showingRestaurantName"
                    } else {
                        Log.d(ContentValues.TAG, "Restaurant Name in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Document Restaurant Name does not exist for user with ID")
                }
            }.addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting Restaurant Name", e)
            }
    }

    /**
     * This code gets the restaurant content from the database
     */

    @SuppressLint("SetTextI18n")
    private fun displayRestaurantContent() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val showingRestaurantContent = documentSnapshot.getString("theReviewContent")
                    if (showingRestaurantContent != null) {
                        restaurantReview.text = "$showingRestaurantContent"
                    } else {
                        Log.d(ContentValues.TAG, "Review Content in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Review Content does not exist for user with ID")
                }
            }.addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document Review Content", e)
            }
    }

    /**
     * This code gets the restaurant rating from the database.
     */

    private fun displayRatingBar() {
        val documentReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")
        documentReference.get().addOnSuccessListener { documentSnapshot ->
                if (documentSnapshot.exists()) {
                    val showingRatingBar = documentSnapshot.getDouble("superRealFoodRatingBar")
                    if (showingRatingBar != null) {
                        actualRatingBar.rating = showingRatingBar.toFloat()
                    } else {
                        Log.d(ContentValues.TAG, "Rating Bar in the document.")
                    }
                } else {
                    Log.d(ContentValues.TAG, "Rating Bar does not exist for user with ID")
                }
            }.addOnFailureListener { e ->
                Log.d(ContentValues.TAG, "Error getting document Rating Bar", e)
            }
    }



} //End of Class