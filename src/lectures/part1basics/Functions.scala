package lectures.part1basics

object Functions extends App {

  def aFunction(a: String, b: Int): String = {
    a + " " + b
  }

  println(aFunction("hello", 3))

  def aParameterlessFunction(): Int = 42
  println(aParameterlessFunction())
  println(aParameterlessFunction)

  def aRepeatedFunction(aString: String, n: Int): String = {
    if (n == 1) aString
    else aString + aRepeatedFunction(aString, n-1)
  }

  println(aRepeatedFunction("hello", 3))
  // WHEN YOU NEED LOOPS, USE RECURSION

  def aFunctionWithSideEffects(aString: String): Unit = println(aString)

  def aBigFunction(n: Int): Int = {
    def aSmallerFunction(a: Int, b: Int): Int = a + b

    aSmallerFunction(n, n-1)
  }

  /*
   1. A greeting function (name, age) => "Hi, my name is $name and I am $age years old"
   2. Factorial function 1 * 2 * 3 * ... * n
   3. A Fibonacci function
     f(1) = 1
     f(2) = 1
     f(n) = f(n - 1) + f(n - 2)
   4. Tests if a number os a prime.
  */

  // 1
  def greeting(name: String, age: Int): String =
    "Hi, my name is " + name + " and I am " + age + " years old"

  //2
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  //3
  def fibonacci(n: Int): Int = {
    if (n <= 2) 1
    else fibonacci(n - 1) + fibonacci(n - 2)
  }

  //4
  def isPrime(n: Int): Boolean = {

    def divideable(m: Int): Boolean = {
      if (m > n / 2) true
      else if (n % m == 0) false
      else divideable(m + 1)
    }

    if (n == 2) true
    else if (n % 2 == 0) false
    else divideable(3)
  }

  println(fibonacci(6))
  println(isPrime(41))
}

