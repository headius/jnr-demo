package in.praj.demo;

import jnr.ffi.Runtime;
import jnr.ffi.Struct;
import jnr.ffi.types.u_int32_t;

public interface LibMinimal {
    // For signed 32-bit integers, primitive int should work
    int get_integer();

    // Use annotations from jnr.ffi.types to specify signedness or width
    long get_sum(@u_int32_t int first, @u_int32_t int second);

    // char arrays from C can be converted to java.lang.String directly
    String get_string();

    // Use StringBuilder, StringBuffer or char[] to represent mutable strings
    void mutate_string(StringBuilder input);

    // Define a subclass of jnr.ffi.Struct to represent C structs
    class SmallStruct extends Struct {
        // Inner classes from Struct should be used for struct fields
        public final Unsigned32 index = new Unsigned32();
        public final Double value = new Double();

        // Make sure the constructor is public
        public SmallStruct(Runtime runtime) {
            super(runtime);
        }
    }

    // Any Struct subclass in Java corresponds to a pointer-to-struct in C
    SmallStruct get_small_struct();
}
