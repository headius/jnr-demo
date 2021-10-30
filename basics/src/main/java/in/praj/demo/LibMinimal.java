package in.praj.demo;

import jnr.ffi.Pointer;
import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.Union;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.types.u_int32_t;
import jnr.ffi.util.EnumMapper;

public interface LibMinimal {
    // For signed 32-bit integers, primitive int should work.
    int get_integer();

    // Use annotations from jnr.ffi.types to specify signedness or width.
    long get_sum(@u_int32_t int first, @u_int32_t int second);

    // char arrays from C can be converted to java.lang.String directly.
    String get_string();

    // Use StringBuilder, StringBuffer or char[] to represent mutable strings.
    void mutate_string(StringBuilder input);

    // Define a subclass of jnr.ffi.Struct to represent C structs.
    class SmallStruct extends Struct {
        // Inner classes from Struct should be used for struct fields.
        public final Unsigned32 index = new Unsigned32();
        public final Double value = new Double();

        // Make sure the constructor is public.
        public SmallStruct(Runtime runtime) {
            super(runtime);
        }
    }

    // Any Struct subclass in Java corresponds to a pointer-to-struct in C.
    SmallStruct get_small_struct();

    // The string sizes must be specified for proper calculation
    // of struct size, which is later used to allocate memory.
    class LargeStruct extends Struct {
        public final AsciiString name = new AsciiString(32);
        public final StructRef<SmallStruct> small = new StructRef<>(SmallStruct.class);
        // In a freshly allocated LargeStruct, the 'small' field will be a null pointer.

        public LargeStruct(Runtime runtime) {
            super(runtime);
        }
    }

    void fill_large_struct(LargeStruct large);

    // Define a subclass of jnr.ffi.Union to represent C unions.
    class LetterUnion extends Union {
        public final Unsigned8 l = new Unsigned8();
        public final Unsigned32 v = new Unsigned32();

        public LetterUnion(Runtime runtime) {
            super(runtime);
        }
    }

    // Unions behave similar to Structs in most cases.
    void fill_letter_union(LetterUnion letter);

    // Since C enums are pretty much normal integers, you can directly
    // use int to represent them. But if you want to make them more
    // Java-like, use the approach shown below.
    enum WeatherEnum implements EnumMapper.IntegerEnum {
        SUNNY(1), CLOUDY(2), RAINY(3);

        private final int value;

        WeatherEnum(int value) {
            this.value = value;
        }

        @Override
        public int intValue() {
            return value;
        }
    }

    // The returned integer will get mapped into corresponding Java enum value.
    WeatherEnum get_weather();

    // Use the general-purpose jnr.ffi.Pointer when you don't know what type it points to.
    Pointer get_opaque_pointer();

    double some_opaque_extraction(Pointer opaque);

    // Any functional interface can be used to represent a function pointer provided
    // it has the appropriate method signature and @Delegate annotation.
    @FunctionalInterface
    interface UnaryFunction {
        @Delegate int apply(int input);
    }

    int apply_unary_function(int arg, UnaryFunction f);
}
