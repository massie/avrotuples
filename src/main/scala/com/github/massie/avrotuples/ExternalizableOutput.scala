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

import java.io.{ObjectOutput, OutputStream}

case class ExternalizableOutput(out: ObjectOutput) extends OutputStream {

  override def flush() = out.flush()

  override def close() = out.close()

  override def write(i: Int) = out.write(i)

  override def write(b: Array[Byte]) = out.write(b)

  override def write(b: Array[Byte], offset: Int, len: Int) = out.write(b, offset, len)

}
