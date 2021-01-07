package com.halilibo.navigationhelloworld

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.halilibo.navigationhelloworld.ui.NavigationHelloWorldTheme

class MainActivity : AppCompatActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NavigationHelloWorldTheme {
        // A surface container using the 'background' color from the theme
        Surface(color = MaterialTheme.colors.background) {
          Column {
            App1()
            App2()
          }
        }
      }
    }
  }
}

@Composable fun App1() {
  var homeState by remember { mutableStateOf(1 to "username") }

  Column {
    Button(onClick = {
      homeState = homeState.copy(second = homeState.second.reversed())
    }) {
      Text("Reverse")
    }
    HomePage(homeState = homeState)
  }
}

@Composable fun App2() {
  var homeState by remember { mutableStateOf(1 to "username") }

  Column {
    Button(onClick = {
      homeState = homeState.copy(second = homeState.second.reversed())
    }) {
      Text("Reverse")
    }
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "profile") {
      composable("profile") {
        ProfilePage(homeState.second)
      }
      composable("counter") {
        Text("${homeState.first}")
      }
    }
  }
}

@Composable fun HomePage(
  homeState: Pair<Int, String>
) {
  val navController = rememberNavController()
  NavHost(navController = navController, startDestination = "profile") {
    composable("profile") {
      ProfilePage(homeState.second)
    }
    composable("counter") {
      Text("${homeState.first}")
    }
  }
}

@Composable fun ProfilePage(
  username: String
) {
  Text(text = username)
}