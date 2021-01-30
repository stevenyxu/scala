abstract class Tree

case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree

object CalculatorPatterns {
  def eval(tree: Tree, env: String => Int): Int = tree match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  def main(args: Array[String]): Unit = {
    val env: String => Int = { case "x" => 5 case "y" => 7 }
    println(
      eval(Sum(
        Sum(Const(7), Var("y")),
        Sum(Var("x"), Var("x"))
      ), env)
    )
  }
}
