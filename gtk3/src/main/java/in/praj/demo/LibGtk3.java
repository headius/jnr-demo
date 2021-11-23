package in.praj.demo;

import jnr.ffi.Pointer;
import jnr.ffi.annotations.Delegate;
import jnr.ffi.types.u_int64_t;

public interface LibGtk3 {
    int gtk_get_major_version();
    int gtk_get_minor_version();
    int gtk_get_micro_version();

    Pointer gtk_application_new(String application_id, int flags);
    @u_int64_t long g_signal_connect_data(
            Pointer instance,
            String detailed_signal,
            GCallback c_handler,
            Pointer data,
            Pointer destroy_data,
            int connect_flags);

    int g_application_run(Pointer app, int argc, Pointer argv);
    void g_object_unref(Pointer object);

    Pointer gtk_application_window_new(Pointer application);
    Pointer gtk_button_new_with_label(String label);
    void gtk_container_add(Pointer container, Pointer widget);
    void gtk_widget_show_all(Pointer widget);

    @FunctionalInterface
    interface GCallback {
        @Delegate void invoke(Pointer gobject, Pointer data);
    }
}
