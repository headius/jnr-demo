package in.praj.demo;

import jnr.ffi.types.u_int32_t;

public interface LibMinimal {
    // For signed 32-bit integers, primitive 'int' should work
    int get_integer();

    // Use annotations from jnr.ffi.types to specify signedness or size
    @u_int32_t int get_sum(@u_int32_t int first, @u_int32_t int second);

    // char arrays from C can be converted to java.lang.String directly
    String get_string();
}
