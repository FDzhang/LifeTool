package cn.lifetool.app.domain;

import javafx.scene.control.Button;

public class StatusButton extends Button {

  // 默认被选中
  private boolean selected = true;

  public StatusButton(String s) {
    super(s);
    setStyle(selected);
    setOnMouseClicked(
        e -> {
          selected = !selected;
          setStyle(selected);
        });
  }

  public boolean isSelected() {
    return selected;
  }

  public void setSelected(boolean selected) {
    this.selected = selected;
    setStyle(selected);
  }

  private void setStyle(boolean selected) {
    if (selected) {
      this.setStyle(
          "-fx-background-color: pink;" + "-fx-background-radius: 20;" + "-fx-text-fill: black;");
    } else {
      this.setStyle(
          "-fx-background-color: grey;" + "-fx-background-radius: 20;" + "-fx-text-fill: black;");
    }
  }
}
