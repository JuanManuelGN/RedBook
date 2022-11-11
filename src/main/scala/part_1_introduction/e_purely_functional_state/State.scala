package part_1_introduction.e_purely_functional_state

import part_1_introduction.e_purely_functional_state.State.{modify, sequence, unit, get}

case class State[S, +A](run: S => (A, S)) {
  def map[B](f: A => B): State[S, B] =
    flatMap(a => unit(f(a)))

  def map2[B, C](sb: State[S, B])(f: (A, B) => C): State[S, C] =
    flatMap(a => sb.map(b => f(a, b)))

  def flatMap[B](f: A => State[S, B]): State[S, B] =
    State(s => {
      val (a, s1) = run(s) // get (A,S) nuevo
      f(a).run(s1) // f(a) = State(S,B); State(S,B).run(s1) = (B, S);
    })
}

object State {
  type Rand[A] = State[RNG, A]

  def unit[S, A](a: A): State[S, A] =
    State(s => (a, s))

  def sequence[S, A](sas: List[State[S, A]]): State[S, List[A]] =
    sas.foldRight(unit[S, List[A]](Nil))((s, acc) => s.map2(acc)(_ :: _))

  def modify[S](f: S => S): State[S, Unit] = for {
    s <- get // Gets the current state and assigns it to `s`.
    _ <- set(f(s)) // Sets the new state to `f` applied to `s`.
  } yield ()

  def get[S]: State[S, S] = State(s => (s, s))

  def set[S](s: S): State[S, Unit] = State(_ => ((), s))

}

sealed trait Input

case object Coin extends Input

case object Turn extends Input

sealed trait KnobPosition

case object Locked extends KnobPosition

case object UnLocked extends KnobPosition

case class Machine(locked: KnobPosition, candies: Int, coins: Int)

object Candy {

  def update: Input => Machine => Machine =
    (i: Input) => (s: Machine) => (i, s) match {
      case (_, Machine(_, 0, _)) => s // Si no quedan caramelos el estado no cambia
      case (Coin, Machine(Locked, candies, coins)) => Machine(UnLocked, candies, coins + 1)
      case (Turn, Machine(UnLocked, candies, coins)) => Machine(Locked, candies - 1, coins)
      case (Turn, Machine(Locked, _, _)) => s // Girar la perilla en una máquina bloqueada no hace nada
      case (Coin, Machine(UnLocked, _, _)) => s // Insertar moneda en una máquina desbloqueada no hace nada
    }

  /**
   * The method simulateMachine should operate the machine based on the list of inputs
   * and return the number of coins and candies left in the machine at the end. For exam-
   * ple, if the input Machine has 10 coins and 5 candies, and a total of 4 candies are suc-
   * cessfully bought, the output should be (14, 1).
   *
   * @param inputs
   * @return
   */
  def simulateMachine(inputs: List[Input]): State[Machine, (Int, Int)] = for {
    _ <- sequence(inputs map (modify[Machine] _ compose update))
    s <- get
  } yield (s.coins, s.candies)
}

object CandyRunner extends App {
  val inputs = List(Coin, Coin, Turn, Coin, Turn)
  val machine = Machine(Locked, 10, 4)
  val finaState = Candy.simulateMachine(inputs).run(machine)
  println(finaState._1)
  println(finaState._2)
}