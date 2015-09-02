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
    
object AvroFlatTuple1 {

  val SCHEMA$ = AvroTupleSchemas.flatSchemas(0)

  val reader = new SpecificDatumReader[AvroFlatTuple1[_]](SCHEMA$)
  val writer = new SpecificDatumWriter[AvroFlatTuple1[_]](SCHEMA$)

  def readFromInputStream(tuple: AvroFlatTuple1[_], in: InputStream) = {
    AvroFlatTuple1.reader.read(tuple, DecoderFactory.get.directBinaryDecoder(in, null))
  }

  def writeToOutputStream(tuple: AvroFlatTuple1[_], out: OutputStream) = {
    AvroFlatTuple1.writer.write(tuple, EncoderFactory.get.directBinaryEncoder(out, null))
  }

  def fromInputStream(in: InputStream) : AvroFlatTuple1[_] = {
    readFromInputStream(null.asInstanceOf[AvroFlatTuple1[_]], in)
  }

  def fromBytes(bytes: Array[Byte]): AvroFlatTuple1[_] = {
    val in = new ByteArrayInputStream(bytes)
    val tuple = fromInputStream(in)
    in.close()
    tuple
  }

}

     
final case class AvroFlatTuple1[T1](
    @transient var _1: T1)
  extends Product1[T1] with SpecificRecord with KryoSerializable with Externalizable {

  def this() = this(null.asInstanceOf[T1])

  def update(n1: T1): AvroFlatTuple1[T1] = {
    _1 = n1
    this
  }

  @throws(classOf[IndexOutOfBoundsException])
  override def get(i: Int): AnyRef = i match {
    case 0 => val values = new util.ArrayList[AnyRef](productArity)
      values.add(0, _1.asInstanceOf[AnyRef])
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
    case _ => throw new IndexOutOfBoundsException(i.toString)
  }

  override def getSchema: Schema = AvroFlatTuple1.SCHEMA$

  override def toString: String = "(" + _1 + ")"

  def toBytes: Array[Byte] = {
    val byteStream = new ByteArrayOutputStream()
    AvroFlatTuple1.writeToOutputStream(this, byteStream)
    byteStream.flush()
    val bytes = byteStream.toByteArray
    byteStream.close()
    bytes
  }

  override def readExternal(in: ObjectInput): Unit = {
    AvroFlatTuple1.readFromInputStream(this, ExternalizableInput(in))
  }

  override def writeExternal(out: ObjectOutput): Unit = {
    AvroFlatTuple1.writeToOutputStream(this, ExternalizableOutput(out))
  }

  override def write(kryo: Kryo, output: Output): Unit = {
    AvroFlatTuple1.writeToOutputStream(this, output.getOutputStream)
  }

  override def read(kryo: Kryo, input: Input): Unit = {
    AvroFlatTuple1.readFromInputStream(this, input.getInputStream)
  }
    

}