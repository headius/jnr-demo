package in.praj.demo;

import jnr.ffi.LibraryLoader;

public class BasicApp {
    public static void main(String[] args) {
        var lib = LibraryLoader.create(LibMinimal.class).load("minimal");
        System.out.println(lib.get_integer());
    }
}
