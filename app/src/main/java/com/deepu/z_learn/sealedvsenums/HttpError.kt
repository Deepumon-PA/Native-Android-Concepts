package com.deepu.z_learn.sealedvsenums

//Sealed vs enum classes

//Http errors- 404: Nor found, 401: UnAuthorized, 403: Forbidden

//sealed class
//with sealed classes we can have more information as compared to enum which will only accept constants
//when you want to have more control over a set of usedful daa
sealed class HttpError(val errorCode: Int) {  // all subclasses of sealed classes are only known at compile time
    object UnAuthorized : HttpError(401)
    object NotFound : HttpError(404)
    object Forbidden : HttpError(403)

    //can have functions as well
    fun doSomething(){

    }
}


//enum
//enum classes are used mainly when dealing with constants
enum class HttpErrorEnum(val errorCode: Int) {
    UnAuthorized(401),
    NotFound(404),
    Forbidden(403);

    //can have functions as well
    fun doSomething(){

    }
}

//sealed interfaces
// same as sealed class but can't pass constructor(as it is an interface), ie, for a simpler version of sealed class
sealed interface HttpErrorInterface{
    object UnAuthorized: HttpErrorInterface
    object NotFound: HttpErrorInterface
    object Forbidden: HttpErrorInterface
}