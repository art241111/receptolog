package ru.art241111.dish_recipes.extensionFunctions

fun List<String>.toStringWithSpacesAndCommas(): String {
    var returnString = ""
    this.forEach{item -> returnString += "$item,"}
    return returnString
}

fun List<String>.toStringWithEnter(): String {
    var returnString = ""
    this.forEach{item -> returnString += "$item \n" }
    return returnString
}