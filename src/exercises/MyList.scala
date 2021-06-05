package exercises

/* Original
abstract class MyList {

  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  val head: Int
  def tail: MyList
  def isEmpty: Boolean
  def add(element: Int): MyList
  def printElements: String
  override def toString: String = "[" + printElements + "]"

}

object Empty extends MyList {
  val head: Int = throw new NoSuchElementException
  def tail: MyList = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add(element: Int): MyList = new Cons(element, Empty)
  def printElements: String = ""
}

class Cons(h: Int, t: MyList) extends MyList {
  val head: Int = h
  def tail: MyList = t
  def isEmpty: Boolean = false
  def add(element: Int): MyList = new Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }
}

object ListTest extends App {
  val list = new Cons(1, new Cons(2, new Cons(3, Empty)))
  println(list.tail.head)
  println(list.add(4).head)
  println(list.isEmpty)

  println(list.toString)
}

 */

// Generic
/* EXERCISE
 1. create a generic trait MyPredicate[-T] with a little method test(T) => Boolean
 2. generate triait MyTrasnformer[-A, B]
 3. MyList:
    - map(transformer) => MyList
    - filter(predicate) => MyList
    - flatMap(transformer from A to MyList[B] => MyList[B]

    class EvenPredicate extends MyPredicate[Int]
    class StringtoIntTransformer extends MyTransformer[String, Int]

    [1,2,3].map(n * 2) = [2, 4, 6]
    [1, 2, 3, 4].filter(n % 2) = [2, 4]
    [1, 2, 3].flatMap(n => [n, n+1]) => [1,2,2,3,3,4]

  IMPORTANT HINT: must be -T and -A (contravriant)
  */

/*
trait MyPredicate[-T] {
  def test(ele: T): Boolean
}

trait MyTransformer[-A, B] {
  def transform(ele: A): B
}

 */

/*
class EvenPredicate extends MyPredicate[Int] {
  def test(para: Int): Boolean = para % 2 == 0
}

class StringToIntTransformer extends MyTransformer[String, Int] {
  def transform(para: String): Int = para.toInt
}
 */
abstract class MyList[+A] {
  /*
    head = first element of the list
    tail = remainder of the list
    isEmpty = is this list empty
    add(int) => new list with this element added
    toString => a string representation of the list
   */

  val head: A
  def tail: MyList[A]
  def isEmpty: Boolean
  def add[B >: A](element: B): MyList[B]
  def printElements: String
  override def toString: String = "[" + printElements + "]"

  /*
  // oop
  def map[B](transformer: MyTransformer[A, B]): MyList[B]
  def filter(predicate: MyPredicate[A]): MyList[A]
  def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B]
   */
  // fp
  // higher-order functions
  def map[B](transformer: A => B): MyList[B]
  def filter(predicate: A => Boolean): MyList[A]
  def flatMap[B](transformer: A => MyList[B]): MyList[B]

  def ++[B >: A](list: MyList[B]): MyList[B] //concact

  def foreach(f: A => Unit): Unit
  def sort(compare: (A, A) => Int): MyList[A]
  def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C]
  def fold[B](start: B)(f: (B, A) => B): B

}

object Empty extends MyList[Nothing] {
  val head: Nothing = throw new NoSuchElementException
  def tail: MyList[Nothing] = throw new NoSuchElementException
  def isEmpty: Boolean = true
  def add[B >: Nothing](element: B): MyList[B] = new Cons(element, Empty)
  def printElements: String = ""

  /*
    def map[B >: Nothing](transformer: MyTransformer[Nothing, B]): MyList[B] = Empty
    def filter(predicate: MyPredicate[Nothing]): MyList[Nothing] = Empty
    def flatMap[B](transformer: MyTransformer[Nothing, MyList[B]]): MyList[Nothing] = Empty
   */
  def map[B >: Nothing](transformer: Nothing => B): MyList[B] = Empty
  def filter(predicate: Nothing => Nothing): MyList[Nothing] = Empty
  def flatMap[B](transformer: Nothing => MyList[B]): MyList[Nothing] = Empty
  def ++[B >: Nothing](list: MyList[Nothing]): MyList[Nothing] = list

  // hofs
  def foreach(f: Nothing => Unit): Unit = ()
  def sort(compare: (Nothing, Nothing) => Int) = Empty
  def zipWith[B, C](list: MyList[B], f: (Nothing, B) => C): MyList[C] = {
    if (!list.isEmpty) throw new RuntimeException("Lists do not have the same length")
    else Empty
  }
  def fold[B](start: B)(f: (B, Nothing) => B) = start
}

