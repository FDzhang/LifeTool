package cn.lifetool.app.infrastructure;

import org.junit.jupiter.api.Test;

class FilesUtilTest {

  @Test
  void test_listDirNames() {
    System.out.println("xxxx");
    System.out.println(TFilesUtil.listDirPaths("D:\\dev\\Java21OpenJDK"));
  }
}
