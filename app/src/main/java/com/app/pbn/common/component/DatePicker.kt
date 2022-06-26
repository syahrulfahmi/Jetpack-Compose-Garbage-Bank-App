package com.app.pbn.common.component

import android.app.DatePickerDialog
import android.content.Context
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.app.pbn.R
import com.app.pbn.common.ext.fieldModifier
import java.util.*

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ShowDatePicker(context: Context, onChangeValue: (String) -> Unit) {
    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context, { _: DatePicker, dateYear: Int, dateMonth: Int, dayOfMonth: Int ->
            date.value = "$dayOfMonth/$dateMonth/$dateYear"
            onChangeValue.invoke(date.value)
        }, year, month, day
    )
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .wrapContentSize(Alignment.TopStart),
    ) {
        Column {
            TextBold(
                text = stringResource(R.string.pickup_date),
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
                    datePickerDialog.show()
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
                        text = date.value.ifEmpty { "Masukan tanggal" },
                        color = if (date.value.isEmpty()) Color.Gray else Color.Black
                    )
                    Icon(
                        imageVector = Icons.Filled.CalendarMonth,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            }
        }
    }

}