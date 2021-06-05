package lectures.part1basics

object CBNvsCBV extends App{

  def calledByValue(x: Long): Unit = {
    println("by value: " + x)
    println("by value: " + x)
    // x is passed as a value
  }

  def calledByName(x: => Long): Unit = {
    println("by name: " + x)
    println("by name: " + x)
    // x is passed as an expression
  }

  calledByValue(System.nanoTime()) // x evaluated once when running the function
  calledByName(System.nanoTime()) // x evaluated twice

  def infinite(): Int = 1 + infinite()
  def printFirst(x: Int, y: => Int) = println(x)

  printFirst(infinite(), 34) // not working
  printFirst(34, infinite()) // works, becasue y is never evaluated

}
