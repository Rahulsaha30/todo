package eu.tutorials.wishlist

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import eu.tutorials.wishlist.data.Wish


@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun homeview(
    navController: NavController,
    viewModel: WishViewModel
){
    val context= LocalContext.current
    Scaffold(topBar = {Appbar(title = "Wishlist",{Toast.makeText(context,"Button Clicked", Toast.LENGTH_LONG).show()})}
    , floatingActionButton = { FloatingActionButton(
            modifier = Modifier.padding(all = 20.dp),
            contentColor = Color.White,
            onClick = {
                navController.navigate(Screen.Addscreen.route + "/0L")
                 }) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }
    }
    ) {
        val wishlist=viewModel.getAllWishes.collectAsState(initial = listOf())
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it)){
                items(wishlist.value, key = { wish ->wish.id }){
                    wish ->
                    val dismissState= rememberDismissState(
                        confirmValueChange = {
                            if (it==DismissValue.DismissedToEnd||it==DismissValue.DismissedToStart) {
                            viewModel.deleteWish(wish)
                            }
                            true
                        }
                    )
                   SwipeToDismiss(state = dismissState,
                       background = {},
                       dismissContent = {

                           wishitem(wish = wish) {
                               val id=wish.id
                               navController.navigate(Screen.Addscreen.route + "/$id")
                           }

                       })
                }
        }

    }
}


@Composable
fun wishitem(wish: Wish, onClick: () -> Unit){
    Card(modifier = Modifier
        .fillMaxSize()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable {
            onClick()
        }
        ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.description)
        }
    }

}
