module com.tlaq.englishappjavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.tlaq.englishappjavafx to javafx.fxml;
    exports com.tlaq.englishappjavafx;
    opens com.tlaq.pojo to javafx.base;

}
