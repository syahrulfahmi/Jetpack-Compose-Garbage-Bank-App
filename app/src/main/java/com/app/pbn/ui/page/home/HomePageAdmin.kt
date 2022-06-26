package com.app.pbn.ui.page.home

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.app.pbn.R
import com.app.pbn.common.component.TextBold
import com.app.pbn.common.ext.fullTextWidth
import com.app.pbn.model.UserModel

@Composable
fun HomePageAdmin(userData: UserModel, doOnGarbageTypeClick: () -> Unit, doOnHistoryClick: () -> Unit, doOnLogOut: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.white))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    dimensionResource(id = R.dimen.padding_16)
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = stringResource(R.string.welcome_admin),
                    fontSize = dimensionResource(id = R.dimen.font_18).value.sp
                )
                Text(
                    text = userData.name,
                    fontSize = dimensionResource(id = R.dimen.font_20).value.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Image(
                modifier = Modifier
                    .size(48.dp)
                    .clickable {
                        doOnLogOut.invoke()
                    },
                painter = painterResource(
                    id = R.drawable.ic_user
                ),
                contentDescription = null
            )
        }
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp),
            shape = RoundedCornerShape(10.dp),
            elevation = dimensionResource(id = R.dimen.padding_4)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(dimensionResource(id = R.dimen.padding_16)),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontSize = dimensionResource(id = R.dimen.font_20).value.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        modifier = Modifier
                            .padding(
                                end = dimensionResource(id = R.dimen.padding_16),
                                top = dimensionResource(id = R.dimen.padding_8)
                            ),
                        text = stringResource(R.string.trash_description)
                    )
                }
                Image(
                    modifier = Modifier.size(72.dp),
                    painter = painterResource(
                        id = R.drawable.ic_trash
                    ),
                    contentDescription = null
                )
            }
        }
        TextBold(text = stringResource(R.string.menu_category), modifier = Modifier.fullTextWidth(), fontSize = R.dimen.font_18)
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Spacer(modifier = Modifier.width(4.dp))
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_8))
                    .height(180.dp)
                    .clickable {
                        doOnGarbageTypeClick.invoke()
                    },
                shape = RoundedCornerShape(10.dp),
                elevation = dimensionResource(id = R.dimen.padding_4)
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(dimensionResource(id = R.dimen.padding_8)),
                        painter = painterResource(
                            id = R.drawable.ic_recycling
                        ),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.trash_type)
                    )

                }
            }
            Card(
                modifier = Modifier
                    .weight(1f)
                    .padding(dimensionResource(id = R.dimen.padding_8))
                    .height(180.dp)
                    .clickable { doOnHistoryClick.invoke() },
                shape = RoundedCornerShape(10.dp),
                elevation = dimensionResource(id = R.dimen.padding_4)
            ) {
                Column(
                    modifier = Modifier.padding(15.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        modifier = Modifier
                            .size(64.dp)
                            .padding(dimensionResource(id = R.dimen.padding_8)),
                        painter = painterResource(
                            id = R.drawable.ic_garbage_bag
                        ),
                        contentDescription = null
                    )
                    Text(
                        text = stringResource(id = R.string.trash_order_history)
                    )
                }
            }
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}