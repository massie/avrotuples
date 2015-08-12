# Avro Tuples

[![Build Status](https://travis-ci.org/massie/avrotuples.svg)](https://travis-ci.org/massie/avrotuples)

The [Scala library](https://github.com/scala/scala/tree/2.11.x/src/library/scala) provides `Tuple1` to `Tuple22` that allow programmers to hold a fixed number of items together so they can be passed as a single object. While all the elements in an `Array` have the same type, a `TupleN` can have a mix of element types, e.g.

```scala
scala> val mytuple = ((2, "Be"), "Or", "Not", (2, "Be"))
mytuple: ((Int, String), String, String, (Int, String)) = ((2,Be),Or,Not,(2,Be))

scala> mytuple._1
res1: (Int, String) = (2,Be)
```

In this example, `mytuple` is a `Tuple4` and has both `Int` and `String` elements.

The same code using Avro tuples, looks like...

```scala
scala> val mytuple = AvroTuple4(AvroTuple2(2, "Be"), "Or", "Not", AvroTuple2(2, "Be"))
mytuple: com.github.massie.avrotuples.AvroTuple4[com.github.massie.avrotuples.AvroTuple2[Int,String],String,String,com.github.massie.avrotuples.AvroTuple2[Int,String]] = ((2,Be),Or,Not,(2,Be))

scala> mytuple._1
res0: com.github.massie.avrotuples.AvroTuple2[Int,String] = (2,Be)
```

## Avro Tuples are like Scala Tuples

* Avro tuples can serve as a drop in replacement for Scala tuples
* `AvroTuple2` has a `swap` method just like `Tuple2`
* All Avro tuples extend `ProductN`, e.g. `AvroTuple1[T1]` extends `Product1[T1]`
* Avro tuples implement `Externalizable` making them Java serializable
* Avro tuples can be nested

## Avro Tuples have additional functionality over Scala tuples

### Avro tuples implement SpecificRecord

This interface allows Avro to (de)serialize Avro tuples. An Avro serialize/deserialize round-trip looks like...

```scala
val tuple = AvroTuple2("This", AvroTuple4("That", "and", "the", "other"))
val outTuple = AvroTuple2.fromBytes(tuple.toBytes)
assert(tuple == outTuple)
```

### Avro tuples are mutable

You can update the values for an Avro tuple without needing to create a new tuple, e.g.

```scala
val tuple = AvroTuple2("One", 1L)
assert(tuple._1 == "One")
assert(tuple._2 == 1L)
tuple.update("Two", 2L)
assert(tuple._1 == "Two")
assert(tuple._2 == 2L)
```

## Avro tuples have limitations (for now)

### No syntactic sugar

Scala provides syntactic sugar that Avro tuples do not. In Scala, you don't need to write `Tuple2("a", "b")`, you can just use `("a", "b")`. Avro tuple code is more verbose.

### Limited number of types

For now, Avro tuples can be comprised of null values, strings, booleans, floats, doubles, ints, and longs. Support for more types is coming, e.g. `Option`.

### Recursive schemas break Parquet

There is a known issue with Avro/Parquet and recursive schemas. Avro tuples use a recursive schema in order to support nesting.

## Using Avro tuples in your project

For now, you can use [JitPack](https://jitpack.io/#massie/avrotuples/) to add `avrotuples` as a dependency in your Maven project. In the future, `avrotuples` will likely be published to Maven Central.

## License

Avro tuples is released under an Apache 2.0 license.

Pull requests are welcomed.
