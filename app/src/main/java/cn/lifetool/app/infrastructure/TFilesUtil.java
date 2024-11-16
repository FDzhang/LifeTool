package cn.lifetool.app.infrastructure;

import cn.hutool.core.io.FileUtil;
import cn.lifetool.app.domain.ToolLogger;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class TFilesUtil {
  private static ToolLogger toolLogger;

  public static void setToolLogger(ToolLogger toolLogger) {
    TFilesUtil.toolLogger = toolLogger;
  }

  private static void info(String msg) {
    if (toolLogger != null) {
      toolLogger.info(msg);
    }
  }

  // 分类srcDir下所有的文件，到各destDirs目录下
  public static void cateFiles(String srcDir, List<String> destDirs) {
    // 所有文件
    List<String> files = new ArrayList<>(listDeepFilePaths(srcDir));
    for (String destDir : destDirs) {
      cateFiles(files, destDir);
    }
  }

  // 分类文件到目标目录，再清理掉已经分类的目录
  private static void cateFiles(List<String> files, String destDir) {
    String key = FileUtil.getName(destDir);
    for (String file : files) {
      String name = FileUtil.getName(file);
      if (name.contains(key)) {
        // 同名则覆盖
        //        FileUtil.copyFile(
        //            file, destDir + File.separator + name, StandardCopyOption.REPLACE_EXISTING);
        info("[copy] file " + file + " to " + destDir + File.separator + name);
      }
    }
    files.removeIf(f -> FileUtil.getName(f).contains(key));
  }

  // 获取目录下，所有目录的路径（1层）
  public static List<String> listDirPaths(String dirPath) {
    info("listDirPaths " + dirPath);
    return FileUtil.loopFiles(new File(dirPath), 1, FileUtil::isDirectory).stream()
        .map(File::getPath)
        .toList();
  }

  // 获取目录下，所有目录的名称（1层）
  public static List<String> listDirNames(String dirPath) {
    info("listDirNames " + dirPath);
    return FileUtil.loopFiles(new File(dirPath), 1, FileUtil::isDirectory).stream()
        .map(FileUtil::getName)
        .toList();
  }

  // 获取目录下，所有文件的路径（1层）
  public static List<String> listFilePaths(String dirPath) {
    info("listFilePaths " + dirPath);
    return FileUtil.loopFiles(new File(dirPath), 1, FileUtil::isFile).stream()
        .map(File::getPath)
        .toList();
  }

  // 获取目录下，所有文件的路径（n层）
  public static List<String> listDeepFilePaths(String dirPath) {
    info("listDeepFilePaths " + dirPath);
    return FileUtil.loopFiles(new File(dirPath), FileUtil::isFile).stream()
        .map(File::getPath)
        .toList();
  }
}
