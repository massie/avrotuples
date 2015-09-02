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
package com.github.massie.avrotuples

import java.io.{ByteArrayInputStream, ByteArrayOutputStream, ObjectInputStream, ObjectOutputStream}

import com.esotericsoftware.kryo.Kryo
import com.esotericsoftware.kryo.io.{Input, Output}
import org.scalatest.FunSuite

class AvroTupleSuite extends FunSuite {

  test("AvroTuples have an empty ctor for serialization") {
    val tuple = new AvroTuple2()
    assert(tuple != null)
  }

  test("AvroTuples are mutable") {
    val tuple = AvroTuple2("One", 1L)
    assert(tuple._1 == "One")
    assert(tuple._2 == 1L)
    tuple.update("Two", 2L)
    assert(tuple._1 == "Two")
    assert(tuple._2 == 2L)
  }

  test("AvroTuple2 has a swap method") {
    val tuple2 = AvroTuple2("One", 1)
    val swapped = tuple2.swap
    assert(swapped._1 == 1)
    assert(swapped._2 == "One")
  }

  test("AvroTuples can have null values") {
    val tuple = AvroTuple3(null, 0xCAFE, null)
    assert(AvroTuple3.fromBytes(tuple.toBytes) == tuple)
  }

  test("Avro Tuples can be (de)serialized to Avro") {
    val inTuple = AvroTuple11(10, 9, 8, 7, 6, 5, 4, 3, 2, 1, "blastoff!")
    val outTuple = AvroTuple11.fromBytes(inTuple.toBytes)
    assert(inTuple == outTuple)
  }

  test("Avro Tuples are Java serializable") {
    val out = new ByteArrayOutputStream()
    val objOut = new ObjectOutputStream(out)
    val inTuple = AvroTuple4(AvroTuple2(2, "Be"), "Or", "Not", AvroTuple2(2, "Be"))
    objOut.writeObject(inTuple)
    objOut.close()
    out.close()
    val in = new ByteArrayInputStream(out.toByteArray)
    val objIn = new ObjectInputStream(in)
    val outTuple = objIn.readObject()
    objIn.close()
    in.close()
    assert(inTuple == outTuple)
  }

  test("Avro Tuples are Kryo serializable") {
    val kryo = new Kryo()
    kryo.setReferences(false)
    val bytesOut = new ByteArrayOutputStream()
    val out = new Output(bytesOut)
    val tuple = AvroTuple4("Avro", "Kryo", 2, "together")
    kryo.writeObject(out, tuple)
    out.close()
    val bytes = bytesOut.toByteArray
    bytesOut.close()
    val bytesIn = new ByteArrayInputStream(bytes)
    val in = new Input(bytesIn)
    val tupleOut = kryo.readObject(in, classOf[AvroTuple4[String, String, Int, String]])
    in.close()
    bytesIn.close()
    assert(tupleOut == tuple)
  }

  test("Avro tuples can be nested") {
    val tuple = AvroTuple2("This", AvroTuple4("That", "and", "the", "other"))
    val outTuple = AvroTuple2.fromBytes(tuple.toBytes)
    assert(tuple == outTuple)
  }

  test("Avro flat tuples don't support nesting") {
    val tuple = AvroFlatTuple4(1, 2, "three", "four")
    val outTuple = AvroFlatTuple4.fromBytes(tuple.toBytes)
    assert(tuple == outTuple)
  }

}
