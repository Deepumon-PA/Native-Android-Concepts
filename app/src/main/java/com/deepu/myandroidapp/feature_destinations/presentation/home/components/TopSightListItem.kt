package com.deepu.myandroidapp.feature_destinations.presentation.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.deepu.myandroidapp.feature_destinations.domain.model.Countries

@ExperimentalCoilApi
@Composable
fun TopSightListItem(
    topSight: Countries,
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.wrapContentHeight().width(150.dp).padding(20.dp)
    ) {
        Image(
            painter = rememberImagePainter(topSight.countryImage),
            contentDescription = "top_sight_image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(100.dp)
                .height(100.dp)
                .clip(RoundedCornerShape(5.dp))
                .clickable {  }
        )

        Text(
            text = topSight.countryName,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.body1,
            maxLines = 1
        )

    }
}