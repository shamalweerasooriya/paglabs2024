// automatically generated by the FlatBuffers compiler, do not modify

package MyNamespace;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class People extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static People getRootAsPeople(ByteBuffer _bb) { return getRootAsPeople(_bb, new People()); }
  public static People getRootAsPeople(ByteBuffer _bb, People obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public People __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public MyNamespace.Person people(int j) { return people(new MyNamespace.Person(), j); }
  public MyNamespace.Person people(MyNamespace.Person obj, int j) { int o = __offset(4); return o != 0 ? obj.__assign(__indirect(__vector(o) + j * 4), bb) : null; }
  public int peopleLength() { int o = __offset(4); return o != 0 ? __vector_len(o) : 0; }
  public MyNamespace.Person.Vector peopleVector() { return peopleVector(new MyNamespace.Person.Vector()); }
  public MyNamespace.Person.Vector peopleVector(MyNamespace.Person.Vector obj) { int o = __offset(4); return o != 0 ? obj.__assign(__vector(o), 4, bb) : null; }

  public static int createPeople(FlatBufferBuilder builder,
      int peopleOffset) {
    builder.startTable(1);
    People.addPeople(builder, peopleOffset);
    return People.endPeople(builder);
  }

  public static void startPeople(FlatBufferBuilder builder) { builder.startTable(1); }
  public static void addPeople(FlatBufferBuilder builder, int peopleOffset) { builder.addOffset(0, peopleOffset, 0); }
  public static int createPeopleVector(FlatBufferBuilder builder, int[] data) { builder.startVector(4, data.length, 4); for (int i = data.length - 1; i >= 0; i--) builder.addOffset(data[i]); return builder.endVector(); }
  public static void startPeopleVector(FlatBufferBuilder builder, int numElems) { builder.startVector(4, numElems, 4); }
  public static int endPeople(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }
  public static void finishPeopleBuffer(FlatBufferBuilder builder, int offset) { builder.finish(offset); }
  public static void finishSizePrefixedPeopleBuffer(FlatBufferBuilder builder, int offset) { builder.finishSizePrefixed(offset); }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public People get(int j) { return get(new People(), j); }
    public People get(People obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

