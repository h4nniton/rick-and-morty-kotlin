package br.senai.sp.jandira

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import br.senai.sp.jandira.screens.CharactersList
import br.senai.sp.jandira.screens.GetCharacterDetails
import br.senai.sp.jandira.ui.theme.RickAndMorty2Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
                RickAndMorty2Theme {
                    CharactersList()

            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RickAndMorty2Theme {
        GetCharacterDetails()
    }
}