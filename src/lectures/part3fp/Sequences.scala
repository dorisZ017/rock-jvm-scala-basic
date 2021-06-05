package lectures.part3fp

import scala.util.Random

object Sequences extends App{

  // Seq
  val aSequence = Seq(1, 3, 2, 4)
  println(aSequence)
  println((aSequence.reverse))
  println(aSequence(2))
  println(aSequence ++ Seq(7, 5, 6))
  println(aSequence.sorted)

  // Ranges
  val aRange: Seq[Int] = 1 to 10
  aRange.foreach(println)
  (1 to 10).foreach(x => println("Hello"))

  // Lists
  val aList = List(1, 2, 3)
  val prepended = 42 +: aList :+ 89
  println(prepended)

  val apples5 = List.fill(5)("apple")
  println(apples5)  // (apple, apple, apple, apple, apple)
  println(aList.mkString("-")) // Concatenated string: "1-2-3"

  // arrays
  val numbers = Array(1, 2, 3, 4)
  val threeElements = Array.ofDim[Int](3)
  threeElements.foreach(println) // 0, 0, 0
  // if [String]: null, null, null
  // mutation
  numbers(2) = 0 // syntax sugar for numbers.update(2, 0)
  println(numbers.mkString(" ")) // 1, 2, 0, 4

  // arrays and seq
  val numbersSeq: Seq[Int] = numbers // implicit conversion
  println(numbersSeq) // WrappedArray(1, 2, 0, 4)

  // Vectors
  val vector: Vector[Int] = Vector(1, 2, 3)
  println(vector)

  // Vector vs Lists
  val maxRuns = 1000
  val maxCapacity = 1000000

  // for each run, update a random index with a random value, record the running time in times
  // calculate the average running time
  def getWriteTime(collection: Seq[Int]): Double = {
    val r = new Random
    val times = for {
      it <- 1 to maxRuns
    } yield {
      val currentTime = System.nanoTime()
      collection.updated(r.nextInt(maxCapacity), r.nextInt())
      System.nanoTime() - currentTime
    }
    times.sum * 1.0 / maxRuns
  }

  val numbersList = (1 to maxCapacity).toList
  val numbersVector = (1 to maxCapacity).toVector

  // keeps reference to tails
  // updating an element in the middle takes long
  println(getWriteTime(numbersList))

  // depth of the tree is small
  // needs ro replace an entire 32-element chunk
  println(getWriteTime(numbersVector))

  // Vector is way faster, which is why it is the default implementation of Sequence

}
