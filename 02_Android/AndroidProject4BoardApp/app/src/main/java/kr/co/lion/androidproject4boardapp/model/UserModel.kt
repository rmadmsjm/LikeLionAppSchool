package kr.co.lion.androidproject4boardapp.model

data class UserModel(
    var userIdx: Int, var userId: String, var userPw: String, var userNickname: String, var usrAge: String,
    var userGender: Int, var userHobby1: Boolean, var userHobby2: Boolean, var userHobby3: Boolean,
    var userHobby4: Boolean, var userHobby5: Boolean, var userHobby6: Boolean, var userState: Int) {
}