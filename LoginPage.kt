package com.example.mobileappdevcoursework

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethod
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth

/**
 * This class deals with the logging in page.
 * User needs authentication to update / create / delete a review.
 * @author Rajan Singh Bhamra - 2034215
 * @version V15
 */

class LoginPage : AppCompatActivity() {

    /**
     *  This instantiates firebase, and calls firebase authentication.
     *  It also sets the current user variable to be authenticated -
     *      uses Firebase API.
     *  Sets the necessary lateinit variables for email, pswrd and button.
     */

    private var mAuth = FirebaseAuth.getInstance()
    private var currentUser = mAuth.currentUser

    private lateinit var userEmail : EditText
    private lateinit var userPassword : EditText
    private lateinit var loginBtn: Button

    /**
     * This sets the necessary .
     */

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_page_layout)
        FirebaseApp.initializeApp(this)

        userEmail = findViewById(R.id.editText_UserInputEmail_LoginPage)
        userPassword = findViewById(R.id.editText_UserInputPassword_LoginPage)
        loginBtn = findViewById(R.id.LogMeInButton_LoginPage)
        loginBtn.setOnClickListener { v -> loginClick(v) }

        val signUpLaunchButton = findViewById<Button>(R.id.SignUpButton_LoginPage)
        signUpLaunchButton.setOnClickListener { v -> signUp(v) }
        //val logMeInLaunchButton = findViewById<Button>(R.id.LogMeInButton_LoginPage)
        //logMeInLaunchButton.setOnClickListener { v -> logMeIn(v) }
    }

    /**
     *  This is the loginClick method. It is called when the user clicks the login button.
     *  It needs the username and password fields to not be empty, and to match up
     *       in the Firebase authentication database.
     *  (Successful) - calls various methods
     *  (UNSuccessful) - takes users to sign up page and displays error msg.
     */

    private fun loginClick (view: View){

        if (userEmail.length() == 0 || userPassword.length() == 0) {
            displayMessage( (loginBtn), getString(R.string.no_user_or_password_boyo) )
            return
        }

        mAuth.signInWithEmailAndPassword(
            userEmail.text.toString(),
            userPassword.text.toString()).addOnCompleteListener(this) {
                task ->
                if (task.isSuccessful) {
                    closeKeyBoard()
                    update()
                    logMeIn(view)
                } else {
                    closeKeyBoard()
                    displayMessage( (loginBtn), getString(R.string.loginFailure) )
                    signUp(view)
                }

            }
    }


    /**
     *  Function updates UI with login status.
     *  It first sets the current user to be the authenticated user.
     *  Occurs when the email and passwords fields are filled.
     */

    @SuppressLint("SetTextI18n")
    private fun update(){
        currentUser = mAuth.currentUser

        val currentEmail = currentUser?.email
        val greetingSpace = findViewById<TextView>(R.id.greetingView)

        if (currentEmail == null) {
            greetingSpace.text = "Please sign in above."
        } else {
            greetingSpace.text = "You are currently signed in with" + {currentUser}
        }
        currentUser = this.currentUser
        Log.d("THE CURRENT USER IS", "THE CURRENT USER IS $currentUser")
    }


    /**
     *  Function makes snack bars easier
     */

    private fun displayMessage(view: View , msgText: String){
        val sb = Snackbar.make(view, msgText, Snackbar.LENGTH_SHORT)
        sb.show()
    }


    /**
     *  This function automatically closes off the keyboard.
     *  It hides the input from the user, makes easier accessibility.
     */

    private fun closeKeyBoard() {
        val view = this.currentFocus
        if (view != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


    /**
     *  This function takes the user to the sign up page.
     *  Used when the button is clicked, starts a new activity.
     */

    private fun signUp(view: View) {
        val newIntent = Intent(this, NewUserPage::class.java)
        startActivity(newIntent)
    }


    /**
     *  This function logs in the user.
     *  When called, it takes the user to the activity "AppBarSetup".
     *      Aka - Goes to fragmentation page.
     */

    private fun logMeIn(view: View) {
        val newIntent = Intent(this, AppBarSetup::class.java)
        startActivity(newIntent)
    }

}








