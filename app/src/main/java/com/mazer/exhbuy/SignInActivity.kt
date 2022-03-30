package com.mazer.exhbuy

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.FirebaseException
import com.google.firebase.auth.*
import com.mazer.exhbuy.ui.components.MyTextField
import com.mazer.exhbuy.ui.screens.LogInScreen
import com.mazer.exhbuy.ui.theme.AppTypography
import com.mazer.exhbuy.ui.theme.EXHBuyTheme
import java.util.concurrent.TimeUnit

class SignInActivity: ComponentActivity() {
    private lateinit var launcher: ActivityResultLauncher<Intent>
    private val mAuth = FirebaseAuth.getInstance()
    var verificationOtp = ""

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            if(mAuth.currentUser != null){
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
                finish()
            }
            else {
                EXHBuyTheme {
                    ChooseSignInType()
                }
            }
        }
        launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val account = task.getResult(ApiException::class.java)
                if(account !=null){
                    firebaseAuthWithGoogle(account.idToken!!)
                }
            } catch (e: ApiException){
                Log.d("GoogleSignIn", "Api exception")
                Toast.makeText(
                    applicationContext,
                    "Can't authenticate with Google",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun checkAuthState(){
        if(mAuth.currentUser != null){
            val i = Intent(this, MainActivity::class.java)
            startActivity(i)
            finish()
        }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun ChooseSignInType() {
        val navController = rememberNavController()
        Scaffold {
            SignInNavGraph(navController = navController)
        }
    }

    @Composable
    fun ChooseSignInTypeScreen(navController: NavHostController) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        signInWithGoogle()
                    }
                ) {
                    Text(
                        text = "Authenticate with Google"
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = {
                        navController.navigate(EXHBuyNav.PHONESIGNIN.route)
                    }
                ) {
                    Text(
                        text = "Authenticate with phone"
                    )
                }
            }
        }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun PhoneSignInScreen() {
        var otpVal = ""
        var phoneNumber by remember { mutableStateOf("") }

        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row {
                        Box(
                            modifier = Modifier.padding(horizontal = 4.dp)
                                .background(
                                    MaterialTheme.colorScheme.surfaceVariant,
                                    RoundedCornerShape(8.dp)
                                )
                                .padding(horizontal = 16.dp, vertical = 12.dp)
                        ) {
                            Text(
                                text = "+7",
                                style = AppTypography.bodyLarge
                            )
                        }
                        Spacer(modifier = Modifier.width(4.dp))
                        MyTextField(
                            hint = "Enter phone number...",
                            isNumericInput = true,
                            onTextValueChange = {
                                phoneNumber = it
                            }
                        )
                    }
                    Button(
                        onClick = {
                            if (phoneNumber.isNotEmpty())
                                sendPhone(phoneNumber)
                        }
                    ) {
                        Text(
                            text = "Get code"
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    MyTextField(
                        hint = "Enter verification code...",
                        isNumericInput = true,
                        onTextValueChange = {
                            otpVal = it
                        }
                    )
                    Button(
                        onClick = {
                            if (otpVal != "")
                                verifyOtp(otpVal)
                        }
                    ) {
                        Text(
                            text = "Verify"
                        )
                    }
                }
            }
        }
    }

    private fun sendPhone(
        phoneNumber: String
    ) {
        val options = PhoneAuthOptions.newBuilder(mAuth)
            .setPhoneNumber("+7$phoneNumber")
            .setTimeout(60L, TimeUnit.SECONDS)
            .setActivity(this)
            .setCallbacks(object :
                PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                override fun onVerificationCompleted(p0: PhoneAuthCredential) {
                    Toast.makeText(
                        applicationContext,
                        "Verification Completed",
                        Toast.LENGTH_SHORT
                    ).show()
                    checkAuthState()
                }

                override fun onVerificationFailed(p0: FirebaseException) {
                    Toast.makeText(
                        applicationContext,
                        "Verification Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onCodeSent(otp: String, p1: PhoneAuthProvider.ForceResendingToken) {
                    super.onCodeSent(otp, p1)
                    verificationOtp = otp
                    Toast.makeText(
                        applicationContext,
                        "Otp Send Successfully",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }).build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }

    private fun verifyOtp(
        otp: String
    ) {
        val credential = PhoneAuthProvider.getCredential(verificationOtp, otp)
        FirebaseAuth.getInstance().signInWithCredential(credential)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Verification Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else
                    Toast.makeText(
                        applicationContext,
                        "Wrong Otp",
                        Toast.LENGTH_SHORT
                    ).show()
            }
    }

    //Google authentication
    private fun getClient(): GoogleSignInClient {
        val gso = GoogleSignInOptions
            .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, gso)
    }

    private fun signInWithGoogle() {
        val signIngClient = getClient()
        launcher.launch(signIngClient.signInIntent)
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        mAuth.signInWithCredential(credential)
            .addOnCompleteListener(this) {
                if (it.isSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Verification Successful",
                        Toast.LENGTH_SHORT
                    ).show()
                    checkAuthState()
                } else
                    Toast.makeText(
                        applicationContext,
                        "Something went wrong",
                        Toast.LENGTH_SHORT
                    ).show()
            }
    }

    @ExperimentalMaterial3Api
    @Composable
    fun SignInNavGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = EXHBuyNav.LOGIN.route
        ) {
            composable(route = EXHBuyNav.SIGNINCHOOSE.route) {
                ChooseSignInTypeScreen(navController)
            }

            composable(route = EXHBuyNav.PHONESIGNIN.route) {
                PhoneSignInScreen()
            }

            composable(route = EXHBuyNav.LOGIN.route) {
                LogInScreen(navController)
            }
        }
    }
}