package com.mazer.exhbuy.viewmodels

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.core.app.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.mazer.exhbuy.EXHBuyApp
import com.mazer.exhbuy.R
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class HomeVM @Inject constructor() : ViewModel() {

    lateinit var launcher: ActivityResultLauncher<Intent>
    private val mAuth = FirebaseAuth.getInstance()
    var verificationOtp = ""

    @Inject
    lateinit var application: EXHBuyApp

//    init {
//        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
//            try {
//                val account = task.getResult(ApiException::class.java)
//                if (account != null) {
//                    firebaseAuthWithGoogle(account.idToken!!)
//                }
//            } catch (e: ApiException) {
//                Log.d("GoogleSignIn", "Api exception")
//                Toast.makeText(
//                    applicationContext,
//                    "Can't authenticate with Google",
//                    Toast.LENGTH_SHORT
//                ).show()
//            }
//        }
//    }

    fun sendPhone(
        phoneNumber: String,
        context: Context,
        activity: ComponentActivity,
        navController: NavController
    ) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+7$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(activity)
            .setCallbacks(object :
                PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Toast.makeText(
                        context,
                        "Verification Completed",
                        Toast.LENGTH_SHORT
                    ).show()
                    checkAuthState(navController)
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(
                        context,
                        "Verification Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCodeSent(otp: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(otp, p1)
                    verificationOtp = otp
                    Toast.makeText(
                        context,
                        "Otp Send Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(
        otp: String,
        context: Context,
        navController: NavController
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Verification Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    navController.navigate(NavigationRouts.HOME.route)
                } else
                    Toast.makeText(
                        context,
                        "Wrong Otp",
                        Toast.LENGTH_SHORT
                    ).show()
            }
    }

    fun checkAuthState(
        navController: NavController
    ) {
        if (mAuth.currentUser != null) {
            navController.navigate(NavigationRouts.HOME.route)
        }
    }

    fun signInWithGoogle(
        activity: ComponentActivity
    ) {
        val signIngClient = getClient(activity)
        launcher.launch(signIngClient.signInIntent)
    }

    fun firebaseAuthWithGoogle(
        idToken: String,
        context: Context,
        navController: NavController
    ) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener() {
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Verification Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    checkAuthState(navController)
                } else
                    Toast.makeText(
                        context,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
            }
    }

    //Google authentication
    fun getClient(
        activity: ComponentActivity
    ): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(activity.resources.getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(activity, gso)
    }

}