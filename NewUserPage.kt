package com.example.mobileappdevcoursework

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.Timestamp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import java.util.Date
import java.util.Objects

/**
 * This class allows the app user to create an account
 * @author Rajan Singh Bhamra - 2034215
 * @version V17
 */
class NewUserPage : AppCompatActivity() {

    /**
     * This code instantiates firebase and sets the necessary
     *      Buttons, EditTexts, Switches and Strings.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser
    val db = Firebase.firestore

    private lateinit var enterEmail: EditText
    private lateinit var enterPassword: EditText
    private lateinit var createUserBtn: Button

    private lateinit var enterName: EditText
    private lateinit var enterNumber: EditText
    private lateinit var enterAge: EditText

    private lateinit var indianFoodToggle: Switch
    private lateinit var italianFoodToggle: Switch
    private lateinit var asianFoodToggle: Switch
    private lateinit var africanFoodToggle: Switch
    private lateinit var foodCreationsToggle: Switch
    private lateinit var foodFailsToggle: Switch

    private lateinit var bookingLocation: String
    private lateinit var bookingDate: String
    private lateinit var bookingTime: String

    /**
     * This sets the IDs for the lateinit variables and sets some button onclick listeners up.
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_user_page_layout)
        FirebaseApp.initializeApp(this)

        enterEmail = findViewById(R.id.editText_EnterEmail_CreateUserAccount)
        enterPassword = findViewById(R.id.editText_EnterPassword_CreateUserAccount)

        enterName = findViewById(R.id.editText_EnterName_CreateUserAccount)
        enterNumber = findViewById(R.id.editText_EnterPhone_CreateUserAccount)
        enterAge = findViewById(R.id.editText_EnterAge_CreateUserAccount)

        indianFoodToggle = findViewById(R.id.indianFoodToggle)
        italianFoodToggle = findViewById(R.id.italianFoodToggle)
        asianFoodToggle = findViewById(R.id.asianFoodToggle)
        africanFoodToggle = findViewById(R.id.africanFoodToggle)
        foodCreationsToggle = findViewById(R.id.foodCreationsToggle)
        foodFailsToggle = findViewById(R.id.foodFailsToggle)

        //This is for the real working stuff
        createUserBtn = findViewById(R.id.CreateUserButton_NewUserPage)
        createUserBtn.setOnClickListener { v -> createUserOnClick(v) }

        val loginButton = findViewById<Button>(R.id.LoginButton_NewUserPage)
        loginButton.setOnClickListener { v -> launchLoginPage(v) }
    } //end of OnCreate


    /**
     * This code creates a user account.
     * it gets the inputted username and password.
     * If there is no account with those credentials, code says new user created
     * Otherwise, no user created as prev. exists.
     */

    private fun createUserOnClick(view: View) {
        mAuth.signOut()
        currentUser = mAuth.currentUser
        if(mAuth.currentUser != null) {
            displayMessage(createUserBtn, getString(R.string.registerWhileLoggedIn))
        } else {
            mAuth.createUserWithEmailAndPassword(
                enterEmail.text.toString(),
                enterPassword.text.toString()
            ).addOnCompleteListener(this) { task ->
                if(task.isSuccessful) {
                    displayMessage(createUserBtn, getString(R.string.newUserCreatedMsg))
                    saveUserInfoReal()
                    saveNewBooking()
                } else {
                    displayMessage(createUserBtn, getString(R.string.newUserNotCreatedMsg))
                }
            }

        }
    }

    /**
     * This creates the basic database for the user.
     * Gets all the inputted data and stores it.
     */

