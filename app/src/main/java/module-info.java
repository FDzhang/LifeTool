module cn.lifetool.app {
  requires javafx.controls;
  requires javafx.fxml;
  requires org.controlsfx.controls;
  requires java.desktop;
  requires cn.hutool;

  opens cn.lifetool.app to
      javafx.fxml;

  exports cn.lifetool.app;
}
