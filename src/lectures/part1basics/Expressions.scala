package lectures.part1basics

object Expressions extends App {

  val x = 1 + 2 // expression
  println(x)

  println(2 + 3 * 4)
  // + - / * & | ^ << >> >>> (right shift with zero extension)

  println(1 == x)
  // -- != > >= <=

  println(!(1 == x))
  // ! && ||

  var aVariable = 2
  aVariable += 3 // also works with -= *= /= all side effects
  println(aVariable)

  // Instructions (DO) vs Expressions (VALUE)

  // IF expression
  val aCondition = true
  val aConditionValue = if(aCondition) 5 else 3 // IF EXPRESSION
  println(aConditionValue)
  println(if(aCondition) 5 else 3)
  println(1 + 3)

  var i = 0
  while (i < 10) {
    println(i)
    i += 1
  } // NEVER WRITE THIS AGAIN.

  // EVERYTHING in scala is an Expression!

  val aWeirdValue = (aVariable = 3) // Unit === void
  val awhile = while (i < 10) {
    println(i)
    i += 1
  } // a unit
  println(aWeirdValue)

  // side effects: println(), whiles, reassigning

  // Code blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1

    if (z > 2) "hello" else "goodbye"
  }
  // code blocks in Scala are expressions
  // the value of code block is the last line

  // 1. difference between "hello world" vs println("hello world")?
  // first is a string type, second is an expression - a side effect that returns a unit


  // 2. What's the valueS?
  val someValue = {
    2 < 3
  }
  println(someValue)
  // true

  val someOtherValue = {
    if (someValue) 239 else 986
    42
  }
  println(someOtherValue)
  // 42



}