    private fun saveUserInfoReal() {
        val user = hashMapOf(
            "UserName" to enterName.text.toString(),
            "Email" to enterEmail.text.toString(),
            "Number" to enterNumber.text.toString(),
            "Age" to enterAge.text.toString(),
            "Password" to enterPassword.text.toString(),

            "IndianFood" to indianFoodToggle.isChecked,
            "ItalianFood" to italianFoodToggle.isChecked,
            "AsianFood" to asianFoodToggle.isChecked,
            "AfricanFood" to africanFoodToggle.isChecked,
            "NewCreations" to foodCreationsToggle.isChecked,
            "FoodFails" to foodFailsToggle.isChecked,

            "BookingLocation" to "None Yet!",
            "BookingDate" to "None Yet!",
            "BookingTime" to "None Yet!"
        )

        val docReference = db.collection("newlyCreatedUsers").document("RajTesterDocument")
        docReference
            .set(user)
            .addOnSuccessListener {
                Log.d(TAG, "New user info successfully")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding/updating new user info", e)
            }
    }

    /**
     * This function allows the user to save the booking.
     */

    private fun saveNewBooking() {

        val newReview = hashMapOf(

            "theReviewLocation" to "AAAAAHHHHHH!!! No Location Yet!",
            "theReviewContent" to "AAAAAAAHHHH!!!! No Content Yet!",

            "superRealFoodRatingBar" to 0.0,
            "superRealServiceRatingBar" to 0.0

        )

        val docReference = db.collection("newlyCreatedReviews").document("reviewOneDocument")

        docReference
            .set(newReview)
            .addOnSuccessListener {
                Log.d(ContentValues.TAG, "Rating info added/updated successfully")
            }
            .addOnFailureListener { e ->
                Log.w(ContentValues.TAG, "Error adding/updating rating info", e)
            }

    }

    /**
     * This code updates the current user to be authenticated user.
     */

    @SuppressLint("SetTextI18n")
    private fun update(){
        currentUser = mAuth.currentUser

        val currentEmail = currentUser?.email
        val greetingSpace = findViewById<TextView>(R.id.greetingView)

        if (currentEmail == null) {
            greetingSpace.text = "Please sign in above."
        } else {
            greetingSpace.text = "You are currently signed in with" + {currentEmail}
        }
    }

    /**
     * This code allows snack bars to be seen easily.
     */

    private fun displayMessage(view: View , msgText: String){
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }

    /**
     * This code launches the login page.
     */

    private fun launchLoginPage(view: View) {
        val newIntent = Intent(this, LoginPage::class.java)
        startActivity(newIntent)
    }

} //end of CLASS





// ORIGINAL WORKING DO NOT MESS AROUND WITH

//    private fun saveUserInfoReal() {
//        val user = hashMapOf(
//            "UserName" to enterName.text.toString(),
//            "Email" to enterEmail.text.toString(),
//            "Number" to enterNumber.text.toString(),
//            "Age" to enterAge.text.toString(),
//            "Password" to enterPassword.text.toString(),
//
//            "IndianFood" to indianFoodToggle.isChecked,
//            "ItalianFood" to italianFoodToggle.isChecked,
//            "AsianFood" to asianFoodToggle.isChecked,
//            "AfricanFood" to africanFoodToggle.isChecked,
//            "NewCreations" to foodCreationsToggle.isChecked,
//            "FoodFails" to foodFailsToggle.isChecked,
//
//            "BookingLocation" to null,
//            "BookingDate" to null,
//            "BookingTime" to null
//        )
//
//
//        db.collection("newlyCreatedUsers")
//            .add(user)
//            .addOnSuccessListener { documentReference ->
//                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//            }
//            .addOnFailureListener { e ->
//                Log.w(TAG, "Error adding document", e)
//            }
//
//    }


//    private fun launchNewAccount(view: View) {
//        val newIntent = Intent(this, LoginPage::class.java)
////        val newIntent = Intent(this, AppBarSetup::class.java)
//        startActivity(newIntent)
//    }


