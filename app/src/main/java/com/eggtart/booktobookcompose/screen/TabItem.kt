package com.eggtart.booktobookcompose.screen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable

typealias ComposableFun = @Composable () -> Unit

sealed class TabItem(var title: String, var screen: ComposableFun) {

    @ExperimentalFoundationApi
    object Recent : TabItem("최근", { RecentScreen() })

    @ExperimentalFoundationApi
    object Philosophy : TabItem("철학", { PhilosophyScreen() })

    @ExperimentalFoundationApi
    object Literature : TabItem("문학", { LiteratureScreen() })

    @ExperimentalFoundationApi
    object Fiction : TabItem("소설", { FictionScreen() })

    @ExperimentalFoundationApi
    object Computer : TabItem("컴퓨터", { ComputerScreen() })

    @ExperimentalFoundationApi
    object Toeic : TabItem("토익", { ToeicScreen() })

}
