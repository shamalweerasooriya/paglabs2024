// automatically generated by the FlatBuffers compiler, do not modify

package MyNamespace;

import java.nio.*;
import java.lang.*;
import java.util.*;
import com.google.flatbuffers.*;

@SuppressWarnings("unused")
public final class Address extends Table {
  public static void ValidateVersion() { Constants.FLATBUFFERS_2_0_0(); }
  public static Address getRootAsAddress(ByteBuffer _bb) { return getRootAsAddress(_bb, new Address()); }
  public static Address getRootAsAddress(ByteBuffer _bb, Address obj) { _bb.order(ByteOrder.LITTLE_ENDIAN); return (obj.__assign(_bb.getInt(_bb.position()) + _bb.position(), _bb)); }
  public void __init(int _i, ByteBuffer _bb) { __reset(_i, _bb); }
  public Address __assign(int _i, ByteBuffer _bb) { __init(_i, _bb); return this; }

  public String street() { int o = __offset(4); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer streetAsByteBuffer() { return __vector_as_bytebuffer(4, 1); }
  public ByteBuffer streetInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 4, 1); }
  public String city() { int o = __offset(6); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer cityAsByteBuffer() { return __vector_as_bytebuffer(6, 1); }
  public ByteBuffer cityInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 6, 1); }
  public String state() { int o = __offset(8); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer stateAsByteBuffer() { return __vector_as_bytebuffer(8, 1); }
  public ByteBuffer stateInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 8, 1); }
  public String postalCode() { int o = __offset(10); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer postalCodeAsByteBuffer() { return __vector_as_bytebuffer(10, 1); }
  public ByteBuffer postalCodeInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 10, 1); }
  public String country() { int o = __offset(12); return o != 0 ? __string(o + bb_pos) : null; }
  public ByteBuffer countryAsByteBuffer() { return __vector_as_bytebuffer(12, 1); }
  public ByteBuffer countryInByteBuffer(ByteBuffer _bb) { return __vector_in_bytebuffer(_bb, 12, 1); }

  public static int createAddress(FlatBufferBuilder builder,
      int streetOffset,
      int cityOffset,
      int stateOffset,
      int postalCodeOffset,
      int countryOffset) {
    builder.startTable(5);
    Address.addCountry(builder, countryOffset);
    Address.addPostalCode(builder, postalCodeOffset);
    Address.addState(builder, stateOffset);
    Address.addCity(builder, cityOffset);
    Address.addStreet(builder, streetOffset);
    return Address.endAddress(builder);
  }

  public static void startAddress(FlatBufferBuilder builder) { builder.startTable(5); }
  public static void addStreet(FlatBufferBuilder builder, int streetOffset) { builder.addOffset(0, streetOffset, 0); }
  public static void addCity(FlatBufferBuilder builder, int cityOffset) { builder.addOffset(1, cityOffset, 0); }
  public static void addState(FlatBufferBuilder builder, int stateOffset) { builder.addOffset(2, stateOffset, 0); }
  public static void addPostalCode(FlatBufferBuilder builder, int postalCodeOffset) { builder.addOffset(3, postalCodeOffset, 0); }
  public static void addCountry(FlatBufferBuilder builder, int countryOffset) { builder.addOffset(4, countryOffset, 0); }
  public static int endAddress(FlatBufferBuilder builder) {
    int o = builder.endTable();
    return o;
  }

  public static final class Vector extends BaseVector {
    public Vector __assign(int _vector, int _element_size, ByteBuffer _bb) { __reset(_vector, _element_size, _bb); return this; }

    public Address get(int j) { return get(new Address(), j); }
    public Address get(Address obj, int j) {  return obj.__assign(__indirect(__element(j), bb), bb); }
  }
}