//RAJ DEBUGGING HERE
//    private lateinit var CreateUserButton_NewUserPage: Button
//    private lateinit var editText_EnterName_CreateUserAccount: EditText
//    private lateinit var editText_EnterEmail_CreateUserAccount: EditText
//    private lateinit var editText_EnterPhone_CreateUserAccount: EditText
//    private lateinit var editText_EnterAge_CreateUserAccount: EditText
//    private lateinit var editText_EnterPassword_CreateUserAccount: EditText
//    private lateinit var indianFoodToggle: Switch
//    private lateinit var italianFoodToggle: Switch
//    private lateinit var asianFoodToggle: Switch
//    private lateinit var africanFoodToggle: Switch
//    private lateinit var foodCreationsToggle: Switch
//    private lateinit var foodFailsToggle: Switch
//    private lateinit var bookingLocation: String
//    private lateinit var bookingDate: String
//    private lateinit var bookingTime: String
//    private lateinit var uselessTesterDeleteSoon: EditText

//         //RAJ DEBUGGING HERE
//        CreateUserButton_NewUserPage = findViewById(R.id.CreateUserButton_NewUserPage)
//        editText_EnterName_CreateUserAccount = findViewById(R.id.editText_EnterName_CreateUserAccount)
//        editText_EnterEmail_CreateUserAccount = findViewById(R.id.editText_EnterEmail_CreateUserAccount)
//        editText_EnterPhone_CreateUserAccount = findViewById(R.id.editText_EnterPhone_CreateUserAccount)
//        editText_EnterAge_CreateUserAccount = findViewById(R.id.editText_EnterAge_CreateUserAccount)
//        editText_EnterPassword_CreateUserAccount = findViewById(R.id.editText_EnterPassword_CreateUserAccount)
//        indianFoodToggle = findViewById(R.id.indianFoodToggle)
//        italianFoodToggle = findViewById(R.id.italianFoodToggle)
//        asianFoodToggle = findViewById(R.id.asianFoodToggle)
//        africanFoodToggle = findViewById(R.id.africanFoodToggle)
//        foodCreationsToggle = findViewById(R.id.foodCreationsToggle)
//        foodFailsToggle = findViewById(R.id.foodFailsToggle)
//
//        uselessTesterDeleteSoon = findViewById(R.id.uselessTesterDeleteSoon)
//
//
//
//
//        CreateUserButton_NewUserPage.setOnClickListener {
//            val db = UserDatabaseHelper (this)
//
//
//            val name = editText_EnterName_CreateUserAccount.text.toString()
//            val email = editText_EnterEmail_CreateUserAccount.text.toString()
//            val phone = editText_EnterPhone_CreateUserAccount.text.toString()
//            val age = editText_EnterAge_CreateUserAccount.text.toString()
//            val password = editText_EnterPassword_CreateUserAccount.text.toString()
//
//            val indianFood = indianFoodToggle.isChecked
//            val italianFood = italianFoodToggle.isChecked
//            val asianFood = asianFoodToggle.isChecked
//            val africanFood = africanFoodToggle.isChecked
//            val foodCreations = foodCreationsToggle.isChecked
//            val foodFails = foodFailsToggle.isChecked
//
//            val bookingLocation: String = ""
//            val bookingDate: String = ""
//            val bookingTime: String = ""
//
//
//            db.addUserInfoToDb(name, email, phone.toInt(), age.toInt(), password, indianFood, italianFood,
//                asianFood, africanFood, foodCreations, foodFails, bookingLocation, bookingDate,
//                bookingTime)
//
//            Toast.makeText(this, "$name added to database", Toast.LENGTH_LONG).show()
//
//            //Debugging here
//            Toast.makeText(applicationContext, "$name added to database", Toast.LENGTH_LONG).show()
//            Log.d("NewUserPage", "$name added to database")
//
//
//            editText_EnterName_CreateUserAccount.text.clear()
//            editText_EnterEmail_CreateUserAccount.text.clear()
//            editText_EnterPhone_CreateUserAccount.text.clear()
//            editText_EnterAge_CreateUserAccount.text.clear()
//            editText_EnterPassword_CreateUserAccount.text.clear()
//            indianFoodToggle.isChecked = false
//            italianFoodToggle.isChecked = false
//            asianFoodToggle.isChecked = false
//            africanFoodToggle.isChecked = false
//            foodCreationsToggle.isChecked = false
//            foodFailsToggle.isChecked = false
//
//
//        }//end of this wierd method
