package com.github.massie.avrotuples

case class AvroRecordTestClass(var x: String) extends org.apache.avro.specific.SpecificRecordBase {
  def this() = this("")
  def get(field: Int): AnyRef = {
    field match {
      case pos if pos == 0 => {
        x
      }.asInstanceOf[AnyRef]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
  }
  def put(field: Int, value: Any): Unit = {
    field match {
      case pos if pos == 0 => this.x = {
        value match {
          case (value: org.apache.avro.util.Utf8) => value.toString
          case _ => value
        }
      }.asInstanceOf[String]
      case _ => new org.apache.avro.AvroRuntimeException("Bad index")
    }
    ()
  }
  def getSchema: org.apache.avro.Schema = AvroRecordTestClass.SCHEMA$
}

object AvroRecordTestClass {
  val SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroRecordTestClass\",\"namespace\":\"com.github.massie.avrotuples\",\"doc\":\"Auto-Generated Schema\",\"fields\":[{\"name\":\"x\",\"type\":\"string\",\"doc\":\"Auto-Generated Field\"}]}")
}
