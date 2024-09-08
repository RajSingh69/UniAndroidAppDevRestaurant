package com.example.mobileappdevcoursework

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore
import com.google.firebase.firestore.snapshots

/**
 * This class is the main activity. It is he one called when the code starts up for the first time.
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */

class MainActivity : AppCompatActivity() {

    /**
     * These codes instantiate firebase and the necessary TextView.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore
    private lateinit var topRestaurantText: TextView

    /**
     * When this function is called, it sets the ID to the correct buttons and
     *      TextView, and sets up the onclick listeners and handlers.
     */


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        topRestaurantText = findViewById(R.id.TopRestaurantText_HomePage)

        val newUserLaunchButton = findViewById<Button>(R.id.NewUserButton_OnHomePage)
        newUserLaunchButton.setOnClickListener { v -> launchNewUser(v) }

        val loginLaunchButton = findViewById<Button>(R.id.LoginButton_OnHomePage)
        loginLaunchButton.setOnClickListener { v -> launchLogin(v) }

        val guestLaunchButton = findViewById<Button>(R.id.LoginAsGuestButton_OnHomePage)
        guestLaunchButton.setOnClickListener { v -> launchGuest(v) }

        getTheHighestRatedRestaurant()

    }

    /**
     * This function launches the new user class when called.
     */

    private fun launchNewUser(view: View) {
        val newIntent = Intent(this, NewUserPage::class.java)
        startActivity(newIntent)
    }

    /**
     * This function launches the login page class when called.
     */

    private fun launchLogin(view: View) {
        val newIntent = Intent(this, LoginPage::class.java)
        startActivity(newIntent)
    }

    /**
     * This function launches the guest app bar class when called.
     */

    private fun launchGuest(view: View) {
        val newIntent = Intent(this, GuestAppBarSetup::class.java)
        startActivity(newIntent)
    }

    /**
     * This function displays the highest rated review.
     * It gets the necessary document from the necessary database.
     * Itt sorts it out be descending order, getting the first value (which is highest)
     * It then displays it with the name and the rating.
     */

    @SuppressLint("SetTextI18n")
    private fun getTheHighestRatedRestaurant() {
        val collectionReference = db.collection("newlyCreatedReviews")
        val query = collectionReference.orderBy("superRealFoodRatingBar",
            Query.Direction.DESCENDING)
        query.limit(1).get()
            .addOnSuccessListener { querySnapshot ->
                if (!querySnapshot.isEmpty) {
                    val theHighestRatedReview = querySnapshot.documents[0]
                    val gettingReviewLocation = theHighestRatedReview.
                    getString("theReviewLocation")
                    val gettingRatingBarValue = theHighestRatedReview.
                    getDouble("superRealFoodRatingBar")
                    topRestaurantText.text = "Highest Rated Review Location:" +
                            " $gettingReviewLocation" + " (Has $gettingRatingBarValue stars!)"
                } else {
                    topRestaurantText.text = "No reviews submitted! Be the first!"
                }
            }
    }


}


