package abhishek.pathak.firebasedemob44.realtime_database

//Assumption is userType 1 meanns sender and userType 2 means receiver
data class Chat(val userType: Int = 1, val message: String = "")
