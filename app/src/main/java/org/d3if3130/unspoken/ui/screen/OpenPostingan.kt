package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.model.Komentar
import org.d3if3130.unspoken.model.Postingan
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

const val KEY_ID_POSTINGAN = "idPostingan"


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenPostingan(navController: NavHostController, id: String) {

    val viewModel: MainViewModel = viewModel()
    val data by viewModel.data
    val komentar by viewModel.komentar

    LaunchedEffect(id) {
        viewModel.retrieveDetailPostingan(id)
        viewModel.retrieveKomentarPostingan(id)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(id = R.string.kembali),
                            tint = Color.White
                        )
                    }
                },
                title = {
                    Text(
                        text = stringResource(id = R.string.postingan),
                        color = Color.White,
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Orange,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
            )
        }
    ) { padding ->
        LazyColumn(
            Modifier.padding(padding)
        ) {
            items(data) {
                Postingan(postingan = it, { navController.navigate(Screen.BeriKomentar.withId(it.id_postingan)) })
                Log.d("IDKOMENTAR", it.id_postingan)
            }
            items(komentar) {
                Komentar(komentar = it)
            }
        }
    }
}

@Composable
fun Postingan(postingan: Postingan, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row {
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
                    contentDescription = "Profile"
                )
                Column {
                    Text(
                        text = postingan.username,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = postingan.postingan)
                }
            }
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = postingan.tanggal_detail,
                color = Color.Gray
            )
            Divider()
            Row(
                modifier = Modifier
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { onClick() },
                    text = "Beri komentar",
                    color = Color.Blue
                )
            }
            Divider()
        }
    }
}

@Composable
fun Komentar(komentar: Komentar) {
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Row(
            modifier = Modifier
                .padding(top = 10.dp, bottom = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
                contentDescription = "Profile"
            )
            Column {
                Row {
                    Text(
                        text = komentar.username,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = " - " + komentar.tanggal_detail,
                        color = Color.Gray,
                    )
                }
                Text(text = komentar.isi_komentar)
            }
        }
        Divider()
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OpenScreenPreview() {
    UnspokenTheme {
        OpenPostingan(rememberNavController(), "1")
    }
}