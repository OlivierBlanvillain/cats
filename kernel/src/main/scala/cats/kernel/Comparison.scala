package cats.kernel

/** ADT encoding the possible results of a comparison */
sealed abstract class Comparison(val toInt: Int, val toDouble: Double) extends Product with Serializable
