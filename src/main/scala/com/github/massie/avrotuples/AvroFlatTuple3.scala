/*
 * Copyright 2015 Matt Massie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

    
// GENERATED SOURCE: DO NOT EDIT.

package com.github.massie.avrotuples

import java.io._
import java.util

import com.esotericsoftware.kryo.{Kryo, KryoSerializable}
import com.esotericsoftware.kryo.io.{Input, Output}
import org.apache.avro.Schema
import org.apache.avro.generic.GenericData
import org.apache.avro.io.{DecoderFactory, EncoderFactory}
import org.apache.avro.specific.{SpecificDatumReader, SpecificDatumWriter, SpecificRecord}
import org.apache.avro.util.Utf8
    
object AvroFlatTuple3 {

  val SCHEMA$ = AvroTupleSchemas.flatSchemas(2)

  val reader = new SpecificDatumReader[AvroFlatTuple3[_, _, _]](SCHEMA$)
  val writer = new SpecificDatumWriter[AvroFlatTuple3[_, _, _]](SCHEMA$)

  def readFromInputStream(tuple: AvroFlatTuple3[_, _, _], in: InputStream) = {
    AvroFlatTuple3.reader.read(tuple, DecoderFactory.get.directBinaryDecoder(in, null))
  }

  def writeToOutputStream(tuple: AvroFlatTuple3[_, _, _], out: OutputStream) = {
    AvroFlatTuple3.writer.write(tuple, EncoderFactory.get.directBinaryEncoder(out, null))
  }

  def fromInputStream(in: InputStream) : AvroFlatTuple3[_, _, _] = {
    readFromInputStream(null.asInstanceOf[AvroFlatTuple3[_, _, _]], in)
  }

  def fromBytes(bytes: Array[Byte]): AvroFlatTuple3[_, _, _] = {
    val in = new ByteArrayInputStream(bytes)
    val tuple = fromInputStream(in)
    in.close()
    tuple
  }

}

     
final case class AvroFlatTuple3[T1, T2, T3](
    @transient var _1: T1,
    @transient var _2: T2,
    @transient var _3: T3)
  extends Product3[T1, T2, T3] with SpecificRecord with KryoSerializable with Externalizable {

  def this() = this(null.asInstanceOf[T1],
                    null.asInstanceOf[T2],
                    null.asInstanceOf[T3])

  def update(n1: T1, n2: T2, n3: T3): AvroFlatTuple3[T1, T2, T3] = {
    _1 = n1
    _2 = n2
    _3 = n3
    this
  }

  @throws(classOf[IndexOutOfBoundsException])
  override def get(i: Int): AnyRef = i match {
    case 0 => val values = new util.ArrayList[AnyRef](productArity)
      values.add(0, _1.asInstanceOf[AnyRef])
      values.add(1, _2.asInstanceOf[AnyRef])
      values.add(2, _3.asInstanceOf[AnyRef])
      values.asInstanceOf[AnyRef]
    case _ => throw new IndexOutOfBoundsException(i.toString)
  }

  private def utf8string(obj: Any) = obj match {
    case u: Utf8 => u.toString
    case _ => obj
  }

  @throws(classOf[IndexOutOfBoundsException])
  override def put(i: Int, v: scala.Any): Unit = i match {
    case 0 =>
      val array = v match {
        case avroArray: GenericData.Array[_]=> avroArray
        case javaArray: util.ArrayList[_]=> javaArray
      }
      assert(array.size == productArity,
        s"Tried to put ${array.size} values into AvroTuple with productArity of $productArity")
      _1 = utf8string(array.get(0)).asInstanceOf[T1]
      _2 = utf8string(array.get(1)).asInstanceOf[T2]
      _3 = utf8string(array.get(2)).asInstanceOf[T3]
    case _ => throw new IndexOutOfBoundsException(i.toString)
  }

  override def getSchema: Schema = AvroFlatTuple3.SCHEMA$

  override def toString: String = "(" + _1 + "," + _2 + "," + _3 + ")"

  def toBytes: Array[Byte] = {
    val byteStream = new ByteArrayOutputStream()
    AvroFlatTuple3.writeToOutputStream(this, byteStream)
    byteStream.flush()
    val bytes = byteStream.toByteArray
    byteStream.close()
    bytes
  }

  override def readExternal(in: ObjectInput): Unit = {
    AvroFlatTuple3.readFromInputStream(this, ExternalizableInput(in))
  }

  override def writeExternal(out: ObjectOutput): Unit = {
    AvroFlatTuple3.writeToOutputStream(this, ExternalizableOutput(out))
  }

  override def write(kryo: Kryo, output: Output): Unit = {
    AvroFlatTuple3.writeToOutputStream(this, output.getOutputStream)
  }

  override def read(kryo: Kryo, input: Input): Unit = {
    AvroFlatTuple3.readFromInputStream(this, input.getInputStream)
  }
    

}