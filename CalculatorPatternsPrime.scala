abstract class Tree

case class Sum(l: Tree, r: Tree) extends Tree
case class Var(n: String) extends Tree
case class Const(v: Int) extends Tree

object CalculatorPatternsPrime {
  def eval(tree: Tree, env: String => Int): Int = tree match {
    case Sum(l, r) => eval(l, env) + eval(r, env)
    case Var(n) => env(n)
    case Const(v) => v
  }

  def eval(tree: Tree): Int = eval(tree, { case "ignore" => -1 })

  def derive(tree: Tree, v: String): Tree = tree match {
    case Sum(l, r) => Sum(derive(l, v), derive(r, v))
    case Var(n) if (n == v) => Const(1)
    case _ => Const(0)
  }

  def main(args: Array[String]): Unit = {
    val env: String => Int = { case "x" => 5 case "y" => 7 }
    val tree = Sum(
      Sum(Const(7), Var("y")),
      Sum(Var("x"), Var("x"))
    )
    println(eval(tree, env))
    println(derive(tree, "x"))
    println(eval(derive(tree, "x")))
    println(derive(tree, "y"))
    println(eval(derive(tree, "y")))
  }
}
