package com.example.potater

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun App() {
    // This is what we send to the IP!
    val message: ByteArray = "🥔".encodeToByteArray()
    MaterialTheme {
        // IP input
        var IPAddress by remember { mutableStateOf("") }

        // Network attack type
        val networkTypeOptions = listOf("UDP", "TCP", "ICMP")
        var networkTypeExpanded by remember { mutableStateOf(false) }
        var networkTypeSelectedOption by remember { mutableStateOf<String>(networkTypeOptions.first()) }
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(Color(0xFFFF9800))
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.Start,
        ) {
            ExposedDropdownMenuBox(
                expanded = networkTypeExpanded,
                onExpandedChange = { networkTypeExpanded = !networkTypeExpanded }
            ) {
                TextField(
                    // menuAnchor links the text field to the dropdown layout
                    modifier = Modifier.menuAnchor(),
                    value = networkTypeSelectedOption,
                    onValueChange = {}, // Leave empty because readOnly handles it
                    readOnly = true,    // Prevents typing and hides the keyboard
                    label = { Text("Network Type") },
                )

                // This generates the actual visual dropdown overlay
                ExposedDropdownMenu(
                    expanded = networkTypeExpanded,
                    onDismissRequest = { networkTypeExpanded = false }
                ) {
                    networkTypeOptions.forEach { option ->
                        DropdownMenuItem(
                            text = { Text(text = option) },
                            onClick = {
                                networkTypeSelectedOption = option // Set the chosen value
                                networkTypeExpanded = false        // Close the menu
                            }
                        )
                    }
                }
            }

            TextField(
                value = IPAddress,
                onValueChange = {
                    IPAddress = it
                },
                label = { Text("IP address") },
                placeholder = { Text("IP address") },
            )

            Button(onClick = {
                println("this needs to me implemented!")
            }) {
                Text("Start test")
            }

        }
    }
}
