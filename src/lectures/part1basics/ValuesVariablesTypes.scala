package lectures.part1basics

object ValuesVariablesTypes extends App {
  val x: Int  = 42
  println(x)
  // vals are immutable
  val y = 42
  // compiler can infer types

  val astring: String = "hello"; val anotherString = "goodbye" // bad style

  val aBoolean: Boolean = false
  val achar: Char = 'a'
  val anInt: Int = x
  val aShort: Short = 2222
  val along: Long = 12345678929l
  val afloat: Float = 2.0f
  val aDouble: Double = 3.14

  // variables
  var aVariable: Int = 4
  aVariable = 5 // side effects
}
