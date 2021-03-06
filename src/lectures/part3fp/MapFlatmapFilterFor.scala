package lectures.part3fp

object MapFlatmapFilterFor extends App{

  val list = List(1, 2, 3)
  println(list.head)
  println(list.tail)

  // map
  println(list.map(_ + 1))
  println(list.map(_ + " is a number"))

  // filter
  println(list.filter(_ % 2 == 0))

  // flatMap
  val toPair = (x: Int) => List(x, x+1)
  println(list.flatMap(toPair)) // (1, 2, 2, 3, 3, 4)

  // print all combinations between two lists
  val numbers = List(1, 2, 3, 4)
  val chars = List('a', 'b', 'c', 'd')
  // List("a1", "a2", ... "d4")
  val combinantions = numbers.flatMap(n => chars.map(c => "" + c + n))
  println(combinantions)
  // three iterations
  val colors = List("black", "white")
  val combinations2 = numbers.flatMap(n => chars.flatMap(c => colors.map(color => "" + c + n + color)))

  // foreach
  list.foreach(println)

  // for-comprehensions
  val forCombinations = for {
    n <- numbers if n % 2 ==0 //fiter
    c <- chars
    color <- colors
  } yield "" + c + n + "-" + color
  println(forCombinations)

  for {
    n <- numbers
  } println(n)

  // syntax overload
  list.map { x =>
    x * 2
  }

  /*
    1. MyList supports for comprehensions? yes
    2. A small collection of at most ONE element - Maybe[+T]
    - map, flatMap, filter
   */
}