case class Cons[+A](h: A, t: MyList[A]) extends MyList[A] {
  val head: A = h
  def tail: MyList[A] = t
  def isEmpty: Boolean = false
  def add[B >: A](element: B): MyList[B] = new Cons(element, this)
  def printElements: String = {
    if (t.isEmpty) "" + h
    else h + " " + t.printElements
  }

  /*
   def map[B >: A](transformer: MyTransformer[A, B]): MyList[B] = {
     new Cons(transformer.transform(h), t.map(transformer))
     /*
     if (t.isEmpty) new Cons(transformer.transform(h), Empty)
     else t.map(transformer).add(transformer.transform(h))
      */
   }
   */
  def map[B >: A](transformer: A => B): MyList[B] = new Cons(transformer(h), t.map(transformer))

  /*
  def filter(predicate: MyPredicate[A]): MyList[A] = {
    if (predicate.test(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
    /*
    if (t.isEmpty) {
      if (predicate.test(h)) new Cons(h, Empty)
      else Empty
    }
    else {
      if (predicate.test(h)) t.filter(predicate).add(h)
      else t.filter(predicate)
    }
     */
  }
  */
  def filter(predicate: A => Boolean): MyList[A] = {
    if (predicate(h)) new Cons(h, t.filter(predicate))
    else t.filter(predicate)
  }

    /*
    [1, 2] ++ [3, 4, 5]
    = new (Cons(1, [2] ++ [3, 4, 5]))
    = new (Cons(1, new Cons(2, Empty ++ [3, 4, 5])))
    = new (Cons(1, new Cons(2, [3, 4, 5])))
    MAGIC!
     */
    def ++[B >: A](list: MyList[B]): MyList[B] = new Cons(h, tail ++ list)

    /*
    [1, 2, 3].flatMap([n*2, n*3])
    = [1*2, 1*3] ++ [2, 3].flatMap()
    = [1*2, 1*3] ++ [2*2, 2*3] ++ [3].flatMap()
    = [1*2, 1*3] ++ [2*2, 2*3] ++ [3*2, 3*3]
     */
    /*
    def flatMap[B](transformer: MyTransformer[A, MyList[B]]): MyList[B] = {
      transformer.transform(h) ++ t.flatMap(transformer)
  }
     */
    def flatMap[B](transformer: A => MyList[B]): MyList[B] =
    transformer(h) ++ t.flatMap(transformer)

    // hofs
    def foreach(f: A => Unit): Unit = { f(h); t.foreach(f) }

    def sort(compare: (A, A) => Int): MyList[A] = {
      def insert(x: A, sortedList: MyList[A]): MyList[A] = {
        if (sortedList.isEmpty) new Cons(x, Empty)
        else if (compare(x, sortedList.head) <= 0) new Cons(x, sortedList)
        else new Cons(sortedList.head, insert(x, sortedList.tail))
      }
      val sortedTail = t.sort(compare)
      insert(h, sortedTail)
    }
    def zipWith[B, C](list: MyList[B], f: (A, B) => C): MyList[C] = {
      if (list.isEmpty) throw new RuntimeException("Lists do not have the same length")
      new Cons(f(h, list.head), t.zipWith(list.tail, f))
    }
    /*
    [1, 2, 3].fold(0)(+) =
   = [2, 3].fold(1)
   = [3].fold(3)
   = [].fold(6)
   = 6
     */
    def fold[B](start: B)(f: (B, A) => B): B
    = t.fold(f(start, h))(f)
}
  object ListTest extends App {
    val listOfIntegers: MyList[Int] = new Cons(1, new Cons(2, new Cons(3, Empty)))
    val listOfStrings: MyList[String] = new Cons("Hello", new Cons("Scala", Empty))

    val anotherLitsOfIntgers: MyList[Int] = new Cons(4, new Cons(5, Empty))

    println(listOfIntegers.toString)

    // Implement anonymous classes on the go
    /* OOP
    println(listOfIntegers.map(new MyTransformer[Int, Int] {
      override def transform(ele: Int): Int = ele * 2
    }).toString)

    println(listOfIntegers.filter(new MyPredicate[Int] {
      override def test(ele: Int): Boolean = ele % 2 == 0
    }).toString)

    println(listOfIntegers.flatMap(new MyTransformer[Int, MyList[Int]] {
      override def transform(ele: Int): MyList[Int] = new Cons(ele, new Cons(ele + 1, Empty))
    }))
     */
    /* FP
    println(listOfIntegers.map(new Function1[Int, Int] {
      override def apply(ele: Int): Int = ele * 2
    }).toString)

    println(listOfIntegers.filter(new Function1[Int, Boolean] {
      override def apply(ele: Int): Boolean = ele % 2 == 0
    }).toString)

    println(listOfIntegers.flatMap(new Function1[Int, MyList[Int]] {
      override def apply(ele: Int): MyList[Int] = new Cons(ele, new Cons(ele + 1, Empty))
    }))
     */
    // LAMBDA
    println(listOfIntegers.map(x => x * 2).toString) // or _ * 2

    println(listOfIntegers.filter(x => x % 2 == 0).toString) // or _ % 2 == 0

    val flatIncrementer: Int => MyList[Int] = ele => new Cons(ele, new Cons(ele + 1, Empty))
    println(listOfIntegers.flatMap(ele => new Cons(ele, new Cons(ele + 1, Empty))))

    // hofs
    listOfIntegers.foreach(println)
    println(listOfIntegers.sort((x, y) => y - x))
    println(anotherLitsOfIntgers.zipWith[String, String](listOfStrings, _ + "-" + _))
    listOfIntegers.fold(0)(_ + _)

    // for comprehensions
    val combinations = for {
      n <- listOfIntegers
      string <- listOfStrings
    } yield n + "-" + string
    println(combinations)
  }