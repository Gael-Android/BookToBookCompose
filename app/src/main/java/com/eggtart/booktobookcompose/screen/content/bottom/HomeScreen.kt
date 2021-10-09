package com.eggtart.booktobookcompose.screen.content.bottom

import android.annotation.SuppressLint
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.debugInspectorInfo
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eggtart.booktobookcompose.screen.content.tabs.TabItem
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState
import kotlinx.coroutines.launch


@ExperimentalFoundationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun HomeScreen() {
    Scaffold {

        val textState = remember { mutableStateOf(TextFieldValue("")) }
        SearchView(textState)
        val tabs = listOf(
            TabItem.Recent,
            TabItem.Philosophy,
            TabItem.Literature,
            TabItem.Fiction,
            TabItem.Computer,
            TabItem.Toeic
        )

        val pagerState = rememberPagerState(pageCount = tabs.size)
        TextTabs(tabs, pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }

}


@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TabsContent(tabs: List<TabItem>, pagerState: PagerState) {
    HorizontalPager(state = pagerState, modifier = Modifier.offset(0.dp, 240.dp)) { page ->
        tabs[page].screen()
    }
}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun TextTabs(tabs: List<TabItem>, pagerState: PagerState) {
    val scope = rememberCoroutineScope()

    ScrollableTabRow(selectedTabIndex =
    pagerState.currentPage, modifier = Modifier.offset(0.dp, 156.dp),
        backgroundColor = Color.White,
        edgePadding = 10.dp,
        divider = {
            TabRowDefaults.Divider(
                thickness = 0.dp
            )
        }, indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                modifier = Modifier.customTabIndicatorOffset(tabPositions[pagerState.currentPage]),
                height = 3.dp,
                color = Color.Red
            )
        }) {

        val interactionSource = remember { MutableInteractionSource() }

        tabs.forEachIndexed { index, tab ->
            Tab(selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }


                }, text = {
                    Text(text = tab.title, color = Color.Black)
                }, modifier = Modifier
                    .padding(0.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null,
                        enabled = true

                    ) {})


        }
    }

}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.customTabIndicatorOffset(currentTabPosition: TabPosition): Modifier = composed(
    inspectorInfo = debugInspectorInfo {
        name = "tabIndicatorOffset"
        value = currentTabPosition
    }
) {
    val indicatorWidth = 32.dp
    val currentTabWidth = currentTabPosition.width
    val indicatorOffset by animateDpAsState(
        targetValue = currentTabPosition.left + currentTabWidth / 2 - indicatorWidth / 2,
        animationSpec = tween(durationMillis = 100, easing = LinearEasing)
    )
    fillMaxWidth()
        .wrapContentSize(Alignment.BottomStart)
        .offset(x = indicatorOffset)
        .width(indicatorWidth)
}

@Composable
fun SearchView(state: MutableState<TextFieldValue>) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Card(
            elevation = 20.dp,
            modifier = Modifier
                .size(295.dp, 50.dp)
                .offset(0.dp, 70.dp),
            shape = RoundedCornerShape(5.dp)
        ) {

            TextField(
                value = state.value, onValueChange = { value ->
                    state.value = value
                },
                modifier = Modifier.fillMaxSize(),

                textStyle = TextStyle(color = Color.Gray, fontSize = 13.sp),

                leadingIcon = {
                    Icon(
                        Icons.Default.Search, contentDescription = "",
                        modifier = Modifier
                            .size(20.dp)
                    )
                },
                trailingIcon = {
                    if (state.value != TextFieldValue("")) {
                        IconButton(onClick = {
                            state.value = TextFieldValue("")
                        })
                        {
                            Icon(
                                Icons.Default.Close,
                                contentDescription = "",
                                modifier = Modifier
                                    .size(20.dp)

                            )

                        }
                    }
                },
                singleLine = true,
                shape = RectangleShape,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.White,
                    cursorColor = Color.White,
                    leadingIconColor = Color.LightGray,
                    trailingIconColor = Color.LightGray,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
        }
    }
}