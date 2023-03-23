package com.example.custom

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.custom.TopBar.CTopBar
import com.example.custom.ui.theme.CustomTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CustomTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CustomTheme {
        Greeting("Android")
    }
}
@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "UnrememberedMutableState")
@Composable
@Preview
fun addList(){
    val currentScreen = mutableStateOf<Screen>(Screen.Carte)
    val openDialog = remember { mutableStateOf(false)  }
    var titre by remember {
        mutableStateOf("")
    }
    var message by remember {
        mutableStateOf("")
    }
    var selectedItem by remember { mutableStateOf("Type ") }
    val items = listOf("Embouteillage", "Inondation", "Autres")

    var selectedItem2 by remember { mutableStateOf("Niveau ") }
    val items2 = listOf("Niveau 1", "Niveau 2", "Niveau 3","Niveau 4")
    var expanded by remember { mutableStateOf(false) }
    var expanded2 by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CTopBar(title = "Créer un Sango ")
    },
        bottomBar = {
            CustomBottomNavigationMain(currendScrenId = currentScreen.value.id) {
                currentScreen.value = it
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            /*
            *
            * */
            OutlinedTextField(value = titre, onValueChange ={
                titre = it
            },
            placeholder = { Text("Titre")},
                maxLines = 2,
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
                )
            Row(modifier = Modifier.padding(13.dp )) {
                /*

                 */

                Box(modifier = Modifier.wrapContentSize()) {

                    Box(
                        modifier = Modifier
                            .background(
                                Color.LightGray, RoundedCornerShape(6.dp)
                            )
                            .border(0.dp, Color.Transparent)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                Icons.Filled.Map,
                                contentDescription = "Favorite",
                                modifier = Modifier.size(ButtonDefaults.IconSize)
                            )
                            Text(
                                text = "$selectedItem",
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .clickable(onClick = { openDialog.value = true })
                            )
                            Icon(Icons.Filled.ArrowRight, contentDescription = "Icône de favori")

                        }
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        items.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedItem = item
                                    expanded = false
                                },
                                content = { Text(item) }
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.width(32.dp))
                Box(modifier = Modifier.wrapContentSize()) {

                    Box(
                        modifier = Modifier
                            .background(Color.LightGray, RoundedCornerShape(6.dp))
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "$selectedItem2",
                                modifier = Modifier
                                    .padding(start = 4.dp)
                                    .clickable(onClick = { expanded2 = true })
                            )
                            Icon(Icons.Filled.ArrowDropDown, contentDescription = "Icône de favori")

                        }
                    }
                    DropdownMenu(
                        expanded = expanded2,
                        onDismissRequest = { expanded2 = false }
                    ) {
                        items2.forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedItem2 = item
                                    expanded2 = false
                                },
                                content = { Text(item) }
                            )
                        }
                    }
                }

            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Dialog Title")
                    },
                    text = {
                            LazyColumn() {
                                items(items = items, itemContent = { item ->
                                    Text(
                                        text = "$item",
                                        modifier = Modifier
                                            .padding(start = 4.dp)
                                            .clickable(onClick = {
                                                selectedItem = item
                                                openDialog.value = false

                                            })
                                    )
                                })
                            }


                    },
                    confirmButton = {},
                    dismissButton = {}
                )
            }
            BorderlessTextArea(
                value = message,
                onValueChange ={
                    message = it
                }
            )
        }
    }
}


@Composable
fun MyModal(open:Boolean = false){
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
            open
        },
        title = {
            Text(text = "Dialog Title")
        },
        text = {
            Text("Here is a text ")
        },
        confirmButton = {},
        dismissButton = {}
    )
}

