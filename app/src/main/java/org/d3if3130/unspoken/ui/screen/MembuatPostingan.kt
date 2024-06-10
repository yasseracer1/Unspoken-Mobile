package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MembuatPostingan(navController: NavHostController, id: Long? = null) {
    val viewModel: MainViewModel = viewModel()
    val context = LocalContext.current
    val user = FirebaseAuth.getInstance().currentUser

    val username = user!!.displayName
    var postingan by remember { mutableStateOf("") }
    var suka by remember { mutableStateOf("0") }

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
                    if (id == null)
                        Text(
                            text = stringResource(id = R.string.buat_postingan),
                            color = Color.White,
                        )
                    else
                        Text(
                            text = stringResource(id = R.string.edit_postingan),
                            color = Color.White,
                        )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Orange,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(
                        onClick = {
                            if (postingan == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_LONG).show()
                            return@IconButton
                            }
                            viewModel.saveData(username!!,postingan,suka)
                            navController.popBackStack()
                            Log.d("POSTING", "$username $postingan $suka ditambahkan")
                        }
                    ) {
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
        FormCerita(
            postingan = postingan,
            onPostinganChange = { postingan = it },
            viewModel,
            modifier = Modifier.padding(padding)
        )
    }
}


@Composable
fun FormCerita(
    postingan: String, onPostinganChange: (String) -> Unit,
    viewModel: MainViewModel,
    modifier: Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp),
        horizontalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
            contentDescription = "Profile"
        )
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            value = postingan,
            onValueChange = { onPostinganChange(it) },
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


//@Composable
//fun DeleteAction(delete: () -> Unit) {
//    var expanded by remember { mutableStateOf(false) }
//
//    IconButton(onClick = { expanded = true }) {
//        Icon(
//            imageVector = Icons.Filled.MoreVert,
//            contentDescription = stringResource(id = R.string.lainnya),
//            tint = MaterialTheme.colorScheme.primary
//        )
//        DropdownMenu(
//            expanded = expanded,
//            onDismissRequest = { expanded = false }
//        ) {
//            DropdownMenuItem(
//                text = {
//                    Text(text = stringResource(id = R.string.hapus))
//                },
//                onClick = {
//                    expanded = false
//                    delete()
//                }
//            )
//        }
//    }
//}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun AddPostinganPreview() {
    UnspokenTheme {
        MembuatPostingan(viewModel())
    }
}