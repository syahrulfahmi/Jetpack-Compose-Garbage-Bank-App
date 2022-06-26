package com.app.pbn.common.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.ext.fieldModifier
import com.app.pbn.model.TrashTypeModel


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DropdownDemo(items: State<ArrayList<TrashTypeModel>>, doOnItemClick: (TrashTypeModel) -> Unit) {
    if (items.value.isNotEmpty()) {
        var expanded by remember { mutableStateOf(false) }
        var selectedIndex by remember { mutableStateOf(0) }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentSize(Alignment.TopStart),
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                TextBold(
                    text = "Kategori Sampah",
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp),
                    fontSize = R.dimen.font_14
                )
                Card(
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.padding(top = 8.dp),
                    border = BorderStroke(1.dp, MaterialTheme.colors.onSurface),
                    backgroundColor = Color.White,
                    onClick = {
                        expanded = true
                    },
                ) {
                    Row(
                        modifier = Modifier
                            .fieldModifier()
                            .padding(vertical = 16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = items.value[selectedIndex].name,
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Back",
                            tint = Color.Black
                        )
                    }
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    items.value.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                doOnItemClick.invoke(item)
                                selectedIndex = index
                                expanded = false
                            }) {
                            Text(text = item.name)
                        }
                    }
                }
            }
        }
    }
}