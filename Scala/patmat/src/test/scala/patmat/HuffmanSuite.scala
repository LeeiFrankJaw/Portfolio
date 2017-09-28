package patmat

import org.scalatest.FunSuite

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import patmat.Huffman._

@RunWith(classOf[JUnitRunner])
class HuffmanSuite extends FunSuite {
  trait TestTrees {
    val t1 = Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5)
    val t2 = Fork(Fork(Leaf('a', 2), Leaf('b', 3), List('a', 'b'), 5), Leaf('d', 4), List('a', 'b', 'd'), 9)
  }

  test("weight of a larger tree") {
    new TestTrees {
      assert(weight(t1) === 5)
    }
  }

  test("chars of a larger tree") {
    new TestTrees {
      assert(chars(t2) === List('a', 'b', 'd'))
    }
  }

  test("string2chars(\"hello, world\")") {
    assert(string2Chars("hello, world") === List('h', 'e', 'l', 'l', 'o', ',', ' ', 'w', 'o', 'r', 'l', 'd'))
  }

  test("times for some List[Char]") {
    assert(times(string2Chars("Hello, world")) ===
      List(('H', 1), ('e', 1), ('l', 3), ('o', 2), (',', 1), (' ', 1), ('w', 1), ('r', 1), ('d', 1)))
  }

  test("makeOrderedLeafList for some frequency table") {
    assert(makeOrderedLeafList(List(('t', 2), ('e', 1), ('x', 3))) === List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 3)))
  }

  test("makeOrderedLeafList of HelloWorld") {
    assert(makeOrderedLeafList(times(string2Chars("Hello, world"))) ===
      List(Leaf('d', 1), Leaf('r', 1), Leaf('w', 1), Leaf(' ', 1), Leaf(',', 1), Leaf('e', 1), Leaf('H', 1), Leaf('o', 2), Leaf('l', 3)))
  }

  test("false singleton") {
    assert(!singleton(makeOrderedLeafList(times(string2Chars("Hello, world")))))
  }

  test("true singleton") {
    assert(singleton(makeOrderedLeafList(List(('t', 2)))))
  }

  test("combine of some leaf list") {
    val leaflist = List(Leaf('e', 1), Leaf('t', 2), Leaf('x', 4))
    assert(combine(leaflist) === List(Fork(Leaf('e', 1), Leaf('t', 2), List('e', 't'), 3), Leaf('x', 4)))
  }

  test("combine of a singleton or nil") {
    assert(combine(List(Leaf('x', 3))) === List(Leaf('x', 3)))
    assert(combine(List()) === List())
  }

  test("createCodeTree for HelloWorld") {
    val chars = string2Chars("Hello, world")
    assert(createCodeTree(chars) ===
      makeCodeTree(makeCodeTree(Leaf('o', 2), makeCodeTree(Leaf('H', 1), makeCodeTree(Leaf(',', 1), Leaf('e', 1)))), makeCodeTree(Leaf('l', 3),
          makeCodeTree(makeCodeTree(Leaf('w', 1), Leaf(' ', 1)), makeCodeTree(Leaf('d', 1), Leaf('r', 1)))))
      )
  }

  test("decode and encode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, encode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and encode HelloWorld") {
    val chars = string2Chars("Hello, world")
    val tree = createCodeTree(chars)
    assert(decode(tree, encode(tree)(chars)) === chars)
  }

  test("decode and quickEncode a very short text should be identity") {
    new TestTrees {
      assert(decode(t1, quickEncode(t1)("ab".toList)) === "ab".toList)
    }
  }

  test("decode and quickEncode HelloWorld") {
    val chars = string2Chars("Hello, world")
    val tree = createCodeTree(chars)
    assert(decode(tree, quickEncode(tree)(chars)) === chars)
  }

  //  println(decodedSecret.toString)
}
