package cn.lifetool.app;

import cn.lifetool.app.domain.StatusButton;
import cn.lifetool.app.domain.ToolLogger;
import cn.lifetool.app.infrastructure.TFilesUtil;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class LifeToolController {

  @FXML public TextArea logWindow;
  public TextField prefix;
  public TextField category;
  public TextField labelDir;
  public FlowPane labelFlow;

  public void initialize() {
    TFilesUtil.setToolLogger(
        new ToolLogger() {
          @Override
          public void info(String log) {
            logWindow.appendText(log + "\n");
          }
        });
  }

  public void updLabel(ActionEvent actionEvent) {
    List<String> labels = TFilesUtil.listDirNames(labelDir.getText());
    for (String label : labels) {
      labelFlow.getChildren().add(new StatusButton(label));
    }
  }

  public void categorizeDir(ActionEvent actionEvent) {
    List<String> labels =
        labelFlow.getChildren().stream()
            .filter(node -> node instanceof StatusButton && ((StatusButton) node).isSelected())
            .map(node -> ((StatusButton) node).getText())
            .collect(Collectors.toList());

    labels.replaceAll(s -> category.getText() + File.separator + s);

    TFilesUtil.cateFiles(category.getText(), labels);
  }

  public void addPrefix(ActionEvent actionEvent) {}

  public void prefixSelect(ActionEvent actionEvent) {
    prefix.setText(selectDir());
  }

  public String selectDir() {
    DirectoryChooser dc = new DirectoryChooser();
    dc.setInitialDirectory(new File(System.getProperty("user.dir")));
    dc.setTitle("选择目录");

    File file = dc.showDialog(Context.STAGE_MAP.get(this));
    return file == null ? "" : file.toString();
  }

  public String fileDir() {
    FileChooser fc = new FileChooser();
    fc.setInitialDirectory(new File(System.getProperty("user.dir")));
    fc.setTitle("选择文件");

    File file = fc.showOpenDialog(Context.STAGE_MAP.get(this));
    return file == null ? "" : file.toString();
  }

  public void cateSelect(ActionEvent actionEvent) {
    category.setText(selectDir());
  }

  public void labelDirSelect(ActionEvent actionEvent) {
    labelDir.setText(selectDir());
  }
}
