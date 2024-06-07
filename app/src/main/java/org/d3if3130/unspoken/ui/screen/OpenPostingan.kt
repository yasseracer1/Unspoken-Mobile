package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.SettingsDataStore
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenPostingan(navController: NavHostController) {
    val dataStore = SettingsDataStore(LocalContext.current)
    val showList by dataStore.layoutFlow.collectAsState(true)

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
                        text = stringResource(id = R.string.posting),
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
        OpenScreenContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun OpenScreenContent(modifier: Modifier, navController: NavHostController) {
    Column (
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 0.dp, top = 8.dp, end = 2.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Row (
                modifier = Modifier,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ){
                Icon(
                    modifier = Modifier
                        .padding(horizontal = 10.dp),
                    painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
                    contentDescription = "Profile"
                )
                Text(
                    text = "Yasser AR",
                    fontSize = 20.sp
                )
            }
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "Dear Rovers,\n" +
                    "Since the official launch of Wuthering Waves, we have received a lot of valuable feedback and suggestions through social media and in-game surveys. We are genuinely grateful for your attention and support.\n" +
                    "We apologize for the deficiencies and issues present in Wuthering Waves, our first fully independently developed and globally published game at Kuro Games. We understand that this has affected your gaming experience, and we are working to improve it for those who love the game.\n" +
                    "We have been working on optimizations and iterations for the current 1.0 version, and the development of subsequent version updates is also underway. We will address some of the most discussed and concerned issues."
            )
            Text(
                modifier = Modifier
                    .padding(10.dp),
                text = "18:10 - 22 Mar 24",
                color = Color.Gray
            )
            Divider()
            Row (
                modifier = Modifier
                    .padding(15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Icon(
                    modifier = Modifier.padding(end = 20.dp),
                    painter = painterResource(id = R.drawable.baseline_mode_comment_24),
                    contentDescription = "comment"
                )
                Icon(
                    painter = painterResource(id = R.drawable.baseline_favorite_border_24),
                    contentDescription = "like"
                )
            }
            Divider()
            Row (
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
                        Text(text = "Ganendra Kalla R")
                        Text(
                            text = " - 23 Mar",
                            color = Color.Gray
                        )
                    }
                    Text(text = "also bring back scar's onigiri please\uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D\uD83D\uDE2D")
                }
            }
            Divider()
        }
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun OpenScreenPreview() {
    UnspokenTheme {
        OpenPostingan(rememberNavController())
    }
}