package org.d3if3130.unspoken.ui.screen

import android.content.res.Configuration
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.d3if3130.mobpro1.navigation.Screen
import org.d3if3130.unspoken.R
import org.d3if3130.unspoken.model.Postingan
import org.d3if3130.unspoken.ui.theme.Orange
import org.d3if3130.unspoken.ui.theme.UnspokenTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostinganScreen(navController: NavHostController, currentUser: FirebaseUser?) {

    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            route = Screen.Home.route,
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            hasNews = false,
            badgeCount = 2
        ),
        BottomNavigationItem(
            title = "Postingan saya",
            route = Screen.Postingan.route,
            selectedIcon = Icons.Filled.DateRange,
            unselectedIcon = Icons.Outlined.DateRange,
            hasNews = false
        ),
        BottomNavigationItem(
            title = "Profile",
            route = Screen.Profile.route,
            selectedIcon = Icons.Filled.AccountCircle,
            unselectedIcon = Icons.Outlined.AccountCircle,
            hasNews = true
        )
    )
    var selectedItemIndexed by rememberSaveable {
        mutableStateOf(1)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White,
                        fontFamily = FontFamily.Cursive
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Orange,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    IconButton(onClick = {
                        CoroutineScope(Dispatchers.IO).launch {

                        }
                    }) {
                        currentUser?.let { user ->
                            user.photoUrl?.let {
                                AsyncImage(
                                    modifier = Modifier
                                        .size(140.dp)
                                        .clip(RoundedCornerShape(4.dp)),
                                    model = ImageRequest.Builder(LocalContext.current)
                                        .data(it)
                                        .crossfade(true)
                                        .build(),
                                    contentDescription = "profile picture",
                                    contentScale = ContentScale.Crop
                                )
                                Spacer(modifier = Modifier.size(16.dp))
                            }
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.CreatePostingan.route)
                },
                containerColor = Orange
            ) {
                Icon(
                    imageVector = Icons.Filled.Create,
                    contentDescription = stringResource(id = R.string.buat_postingan),
                    tint = Color.White
                )
            }
        },
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = selectedItemIndexed == index,
                        onClick = {
                            selectedItemIndexed = index
                            navController.navigate(item.route)
                        },
                        label = {
                            Text(text = item.title)
                        },
                        icon = {
                            BadgedBox(
                                badge = {
                                    if (item.badgeCount != null) {
                                        Badge {
                                            Text(text = item.badgeCount.toString())
                                        }
                                    } else if (item.hasNews) {
                                        Badge ()
                                    }
                                }
                            ) {
                                Icon(
                                    imageVector = if (index == selectedItemIndexed) {
                                        item.selectedIcon
                                    } else item.unselectedIcon,
                                    contentDescription = item.title
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { padding ->
        PostinganScreenContent(Modifier.padding(padding), navController)
    }
}

@Composable
fun PostinganScreenContent(modifier: Modifier, navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val data by viewModel.data
    val username = FirebaseAuth.getInstance().currentUser?.displayName.toString()

    LaunchedEffect(username) {
        viewModel.retrievePrivateUserPostingan(username)
    }

    if (data.isEmpty()) {
        Column (
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = stringResource(id = R.string.postingan_kosong))
        }
    }
    else {
        LazyColumn(
            modifier = modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            items(data) {
                ListItemPostingan(postingan = it) {
                    navController.navigate(Screen.OpenPostingan.withId(it.id_postingan))
                    Log.d("IDPOSTINGAN", "${it.id_postingan}")
                }
            }
        }
    }
}

@Composable
fun ListItemPostingan(postingan: Postingan, onClick: () -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(start = 0.dp, top = 8.dp, end = 2.dp, bottom = 8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Row {
            Icon(
                modifier = Modifier
                    .padding(horizontal = 10.dp),
                painter = painterResource(id = R.drawable.baseline_account_circle_24_tampilan),
                contentDescription = "Profile"
            )
            Column {
                Row(
                    modifier = Modifier,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Text(
                        text = postingan.username,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text = " ")
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_circle_24),
                        contentDescription = "",
                        tint = Color.Gray
                    )
                    Text(text = " ")
                    Text(
                        text = postingan.tanggal,
                        color = Color.Gray
                    )
                }
                Text(
                    modifier = Modifier
                        .padding(bottom = 10.dp),
                    text = postingan.postingan,
                    maxLines = 8,
                    overflow = TextOverflow.Ellipsis
                )
                Row (
                    modifier = Modifier
                        .padding(1.dp),
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
                    Text(text = postingan.suka)
                }
            }
        }
    }
    Divider()
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun PostinganPreview() {
    UnspokenTheme {
        PostinganScreen(rememberNavController(), FirebaseAuth.getInstance().currentUser)
    }
}