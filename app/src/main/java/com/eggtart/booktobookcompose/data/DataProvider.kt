package com.eggtart.booktobookcompose.data

data class RandomUser(
    val name: String = "고윤서",
    val recentMsg: String = "안녕하세요 책 좀 빌릴 수 있을까요?",
    val ProfileImg: String = "https://randomuser.me/api/portraits/women/72.jpg",
    val timestamp: String = "오전 4:26"
)

object DataProvider {
    val userList = List(20) { RandomUser() }
}