package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3130.unspoken.google.GoogleSignIn
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

@Composable
fun LoginScreen(navController: NavHostController, googleSignIn: GoogleSignIn){

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Unspoken",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Orange
        )

        Spacer(
            modifier = Modifier.height(50.dp)
        )

        Text(
            text = "Selamat Datang",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Login ke Akun Anda"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(onClick = { googleSignIn.onSignIn(navController) }) {
            Text(text = "Login with Google")
        }

//        Button(
//            onClick = {
//                navController.navigate(Screen.Home.route)
//            }
//        ) {
//            Text(text = "Login")
//        }


//        Spacer(
//            modifier = Modifier.height(32.dp)
//        )
//
//        Text(text = "Belum punya akun?", modifier = Modifier.clickable { }
//        )
//        TextButton(
//            onClick = {
//                navController.navigate(Screen.Register.route)
//            }
//        ) {
//            Text(
//                text = stringResource(id = R.string.register),
//                fontSize = 18.sp
//            )
//        }
    }
}


@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun LoginScreenPreview() {
    UnspokenTheme {
        LoginScreen(rememberNavController(), googleSignIn = GoogleSignIn(LocalContext.current, rememberCoroutineScope()))}
}