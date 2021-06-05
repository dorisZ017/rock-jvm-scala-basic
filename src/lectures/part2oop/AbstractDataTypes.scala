package lectures.part2oop

object AbstractDataTypes extends App {

  // abstract
  abstract class Animal {
    val creatureType: String
    def eat: Unit
  }

  class Dog extends Animal {
    override val creatureType: String = "Canine"
    def eat: Unit = println("crunch crunch")
  }

  // traits
  trait Carnivore {
    def eat(animal: Animal): Unit
    val preferredMeal: String = "Fresh meat"
  }

  trait ColdBlooded

  class Crocodile extends Animal with Carnivore with ColdBlooded {
    override val creatureType: String = "croc"
    def eat: Unit = println("nomnom")
    def eat(animal: Animal): Unit = println(s"I'm a croc and I'm eating ${animal.creatureType}")
  }

  val dog = new Dog
  val croc = new Crocodile
  croc.eat(dog)

  /* traits vs abstract classes
    1. traits do not have constractor parameters
    2. multiple traits may be inherited by the same class
    3. traits are behavior, abstract class is a type of thing
   */

  /* Scala's Type Hierarchy
  - scala.Any
    - scala.AnyRef
      - String, List Set
        - scala.Null
          - scala.Nothing
    - scala.AnyVal
      - Int, Unit, Boolean, Float
        - scala.Nothing
   Nothing is a sub type of any instance
   */


}
