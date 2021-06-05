package lectures.part2oop

import playground.{PrinceCharming, Cinderalla => Princess}

import java.sql.Date
import java.util.Date

object PackagingAndImports extends App {

  // package members are accessible by their simple name
  val writer = new Writer("Daniel", "RockTheJVM", 2018)

  // import the package
  val princess = new Princess
  // or playground.Cinderalla: fully qualified name without importing

  // packages are in hieracrchy
  // matching folder structure

  // package object
  sayHello
  println(SPEED_OF_LIGHT)

  // imports

  val prince = new PrinceCharming

  val d = new Date

  // default imports
  // java.lang - String, Object, Exception
  // scala - Int, Nothing, Function
  // scala.Predef - println, ???


}
