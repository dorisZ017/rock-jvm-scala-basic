package lectures.part2oop

object Inheritance extends App{

  // single class inheritance
  class Animal {
    val creatureType = "wild"
    def eat = println("nomnom")
  }

  class Cat extends Animal {
    def crunch = {
      eat // protected functions of super class are accessible from inside of the sub class
      println("crunch crunch")
    }
  }

  val cat = new Cat
  //cat.eat //protected functions of super class are NOT accessible from outside of the sub class
  cat.crunch

  // constructors
  class Person(name: String, age: Int) {
    def this(name: String) = this(name, 0)
  }
  class Adult(name: String, age: Int, idCard: String) extends Person(name, age)
  // jvm calls constructor of Person before calling constructor of Adult
 // class Adult(name: String, age: Int, idCard: String) extends Person(name)

  // overriding
  class Dog(override val creatureType: String) extends Animal {
    override def eat = {
      super.eat
      println("crunch, crunch")}
  }

  val dog = new Dog("domestic")
  dog.eat
  println(dog.creatureType)

  // type substitution (broad: polymorphism)
  val unknownAnimal: Animal = new Dog("k9")
  unknownAnimal.eat // using the method of dog even though it's decalred as animal
  // A method call will go to the most overriden verison whenever possible

  // overRIDING VS overLOADING

  // super
  dog.eat

  // preventing overrides:
  // 1 - use final on member
  // 2 - final class: cannot be extended
  // 3 - sealed class: cannot be extended in another file

}
