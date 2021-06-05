package lectures.part3fp

object TuplesAndMaps extends App{

  // tuples = finite ordered "lists"
  val aTuple = new Tuple2(2, "Hello, scala") // Tuple2[Int, String] = (Int, String)
  // val aTuple = Tuple2(2, "Hello, scala")

  println(aTuple._1) // 2
  println(aTuple.copy(_2 = "goodbye Java"))
  println(aTuple.swap) // ("hello, scala", 2)

  // Maps - keys -> values
  val aMap: Map[String, Int] = Map()

  val phonebook = Map(("Jim", 555), "Daniel" -> 789)
  // a -> b is sugar for (a, b)
  println(phonebook)

  // map ops
  println(phonebook.contains("Jim")) // true
  println(phonebook("Jim"))
  println(phonebook("Mary")) //NoSuchElementException
  // val phonebook = Map(("Jim", 555), "Daniel" -> 789).withDefaultValue(-1)

  // add a pairing
  val newPairing = "Mary" -> 678
  val newPhonebook = phonebook + newPairing

  // functions on maps
  // map, flatMap, filter
  println(phonebook.map(pair => pair._1.toLowerCase -> pair._2))

  // filterKeys
  println(phonebook.filterKeys(x => x.startsWith("J")))
  // mapValues
  println(phonebook.mapValues(number => number * 10))

  // conversions to other collections
  println(phonebook.toList)
  println(List(("Daniel", 555)).toMap)
  val names = List("Bob", "James", "Angela", "Mary", "Daniel", "Jim")
  println(names.groupBy(name => name.charAt(0))) // Map(J -> List(James, Jim), A -> List(Angela), ...)

  /*
    1. What would happen if I had two original entries "Jim" -> 555 and "JIM" -> 900?
       care for with mapping keys
    2. Overly simplyfied social network based on maps
       Person: String
       - add a person to the network
       - friend (mutual)
       - unfriend
       - remove a person from network

       - number of friends of a person
       - person with most friends
       - how many people have NO friend
       - if there is a social connection
   */
  def add(network: Map[String, Set[String]], name: String): Map[String, Set[String]] =
    network + (name -> Set())

  def friend(network: Map[String, Set[String]], name1: String, name2: String):  Map[String, Set[String]] = {
    val friendsA = network(name1)
    val friendsB = network(name2)
    network + (name1 -> (friendsA + name2)) + (name2 -> (friendsB + name1))
  }

  def unfriend(network: Map[String, Set[String]], name1: String, name2: String):  Map[String, Set[String]] = {
    val friendsA = network(name1)
    val friendsB = network(name2)
    network + (name1 -> (friendsA - name2)) + (name2 -> (friendsB - name1))
  }

  def remove(network: Map[String, Set[String]], person: String): Map[String, Set[String]] ={
    def removeAux(friends: Set[String], networkAcc: Map[String, Set[String]]): Map[String, Set[String]] =
      if (friends.isEmpty) networkAcc
      else removeAux(friends.tail, unfriend(networkAcc, person, friends.head))

    val unfriended = removeAux(network(person), network)
    unfriended - person
  }

  val empty: Map[String, Set[String]] = Map()
  val network = add(add(empty, "Bob"), "Mary")
  println(network)
  println(friend(network, "Bob", "Mary"))
  println(unfriend(friend(network, "Bob", "Mary"), "Bob", "Mary"))
  println(remove(friend(network, "Bob", "Mary"), "Bob"))

  val people = add(add(add(empty, "Bob"), "Mary"), "Jim")
  val jimBob = friend(people, "Bob", "Jim")
  val testNet = friend(jimBob, "Bob", "Mary")

  println(testNet)

  def nFriends(network: Map[String, Set[String]], person: String): Int =
    if (!network.contains(person)) 0
    else network(person).size

  println(nFriends(testNet, "Bob"))

  def mostFriends(network: Map[String, Set[String]]): String =
    network.maxBy(pair => pair._2.size)._1

  println(mostFriends(network))

  def nPeopleWithNoFriend(network: Map[String, Set[String]]): Int =
    network.filterKeys(k => network(k).isEmpty).size
    // or network.count(pair => pair._2.isEmpty) or network.count(_._2.isEmpty)
  println(nPeopleWithNoFriend(testNet))

  def socialConnection(network: Map[String, Set[String]], a: String, b: String): Boolean = {
    def bfs(target: String, consideredPeople: Set[String], discoveredPeople: Set[String]): Boolean = {
      if (discoveredPeople.isEmpty) false
      else {
        val person = discoveredPeople.head
        if (person == target) true
        else if (consideredPeople.contains(person)) bfs(target, consideredPeople, discoveredPeople.tail)
        else bfs(target, consideredPeople + person, discoveredPeople.tail ++ network(person))
      }
    }

    bfs(b, Set(), network(a) + a)
  }

  println(socialConnection(testNet, "Mary", "Jim"))
  println(socialConnection(network, "Mary", "Bob"))


  /*class SocialNetwork {
    val network: Map[String, List[String]] = Map().withDefaultValue(List())
    def addPerson(name: String): Map[String, List[String]] = {
      val newPerson = name -> List()
      network + newPerson
    }
    def friend(name1: String, name2: String): Map[String, List[String]] = {
      val name1_new = name1 -> (network(name1) :+ name2)
      val name2_new = name2 -> (network(name2) :+ name1)
      network + name1_new + name2_new
    }
    def unfriend(name1: String, name2: String): Map[String, List[String]] = {
      val name1_new = name1 -> (network(name1).filter(_ != name2))
      val name2_new = name2 -> (network(name1).filter(_ != name1))
      network + name1_new + name2_new
    }
    def NumFriend(name: String): Int = network(name).length
    def MostFriends(): String = {
      val res = ""
      val maxNum = network(res).length
      ???
    }

   */




}
