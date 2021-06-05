package lectures.part4pm

import scala.util.Random

object PatternMatching extends App {

  // switch on steroids
  val random = new Random
  val x = random.nextInt(10)

  val description = x match {
    case 1 => "the ONE"
    case 2 => "double or nothing"
    case 3 => "third time is the charm"
    // case _ => "something else" // _ = WILDCARD
  }

  println(x) // 2
  println(description) // double or nothing

  // 1. Decompose values
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)

  val greeting = bob match {
    case Person(n, a) if a < 21 => s"Hi, my name is $n and I cannot drink in the US"
    case Person(n, a)  => s"Hi, my name is $n and I amd $a years old"
    case _ => "I don't know who I am"
  }
  println(greeting) // Hi, my name is Bob and I cannot drink in the use
  /*
   1. cases are matched in orders
   2. what if no cases match? MatchError
   3. what's the type of PM expression: unified type of all the types of all the cases
   4. PM warks really well with case classes
   */

  // PM on sealed hierachies
  sealed class Animal
  case class Dog(breed: String) extends Animal
  case class Parrot(greeting: String) extends Animal

  val animal: Animal = Dog("Beagle")
  animal match {
    case Dog(someBreed) => println(s"Matched a dig of the $someBreed breed")
  }

  // match everything
  val isEven = x match {
    case n if n % 2 == 0 => true
    case _ => false
  } // WAY overkill

  /*
   EXERCISE
   simple function uses PM
     takes an Expr => human readable form

     Sum(Number(2), Number(3)) => 2 + 3
     Sum(Sum(Number(2), Number(3)), Number(4)) => 2 + 3 + 4
     Prod(Sum(Number(2), Number(1)), Number(3)) = (2 + 1) * 3
     Sum(Prod(Number(2), Number(1)), Number(3)) = 2 * 1 + 3
   */
  trait Expr
  case class Number(n: Int) extends Expr
  case class Sum(el: Expr, e2: Expr) extends Expr
  case class Prod(e1: Expr, e2: Expr) extends Expr

  def show(e: Expr): String = e match {
    case Number(n) => s"$n"
    case Sum(e1, e2) => show(e1) + " + " + show(e2)
    case Prod(e1, e2) => {
      def maybeShowParentheses(exp: Expr) = exp match {
        case Prod(_, _) => show(exp)
        case Number(_) => show(exp)
        case _ => "(" + show(exp) + ")"
      }
      maybeShowParentheses(e1) + " * " + maybeShowParentheses(e2)
    }
  }

  println(Sum(Number(2), Number(3)))
  println(Sum(Sum(Number(2), Number(3)), Number(4)))
  println(Prod(Sum(Number(2), Number(1)), Number(3)))


}
