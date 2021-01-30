abstract class Tree {
  def eval(env: String => Int): Int
  def derive(v: String): Tree
}

class Sum(l: Tree, r: Tree) extends Tree {
  override def eval(env: String => Int): Int = {
    return l.eval(env) + r.eval(env)
  }

  override def derive(v: String): Tree = {
    return new Sum(l.derive(v), r.derive(v))
  }
}

class Var(n: String) extends Tree {
  override def eval(env: String => Int): Int = env(n)
  override def derive(v: String) = v match {
    case `n` => new Const(1)
    case _ => new Const(0)
  }
}

class Const(v: Int) extends Tree {
  override def eval(env: String => Int): Int = v
  override def derive(v: String) = new Const(0)
}

object CalculatorMethods {
  def main(args: Array[String]): Unit = {
    val env: String => Int = { case "x" => 5 case "y" => 7 }
    println(
      new Sum(
        new Sum(new Const(7), new Var("y")),
        new Sum(new Var("x"), new Var("x"))
      ) eval env)
    println(
      new Sum(
        new Sum(new Const(7), new Var("y")),
        new Sum(new Var("x"), new Var("x"))
      ) derive "x" eval { case "ignore" => -1 })
    println(
      new Sum(
        new Sum(new Const(7), new Var("y")),
        new Sum(new Var("x"), new Var("x"))
      ) derive "y" eval { case "ignore" => -1 })
  }
}
