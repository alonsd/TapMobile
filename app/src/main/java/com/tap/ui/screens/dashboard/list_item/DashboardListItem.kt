package com.tap.ui.screens.dashboard.list_item

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.tap.R
import com.tap.model.YouTubeListItemModel

@Composable
fun DashboardListItem(model: YouTubeListItemModel) {
    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(id = R.drawable.play_icon),
            contentDescription = stringResource(R.string.dashboard_list_item_play_icon_content_description)
        )
        Text(text = model.title,
            modifier = Modifier.padding(horizontal = 16.dp),
            fontSize = 12.sp,
            maxLines = 1)
        AsyncImage(model = model.imageUrl, contentDescription = stringResource(id = R.string.dashboard_list_item_video_icon_content_description))
    }
}

@Preview(showBackground = true)
@Composable
fun DashboardListItemPreview() {
    DashboardListItem(
        YouTubeListItemModel(
            "Apple iPhone 14 Pro",
            "https://i.ytimg.com/vi/7m5UcXSsMGo/default.jpg",
            "1"
        )
    )
}