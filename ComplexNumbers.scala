class Complex(real: Double, imaginary: Double) {
  def re = real
  def im = imaginary
  override def toString(): String = {
    return "" + re + (if (im >= 0) "+" else "") + im + "i"
  }
}

object ComplexNumbers {
  def main(args: Array[String]): Unit = {
    var c = new Complex(5.3, -4.1)
    println(c.toString)
  }
}