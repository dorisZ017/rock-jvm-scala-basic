package lectures.part2oop

object Generics extends App {

  class MyList[+A] {
    // use the type A
    // def add(element: A): MyList[A] = ??? //doesn't work

    def add[B >: A](element: B): MyList[B] = ???
    /*
     A = Cat
     B = Animal
     */
  }

  class MyMap[Key, Value]

  val listOfIntegers = new MyList[Int]
  val listOfStrings = new MyList[String]

  // generic methods
  object MyList {
    def empty[A]: MyList[A] = ???
  }
  val emptyListOfIntegers = MyList.empty[Int]

  // variance problem
  class Animal
  class Cat extends Animal
  class Dog extends Animal

  // Does List[Cat] also extend List[Animal] ?

  // 1. yes, List[Cat] extends List[Animal] = COVARIANCE
  class CovariantList[+A]
  val animal: Animal = new Cat
  val animalList: CovariantList[Animal] = new CovariantList[Cat]
  // animalList.add(new Dog) ??? Hard problem: we return a list of Animals

  // 2. no = INVARICANCE
  class InvariantList[A]
  val invariantAnimalList: InvariantList[Animal] = new InvariantList[Animal]

  // 3. Hell no = CONTRAVARIANCE
  class ContravariantList[-A]
  val contravariantList: ContravariantList[Cat] = new ContravariantList[Animal]

  class Trainer[-A] // this makes more sense than ^
  val trainer: Trainer[Cat] = new Trainer[Animal] // a trainer of animal is a trainer of cat

  // bounded types
  class Cage[A <: Animal](animal: A) // cages only accepts types that are subtypes of Animal
  // Cage[A :> Animal] only accepts super type of anmial
  val cage = new Cage(new Dog)
  val aCage = new Cage(new Animal)


  class car
  val newCage = new Cage(new Car) //error

  // expand MyList to be generic in MyList.scala


}
