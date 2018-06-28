package redBook.part1introduction.bFunctionalDataStructures

/**
  * Implement the function tail for removing the first element of a List. Note that the
  * function takes constant time. What are different choices you could make in your
  * implementation if the List is Nil? We’ll return to this question in the next chapter.
  */
object Exercise32_Tail extends App {
  println(
    List.tail(List.listInteger)
  )
}
