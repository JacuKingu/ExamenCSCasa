module org.agendafx {
    requires javafx.controls;
    requires javafx.fxml;

    opens org.agendafx to javafx.fxml;
    exports org.agendafx;
}
