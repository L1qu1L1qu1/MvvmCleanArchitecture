package com.example.mvvmcleanarchitecture.presentation

import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import dagger.hilt.android.AndroidEntryPoint
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.*
import com.example.mvvmcleanarchitecture.domain.Cat
import com.google.gson.Gson
import java.net.URLEncoder

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var rememberNavController = rememberNavController()
            NavHost(navController = rememberNavController, startDestination = "one"){
                composable("one"){ getList(navController = rememberNavController)}
                composable("two/{name}"){
                    var name = it.arguments?.getString("name")
                    Cat(name = name?:"", navController = rememberNavController)}

                composable("three/{cat}", arguments = listOf(
                    navArgument("cat"){
                        type = AccetParamType()
                    }
                )){
                    var name = it.arguments?.getString("name")
                    var cat = it.arguments?.getParcelable("cat", Cat::class.java)
                    Cat(cat = cat?:null, name = name?:"", navController = rememberNavController)}

            }

        }
    }
}
@Composable
fun getList(catViewModel: CatViewModel = hiltViewModel(), navController: NavController){
    catViewModel.getCats()
    LazyColumn(modifier = Modifier.padding(10.dp)){
        items(catViewModel.list){
            Text(it.name, fontSize = 20.sp, modifier = Modifier.clickable { navController.navigate("two/"+it.name) })
            var json = Uri.encode(Gson().toJson(it))
            Text(it.name, fontSize = 20.sp, modifier = Modifier.clickable { navController.navigate("three/"+json) })
        }
    }
}
@Composable
fun Cat(cat: Cat? = null, name: String="", navController: NavController){
    Column() {
        Text(text = name, modifier = Modifier.clickable { navController.popBackStack() })
        Text(text = cat.toString(), modifier = Modifier.clickable { navController.popBackStack() })
    }

}

class AccetParamType():NavType<Cat>(isNullableAllowed = false){
    override fun get(bundle: Bundle, key: String): Cat? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): Cat {
        return Gson().fromJson(value, Cat::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: Cat) {
        bundle.putParcelable(key, value)
    }

}


