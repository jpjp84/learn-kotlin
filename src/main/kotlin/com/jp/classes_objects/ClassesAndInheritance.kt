package com.jp.classes_objects


fun main() {
    classDefinedTest()
    secondaryConstructorTest()
    inheritanceClassTest()
}

private fun classDefinedTest() {
    var classTestWithVisibilityConstructor = ClassesAndInheritance.ClassTestWithVisibilityConstructor("ClassTestWithVisibilityConstructor");
    var classTest = ClassesAndInheritance.ClassTest("ClassTest")
}

private fun secondaryConstructorTest() {
    println("----")
    val secondClass = ClassesAndInheritance.Person("jpzz", 25)
}

private fun inheritanceClassTest() {
    println("----")
    ClassesAndInheritance.InheritanceClass.Derived(3)
    println()
    ClassesAndInheritance.InheritanceClass.Derived2(3)
    println()
    ClassesAndInheritance.InheritanceClass.Derived2(3, "test")
    println()
    ClassesAndInheritance.OverrideClass.Derived().getInnerDerived().showNameParent()
}

class ClassesAndInheritance {

    class Empty

    class ClassTestWithVisibilityConstructor constructor(name: String) {
    }

    class ClassTest(var name: String) {
        val firstVal = "firstVal".also { println(it) }

        init {
            println("first init $name, $firstVal")
            name = "ClassTest!!!"
        }

        val secondVal = "secVal".also(::println)

        init {
            println("second init $name, $firstVal, $secondVal")
        }
    }

    class Person(val name: String) {
        var age: Int = 0

        constructor(name: String, age: Int) : this(name) {
            this.age = age
            println("person sec constructor $name ${this.name} $age")
        }

        init {
            println("init")
        }
    }

    class InheritanceClass {
        open class Base(p: Int) {
            init {
                println("Base init $p")
            }

            constructor(p: Int, q: String) : this(p) {
                println("Base Secondary Constructor $q")
            }
        }

        class Derived(p: Int) : Base(p)

        class Derived2 : Base {

            constructor(p: Int) : super(p) {
                println("Derived2 Secondary Constructor $p")
            }

            constructor(p: Int, q: String) : super(p, q) {
                println("Derived2 Secondary Constructor $p and $q")
            }
        }

    }

    class OverrideClass {
        open class Base {
            open fun getName(): String {
                return "ovverrided"
            }
        }

        class Derived : Base() {
            override fun getName(): String {
                return "Override"
            }

            inner class InnerDerived {
                fun showNameParent() {
                    println(super@Derived.getName())
                    println(getName())
                }
            }

            fun getInnerDerived(): InnerDerived {
                return InnerDerived()
            }
        }
    }
}
