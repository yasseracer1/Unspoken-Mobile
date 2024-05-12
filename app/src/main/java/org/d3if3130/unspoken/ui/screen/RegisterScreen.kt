package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme


@Composable
fun RegisterScreen(navController: NavHostController){

    var nama by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Unspoken",
            fontSize = 50.sp,
            fontWeight = FontWeight.Bold,
            color = Orange,
            modifier = Modifier.height(110.dp)
        )

        Text(
            text = "Silahkan Registrasi",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(4.dp)
        )

        Text(
            text = "Daftarkan diri anda"
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        OutlinedTextField(
            value = email,
            onValueChange = {
                email = it
            },
            label = { Text(text = "Nama Pengguna")}
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = nama,
            onValueChange = {
                nama = it
            },
            label = { Text(text = "Email")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = { Text(text = "Konfirmasi Password")
            },
            visualTransformation = PasswordVisualTransformation()
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        Button(
            onClick = {
                Log.i("Credential", "Nama Pengguna : $nama Email : $email Password : $password")
            }) {
            Text(
                text = "Registrasi"
            )

        }

        Spacer(
            modifier = Modifier.height(32.dp)
        )

        Text(text = "Sudah punya akun?", modifier = Modifier.clickable { }
        )
        TextButton(
            onClick = {
                navController.navigate(Screen.Login.route)
            }
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 18.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun RegisterScreenPreview() {
    UnspokenTheme {
        RegisterScreen(rememberNavController())
    }
}
