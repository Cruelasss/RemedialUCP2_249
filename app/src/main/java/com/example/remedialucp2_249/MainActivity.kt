package com.example.remedialucp2_249

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.remedialucp2_249.ui.theme.RemedialUCP2_249Theme
import com.example.remedialucp2_249.view.route.PengelolaHalaman


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RemedialUCP2_249Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    // We pass the padding to our navigation controller/page manager
                    PengelolaHalaman(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}