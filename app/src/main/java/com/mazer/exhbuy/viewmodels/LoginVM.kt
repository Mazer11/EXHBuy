package com.mazer.exhbuy.viewmodels

import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.runtime.MutableState
import androidx.core.app.ComponentActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.userProfileChangeRequest
import com.mazer.exhbuy.EXHBuyApp
import com.mazer.exhbuy.ui.navigation.NavigationRouts
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor() : ViewModel() {

    lateinit var launcher: ActivityResultLauncher<Intent>
    val mAuth = FirebaseAuth.getInstance()
    var verificationOtp = ""
    val TAG = "login_view_model"

    private val _isOtpSended: MutableLiveData<Boolean> by lazy {
        MutableLiveData<Boolean>(false)
    }
    val isOtpSended: LiveData<Boolean> = _isOtpSended

    @Inject
    lateinit var application: EXHBuyApp

    fun sendPhone(
        phoneNumber: String,
        context: Context,
        activity: ComponentActivity,
        navController: NavController
    ) {
        mAuth.useAppLanguage()
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
                    _isOtpSended.value = true
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    fun verifyOtp(
        otp: String,
        userName: MutableState<String>? = null,
        context: Context,
        navController: NavController
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener() {
                val isNewUser = mAuth.currentUser?.displayName == null
                val updateRequest: UserProfileChangeRequest
                if (isNewUser) {
                    updateRequest = userProfileChangeRequest {
                        displayName = userName?.value ?: "John Doe"
                    }
                    mAuth.currentUser!!.updateProfile(updateRequest)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful)
                                Log.d(TAG, "User profile updated.")
                            else
                                Log.e(
                                    TAG,
                                    "Get error while updating profile: ${task.exception.toString()}"
                                )
                        }
                }
                if (it.isSuccessful) {
                    Toast.makeText(
                        context,
                        "Verification Successful",
                        Toast.LENGTH_SHORT
                    ).show()

                    navController.navigate(route = NavigationRouts.HOME.route) {
                        popUpTo(NavigationRouts.HOME.route) { inclusive = true }
                    }
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
}