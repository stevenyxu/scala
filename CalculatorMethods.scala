abstract class Tree {
  def eval(env: String => Int): Int
}

class Sum(l: Tree, r: Tree) extends Tree {
  override def eval(env: String => Int): Int = {
    return l.eval(env) + r.eval(env)
  }
}

class Var(n: String) extends Tree {
  override def eval(env: String => Int): Int = env(n)
}

class Const(v: Int) extends Tree {
  override def eval(env: String => Int): Int = v
}

object CalculatorMethods {
  def main(args: Array[String]): Unit = {
    val env: String => Int = { case "x" => 5 case "y" => 7 }
    println(
      new Sum(
        new Sum(new Const(7), new Var("y")),
        new Sum(new Var("x"), new Var("x"))
      ) eval env)
  }
}
