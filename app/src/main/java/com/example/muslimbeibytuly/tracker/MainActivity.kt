package com.example.muslimbeibytuly.tracker

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.firebase.ui.auth.AuthUI
import java.util.Arrays.asList
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth





class MainActivity : AppCompatActivity() {
    companion object {
        const val RC_SIGN_IN = 123
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            showMainActivity()
        } else {
            val providers = asList(
                    AuthUI.IdpConfig.EmailBuilder().build(),
                    AuthUI.IdpConfig.GoogleBuilder().build()
            )
            startActivityForResult(
                    AuthUI.getInstance()
                            .createSignInIntentBuilder()
                            .setAvailableProviders(providers)
                            .setTosUrl("https://superapp.example.com/terms-of-service.html")
                            .setPrivacyPolicyUrl("https://superapp.example.com/privacy-policy.html")
                            .build(),
                    RC_SIGN_IN
            )
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)

            if (resultCode == Activity.RESULT_OK) {
                // Successfully signed in
                showMainActivity()
            } else {
                Log.e("Response error", response.toString())
                // Sign in failed, check response for error code
            }
        }

    }

    private fun showMainActivity() {
        val intent = Intent(this, MasterActivity()::class.java)
        startActivity(intent)
    }
}
