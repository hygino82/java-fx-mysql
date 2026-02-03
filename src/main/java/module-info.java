module br.dev.hygino {
    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    //requires com.mysql.cj;
    requires static lombok;

    exports br.dev.hygino.app;
    opens br.dev.hygino.controller to javafx.fxml;
}
