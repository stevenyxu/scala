object Timer {
  def delay(cb: () => Unit, delay: Int) = {
    val thread = new Thread {
      override def run = {
        while(true) {
          cb()
          Thread sleep delay
        }
      }
    }
    thread.start
  }

  def main(args: Array[String]): Unit = {
    delay(() => println("boom"), 1000);
    delay(() => println("chhhhhhh"), 2000);
  }
}