package org.d3if3130.unspoken.google

import android.content.Context
import android.widget.Toast
import androidx.credentials.CredentialManager
import androidx.credentials.GetCredentialRequest
import androidx.navigation.NavHostController
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.R

open class GoogleSignIn(private val context: Context, private val scope: CoroutineScope) {

    val auth: FirebaseAuth = Firebase.auth
    private val credentialManager: CredentialManager = CredentialManager.create(context)

    fun onSignIn(navController: NavHostController) {
        val googleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(context.getString(R.string.web_client_id))
            .build()

        val request = GetCredentialRequest.Builder()
            .addCredentialOption(googleIdOption)
            .build()

        scope.launch {
            try {
                val result = credentialManager.getCredential(
                    context = context,
                    request = request
                )
                val credential = result.credential
                val googleIdTokenCredential = GoogleIdTokenCredential
                    .createFrom(credential.data)
                val googleIdToken = googleIdTokenCredential.idToken

                val firebaseCredential =
                    GoogleAuthProvider.getCredential(googleIdToken, null)
                auth.signInWithCredential(firebaseCredential)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            // Sign in success, update UI with the signed-in user's information
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Login.route) { inclusive = true }
                            }
                        }
                    }
            }catch (e: Exception) {
                Toast.makeText(
                    context,
                    "Error: ${e.message}",
                    Toast.LENGTH_SHORT
                ).show()
                e.printStackTrace()
            }
        }
    }
}