package lectures.part2oop

object Exceptions extends App{

  val x: String = null
  // println(x.length) // crashes

  // throwing and catching exceptions

  val aWeirdValue = throw new NullPointerException // assigns Nothing
  val aWeirdVal: String = throw new NullPointerException // valid since Nothing extends String

  // throwable classes extendds the Throwable class
  // Exception and Error are the major Throwable subtypes

  // 2. how to catch execptions
  def getInt(withExceptions: Boolean): Int =
    if (withExceptions) throw new RuntimeException("No int for you!")
    else 42

  val potentialFail = try {
    // code that might throw
    getInt(true)
  } catch {
    // case e: RuntimeException => println("caught a Runtime exception")
    // case e: NullPointerException => println("caught a Runtime exception")
      // Runtime Exception will not be caught
    case e => 43 // potentialFail is Int in this case
  } finally {
    // code that will get executed NO MATTER WHAT
    // optional
    // does not influence the return type of this expression
    // use finally only for side effects
    println("finally")
  }

  // 3. how to define your own exceptions
  class MyException extends Exception
  val exception = new MyException

  throw exception

  /*
  1. crash your program with an OutOfMemoryError:  val array = Array.ofDim(Int.MaxValue)
  2. Crash with StackOverflowError: def infinite = Int + infinite
  3. PocketCalculator
     - add(x, y)
     - subtract(x, y)
     - multiply(x, y)
     - divide(x, y)

     Throw
     - OverflowException if add(x,y) exceeds Int.MAX_VALUE
     - UnderflowExceptopm if subtract(x,y) exceeds Int.MIN_VALUE
     - MathCalculationException for division by 0
   */


  class OverflowException extends Exception
  class UnderflowException extends Exception
  class MathCalculationException extends Exception

  object PocketCalculator{
    def add(x: Int, y: Int) = {
      val result = x + y
      // Int.MaxValue + 10 returns negative
      if (x > 0 && y > 0 && result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result > 0) throw new UnderflowException
      else result
    }
    def subtract(x: Int, y: Int) = {
      val result = x - y
      if (x > 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def multiply(x: Int, y: Int) = {
      val result = x * y
      if (x > 0 && y > 0 & result < 0) throw new OverflowException
      else if (x < 0 && y < 0 && result < 0) throw new OverflowException
      else if (x < 0 && y > 0 && result > 0) throw new UnderflowException
      else result
    }
    def divide(x: Int, y: Int): Double = {
      if (y == 0) throw new MathCalculationException
      else x / y
    }

  }


}
