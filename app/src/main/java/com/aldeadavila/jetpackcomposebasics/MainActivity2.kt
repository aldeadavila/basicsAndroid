package com.aldeadavila.jetpackcomposebasics

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.aldeadavila.jetpackcomposebasics.model.User

import com.aldeadavila.jetpackcomposebasics.ui.theme.JetpackComposeBasicsTheme
import com.valentinilk.shimmer.shimmer
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity2 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetpackComposeBasicsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyApp()
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp(
          viewModel: UserViewModel = hiltViewModel(),
          modifier: Modifier = Modifier
) {
    val users by viewModel.users.observeAsState(arrayListOf())
    val isLoading by viewModel.isLoading.observeAsState(false)
   MyApp2(onAddClick = {viewModel.addUser()},
       users = users,
       isLoading = isLoading
   )

}
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyApp2(
    onAddClick: (()->Unit)? = null,
    users:List<User>,
    isLoading:Boolean
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Simple Rest + Room") },
                actions = {
                    IconButton(onClick = {

                            onAddClick?.invoke()

                    }) {
                        Icon(Icons.Filled.Add, "Add")
                    }
                }
            )
        }
    ) {
        LazyColumn {
            var itemCount = users.size
            if (isLoading) itemCount++

            items(count = itemCount) { index ->
                var auxIndex = index
                if (isLoading) {
                    if (auxIndex == 0)
                        return@items LoadingCard()
                    auxIndex--
                }


            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoadingCard() {
    Divider(modifier = Modifier.size(8.dp))
    Card(
        onClick = { /* Do something */ },
        shape = CardDefaults.shape,
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 64.dp)
            .fillMaxWidth()
            .testTag("loadingCard")
    ) {
        Spacer(modifier = Modifier.size(8.dp))
        Row(modifier = Modifier.padding(8.dp)) {
            ImageLoading()

            Column {

                Box(modifier = Modifier.shimmer()) {
                    Column {
                        Box(modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                        )

                        Box(modifier = Modifier
                            .height(15.dp)
                            .fillMaxWidth()
                            .background(Color.Gray)
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ImageLoading() {
    Box(modifier = Modifier.shimmer()) {
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(Color.Gray)
        )
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    JetpackComposeBasicsTheme {
        MyApp2(users = arrayListOf(), isLoading = true)
    }
}