// "Create extension function 'Any.component2'" "true"
// WITH_STDLIB
class FooIterator<T> {
    operator fun hasNext(): Boolean { return false }
    operator fun next(): Any {
        TODO("not implemented")
    }
}
class Foo<T> {
    operator fun iterator(): FooIterator<String> {
        TODO("not implemented")
    }
}
operator fun Any.component1(): Int {
    return 0
}
fun foo() {
    for ((i: Int, j: Int) in Foo<caret><Int>()) { }
}
// FUS_QUICKFIX_NAME: org.jetbrains.kotlin.idea.quickfix.createFromUsage.createCallable.CreateExtensionCallableFromUsageFix