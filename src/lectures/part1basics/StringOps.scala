package lectures.part1basics

object StringOps extends App {

  val str: String = "Hello, I am learning Scala"

  // Same in Java:

  println(str.charAt(2)) // l
  println(str.substring(7, 11)) // I am
  println(str.split(" ").toList)
  println(str.startsWith("Hello"))
  println(str.replace(" ", "-"))
  println(str.toLowerCase())
  println(str.length)

  val aNumberString = "45"
  val aNumber = aNumberString.toInt
  println('a' +: aNumberString :+ 'z') //a45z
  println(str.reverse)
  println(str.take(2)) // He

  // Scala-specific: String interpolators
  // S-introplators
  val name = "David"
  val age = 12
  val greeting = s"Hello, my name is $name and I and $age years old"
  val anotherGreeting = s"Hello, my name is $name and I and $age years old and I will be turning ${age+1} years old"
  println(anotherGreeting)

  // F-introplators
  val speed = 1.2f
  val myth = f"$name%s can eat $speed%2.2f burgers per minute"
  // %s: string; %2.2f: float number format (2 characters total, minimum, 2 decimals precision)
  println(myth)  // David can eat 1.20 burgers per minute
  // can check for type correctness:
  val x = 1.1f
  // val strx = f"$x%3d" // %3d: requires int -> error

  // raw-interpolator
  println(raw"this is a \n new line") // print \n literally
  val escaped = "this is a \n new line"
  print(raw"$escaped") // \n will be escaped




}
