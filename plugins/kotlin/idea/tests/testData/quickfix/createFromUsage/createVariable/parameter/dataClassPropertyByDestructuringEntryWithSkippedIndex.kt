// "Create property 'address2' as constructor parameter" "false"
// ERROR: Destructuring declaration initializer of type Person must have a 'component3()' function
// ERROR: Destructuring declaration initializer of type Person must have a 'component4()' function
data class Person(val name: String, val age: Int)

fun person(): Person = TODO()

fun main(args: Array<String>) {
    val (name, age, address, address2) = <caret>person()
}