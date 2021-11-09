package in.praj.demo;

import jnr.ffi.LibraryLoader;
import jnr.ffi.LibraryOption;

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

        System.out.printf("GTK version: %d.%d.%d\n",
                lib.gtk_get_major_version(), lib.gtk_get_minor_version(), lib.gtk_get_micro_version());

        var application = lib.gtk_application_new("in.praj.demo.Gtk3App", 0);
        LibGtk3.GCallback onActivate = (app, data) -> {
            var window = lib.gtk_application_window_new(app);
            var button = lib.gtk_button_new_wih_label("Click me");
            lib.gtk_container_add(window, button);
            lib.gtk_widget_show_all(window);
        };

        lib.g_signal_connect_data(application, "activate", onActivate, null, null, 0);
        lib.g_application_run(application, 0, null);
        lib.g_object_unref(application);
    }
}