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

import scala.collection.JavaConversions._

import org.apache.avro.Schema
import org.apache.avro.Schema.Field

object AvroTupleSchemas {

  val primitiveTypes = List(
    Schema.create(Schema.Type.NULL),
    Schema.create(Schema.Type.STRING),
    Schema.create(Schema.Type.BOOLEAN),
    Schema.create(Schema.Type.FLOAT),
    Schema.create(Schema.Type.DOUBLE),
    Schema.create(Schema.Type.INT),
    Schema.create(Schema.Type.LONG))
  val recursiveSchemas: Array[Schema] = generateSchemas(true)
  val flatSchemas: Array[Schema] = generateSchemas(false)

  def generateSchemas(recursive: Boolean): Array[Schema] = {
    val rootSchemas: Array[Schema] = Array.tabulate[Schema](22) { i =>
      Schema.createRecord(s"com.github.massie.avrotuples.Avro${if (recursive) "" else "Flat"}Tuple${i + 1}", "", "", false)
    }
    val avroTypes = primitiveTypes ++ {
      if (recursive) rootSchemas else Nil
    }
    for (schema <- rootSchemas) {
      schema.setFields(List(new Field("values", Schema.createArray(Schema.createUnion(avroTypes)), "", null)))
    }
    rootSchemas
  }

  def main(args: Array[String]): Unit = {
    println(recursiveSchemas(10).toString(true))
  }

}
