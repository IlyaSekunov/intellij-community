// "Replace with 'B<F<Int>>'" "true"
// WITH_STDLIB

@Deprecated(message = "renamed", replaceWith = ReplaceWith("B<N>"))
typealias A<E> = List<E>

abstract class B<T> : List<T>
class F<G>

fun test() {
    var x: <caret>A<F<Int>>
}
// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.replaceWith.DeprecatedSymbolUsageFix