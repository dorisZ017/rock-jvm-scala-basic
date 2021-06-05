package lectures.part1basics

import scala.annotation.tailrec

object Recursion extends App {

  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else {
      println("computing factorial of " + n + " - I first need factorial of " + (n - 1))
      val result = n * factorial(n - 1)
      n * factorial(n - 1)
      println("computed factorial of " + n)
      result
    }
  }

  println(factorial(10))
  println(factorial(5000)) //StackOverflowError after n = 535

  def anotherFactorial(n: Int): BigInt = {
    @tailrec
    def factorialHelper(x: Int, accumulator: BigInt): BigInt =
      if (x <= 1) accumulator
      else factorialHelper(x - 1, x * accumulator) // TAIL RECURSION: use recursive call as the LAST expression

    factorialHelper(n, 1)
  }

  /*
   anotherFactorial(10) = factorialHelper(10, 1)
   = factorialHelper(9, 10 * 1)
   = factorialHelper(8, 9 * 10 * 1)
   = factorialHelper(7, 8 * 9 * 10 * 1)
   = ...
   = factorialHelper(1, 2 * 3 * 4 * ... 10 * 1)
   = 2 * 3 * 4 * ... 10 * 1 // accumulator
   */

  anotherFactorial(5000) //actually works

  // in this case scala doesn't need to know the intermediate values to evaluate the function

  // WHEN YOU NEED LOOPS, USE _TAIL_ RECURSION

  /*
  1. Concatenate a string in n times
  2. isPrime
  3. Fibonacci function tail recursive
   */

  //1
  def concatString(s: String, n: Int): String = {
    def concatHelper(count: Int, result: String): String = {
      if (count <= 0) result
      else concatHelper(count - 1, result + s)
    }

    concatHelper(n, "")
  }

  //2
  def isPrime(n: Int): Boolean = {
    def primeHelper(t: Int, isStillPrime: Boolean): Boolean = {
      if (!isStillPrime) false
      else if (t <= 1) true
      else primeHelper(t - 1, n % t != 0 && isStillPrime)
    }

    primeHelper(n / 2, true)

    /*
    n = 31
    primeHepler(15, true)  t=15
    = primeHelper(15 - 1, 31 % 15 != 0 && true) = primeHelper(14, true) t = 14
    = primHelper(14 - 1, 31 % 14 != 0 && true) = primHelper(13, true) t = 13
    t = 12...1
    -> true
    */
  }


  //3
  def fibonacci(n: Int): Int = {
    // last: f(n); nextToLast: f(n-1)
    // # of accumulators = # of paramerters needed to calculate
    def fiboHelper(i: Int, last: Int, nextToLast: Int): Int = {
      if (i >= n) last
      else fiboHelper(i + 1, last + nextToLast, last)
    }

    if (n <= 2) 1
    else fiboHelper(2, 1, 1)
    /*
    n = 5
    fiboHepler(2, 1, 1) = fiboHelper(2+1, 1+1, 1) i = 3
    = fiboHelper(3, 2, 1) = fiboHelper(3+1, 2+1, 2) i = 4
    = fiboHelper(4, 3, 2) = fiboHelper(5, 3+2, 3)
    -> 5
     */
  }
}
