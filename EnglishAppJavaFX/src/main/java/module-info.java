module com.tlaq.englishappjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;

    opens com.tlaq.englishappjavafx to javafx.fxml;
    exports com.tlaq.englishappjavafx;
}
