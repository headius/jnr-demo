package in.praj.demo;

import jnr.ffi.LibraryLoader;
import jnr.ffi.LibraryOption;
import jnr.ffi.Runtime;

import java.util.Map;

public class Gtk3App {
    private static final String[] LIBS = new String[] {
            // From: pkg-config --libs gtk+-3.0
            "gtk-3", "gdk-3", "pangocairo-1.0",
            "pango-1.0", "harfbuzz", "atk-1.0",
            "cairo-gobject", "cairo", "gdk_pixbuf-2.0",
            "gio-2.0", "gobject-2.0", "glib-2.0"
    };

    public static void main(String[] args) {
        var lib = LibraryLoader.loadLibrary(LibGtk3.class, Map.of(LibraryOption.LoadNow, true), LIBS);
        var refs = Runtime.getRuntime(lib).newObjectReferenceManager();

        System.out.printf("GTK version: %d.%d.%d\n",
                lib.gtk_get_major_version(), lib.gtk_get_minor_version(), lib.gtk_get_micro_version());

        var application = lib.gtk_application_new("in.praj.demo.Gtk3App", 0);
        LibGtk3.GCallback onActivate = (gobject, data) -> {
            var window = lib.gtk_application_window_new(gobject);
            var button = lib.gtk_button_new_with_label("Click me");
            lib.gtk_container_add(window, button);
            lib.gtk_widget_show_all(window);
        };
        var callbackPtr = refs.add(onActivate);

        lib.g_signal_connect_data(application, "activate", onActivate, null, null, 0);
        lib.g_application_run(
                lib.g_type_check_instance_cast(application, lib.g_application_get_type()), 0, null);
        refs.remove(callbackPtr);
        lib.g_object_unref(application);
    }
}
