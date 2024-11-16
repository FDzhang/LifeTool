module cn.lifetool.app {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens cn.lifetool.app to javafx.fxml;
    exports cn.lifetool.app;
}