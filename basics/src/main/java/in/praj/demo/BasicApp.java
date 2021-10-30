package in.praj.demo;

import jnr.ffi.LibraryLoader;

public class BasicApp {
    public static void main(String[] args) {
        var lib = LibraryLoader.create(LibMinimal.class).load("minimal");

        System.out.println("Meaning of life = " + lib.get_integer());

        int first = -559087616, second = 48879;
        System.out.printf("%d + %d = %x\n", first, second, lib.get_sum(first, second));

        System.out.println("Received = " + lib.get_string());

        var builder = new StringBuilder("WXYZ");
        lib.mutate_string(builder);
        System.out.println("Reversed = " + builder);

        var smallStruct = lib.get_small_struct();
        System.out.println(smallStruct);
    }
}
