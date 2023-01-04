package com.deepu.myandroidapp.feature_destinations.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.deepu.myandroidapp.R
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries
import com.deepu.myandroidapp.ui.theme.DarkByzantineBlue

@ExperimentalCoilApi
@Composable
fun CountriesListItem(
    countries: Countries,
    onItemClick: (Countries) -> Unit
) {

    Card(
        backgroundColor = DarkByzantineBlue,
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .wrapContentSize()
            .shadow(20.dp)
            .background(color = DarkByzantineBlue, shape = RoundedCornerShape(8.dp))
            .clickable {
                onItemClick(countries)
            }
    ) {

        Column(modifier = Modifier.fillMaxSize()) {

            Image(
                painter = rememberImagePainter(countries.countryImage),
                contentDescription = "countryImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
//                    .clip(RoundedCornerShape(20.dp))

            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = countries.countryName,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1,
                    modifier = Modifier
                        .padding(top = 0.dp, start = 5.dp)
                        .wrapContentHeight(),
                    style = MaterialTheme.typography.h1,

                )

                Icon(
                    painter = painterResource(id = R.drawable.ic_baseline_arrow_forward_24),
                    contentDescription = "forward_arrow",
                    tint = Color.White,
                    modifier = Modifier.wrapContentSize()
                )

            }

            Text(
                text = countries.description,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .background(DarkByzantineBlue, RoundedCornerShape(8.dp))
                    .padding(top = 5.dp, start = 5.dp, bottom = 5.dp)
                    .fillMaxWidth()
                    .wrapContentHeight(),
                style = MaterialTheme.typography.h2
            )
        }

    }
}