@Composable
fun BorderlessTextArea(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(5f)
            .border(0.dp, Color.Transparent)
            .background(Color.White),
        singleLine = false, placeholder = { Text(text = "Message")}
    )
}
@Composable
fun AlertDialogSample() {
    MaterialTheme {
        Column {
            val openDialog = remember { mutableStateOf(false)  }

            Button(onClick = {
                openDialog.value = true
            }) {
                Text("Click me")
            }

            if (openDialog.value) {

                AlertDialog(
                    onDismissRequest = {
                        // Dismiss the dialog when the user clicks outside the dialog or on the back
                        // button. If you want to disable that functionality, simply use an empty
                        // onCloseRequest.
                        openDialog.value = false
                    },
                    title = {
                        Text(text = "Dialog Title")
                    },
                    text = {
                        Text("Here is a text ")
                    },
                    confirmButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("This is the Confirm Button")
                        }
                    },
                    dismissButton = {
                        Button(

                            onClick = {
                                openDialog.value = false
                            }) {
                            Text("This is the dismiss Button")
                        }
                    }
                )
            }
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ComposeEditModal() {
    val message = remember { mutableStateOf("Edit Me") }

    val openDialog = remember { mutableStateOf(false) }
    val editMessage = remember { mutableStateOf("") }

    Box {

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = message.value,
                    fontSize = 24.sp
                )

                Spacer(modifier = Modifier.height(2.dp))

                Button(
                    onClick = {
                        editMessage.value = message.value
                        openDialog.value = true
                    }
                ) {
                    Text("Open Dialog")
                }
            }


        if (openDialog.value) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        color = contentColorFor(MaterialTheme.colors.background)
                            .copy(alpha = 0.6f)
                    )
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null,
                        onClick = {
                            openDialog.value = false
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                CustomDialog(message, openDialog, editMessage)
            }
        }
    }
}

@Composable
fun CustomDialog(
    message: MutableState<String>,
    openDialog: MutableState<Boolean>,
    editMessage: MutableState<String>
) {
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .padding(8.dp),
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
        ) {
            Text(text = "Input Message")

            Spacer(modifier = Modifier.height(8.dp))

            TextField(
                value = editMessage.value,
                onValueChange = { editMessage.value = it },
                singleLine = true
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.align(Alignment.End)
        ) {
            Button(
                onClick = {
                    openDialog.value = false
                }
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    message.value = editMessage.value
                    openDialog.value = false
                }
            ) {
                Text("OK")
            }
        }
    }
}


@SuppressLint("UnrememberedMutableState", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun CustomAlign() {
    val currentScreen = mutableStateOf<Screen>(Screen.Carte)
    Scaffold(bottomBar = {
        CustomBottomNavigationMain(currendScrenId = currentScreen.value.id) {
            currentScreen.value = it
        }
    }
    ) {

        when(currentScreen.value.id){
            "Accueil"-> Text(text = "Accueil")
            "Groupes"->Text(text = "Groupes")
            "Notification"->Text(text = "Notification")
            "Abonnement"->Text(text = "Abonnement")
            "Carte"->Text(text = "Carte")
        }
    }
}

@Composable
fun CustomBottomNavigationMain(currendScrenId: String, onItemSelected: (Screen) -> Unit) {
    val items = Screen.Items.list
    Row(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        items.forEach { item ->
            CustomBottomNavigationBody(item = item, isSelected = item.id == currendScrenId) {
                onItemSelected(item)
            }
        }
    }
}

@Composable
fun CustomBottomNavigationBody(item: Screen, isSelected: Boolean, onClick: () -> Unit) {
    val background =
        if (isSelected) MaterialTheme.colors.primary.copy(alpha = 0.1f) else Color.Transparent
    val contentColor =
        if (isSelected) MaterialTheme.colors.primary else MaterialTheme.colors.onBackground

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(background)
            .clickable(onClick = onClick)
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(imageVector = item.icon, contentDescription = null, tint = contentColor)
            AnimatedVisibility(visible = isSelected) {
                Text(text = item.title, color = contentColor)
            }
        }

    }
}

@Composable
@Preview(showBackground = true)
fun CustomBottomNavigationPreview() {
    CustomAlign()
}