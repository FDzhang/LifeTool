package cn.lifetool.app.domain;

public interface ToolLogger {
  void info(String log);

  default void warn(String log) {}

  default void error(String log) {}
}
