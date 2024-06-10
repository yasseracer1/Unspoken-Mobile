package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.SettingsDataStore
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KomentarScreen(navController: NavHostController, currentUser: FirebaseUser?, id: String) {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser

    var id_postingan by remember { mutableStateOf("") }
    val username = user!!.displayName
    var isi_komentar by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.Clear,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.komentar),
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Orange,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        if (isi_komentar == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                        }
                        viewModel.saveKomentar(id, username!!,isi_komentar)
                        navController.popBackStack()
                        Log.d("KOMENTAR", "$id $username $isi_komentar ditambahkan")

                    }) {
                        Icon(
                            imageVector = Icons.Filled.Check,
                            contentDescription = stringResource(id = R.string.simpan),
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) { padding ->
        KomentarScreenContent(Modifier.padding(padding), navController, isi_komentar, { isi_komentar = it })
    }
}

@Composable
fun KomentarScreenContent(modifier: Modifier, navController: NavHostController,
                          isi_komentar: String, onKomentarChange: (String) -> Unit) {

    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {
        Text(
            modifier = Modifier
                .padding(start = 50.dp),
            text = "Membalas Yasser AR",
            color = Color.Gray
        )
        Column (
        ) {
            Column {
                Row (
                    modifier = Modifier
                        .padding(top = 4.dp),
                    horizontalArrangement = Arrangement.Center
                ){
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
                        contentDescription = "Profile"
                    )
                    Column (
                        modifier = Modifier
                            .padding(start = 4.dp, top = 0.dp, end = 4.dp, bottom = 4.dp),
                    ) {
                        TextField(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillMaxHeight(),
                            value = isi_komentar,
                            onValueChange = { onKomentarChange(it) },
                            label = { Text(text = stringResource(id = R.string.isi)) },
                            keyboardOptions = KeyboardOptions(
                                capitalization = KeyboardCapitalization.Sentences,
                            ),
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent,
                                disabledIndicatorColor = Color.Transparent
                            ),
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun KomentarScreenPreview() {
    UnspokenTheme {
        KomentarScreen(rememberNavController(), currentUser = FirebaseAuth.getInstance().currentUser, "1")
    }
}